/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.setting.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.setting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.storage.OwnerPreferences;
import com.taixinkanghu.app.model.storage.StorageWrapper;
import com.taixinkanghu.app.ui.bottom.BottomCommon;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;

import de.greenrobot.event.EventBus;

public class SettingActivity extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;
	//func
	private TextView     m_TipTV        = null;
	private TextView     m_loginoutTV   = null;
	private LinearLayout m_clickRegin   = null;
	//bottom
	private BottomCommon m_bottomCommon = null;

	//logical data
	private EventBus                 m_eventBus                 = EventBus.getDefault();
	private HandleClickEventLoginout m_handleClickEventLoginout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		init();
		initContent();
		initWidget();
		initSurface();
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
	}

	private void init()
	{
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		m_bottomCommon = new BottomCommon(this);
		m_bottomCommon.init();

		m_TipTV = (TextView)findViewById(R.id.login_tips_tv);
		m_loginoutTV = (TextView)findViewById(R.id.loginout_tv);
		m_clickRegin = (LinearLayout)findViewById(R.id.click_region_linearlayout);

		m_handleClickEventLoginout = new HandleClickEventLoginout(this);
	}

	private void initContent()
	{
		m_headerCommon.setTitle(R.string.title_setting);
		m_bottomCommon.setTitle(R.string.goto_mian_btn_text);
	}


	private void initWidget()
	{
		m_eventBus.register(this);
	}

	private void initSurface()
	{
		boolean bRegisterFlag = DAccount.GetInstance().isRegisterSuccess();

		//01. 未注册页面
		if (bRegisterFlag == false)
		{
			logoutSurface();
		}
		//02. 已注册页面。
		else
		{
			loginSurface();
		}
	}

	private void loginSurface()
	{
		String phoneNum = DAccount.GetInstance().getMobile();
		m_TipTV.setText(phoneNum);
		m_loginoutTV.setText(getResources().getString(R.string.logout));
		m_loginoutTV.setTextColor(getResources().getColor(R.color.main_color));
		m_clickRegin.setOnClickListener(m_handleClickEventLoginout);
	}

	private void logoutSurface()
	{
		m_TipTV.setText(getResources().getString(R.string.tips_logout));
		m_loginoutTV.setText(getResources().getString(R.string.login));
		m_loginoutTV.setTextColor(getResources().getColor(R.color.main_color));
		m_clickRegin.setOnClickListener(m_handleClickEventLoginout);
	}

	/**
	 * event handle
	 */
	public void onEventMainThread(LogoutEvent event)
	{
		//0201. 注销登录数据
		OwnerPreferences setting = StorageWrapper.GetInstance().getOwnerPreferences();
		try
		{
			setting.logout();
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), this);
			RegisterDialog.GetInstance().show();
		}

		//0202. 设置UI界面
		logoutSurface();
	}
}
