/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/22		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

public class HandleClickEventOnNurseOrder extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	public HandleClickEventOnNurseOrder(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		NurseOrderActivity nurseOrderActivity = (NurseOrderActivity)m_context;
		if (nurseOrderActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch (v.getId())
		{
		case R.id.all_rbtn:
		{

		}
		return;
		case R.id.wait_pay_rbtn:
		{

		}
		return;
		case R.id.wait_service_rbtn:
		{

		}
		return;
		}

	}
}
