package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.nurs_order_pay_page.NursOrderPayActivity;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventNurseOrderConfirm extends BaseHandleOnClickEvent
{
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
				FragmentTransaction transaction = orderconfirmActivity.getFragmentManager().beginTransaction();
				SelectPatientStateFragment selectPatientStateFragment = new SelectPatientStateFragment();
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
			case R.id.btn_pay:
			{
				Intent intent = new Intent(orderconfirmActivity, NursOrderPayActivity.class);
				m_context.startActivity(intent);
			}
			return;
		}
		return;
	}


}