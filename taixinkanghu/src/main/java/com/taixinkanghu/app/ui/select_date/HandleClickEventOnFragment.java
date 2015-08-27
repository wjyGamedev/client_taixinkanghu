/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_date.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/12		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_date;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.nurse_order_confirm_page.OrderConfirmActivity;

import java.util.Date;

public class HandleClickEventOnFragment extends BaseHandleOnClickEvent
{
	public HandleClickEventOnFragment(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.header_background_ll:
			{
				cancelAction();
				break;
			}
			case R.id.confirm_btn:
			{
				confirmAction();
				break;
			}
			case R.id.cancel_btn:
			{
				cancelAction();
				break;
			}
			case R.id.bottom_background_ll:
			{
				cancelAction();
				break;
			}
			default:
			{
				break;
			}
		}

		return;

	}

	private void cancelAction()
	{
		Activity activity = (Activity)m_context;
		FragmentManager fgManager = activity.getFragmentManager();
		Fragment fragment = fgManager.findFragmentByTag(SelectDateFragment.class.getName());
		FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
	}

	private void confirmAction()
	{
		Activity activity = (Activity)m_context;
		FragmentManager fgManager = activity.getFragmentManager();
		Fragment fragment = fgManager.findFragmentByTag(SelectDateFragment.class.getName());
		FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();

		SelectDateFragment selectDateFragment = (SelectDateFragment)fragment;
		if (selectDateFragment == null)
			return;

		Date beginDate = selectDateFragment.getBeginDate();
		Date endDate = selectDateFragment.getEndDate();

		EnumConfig.NursingModuleStatus  nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			OrderConfirmActivity orderConfirmActivity = (OrderConfirmActivity)activity;
			if (orderConfirmActivity == null)
			{
				return;
			}
			orderConfirmActivity.setNewBeginDate(beginDate);
			orderConfirmActivity.confirmDateAction();

		}
		else
		{
			SelectDateActivity selectDateActivity = (SelectDateActivity)activity;
			if (selectDateActivity == null)
				return;

			selectDateActivity.setBeginDate(beginDate);
			selectDateActivity.setEndDate(endDate);
			selectDateActivity.confirmDateAction();
		}





	}


}
