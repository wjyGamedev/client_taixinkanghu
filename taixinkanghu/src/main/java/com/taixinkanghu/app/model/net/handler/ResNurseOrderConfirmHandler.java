/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.handler.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.data.DNurseOrder;
import com.taixinkanghu.app.model.data.DNurserOrderList;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.NurseInServiceRTException;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.net.event.recv.FailedNurseOrderConfirmEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderListEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class ResNurseOrderConfirmHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	private int                    m_Status      = ProtocalConfig.HTTP_OK;

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			//01. 返回nurse order list
			DNurserOrderList.GetInstance().serialization(response);
		}
		catch (NurseInServiceRTException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();

			//02. 选择护工处于服务状态，请重新选择。
			FailedNurseOrderConfirmEvent event = new FailedNurseOrderConfirmEvent();
			m_eventBus.post(event);
			return;
		}
		catch (JsonSerializationException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();
			return;
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();
			return;
		}
		catch (ParseException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 接收成功，
		FinishedNurseOrderListEvent finishedNurseOrderListEvent = new FinishedNurseOrderListEvent();
		ArrayList<DNurseOrder> nurseOrders = DNurserOrderList.GetInstance().getNurseOrders();
		if (nurseOrders.size() != 1)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrders.size() != 1[nurseOrders.size():=" + nurseOrders.size() + "]");
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseOrder nurseOrder = nurseOrders.get(0);
		if (nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrder == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		finishedNurseOrderListEvent.setNurseID(nurseOrder.getNurseID());
		finishedNurseOrderListEvent.setOrderID(nurseOrder.getOrderID());

		m_eventBus.post(finishedNurseOrderListEvent);

		return;

	}
}
