/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.page.DFaceImages;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.net.DNurseBasics;
import com.taixinkanghu.app.model.data.net.DNurseBasicsList;
import com.taixinkanghu.app.model.data.net.DNurseContainer;
import com.taixinkanghu.app.model.data.net.DNurseSenior;
import com.taixinkanghu.app.model.data.net.DNurseSeniorList;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseSeniorListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseSeniorListEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.circleimageview.CircleImageView;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

public class NurseInfoActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon = null;

	//nurse basic
	private CircleImageView m_circleImageView  = null;    //头像
	private TextView        m_nameTV           = null;         //姓名
	private TextView        m_nuringLevelTV    = null;         //护理级别
	private TextView        m_jobNumTV         = null;     //工号
	private TextView        m_sexTV            = null;     //性别
	private TextView        m_NuringExpTV      = null;     //护理经验
	private TextView        m_ageTV            = null;     //年龄
	private TextView        m_hometownTV       = null;     //籍贯
	//nurse senior
	private TextView        m_languageLevelTV  = null;     //语言等级
	private TextView        m_nationTV         = null;    //民族
	private TextView        m_educationLevelTV = null;    //教育程度
	private TextView        m_introTV          = null;    //自我介绍
	private TextView        m_certificateTV    = null;    //持有证书
	private TextView        m_serviceContentTV = null;    //服务内容
	private TextView        m_commentRateTV    = null;    //好评率
	private TextView        m_commentNumTV     = null;    //好评数量

	//价格
	private TextView m_serviceChargePerAllCareTV    = null;    //24小时，可自理
	private TextView m_serviceChargePerAllHalfCare  = null;    //24小时，半自理
	private TextView m_serviceChargePerAllCanntCare = null;    //24小时，不可自理

	private TextView m_serviceChargePerDayCare      = null;     //12白，可自理
	private TextView m_serviceChargePerDayHalfCare  = null;    //12白，半自理
	private TextView m_serviceChargePerDayCanntCare = null;    //12白，不可自理

	private TextView m_serviceChargePerNightCare      = null;   //12黑，可自理
	private TextView m_serviceChargePerNightHalfCare  = null;  //12黑，半自理
	private TextView m_serviceChargePerNightCanntCare = null;  //12黑，不可自理

	private Button       m_gotoMainBtn = null;
	private Button       m_selectBtn   = null;
	private LinearLayout m_ReviewsBtn  = null;

	//logical
	private HandlerClickEventNurseInfo m_handlerClickEventNurseInfo = null;
	private EventBus                   m_eventBus                   = EventBus.getDefault();
	private DNurseBasics               m_nurseBasics                = null;
	private DNurseSenior               m_nurseSenior                = null;
	private int                        m_nurseID                    = -1;    //当前护工的ID

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_info);

		init();
		initListener();
		initContent();
	}

	@Override
	protected void onStart()
	{
		initGlobalData();
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		clearupGlobalData();
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	private void init()
	{
		//widget
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.nurse_info);

		//nurse basic
		m_circleImageView = (CircleImageView)findViewById(R.id.header_img_civ);    //头像
		m_nameTV = (TextView)findViewById(R.id.name_tv);         //姓名
		m_nuringLevelTV = (TextView)findViewById(R.id.nuring_level_tv);         //护理级别
		m_jobNumTV = (TextView)findViewById(R.id.job_num_tv);     //工号
		m_sexTV = (TextView)findViewById(R.id.sex_tv);     //性别
		m_NuringExpTV = (TextView)findViewById(R.id.nuring_exp_tv);     //护理经验
		m_ageTV = (TextView)findViewById(R.id.age_tv);     //年龄
		m_hometownTV = (TextView)findViewById(R.id.hometown_tv);     //籍贯
		//nurse senior
		m_languageLevelTV = (TextView)findViewById(R.id.language_level_tv);     //语言等级
		m_nationTV = (TextView)findViewById(R.id.nation_tv);    //民族
		m_educationLevelTV = (TextView)findViewById(R.id.education_level_tv);    //教育程度
		m_introTV = (TextView)findViewById(R.id.intro_tv);    //自我介绍
		m_certificateTV = (TextView)findViewById(R.id.certificate_tv);    //持有证书
		m_serviceContentTV = (TextView)findViewById(R.id.service_content_tv);    //服务内容
		m_commentRateTV = (TextView)findViewById(R.id.comment_rate_tv);    //好评率
		m_commentNumTV = (TextView)findViewById(R.id.comment_num_tv);    //好评数量

		//价格
		m_serviceChargePerAllCareTV = (TextView)findViewById(R.id.service_charge_per_all_care_tv);    //24小时，可自理
		m_serviceChargePerAllHalfCare = (TextView)findViewById(R.id.service_charge_per_all_half_care_tv);    //24小时，半自理
		m_serviceChargePerAllCanntCare = (TextView)findViewById(R.id.service_charge_per_all_cannt_care_tv);    //24小时，不可自理

		m_serviceChargePerDayCare = (TextView)findViewById(R.id.service_charge_per_day_care_tv);     //12白，可自理
		m_serviceChargePerDayHalfCare = (TextView)findViewById(R.id.service_charge_per_day_half_care_tv);    //12白，半自理
		m_serviceChargePerDayCanntCare = (TextView)findViewById(R.id.service_charge_per_day_cannt_care_tv);    //12白，不可自理

		m_serviceChargePerNightCare = (TextView)findViewById(R.id.service_charge_per_night_care_tv);   //12黑，可自理
		m_serviceChargePerNightHalfCare = (TextView)findViewById(R.id.service_charge_per_night_half_care_tv);  //12黑，半自理
		m_serviceChargePerNightCanntCare = (TextView)findViewById(R.id.service_charge_per_night_cannt_care_tv);  //12黑，不可自理

		m_gotoMainBtn = (Button)findViewById(R.id.btn_goto_main);
		m_selectBtn = (Button)findViewById(R.id.btn_select);
		m_ReviewsBtn = (LinearLayout)findViewById(R.id.btn_reviews);

		//logical
		m_handlerClickEventNurseInfo = new HandlerClickEventNurseInfo(this);

		m_eventBus.register(this);

	}

	private void initListener()
	{
		m_gotoMainBtn.setOnClickListener(m_handlerClickEventNurseInfo);
		m_selectBtn.setOnClickListener(m_handlerClickEventNurseInfo);
		m_ReviewsBtn.setOnClickListener(m_handlerClickEventNurseInfo);
	}

	private void initContent()
	{
		//01. 初始化m_nurseBasics
		Intent intent = getIntent();
		if (intent == null)
		{
			RegisterDialog.GetInstance().setMsg("intent == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_nurseID = intent.getIntExtra(NurseBasicListConfig.ID, -1);
		if (m_nurseID == -1)
		{
			RegisterDialog.GetInstance().setMsg("id is invalid", this);
			RegisterDialog.GetInstance().show();
			return;
		}


		DNurseBasicsList m_nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		if (m_nurseBasicsList == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseBasicsList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_nurseBasics = m_nurseBasicsList.getNurseBasicByID(m_nurseID);
		if (m_nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 初始化senior
		DNurseSeniorList nurseSeniorList = DNurseContainer.GetInstance().getNurseSeniorList();
		if (nurseSeniorList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSeniorList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//数据为空，则从新发送消息。
		m_nurseSenior = nurseSeniorList.getNurseSeniorByID(m_nurseID);
		if (m_nurseSenior == null)
		{
			ReqNurseSeniorListEvent reqNurseSeniorListEvent = new ReqNurseSeniorListEvent();
			m_eventBus.post(reqNurseSeniorListEvent);
			return;
		}

		//02. set ui
		//nurse basic
		int iImageID = DFaceImages.getInstance().getImgResIDbyIndex(0);
		m_circleImageView.setImageResource(iImageID);
		m_nameTV.setText(m_nurseBasics.getName());
		m_nuringLevelTV.setText(m_nurseBasics.getNursingLevel());
		m_jobNumTV.setText(String.valueOf(m_nurseBasics.getID()));
		m_sexTV.setText(m_nurseBasics.getSex());
		m_NuringExpTV.setText(m_nurseBasics.getNursingExp());
		m_ageTV.setText(String.valueOf(m_nurseBasics.getAge()));
		m_hometownTV.setText(m_nurseBasics.getHomeTown());
		//nurse senior
		m_languageLevelTV.setText(m_nurseSenior.getLanguageLevel());
		m_nationTV.setText(m_nurseSenior.getNation());
		m_educationLevelTV.setText(m_nurseSenior.getEducationLevel());
		m_introTV.setText(m_nurseSenior.getIntro());
		m_certificateTV.setText(m_nurseSenior.getCertificate());
		m_serviceContentTV.setText(m_nurseSenior.getServiceContent());
		//		m_commentRateTV
		//		m_commentNumTV
		//价格
		String charge = String.valueOf(m_nurseBasics.getServiceChargePerAllCare());
		String unit   = getResources().getString(R.string.content_yuan_per_day);
		charge += unit;
		m_serviceChargePerAllCareTV.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerAllHalfCare());
		charge += unit;
		m_serviceChargePerAllHalfCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerAllCanntCare());
		charge += unit;
		m_serviceChargePerAllCanntCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerDayCare());
		charge += unit;
		m_serviceChargePerDayCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerDayHalfCare());
		charge += unit;
		m_serviceChargePerDayHalfCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerDayCanntCare());
		charge += unit;
		m_serviceChargePerDayCanntCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerNightCare());
		charge += unit;
		m_serviceChargePerNightCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerNightHalfCare());
		charge += unit;
		m_serviceChargePerNightHalfCare.setText(charge);

		charge = String.valueOf(m_nurseBasics.getServiceChargePerNightCanntCare());
		charge += unit;
		m_serviceChargePerNightCanntCare.setText(charge);

	}

	public int getNurseID()
	{
		return m_nurseID;
	}

	/**
	 * EventBus  handler
	 */
	public void onEventMainThread(FinishedNurseSeniorListEvent event)
	{
		initContent();
	}
}
