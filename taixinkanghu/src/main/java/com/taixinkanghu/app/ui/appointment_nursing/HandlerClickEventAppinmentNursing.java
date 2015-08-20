package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.select_date.SelectDateActivity;
import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/7/28.
 */
public class HandlerClickEventAppinmentNursing extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	public HandlerClickEventAppinmentNursing(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		ApoitNursingActivity apoitNursingActivity = (ApoitNursingActivity)activity;
		if (apoitNursingActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("apoitNursingActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}


		FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

		switch (v.getId())
		{
			//01. 点击区域
			case R.id.name_region_ll:
			{
				apoitNursingActivity.setNameFocus();
			}
			return;
			case R.id.gender_region_ll:
			{
				SelectSexFragment selectSexFragment = new SelectSexFragment();
				transaction.replace(R.id.appointment_nursing_page, selectSexFragment, selectSexFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.age_region_ll:
			{
				SelectAgeFragment selectAgeFragment = new SelectAgeFragment();
				transaction.replace(R.id.appointment_nursing_page, selectAgeFragment, selectAgeFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.weight_region_ll:
			{
				SelectWeightFragment selectWeightFragment = new SelectWeightFragment();
				transaction.replace(R.id.appointment_nursing_page, selectWeightFragment, selectWeightFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.hospital_region_ll:
			{
				SelectHospitalFragment selectHospitalFragment = new SelectHospitalFragment();
				transaction.replace(R.id.appointment_nursing_page, selectHospitalFragment, selectHospitalFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.department_region_ll:
			{
				SelectDepartmentFragment selectDepartmentFragment = new SelectDepartmentFragment();
				transaction.replace(R.id.appointment_nursing_page, selectDepartmentFragment, selectDepartmentFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.patient_state_region_ll:
			{
				SelectPatientStateFragment selectPatientStateFragment = new SelectPatientStateFragment();
				transaction.replace(R.id.appointment_nursing_page, selectPatientStateFragment, selectPatientStateFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.service_date_region_ll:
			{
				m_context.startActivity(new Intent(m_context, SelectDateActivity.class));
			}
			return;
			//02. 控件点击
			case R.id.protocol_tv:
			{
				m_context.startActivity(new Intent(m_context, AgreementActivity.class));
			}
			return;
			case R.id.btn_bottom:
			{
				//01. 保存本地信息
				apoitNursingActivity.confirmAction();

				//02. 必填选项是否已经填写
				String hospitalName = apoitNursingActivity.getHospitalName();
				String departmentName = apoitNursingActivity.getDepartmentName();
				String patientState = apoitNursingActivity.getPatientState();
				String dateDescription = apoitNursingActivity.getDateDescription();
				if (TextUtils.isEmpty(hospitalName) ||TextUtils.isEmpty(departmentName) || TextUtils.isEmpty(patientState) || TextUtils.isEmpty(dateDescription))
				{
					RegisterDialog.GetInstance().setMsg(apoitNursingActivity.getResources().getString(R.string
																											  .err_info_fill_required_options),
														apoitNursingActivity
													   );
					RegisterDialog.GetInstance().show();
					return;
				}

				//03. 发送消息到服务器
				//0301. nurse basic list
				ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
				m_eventBus.post(reqApoitNursingEvent);
				//备注：nurse senior list 在接收完成nurse basic list成功后，发送。

				//04. 跳转到护理员列表界面
				m_context.startActivity(new Intent(m_context, SelectNurseActivity.class));
				break;
			}
		}	//end_switch (v.getId())

		return;

	}	//end_onClick



}
