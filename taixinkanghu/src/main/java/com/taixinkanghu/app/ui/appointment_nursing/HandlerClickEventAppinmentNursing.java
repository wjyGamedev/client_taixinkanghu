package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.select_date.SelectDateActivity;
import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;

/**
 * Created by Administrator on 2015/7/28.
 */
public class HandlerClickEventAppinmentNursing extends BaseHandleOnClickEvent
{
	public HandlerClickEventAppinmentNursing(Activity activity)
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
			case R.id.btn_bottom:
			{
				m_context.startActivity(new Intent(m_context, SelectNurseActivity.class));
				break;
			}
			case R.id.btn_gender:
			{
				activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.appointment_nursing_page,
																							  new SelectGenderFragment()
																							 ).commit();
				break;
			}
			case R.id.btn_age:
			{
				activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.appointment_nursing_page,
																							  new SelectAgeFragment()
																							 ).commit();
				break;
			}
			case R.id.btn_weight:
			{
				activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.appointment_nursing_page,
																							  new SelectWeightFragment()
																							 ).commit();
				break;
			}
			case R.id.btn_hospital:
			{
				SelectHospitalFragment fragment = new SelectHospitalFragment();
				fragment.setIntoWay(1);
				activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.appointment_nursing_page, fragment
																							 ).commit();
				break;
			}
			case R.id.btn_patient_state:
			{
				activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.appointment_nursing_page,
																							  new SelectPatientStateFragment()
																							 ).commit();
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
