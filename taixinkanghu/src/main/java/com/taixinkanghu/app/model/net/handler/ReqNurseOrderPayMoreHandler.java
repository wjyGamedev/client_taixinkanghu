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
 * 2015/8/26		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderPayMoreEvent;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ReqNurseOrderPayMoreHandler extends IResponseListener
{
	private int    m_Status         = ProtocalConfig.HTTP_OK;
	private String m_orderID        = null;
	private String m_orderSerialNum = null;
	private int    m_price          = DataConfig.DEFAULT_VALUE;

	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			//01. http is ok
			m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

			if (!LogicalUtil.IsHttpSuccess(m_Status))
			{
				String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
				RegisterDialog.GetInstance().setMsg(errorMsg);
				RegisterDialog.GetInstance().show();
				return;
			}

			m_orderID = response.getString(NurseOrderConfig.ORDER_ID);
			m_orderSerialNum = response.getString(NurseOrderConfig.ORDER_SERIAL_NUM);
			m_price = response.getInt(NurseOrderConfig.ORDER_PAY_MORE_PRICE);

		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.getMessage().toString());
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 补差价event
		FinishedNurseOrderPayMoreEvent event = new FinishedNurseOrderPayMoreEvent();
		event.setOrderID(Integer.valueOf(m_orderID));
		event.setOrderSerialNum(m_orderSerialNum);
		event.setPrice(m_price);
		m_eventBus.post(event);
		return;
	}
}
