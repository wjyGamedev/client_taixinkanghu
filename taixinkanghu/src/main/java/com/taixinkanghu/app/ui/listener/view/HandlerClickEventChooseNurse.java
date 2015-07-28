/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.listener.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.listener.view;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.fragment.SelectDateFragment;
import com.taixinkanghu.app.ui.fragment.SelectHospitalOldFragment;
import com.taixinkanghu.app.ui.fragment.SelectScreeningFragment;
import com.taixinkanghu.app.ui.fragment.SelectSortFragment;

public class HandlerClickEventChooseNurse extends BaseHandleOnClickEvent
{
	public HandlerClickEventChooseNurse(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch (v.getId()) {
		case R.id.select_date:
			activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectDateFragment()).commit();
			break;
		case R.id.select_hospital:
			SelectHospitalOldFragment selectHospitalOldFragment = new SelectHospitalOldFragment();
			selectHospitalOldFragment.setIntoWay(1);//way(调用途径)：1为从自选护工页面打开，2位从续订页面打开
			activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, selectHospitalOldFragment).commit();
			break;
		case R.id.select_sort:
			activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectSortFragment()).commit();
			break;
		case R.id.select_screening:
			activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectScreeningFragment()).commit();
			break;
		case R.id.btn_back:
			activity.finish();
			break;
		case R.id.btn_goto_main:
			activity.finish();
			break;
		}

	}
}
