package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.text.SimpleDateFormat;

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
		case R.id.select_begin_date_region_ll:
		{
			orderconfirmActivity.selectBeginDateAction();
		}
		return;
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
				orderconfirmActivity.confirmAction();

			}
			return;
		}
		return;
	}


}