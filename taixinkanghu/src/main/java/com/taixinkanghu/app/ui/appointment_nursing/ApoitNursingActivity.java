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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.event.editevent.HandleEditActionEvent;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.header.HeaderCommon;

public class ApoitNursingActivity extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;


	//bottom
	private Button m_bottomBtn;

	//name
	private TextView m_nameTV       = null;
	private TextView m_departmentTV = null;
	private TextView m_roomTV       = null;
	private TextView m_bedTV        = null;

	//事件
	private HandlerEditorActionEvent m_handlerEditorActionEvent = null;

	//按钮
	private LinearLayout m_genderBtn;
	private LinearLayout m_ageBtn;
	private LinearLayout m_weightBtn;
	private LinearLayout m_hospitaltBtn;
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

	private HandlerClickEventAppinmentNursing m_handlerClickEventAppointmentNursing = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_nursing);

		init();
		initListener();
		initModule();

	}

	private void init()
	{
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		m_bottomBtn = (Button)findViewById(R.id.btn_bottom);

		//name
		m_nameTV = (TextView)findViewById(R.id.name);
		m_phoneNumTV = (TextView)findViewById(R.id.phone_number_tv);
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
		m_patientStateBtn = (LinearLayout)findViewById(R.id.btn_patient_state);
		m_dateBtn = (LinearLayout)findViewById(R.id.btn_date);

		m_handlerClickEventAppointmentNursing = new HandlerClickEventAppinmentNursing(this);
	}

	private void initListener()
	{
		//title

		//name
		//		m_nameTV.setOnClickListener(m_handlerClickEventAppointmentNursing);
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
		m_patientStateBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
		m_dateBtn.setOnClickListener(m_handlerClickEventAppointmentNursing);
	}

	private void initModule()
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

	public TextView getPatientStateTv()
	{
		return m_patientStateTv;
	}

	public ImageView getDwonPatientState()
	{
		return m_dwonPatientState;
	}
}
