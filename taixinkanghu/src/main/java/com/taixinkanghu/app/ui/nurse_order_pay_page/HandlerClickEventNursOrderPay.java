package com.taixinkanghu.app.ui.nurse_order_pay_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.DNurseOrderPayPage;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderCheckEvent;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventNursOrderPay extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();


	public HandlerClickEventNursOrderPay(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch (v.getId())
		{
		case R.id.btn_bottom:
		{
			ReqNurseOrderCheckEvent event = new ReqNurseOrderCheckEvent();

			String nurseID = DNurseOrderPayPage.GetInstance().getUserID();
			event.setUserID(nurseID);

			String orderID = DNurseOrderPayPage.GetInstance().getOrderID();
			event.setOrderID(orderID);

			m_eventBus.post(event);
			return;
		}
		}

		return;

	}
}