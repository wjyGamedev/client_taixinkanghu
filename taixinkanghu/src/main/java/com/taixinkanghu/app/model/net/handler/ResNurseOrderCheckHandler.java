/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.handler.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/20		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.net.event.recv.FailedNurseOrderCheckEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderCheckEvent;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResNurseOrderCheckHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			int httpStatus = response.getInt(ProtocalConfig.HTTP_STATUS);

			//01. 失败
			if (!LogicalUtil.IsHttpSuccess(httpStatus))
			{
				String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
				RegisterDialog.GetInstance().setMsg(errorMsg);
				RegisterDialog.GetInstance().show();

				//0101. 占用情况，需跳转UI界面。
				if (httpStatus == NurseOrderConfig.NURSE_IN_SERVICE)
				{
					FailedNurseOrderCheckEvent event = new FailedNurseOrderCheckEvent();
					m_eventBus.post(event);
					return;
				}
				//0102. 其他错误情况，不跳转，直接显示失败信息。
				else
				{
					return;
				}
			}
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.getMessage().toString());
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 发送成功event
		FinishedNurseOrderCheckEvent event = new FinishedNurseOrderCheckEvent();
		m_eventBus.post(event);
		return;

	}
}
