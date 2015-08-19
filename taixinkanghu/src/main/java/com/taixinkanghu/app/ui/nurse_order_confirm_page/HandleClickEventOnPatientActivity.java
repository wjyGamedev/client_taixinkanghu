/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_confirm_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_confirm_page;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class HandleClickEventOnPatientActivity extends BaseHandleOnClickEvent
{
	public HandleClickEventOnPatientActivity(Context context)
	{
		super(context);
	}

	@Override
	public void onClick(View v)
	{
		PatientActivity patientActivity = (PatientActivity)m_context;
		if (patientActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("patientActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}


		FragmentTransaction transaction = patientActivity.getFragmentManager().beginTransaction();

		switch (v.getId())
		{
			//01. 点击区域
			case R.id.name_region_ll:
			{
				patientActivity.setNameFocus();
			}
			return;
			case R.id.gender_region_ll:
			{
				SelectSexFragment selectSexFragment = new SelectSexFragment();
				transaction.replace(R.id.patient_page, selectSexFragment, selectSexFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.age_region_ll:
			{
				SelectAgeFragment selectAgeFragment = new SelectAgeFragment();
				transaction.replace(R.id.patient_page, selectAgeFragment, selectAgeFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.weight_region_ll:
			{
				SelectWeightFragment selectWeightFragment = new SelectWeightFragment();
				transaction.replace(R.id.patient_page, selectWeightFragment, selectWeightFragment.getClass().getName());
				transaction.commit();
			}
			return;
			case R.id.btn_bottom:
			{
				//01. 保存本地信息
				patientActivity.confirmAction();

				//02. 跳转到订单确认页面
				m_context.startActivity(new Intent(m_context, OrderConfirmActivity.class));
				break;
			}
		}	//end_switch (v.getId())

		return;

	}	//end_onClick
}
