/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_confirm_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DDepartment;
import com.taixinkanghu.app.model.data.net.DDepartmentList;
import com.taixinkanghu.app.model.data.net.DHospital;
import com.taixinkanghu.app.model.data.net.DHospitalList;
import com.taixinkanghu.app.model.data.page.DApoitNursingPage;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.event.editevent.HandleEditActionEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.ArrayList;

public class PatientActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon   = null;    //title：预约陪护
	private LinearLayout m_nameRegionLL   = null;    //姓名点击区域
	private EditText     m_nameTV         = null;    //姓名
	private EditText     m_phoneNumTV     = null;    //手机号码
	private LinearLayout m_genderRegionLL = null;    //性别点击区域
	private TextView     m_genderTV       = null;     //性别
	private LinearLayout m_ageRegionLL    = null;    //年龄点击区域
	private TextView     m_ageTV          = null;    //年龄
	private LinearLayout m_weightRegionLL = null;    //体重点击区域
	private TextView     m_weightTV       = null;    //体重
	private TextView     m_hospitalTV     = null;    //医院
	private TextView     m_departmentTV   = null;    //科室
	private TextView     m_patientStateTV = null;    //患者状态
	private TextView     m_serviceDateTV  = null;    //服务时间
	private Button       m_confirmBtn     = null;    //确定

	//测量高度用的LL
	private LinearLayout m_measuringGenderHeightLL       = null;    //测量性别下拉框所需高度的LL
	private Integer      m_selectGenderTitleHight        = 0;    //性别下拉框所需高度
	private LinearLayout m_measuringAgeHeightLL          = null;    //测量年龄下拉框所需高度的LL
	private Integer      m_selectAgeTitleHight           = 0;    //年龄下拉框所需高度
	private LinearLayout m_measuringWeightHeightLL       = null;    //测量体重下拉框所需高度的LL
	private Integer      m_selectWeightTitleHight        = 0;    //体重下拉框所需高度
	private LinearLayout m_measuringPatientStateHeightLL = null;    //测量患者状态下拉框所需高度的LL

	//logical
	private HandleEditActionEvent             m_handleEditActionEvent             = null;
	private HandleClickEventOnPatientActivity m_handleClickEventOnPatientActivity = null;

	private DApoitNursingPage m_apoitNursingPage = DNursingModule.GetInstance().getApoitNursingPage();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_info);

		init();
		initListener();
		initContent();
		initDate();
		getHight();
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


	//获取控件高度
	private void getHight()
	{
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

		m_measuringGenderHeightLL.measure(w, h);
		m_selectGenderTitleHight = m_measuringGenderHeightLL.getMeasuredHeight();

		m_measuringAgeHeightLL.measure(w, h);
		m_selectAgeTitleHight = m_measuringAgeHeightLL.getMeasuredHeight();

		m_measuringWeightHeightLL.measure(w, h);
		m_selectWeightTitleHight = m_measuringWeightHeightLL.getMeasuredHeight();

	}


	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	private void initDate()
	{
		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//姓名
		String name = m_apoitNursingPage.getName();
		if (TextUtils.isEmpty(name) == false)
		{
			m_nameTV.setText(name);
		}

		//手机号
		String phone = m_apoitNursingPage.getPhone();
		if (TextUtils.isEmpty(phone) == false)
		{
			m_phoneNumTV.setText(phone);
		}

		//性别
		EnumConfig.GenderStatus genderStatus = m_apoitNursingPage.getGenderStatus();
		if (genderStatus != null)
		{
			m_genderTV.setText(genderStatus.getName());
		}

		//年龄
		EnumConfig.AgeRage ageRage = m_apoitNursingPage.getAgeRage();
		if (ageRage != null)
		{
			m_ageTV.setText(ageRage.getName());
		}

		//体重
		EnumConfig.WeightRage weightRage = m_apoitNursingPage.getWeightRage();
		if (weightRage != null)
		{
			m_weightTV.setText(weightRage.getName());
		}

		//所在医院
		int hospitalID = m_apoitNursingPage.getHospitalID();
		//01. 显示全部
		if (hospitalID == 0)
		{
			m_hospitalTV.setText(getResources().getString(R.string.content_all));
		}
		//02. 显示hospitalname
		else
		{
			ArrayList<DHospital> hospitals = DHospitalList.GetInstance().getHospitals();
			for (DHospital hospital : hospitals)
			{
				if (hospital.getID() == hospitalID)
				{
					m_hospitalTV.setText(hospital.getName());
					break;
				}
			}
		}

		//所在科室
		int                    departmentID   = m_apoitNursingPage.getDepartmenetID();
		ArrayList<DDepartment> departmentList = DDepartmentList.GetInstance().getDepartments();
		for (DDepartment department : departmentList)
		{
			if (department.getID() == departmentID)
			{
				m_departmentTV.setText(department.getName());
				break;
			}
		}

		//病人状态
		EnumConfig.PatientState patientState = m_apoitNursingPage.getPatientState();
		if (patientState != null)
		{
			m_patientStateTV.setText(patientState.getName());
		}

		//护理时间
		DNursingDate nursingDate = m_apoitNursingPage.getNursingDate();
		if (nursingDate != null)
		{
			String dateDescription = nursingDate.getDateDescription();
			m_serviceDateTV.setText(dateDescription);
		}

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	private void init()
	{
		//01. widget
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		m_nameRegionLL = (LinearLayout)findViewById(R.id.name_region_ll);
		m_nameTV = (EditText)findViewById(R.id.name_tv);
		m_phoneNumTV = (EditText)findViewById(R.id.phone_num_tv);
		m_genderRegionLL = (LinearLayout)findViewById(R.id.gender_region_ll);
		m_genderTV = (TextView)findViewById(R.id.gender_tv);
		m_ageRegionLL = (LinearLayout)findViewById(R.id.age_region_ll);
		m_ageTV = (TextView)findViewById(R.id.age_tv);
		m_weightRegionLL = (LinearLayout)findViewById(R.id.weight_region_ll);
		m_weightTV = (TextView)findViewById(R.id.weight_tv);
		m_hospitalTV = (TextView)findViewById(R.id.hospital_tv);
		m_departmentTV = (TextView)findViewById(R.id.department_tv);
		m_patientStateTV = (TextView)findViewById(R.id.patient_state_tv);
		m_serviceDateTV = (TextView)findViewById(R.id.service_date_tv);
		m_confirmBtn = (Button)findViewById(R.id.btn_bottom);

		m_handleEditActionEvent = new HandleEditActionEvent(this);
		m_handleClickEventOnPatientActivity = new HandleClickEventOnPatientActivity(this);

		//测量高度用的LL
		m_measuringGenderHeightLL = (LinearLayout)findViewById(R.id.measuring_height_gender);
		m_measuringAgeHeightLL = (LinearLayout)findViewById(R.id.measuring_height_age);
		m_measuringWeightHeightLL = (LinearLayout)findViewById(R.id.measuring_height_weight);

	}

	private void initListener()
	{
		//01. 点击区域
		m_nameRegionLL.setOnClickListener(m_handleClickEventOnPatientActivity);
		m_genderRegionLL.setOnClickListener(m_handleClickEventOnPatientActivity);
		m_ageRegionLL.setOnClickListener(m_handleClickEventOnPatientActivity);
		m_weightRegionLL.setOnClickListener(m_handleClickEventOnPatientActivity);
		//02. 控件点击
		m_confirmBtn.setOnClickListener(m_handleClickEventOnPatientActivity);
		//03. edittext
		m_nameTV.setOnEditorActionListener(m_handleEditActionEvent);
		m_phoneNumTV.setOnEditorActionListener(m_handleEditActionEvent);

	}

	private void initContent()
	{
		m_headerCommon.setTitle(R.string.content_patient_info);
		m_confirmBtn.setText(R.string.content_confirm);
	}

	//焦点设置
	public void setNameFocus()
	{
		m_nameTV.requestFocus();
	}

	//数据设置
	public void setGenderStatus(EnumConfig.GenderStatus genderStatus)
	{
		m_genderTV.setText(genderStatus.getName());

		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_apoitNursingPage.setGenderStatus(genderStatus);
	}

	public void setAgeRage(EnumConfig.AgeRage ageRage)
	{
		m_ageTV.setText(ageRage.getName());
		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_apoitNursingPage.setAgeRage(ageRage);
	}

	public void setWeightRage(EnumConfig.WeightRage weightRage)
	{
		m_weightTV.setText(weightRage.getName());
		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_apoitNursingPage.setWeightRage(weightRage);
	}

	public void confirmAction()
	{
		if (m_apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//姓名
		String name = m_nameTV.getText().toString();
		if (!TextUtils.isEmpty(name))
		{
			m_apoitNursingPage.setName(name);
		}

		//手机号码
		String phone = m_phoneNumTV.getText().toString();
		if (!TextUtils.isEmpty(phone))
		{
			m_apoitNursingPage.setPhone(phone);
		}

	}

	public Integer getSelectWeightTitleHight()
	{
		return m_selectWeightTitleHight;
	}


	public Integer getSelectAgeTitleHight()
	{
		return m_selectAgeTitleHight;
	}


	public Integer getSelectGenderTitleHight()
	{
		return m_selectGenderTitleHight;
	}

}
