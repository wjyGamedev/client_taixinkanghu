package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.event.send.ReqNurseSeniorListEvent;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.select_date.SelectDateActivity;
import com.taixinkanghu.app.ui.select_date.SelectDateFragment;
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
			return;

		SelectDateFragment          selectDateFragment = new SelectDateFragment();
		FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

		switch (v.getId())
		{
			case R.id.name_region:
			{
				apoitNursingActivity.setNameFocus();
				break;
			}
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
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
				reqApoitNursingEvent.init(m_context);
				m_eventBus.post(reqApoitNursingEvent);
				//0302. nurse senior list
				ReqNurseSeniorListEvent reqNurseSeniorListEvent = new ReqNurseSeniorListEvent();
				m_eventBus.post(reqNurseSeniorListEvent);

				//04. 跳转到护理员列表界面
				m_context.startActivity(new Intent(m_context, SelectNurseActivity.class));
				break;
			}
			case R.id.btn_gender:
			{
				SelectSexFragment selectSexFragment = new SelectSexFragment();
				transaction.replace(R.id.appointment_nursing_page, selectSexFragment, selectSexFragment.getClass().getName());
				transaction.commit();
				break;
			}
			case R.id.btn_age:
			{
				SelectAgeFragment selectAgeFragment = new SelectAgeFragment();
				transaction.replace(R.id.appointment_nursing_page, selectAgeFragment, selectAgeFragment.getClass().getName());
				transaction.commit();
				break;
			}
			case R.id.btn_weight:
			{
				SelectWeightFragment selectWeightFragment = new SelectWeightFragment();
				transaction.replace(R.id.appointment_nursing_page, selectWeightFragment, selectWeightFragment.getClass().getName());
				transaction.commit();
				break;
			}
			case R.id.btn_hospital:
			{
				SelectHospitalFragment selectHospitalFragment = new SelectHospitalFragment();
				transaction.replace(R.id.appointment_nursing_page, selectHospitalFragment, selectHospitalFragment.getClass().getName());
				transaction.commit();
				break;
			}
			case R.id.department_ll:
			{
				SelectDepartmentFragment selectDepartmentFragment = new SelectDepartmentFragment();
				transaction.replace(R.id.appointment_nursing_page, selectDepartmentFragment, selectDepartmentFragment.getClass().getName());
				transaction.commit();
				break;
			}
			case R.id.btn_patient_state:
			{
				SelectPatientStateFragment selectPatientStateFragment = new SelectPatientStateFragment();
				transaction.replace(R.id.appointment_nursing_page, selectPatientStateFragment, selectPatientStateFragment.getClass().getName());
				transaction.commit();
				break;
			}
			case R.id.btn_date:
			{
				m_context.startActivity(new Intent(m_context, SelectDateActivity.class));
				break;
			}
		}
	}
}
