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
 * 2015/8/25		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderListEvent;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResNurseOrderCancelServiceHandler extends IResponseListener
{
	private int m_Status = ProtocalConfig.HTTP_OK;

	private EventBus m_eventBus = EventBus.getDefault();


	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			//02. http is ok
			m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

			if (!LogicalUtil.IsHttpSuccess(m_Status))
			{
				String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
				RegisterDialog.GetInstance().setMsg(errorMsg);
				RegisterDialog.GetInstance().show();
				return;
			}
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.getMessage().toString());
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 取消订单成功，则需要update nurse list
		ReqNurseOrderListEvent event  = new ReqNurseOrderListEvent();
		String                 userID = DAccount.GetInstance().getId();
		event.setUserID(userID);
		m_eventBus.post(event);
		return;
	}
}
