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

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderConfirmEvent;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResNurseOrderConfirmHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	private int                    m_Status      = ProtocalConfig.HTTP_OK;
	private int m_nurseID = 0;
	private int m_orderID = 0;
	private String m_orderSerialNum = null;
	private int m_totalPrice = 0;

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

			if (!LogicalUtil.IsHttpSuccess(m_Status))
			{
				String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
				RegisterDialog.GetInstance().setMsg(errorMsg);
				RegisterDialog.GetInstance().show();
				return;
			}

			JSONArray jsonArray = response.getJSONArray(NurseOrderConfig.NURSE_ORDER_LIST);
			if (jsonArray == null)
			{
				String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
				RegisterDialog.GetInstance().setMsg(errMsg + ":" + NurseOrderConfig.NURSE_ORDER_LIST);
				RegisterDialog.GetInstance().show();
				return;
			}
			if (jsonArray.length() != 1)
			{
				RegisterDialog.GetInstance().setMsg("jsonArray.length() != 1");
				RegisterDialog.GetInstance().show();
				return;
			}

			JSONObject jsonObject = (JSONObject)jsonArray.get(0);
			m_nurseID = jsonObject.getInt(NurseOrderConfig.NURSE_ID);
			m_orderID = jsonObject.getInt(NurseOrderConfig.ORDER_ID);
			m_orderSerialNum = jsonObject.getString(NurseOrderConfig.ORDER_SERIAL_NUM);
			m_totalPrice = jsonObject.getInt(NurseOrderConfig.ORDER_USER_PAY);

		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 接收成功，
		FinishedNurseOrderConfirmEvent finishedNurseOrderConfirmEvent = new FinishedNurseOrderConfirmEvent();
		finishedNurseOrderConfirmEvent.setNurseID(m_nurseID);
		finishedNurseOrderConfirmEvent.setOrderID(m_orderID);
		finishedNurseOrderConfirmEvent.setOrderSerialNum(m_orderSerialNum);
		finishedNurseOrderConfirmEvent.setTotalPrice(m_totalPrice);
		m_eventBus.post(finishedNurseOrderConfirmEvent);

		return;

	}
}
