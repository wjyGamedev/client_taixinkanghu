/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.appointment_nursing.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/27		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.DApoitNursing;
import com.taixinkanghu.app.model.data.DDepartment;
import com.taixinkanghu.app.model.data.DDepartmentList;
import com.taixinkanghu.app.model.data.DGlobal;
import com.taixinkanghu.app.model.data.DHospital;
import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.event.editevent.HandleEditActionEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.select_date.ConfirmSelectDateEvent;

import java.util.ArrayList;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class ApoitNursingActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon         = null;    //title：预约陪护
	private LinearLayout m_nameRegionLL         = null;    //姓名点击区域
	private EditText     m_nameTV               = null;    //姓名
	private EditText     m_phoneNumTV           = null;    //手机号码
	private LinearLayout m_genderRegionLL       = null;    //性别点击区域
	private TextView     m_genderTV             = null;     //性别
	private LinearLayout m_ageRegionLL          = null;    //年龄点击区域
	private TextView     m_ageTV                = null;    //年龄
	private LinearLayout m_weightRegionLL       = null;    //体重点击区域
	private TextView     m_weightTV             = null;    //体重
	private LinearLayout m_hospitaltRegionLL    = null;    //医院点击区域
	private TextView     m_hospitalTV           = null;    //医院
	private LinearLayout m_departmentRegionLL   = null;    //科室点击区域
	private TextView     m_departmentTV         = null;    //科室
	private LinearLayout m_patientStateRegionLL = null;    //患者状态点击区域
	private TextView     m_patientStateTV       = null;    //患者状态
	private LinearLayout m_serviceDateRegionLL  = null;    //服务时间点击区域
	private TextView     m_serviceDateTV        = null;    //服务时间
	private TextView     m_protocolTV           = null;    //用户协议
	private Button       m_confirmBtn           = null;    //确定预约

	//测量高度用的LL
	private LinearLayout m_measuringGenderHeightLL       = null;    //测量性别下拉框所需高度的LL
	private Integer      m_selectGenderTitleHight        = 0;    //性别下拉框所需高度
	private LinearLayout m_measuringAgeHeightLL          = null;    //测量年龄下拉框所需高度的LL
	private Integer      m_selectAgeTitleHight           = 0;    //年龄下拉框所需高度
	private LinearLayout m_measuringWeightHeightLL       = null;    //测量体重下拉框所需高度的LL
	private Integer      m_selectWeightTitleHight        = 0;    //体重下拉框所需高度
	private LinearLayout m_measuringPatientStateHeightLL = null;    //测量患者状态下拉框所需高度的LL
	private Integer      m_selectPatientStateTitleHight  = 0;    //患者状态下拉框所需高度


	//logical
	private HandleEditActionEvent             m_handleEditActionEvent               = null;
	private HandlerClickEventAppinmentNursing m_handlerClickEventAppointmentNursing = null;

	private EventBus m_eventBus = EventBus.getDefault();

	private ArrayList<ArrayList<Date>>    m_dateListAll     = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> m_typeListAll     = new ArrayList<>();
	private String                        m_dateDescription = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_nursing);


		init();
		initListener();
		initContent();
		getHight();
	}

	@Override
	protected void onStart()
	{
		updateDate();
		initGlobalData();
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		clearupGlobalData();
		super.onStop();
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	private void updateDate()
	{
		//姓名
		String name = DApoitNursing.GetInstance().getName();
		if (TextUtils.isEmpty(name) == false)
		{
			m_nameTV.setText(name);
		}

		//手机号
		String phone = DApoitNursing.GetInstance().getPhone();
		if (TextUtils.isEmpty(phone) == false)
		{
			m_phoneNumTV.setText(phone);
		}

		//性别
		EnumConfig.SexType sexType = DApoitNursing.GetInstance().getSexType();
		if (sexType != null)
		{
			m_genderTV.setText(sexType.getName());
		}

		//年龄
		EnumConfig.AgeRage ageRage = DApoitNursing.GetInstance().getAgeRage();
		if (ageRage != null)
		{
			m_ageTV.setText(ageRage.getName());
		}

		//体重
		EnumConfig.WeightRage weightRage = DApoitNursing.GetInstance().getWeightRage();
		if (weightRage != null)
		{
			m_weightTV.setText(weightRage.getName());
		}

		//所在医院
		int hospitalID = DApoitNursing.GetInstance().getHospitalID();
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
		int                    departmentID   = DApoitNursing.GetInstance().getDepartmenetID();
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
		EnumConfig.PatientState patientState = DApoitNursing.GetInstance().getPatientState();
		if (patientState != null)
		{
			m_patientStateTV.setText(patientState.getName());
		}

		//护理时间
		DApoitNursing.DNursingDate dNursingDate = DApoitNursing.GetInstance().getNursingDate();
		if (dNursingDate != null)
		{
			String dateDescription = dNursingDate.getDateDescription();
			m_serviceDateTV.setText(dateDescription);
		}

	}

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
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
		m_hospitaltRegionLL = (LinearLayout)findViewById(R.id.hospital_region_ll);
		m_hospitalTV = (TextView)findViewById(R.id.hospital_tv);
		m_departmentRegionLL = (LinearLayout)findViewById(R.id.department_region_ll);
		m_departmentTV = (TextView)findViewById(R.id.department_tv);
		m_patientStateRegionLL = (LinearLayout)findViewById(R.id.patient_state_region_ll);
		m_patientStateTV = (TextView)findViewById(R.id.patient_state_tv);
		m_serviceDateRegionLL = (LinearLayout)findViewById(R.id.service_date_region_ll);
		m_serviceDateTV = (TextView)findViewById(R.id.service_date_tv);
		m_protocolTV = (TextView)findViewById(R.id.protocol_tv);
		m_confirmBtn = (Button)findViewById(R.id.btn_bottom);

		//测量高度用的LL
		m_measuringGenderHeightLL = (LinearLayout)findViewById(R.id.measuring_height_gender);
		m_measuringAgeHeightLL = (LinearLayout)findViewById(R.id.measuring_height_age);
		m_measuringWeightHeightLL = (LinearLayout)findViewById(R.id.measuring_height_weight);
		m_measuringPatientStateHeightLL = (LinearLayout)findViewById(R.id.measuring_height_patient_state);

		m_handleEditActionEvent = new HandleEditActionEvent(this);

		m_handlerClickEventAppointmentNursing = new HandlerClickEventAppinmentNursing(this);
		m_eventBus.register(this);
	}

	private void initListener()
	{
		//01. 点击区域
		m_nameRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_genderRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_ageRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_weightRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_hospitaltRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_departmentRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_patientStateRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_serviceDateRegionLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		//02. 控件点击
		m_protocolTV.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_confirmBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		//03. edittext
		m_nameTV.setOnEditorActionListener(m_handleEditActionEvent);
		m_phoneNumTV.setOnEditorActionListener(m_handleEditActionEvent);

	}

	private void initContent()
	{
		m_headerCommon.setTitle(R.string.appointment_nursing_title);
		m_confirmBtn.setText(R.string.confirm_btn_text);
		m_protocolTV.append(Html.fromHtml("<a href=>" + "《用户协议》" + "</a> "));
	}

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

		m_measuringPatientStateHeightLL.measure(w, h);
		m_selectPatientStateTitleHight = m_measuringPatientStateHeightLL.getMeasuredHeight();

	}


	public TextView getGenderTV()
	{
		return m_genderTV;
	}


	public TextView getAgeTV()
	{
		return m_ageTV;
	}


	public TextView getWeightTV()
	{
		return m_weightTV;
	}


	public TextView getHospitalTV()
	{
		return m_hospitalTV;
	}


	public Integer getSelectPatientStateTitleHight()
	{
		return m_selectPatientStateTitleHight;
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


	public String getHospitalName()
	{
		return m_hospitalTV.getText().toString();
	}

	public String getDepartmentName()
	{
		return m_departmentTV.getText().toString();
	}

	public String getPatientState()
	{
		return m_patientStateTV.getText().toString();
	}

	public TextView getPatientStateTV()
	{
		return m_patientStateTV;
	}

	public String getDateDescription()
	{
		return m_serviceDateTV.getText().toString();
	}

	public void setDateDescription(String dateDescription)
	{
		m_dateDescription = dateDescription;
		if (m_serviceDateTV == null)
			return;
		m_serviceDateTV.setText(m_dateDescription);
	}

	public ArrayList<ArrayList<Integer>> getTypeListAll()
	{
		return m_typeListAll;
	}

	public void setTypeListAll(ArrayList<ArrayList<Integer>> typeListAll)
	{
		m_typeListAll = typeListAll;
	}

	public ArrayList<ArrayList<Date>> getDateListAll()
	{
		return m_dateListAll;
	}

	public void setDateListAll(ArrayList<ArrayList<Date>> dateListAll)
	{
		m_dateListAll = dateListAll;
	}


	//焦点设置
	public void setNameFocus()
	{
		m_nameTV.requestFocus();
	}

	//数据设置
	public void setSexType(EnumConfig.SexType sexType)
	{
		m_genderTV.setText(sexType.getName());
		DApoitNursing.GetInstance().setSexType(sexType);
	}

	public void setAgeRage(EnumConfig.AgeRage ageRage)
	{
		m_ageTV.setText(ageRage.getName());
		DApoitNursing.GetInstance().setAgeRage(ageRage);
	}

	public void setWeightRage(EnumConfig.WeightRage weightRage)
	{
		m_weightTV.setText(weightRage.getName());
		DApoitNursing.GetInstance().setWeightRage(weightRage);
	}

	public void setDepartmentID(int departmentID)
	{
		//01. set department ui
		ArrayList<DDepartment> departmentList = DDepartmentList.GetInstance().getDepartments();
		for (DDepartment department : departmentList)
		{
			if (department.getID() == departmentID)
			{
				m_departmentTV.setText(department.getName());
			}
		}

		//02. 保存到数据类中。
		DApoitNursing.GetInstance().setDepartmenetID(departmentID);
	}

	public void setHospitalID(int hospitalID)
	{
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
				}
			}
		}
		//03. 保存到数据类中。
		DApoitNursing.GetInstance().setHospitalID(hospitalID);
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		m_patientStateTV.setText(patientState.getName());
		DApoitNursing.GetInstance().setPatientState(patientState);
	}

	public void confirmAction()
	{
		//姓名
		String name = m_nameTV.getText().toString();
		if (!TextUtils.isEmpty(name))
		{
			DApoitNursing.GetInstance().setName(name);
		}

		//手机号码
		String phone = m_phoneNumTV.getText().toString();
		if (!TextUtils.isEmpty(phone))
		{
			DApoitNursing.GetInstance().setPhone(phone);
		}

	}

	/**
	 * EventBus  handler
	 */
	public void onEventMainThread(ConfirmSelectDateEvent event)
	{
		if (event == null)
			return;

		if (event.getdNursingDate() == null)
			return;

		setDateDescription(event.getdNursingDate().getDateDescription());
		DApoitNursing.GetInstance().setNursingDate(event.getdNursingDate());
	}
}
