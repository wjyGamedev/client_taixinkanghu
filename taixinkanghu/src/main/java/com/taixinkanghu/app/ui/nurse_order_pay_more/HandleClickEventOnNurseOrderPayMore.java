/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_pay_more.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/25		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_pay_more;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

public class HandleClickEventOnNurseOrderPayMore extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();


	public HandleClickEventOnNurseOrderPayMore(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		NurseOrderPayMoreActivity activity = (NurseOrderPayMoreActivity)m_context;
		if (activity == null)
		{
			RegisterDialog.GetInstance().setMsg("activity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch (v.getId())
		{
		case R.id.reason_value_care_rbtn:
		case R.id.reason_value_half_care_rbtn:
		case R.id.reason_value_cannt_care_rbtn:
		{
			activity.reasonValueClickAction();
		}
		return;
		case R.id.btn_bottom:
		{
			activity.confirmAction();
		}
		return;
		}

		return;

	}

}
