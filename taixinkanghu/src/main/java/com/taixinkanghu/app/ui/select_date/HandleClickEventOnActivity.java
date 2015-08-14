/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_date.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/11		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_date;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

import de.greenrobot.event.EventBus;

public class HandleClickEventOnActivity extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();
	public HandleClickEventOnActivity(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch (v.getId())
		{
			case R.id.date_select_region:
			{
				SelectDateFragment selectDateFragment = new SelectDateFragment();
				FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
				transaction.replace(R.id.select_date_activity, selectDateFragment, selectDateFragment.getClass().getName());
				transaction.addToBackStack(null);
				transaction.commit();
				break;
			}
			case R.id.btn_bottom:
			{
				activity.finish();
				SelectDateActivity selectDateActivity = (SelectDateActivity)activity;
				if (selectDateActivity == null)
					return;

				SureSelectDateEvent sureSelectDateEvent = new SureSelectDateEvent();


				m_eventBus.post(sureSelectDateEvent);
				break;
			}
			default:
			{
				break;
			}
		}

		return;

	}
}
