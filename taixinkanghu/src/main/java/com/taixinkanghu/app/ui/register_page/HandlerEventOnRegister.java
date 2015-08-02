/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.register_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.register_page;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.event.ReqRegisterEvent;
import com.taixinkanghu.third.party.sms.DSmsAutho;
import com.taixinkanghu.third.party.sms.SmsAutho;
import com.taixinkanghu.third.party.sms.SmsConfig;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.SMSSDK;
import de.greenrobot.event.EventBus;

public class HandlerEventOnRegister implements View.OnClickListener
{
	private RegisterActivity  m_registerActivity             = null;
	private String m_CountryZipCode = null;
	private String m_phoneNum = null;
	private TextView m_phoneNumTV = null;

	public HandlerEventOnRegister()
	{
	}


	public void init(RegisterActivity registerActivity)
	{
		m_registerActivity = registerActivity;
		m_phoneNumTV = (TextView)m_registerActivity.findViewById(R.id.phone_num_tv);
	}

	public void clearup()
	{
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.verification_id_btn:
			{
				handleVerificationEvent();
			}
			break;
			case R.id.register_id_btn:
			{
				handleRegisterEvent();
			}
			break;

		}
	}


	//发送手机号码，进行验证
	private void handleVerificationEvent()
	{
		if (m_registerActivity == null)
			return;

		initPhoneNum();
		//01. 获取手机号码，并判断有效性
		if (LogicalUtil.isMobileNumValid(m_phoneNum) == false)
		{
			RegisterDialog.GetInstance().setMsg(m_registerActivity.getResources().getString(R.string.err_info_invalid_phone),
												m_registerActivity
											   );
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 获取当前国家,并查看是否支持
		if (!initCountryZipCode())
			return;

		//03. 发送手机验证
		SmsAutho.GetInstance().getVerificationCode(m_CountryZipCode, m_phoneNum);

	}

	private void initPhoneNum()
	{
		String phoneNum = m_phoneNumTV.getText().toString().trim();
		if (TextUtils.isEmpty(m_phoneNum) == true ||
				m_phoneNum.equals(phoneNum) == false)
		{
			m_phoneNum = phoneNum;
		}
	}

	private boolean initCountryZipCode()
	{
		if (TextUtils.isEmpty(m_CountryZipCode) == false)
			return true;

		String strMcc       = AppUtil.GetMCC();
		String strCountry[] = null;
		if (!TextUtils.isEmpty(strMcc))
		{
			strCountry = SMSSDK.getCountryByMCC(strMcc);
		}
		else
		{
			strCountry = SMSSDK.getCountry(SmsConfig.DEFAULT_COUNTRY_ID);
		}

		if (strCountry == null)
		{
			return false;
		}

		m_CountryZipCode = strCountry[1];
		HashMap<String, String> coutryCodeRuleMaps = DSmsAutho.GetInstance().getCountryCodeMaps();
		if (coutryCodeRuleMaps == null)
		{
			return false;
		}

		String strPhoneRule = coutryCodeRuleMaps.get(m_CountryZipCode);
		if (strPhoneRule == null)
		{
			return false;
		}

		Pattern pattern = Pattern.compile(strPhoneRule);
		Matcher matcher = pattern.matcher(m_phoneNum);
		if (matcher.matches() == false)
		{
			RegisterDialog.GetInstance().setMsg(m_registerActivity.getResources().getString(R.string.err_info_invalid_country_zip_code), m_registerActivity);
			RegisterDialog.GetInstance().show();
			return false;
		}
		return true;
	}

	private void handleRegisterEvent()
	{
		//01. 提交验证码
		TextView authTV =  m_registerActivity.getAuthTV();
		String authCode = authTV.getText().toString().trim();
		//更改提交方式，由提交到第三方，更改为提交到本地服务器。
//		SmsAutho.GetInstance().submitVerificationCode(m_CountryZipCode, m_phoneNum, authCode);
		initPhoneNum();
		if (!initCountryZipCode())
			return;

		ReqRegisterEvent reqRegisterEvent = new ReqRegisterEvent();
		reqRegisterEvent.init(m_CountryZipCode, m_phoneNum, authCode);
		EventBus.getDefault().post(reqRegisterEvent);
	}

}
