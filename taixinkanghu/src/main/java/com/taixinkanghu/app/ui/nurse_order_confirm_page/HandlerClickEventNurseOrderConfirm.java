package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.DAccount;
import com.taixinkanghu.app.model.data.DApoitNursingPage;
import com.taixinkanghu.app.model.data.DNurseOrderConfirmPage;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmEvent;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventNurseOrderConfirm extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	public HandlerClickEventNurseOrderConfirm(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		OrderConfirmActivity orderconfirmActivity = (OrderConfirmActivity)m_context;
		if (orderconfirmActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("activity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch (v.getId())
		{
			case R.id.patient_state_region_ll:
			{
				Integer patientStateTitleHight = orderconfirmActivity.getSelectPatientStateTitleHight();
				FragmentTransaction transaction = orderconfirmActivity.getFragmentManager().beginTransaction();
				SelectPatientStateFragment selectPatientStateFragment = new SelectPatientStateFragment();
				selectPatientStateFragment.setPatientStateTitleHight(patientStateTitleHight);
				transaction.replace(R.id.order_confirm_page, selectPatientStateFragment, selectPatientStateFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.patient_region_ll:
			{
				Intent intent = new Intent(orderconfirmActivity, PatientActivity.class);
				m_context.startActivity(intent);
			}
			return;
			case R.id.user_protocol_tv:
			{
				Intent intent = new Intent(orderconfirmActivity, AgreementActivity.class);
				m_context.startActivity(intent);
			}
			return;
			case R.id.confirm_appointment_btn:
			{
				//01. 发送nurse order confirm event
				ReqNurseOrderConfirmEvent event = new ReqNurseOrderConfirmEvent();
				int hospitalID = DApoitNursingPage.GetInstance().getHospitalID();
				event.setHospitalID(hospitalID);

				String userID = DAccount.GetInstance().getId();
				event.setUserID(userID);

				int nurseID = DNurseOrderConfirmPage.GetInstance().getNurseID();
				event.setNurseID(nurseID);

				String phoneNum = DApoitNursingPage.GetInstance().getPhone();
				event.setPhoneNum(phoneNum);

				String name = DApoitNursingPage.GetInstance().getName();
				event.setPatientName(name);

				EnumConfig.SexType sexType = DApoitNursingPage.GetInstance().getSexType();
				if (sexType != null)
				{
					event.setGenderID(sexType.getId());
				}

				EnumConfig.AgeRage age = DApoitNursingPage.GetInstance().getAgeRage();
				if (age != null)
				{
					event.setPatientAge(age.getName());
				}

				EnumConfig.WeightRage weight = DApoitNursingPage.GetInstance().getWeightRage();
				if (weight != null)
				{
					event.setPatientWeight(weight.getName());
				}

				EnumConfig.PatientState patientState = DApoitNursingPage.GetInstance().getPatientState();
				if (patientState != null)
				{
					event.setPatientStatusID(patientState.getId());
				}

				event.setPatientRemark("");

				int totalCharge = DNurseOrderConfirmPage.GetInstance().getTotalCharge();
				event.setTotalCharge(totalCharge);

				DApoitNursingPage.DNursingDate nursingDate = DApoitNursingPage.GetInstance().getNursingDate();
				if (nursingDate == null)
				{
					RegisterDialog.GetInstance().setMsg("nursingDate == null", m_context);
					RegisterDialog.GetInstance().show();
					return;
				}

				String allDescription   = nursingDate.getSchedualAllDescription();
				event.setAllDescription(allDescription);

				String dayDescription   = nursingDate.getSchedualDayDescription();
				event.setDayDescription(dayDescription);

				String nightDescription = nursingDate.getSchedualNightDescription();
				event.setNightDescription(nightDescription);

				m_eventBus.post(event);

			}
			return;
		}
		return;
	}


}