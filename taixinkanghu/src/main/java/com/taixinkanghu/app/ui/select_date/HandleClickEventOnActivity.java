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
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

import java.util.ArrayList;
import java.util.Date;

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

				Date beginDate = selectDateActivity.getBeginDate();
				Date endDate = selectDateActivity.getEndDate();
				ArrayList<ArrayList<Date>>    dateListAll = selectDateActivity.getSchedularDateListAll();
				ArrayList<ArrayList<Integer>> typeListAll = selectDateActivity.getSchedularTypeListAll();
				String dateDescription = selectDateActivity.getDateDescription();

				DNursingDate nursingDate = new DNursingDate(beginDate, endDate, dateListAll, typeListAll, dateDescription);
				ConfirmSelectDateEvent event = new ConfirmSelectDateEvent(nursingDate);
				m_eventBus.post(event);
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
