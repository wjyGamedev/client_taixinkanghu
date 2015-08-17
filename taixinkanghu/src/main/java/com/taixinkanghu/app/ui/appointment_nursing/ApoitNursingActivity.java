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
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.DDepartment;
import com.taixinkanghu.app.model.data.DDepartmentList;
import com.taixinkanghu.app.model.data.DHospital;
import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.event.editevent.HandleEditActionEvent;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.select_date.ConfirmSelectDateEvent;

import java.util.ArrayList;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class ApoitNursingActivity extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;


	//bottom
	private Button m_bottomBtn;

	//name
	private LinearLayout m_nameRegion = null;
	private EditText m_nameTV       = null;
	private EditText m_phoneNumTV   = null;
	private TextView m_departmentTV = null;
	private TextView m_roomTV       = null;
	private TextView m_bedTV        = null;

	//事件
	private HandleEditActionEvent m_handleEditActionEvent = null;

	//按钮
	private LinearLayout m_genderBtn;
	private LinearLayout m_ageBtn;
	private LinearLayout m_weightBtn;
	private LinearLayout m_hospitaltBtn;
	private LinearLayout m_departmentLL = null;
	private LinearLayout m_patientStateBtn;
	private LinearLayout m_dateBtn;

	private TextView  m_dateTv;
	private ImageView m_rightDate;

	private TextView  m_patientStateTv;
	private ImageView m_dwonPatientState;

	private TextView  m_hospitalTv;
	private ImageView m_dwonHospital;

	private TextView  m_weightTv;
	private ImageView m_dwonWeight;

	private TextView  m_ageTv;
	private ImageView m_dwonAge;

	private TextView  m_genderTv;
	private ImageView m_dwonGender;

	private int selected_hospital;

	private TextView m_protocolTv;

	//logical
	private HandlerClickEventAppinmentNursing m_handlerClickEventAppointmentNursing = null;
	private EventBus                          m_eventBus                            = EventBus.getDefault();

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
		initDate();
	}

	private void initDate()
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
			m_genderTv.setText(sexType.getName());
		}

		//年龄
		EnumConfig.AgeRage ageRage = DApoitNursing.GetInstance().getAgeRage();
		if (ageRage != null)
		{
			m_ageTv.setText(ageRage.getName());
		}

		//体重
		EnumConfig.WeightRage weightRage = DApoitNursing.GetInstance().getWeightRage();
		if (weightRage != null)
		{
			m_weightTv.setText(weightRage.getName());
		}

		//所在医院
		int hospitalID = DApoitNursing.GetInstance().getHospitalID();
		//01. 显示全部
		if (hospitalID == 0)
		{
			m_hospitalTv.setText(getResources().getString(R.string.content_all));
		}
		//02. 显示hospitalname
		else
		{
			ArrayList<DHospital> hospitals = DHospitalList.GetInstance().getHospitals();
			for (DHospital hospital : hospitals)
			{
				if (hospital.getID() == hospitalID)
				{
					m_hospitalTv.setText(hospital.getName());
				}
			}
		}

		//所在科室
		int departmentID = DApoitNursing.GetInstance().getDepartmenetID();
		ArrayList<DDepartment> departmentList = DDepartmentList.GetInstance().getDepartments();
		for (DDepartment department : departmentList)
		{
			if (department.getID() == departmentID)
			{
				m_departmentTV.setText(department.getName());
			}
		}

		//病人状态
		EnumConfig.PatientState patientState = DApoitNursing.GetInstance().getPatientState();
		if (patientState != null)
		{
			m_patientStateTv.setText(patientState.getName());
		}

		//护理时间
		DApoitNursing.DNursingDate dNursingDate = DApoitNursing.GetInstance().getdNursingDate();
		if (dNursingDate != null)
		{
			String dateDescription = dNursingDate.getDateDescription();
			m_dateTv.setText(dateDescription);
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
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		m_bottomBtn = (Button)findViewById(R.id.btn_bottom);

		//name
		m_nameRegion = (LinearLayout)findViewById(R.id.name_region);
		m_nameTV = (EditText)findViewById(R.id.name);
		m_phoneNumTV = (EditText)findViewById(R.id.phone_number_tv);
		m_departmentTV = (TextView)findViewById(R.id.department_tv);
		m_roomTV = (TextView)findViewById(R.id.room_tv);
		m_bedTV = (TextView)findViewById(R.id.bed_id_tv);

		m_handleEditActionEvent = new HandleEditActionEvent(this);

		m_protocolTv = (TextView)findViewById(R.id.btn_protocol);

		m_genderTv = (TextView)findViewById(R.id.gender);
		m_ageTv = (TextView)findViewById(R.id.age);
		m_weightTv = (TextView)findViewById(R.id.weight);
		m_hospitalTv = (TextView)findViewById(R.id.hospital);
		m_patientStateTv = (TextView)findViewById(R.id.patient_state);
		m_dateTv = (TextView)findViewById(R.id.date);

		m_dwonGender = (ImageView)findViewById(R.id.dwon_gender);
		m_dwonAge = (ImageView)findViewById(R.id.dwon_age);
		m_dwonWeight = (ImageView)findViewById(R.id.dwon_weight);
		m_dwonHospital = (ImageView)findViewById(R.id.dwon_hospital);
		m_dwonPatientState = (ImageView)findViewById(R.id.dwon_patient_state);
		m_rightDate = (ImageView)findViewById(R.id.right_date);

		m_genderBtn = (LinearLayout)findViewById(R.id.btn_gender);
		m_ageBtn = (LinearLayout)findViewById(R.id.btn_age);
		m_weightBtn = (LinearLayout)findViewById(R.id.btn_weight);
		m_hospitaltBtn = (LinearLayout)findViewById(R.id.btn_hospital);
		m_departmentLL = (LinearLayout)findViewById(R.id.department_ll);
		m_patientStateBtn = (LinearLayout)findViewById(R.id.btn_patient_state);
		m_dateBtn = (LinearLayout)findViewById(R.id.btn_date);

		m_handlerClickEventAppointmentNursing = new HandlerClickEventAppinmentNursing(this);
		m_eventBus.register(this);
	}

	private void initListener()
	{
		//title

		//name
		//		m_nameTV.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_nameRegion.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_nameTV.setOnEditorActionListener(m_handleEditActionEvent);
		m_phoneNumTV.setOnEditorActionListener(m_handleEditActionEvent);

		m_departmentTV.setOnEditorActionListener(m_handleEditActionEvent);
		m_roomTV.setOnEditorActionListener(m_handleEditActionEvent);
		m_bedTV.setOnEditorActionListener(m_handleEditActionEvent);

		m_bottomBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_genderBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_ageBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_weightBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_hospitaltBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_departmentLL.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_patientStateBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_dateBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
	}

	private void initContent()
	{
		m_headerCommon.setTitle(R.string.appointment_nursing_title);
		m_bottomBtn.setText(R.string.confirm_btn_text);
		m_protocolTv.append(Html.fromHtml("<a href=>" + "《用户协议》" + "</a> "));
		m_protocolTv.setOnClickListener(new View.OnClickListener()
										{
											@Override
											public void onClick(View v)
											{
												startActivity(new Intent(ApoitNursingActivity.this, AgreementActivity.class));
											}
										}
									   );


	}


	public TextView getGenderTv()
	{
		return m_genderTv;
	}

	public ImageView getDwonGender()
	{
		return m_dwonGender;
	}

	public TextView getAgeTv()
	{
		return m_ageTv;
	}

	public ImageView getDwonAge()
	{
		return m_dwonAge;
	}

	public TextView getWeightTv()
	{
		return m_weightTv;
	}

	public ImageView getDwonWeight()
	{
		return m_dwonWeight;
	}

	public TextView getHospitalTv()
	{
		return m_hospitalTv;
	}

	public ImageView getDwonHospital()
	{
		return m_dwonHospital;
	}

	public void setSelected_hospital(int selected_hospital)
	{
		this.selected_hospital = selected_hospital;
	}

	public int getSelected_hospital()
	{
		return selected_hospital;
	}

	public String getHospitalName()
	{
		return m_hospitalTv.getText().toString();
	}

	public String getDepartmentName()
	{
		return m_departmentTV.getText().toString();
	}

	public String getPatientState()
	{
		return m_patientStateTv.getText().toString();
	}

	public TextView getPatientStateTv()
	{
		return m_patientStateTv;
	}

	public ImageView getDwonPatientState()
	{
		return m_dwonPatientState;
	}

	public String getDateDescription()
	{
		return m_dateDescription;
	}

	public void setDateDescription(String dateDescription)
	{
		m_dateDescription = dateDescription;
		if (m_dateTv == null)
			return;
		m_dateTv.setText(m_dateDescription);
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
		m_genderTv.setText(sexType.getName());
		DApoitNursing.GetInstance().setSexType(sexType);
	}

	public void setAgeRage(EnumConfig.AgeRage ageRage)
	{
		m_ageTv.setText(ageRage.getName());
		DApoitNursing.GetInstance().setAgeRage(ageRage);
	}

	public void setWeightRage(EnumConfig.WeightRage weightRage)
	{
		m_weightTv.setText(weightRage.getName());
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
			m_hospitalTv.setText(getResources().getString(R.string.content_all));
		}
		//02. 显示hospitalname
		else
		{
			ArrayList<DHospital> hospitals = DHospitalList.GetInstance().getHospitals();
			for (DHospital hospital : hospitals)
			{
				if (hospital.getID() == hospitalID)
				{
					m_hospitalTv.setText(hospital.getName());
				}
			}
		}
		//03. 保存到数据类中。
		DApoitNursing.GetInstance().setHospitalID(hospitalID);
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		m_patientStateTv.setText(patientState.getName());
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
		DApoitNursing.GetInstance().setdNursingDate(event.getdNursingDate());
	}
}
