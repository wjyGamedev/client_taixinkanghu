package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.AgreementActivity;
import com.taixinkanghu.app.ui.appointment_nursing.ApoitNursingActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.nurs_order_pay_page.NursOrderPayActivity;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventNursOrderConfirm extends BaseHandleOnClickEvent
{
	public HandlerClickEventNursOrderConfirm(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_pay:
			{
				Intent intent = new Intent(activity, NursOrderPayActivity.class);
				m_context.startActivity(intent);
				break;
			}
			case R.id.user_protocol:
			{
				Intent intent = new Intent(activity, AgreementActivity.class);
				m_context.startActivity(intent);
				break;
			}
			case R.id.patient_region:
			{
				Intent intent = new Intent(activity, ApoitNursingActivity.class);
				m_context.startActivity(intent);
				break;
			}
			case R.id.patient_state_region:
			{
				activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.order_confirm_page,
																							  new SelectPatientStateFragment()
																							 ).commit();
				break;
			}
		}
	}
}