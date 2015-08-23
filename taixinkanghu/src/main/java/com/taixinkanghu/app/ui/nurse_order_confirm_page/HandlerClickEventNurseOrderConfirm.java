package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.page.DApoitNursingPage;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNurseOrderConfirmPage;
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmForChangeNurse;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventNurseOrderConfirm extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	private SimpleDateFormat m_simpleDateFormat = new SimpleDateFormat(DateConfig.PATTERN_DATE_YEAR_MONTH_DAY);

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
			case R.id.new_nurse_rbtn:
			{
				orderconfirmActivity.switchNewNurse();
			}
			return;
			case R.id.old_nurse_rbtn:
			{
				orderconfirmActivity.switchOldNurse();
			}
			return;
			case R.id.confirm_appointment_btn:
			{
				DApoitNursingPage apoitNursingPage = DNursingModule.GetInstance().getApoitNursingPage();
				if (apoitNursingPage == null)
				{
					RegisterDialog.GetInstance().setMsg("m_apoitNursingPage == null");
					RegisterDialog.GetInstance().show();
					return;
				}
				DNurseOrderConfirmPage nurseOrderConfirmPage = DNursingModule.GetInstance().getNurseOrderConfirmPage();
				if (nurseOrderConfirmPage == null)
				{
					RegisterDialog.GetInstance().setMsg("nurseOrderConfirmPage == null");
					RegisterDialog.GetInstance().show();
					return;
				}

				EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
				if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
				{
					ReqNurseOrderConfirmForChangeNurse event = new ReqNurseOrderConfirmForChangeNurse();
					int nurseID = nurseOrderConfirmPage.getNurseID();
					event.setNurseID(String.valueOf(nurseID));
					String orderID = orderconfirmActivity.getOrderID();
					event.setOrderID(orderID);

					//当时日期+1
					Calendar calendar = Calendar.getInstance();

					int beginMonth = calendar.get(Calendar.MONTH);
					int beginDay = calendar.get(Calendar.DAY_OF_MONTH);
					int iMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
					if (beginDay == iMaxDay)
					{
						calendar.set(Calendar.MONTH, beginMonth+1);
						calendar.set(Calendar.DAY_OF_MONTH, 1);
					}
					else
					{
						calendar.set(Calendar.DAY_OF_MONTH, (beginDay+1));
					}

					String dateDescrption = m_simpleDateFormat.format(calendar.getTime());
					event.setBeginDate(dateDescrption);
					m_eventBus.post(event);

				}
				else
				{

					//01. 发送nurse order confirm event
					ReqNurseOrderConfirmEvent event = new ReqNurseOrderConfirmEvent();
					int hospitalID = apoitNursingPage.getHospitalID();
					event.setHospitalID(hospitalID);

					String userID = DAccount.GetInstance().getId();
					event.setUserID(userID);

					int nurseID = nurseOrderConfirmPage.getNurseID();
					event.setNurseID(nurseID);

					int departmentID = apoitNursingPage.getDepartmenetID();
					event.setNurseDepartmentID(departmentID);

					String phoneNum = apoitNursingPage.getPhone();
					event.setPhoneNum(phoneNum);

					String name = apoitNursingPage.getName();
					event.setPatientName(name);

					EnumConfig.GenderStatus genderStatus = apoitNursingPage.getGenderStatus();
					if (genderStatus != null)
					{
						event.setGenderID(genderStatus.getId());
					}

					EnumConfig.AgeRage age = apoitNursingPage.getAgeRage();
					if (age != null)
					{
						event.setPatientAge(age.getName());
					}

					EnumConfig.WeightRage weight = apoitNursingPage.getWeightRage();
					if (weight != null)
					{
						event.setPatientWeight(weight.getName());
					}

					EnumConfig.PatientState patientState = apoitNursingPage.getPatientState();
					if (patientState != null)
					{
						event.setPatientStatusID(patientState.getId());
					}

					event.setPatientRemark("");

					int totalCharge = nurseOrderConfirmPage.getTotalCharge();
					event.setTotalCharge(totalCharge);

					DNursingDate nursingDate = apoitNursingPage.getNursingDate();
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
			}
			return;
		}
		return;
	}


}