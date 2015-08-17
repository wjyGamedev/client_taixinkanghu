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
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.data.DAccount;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.ui.register_page.DeserialFinishedEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResRegisterHandler extends IResponseListener
{
	private EventBus m_eventBus   = EventBus.getDefault();

	public void init()
	{
	}

	public void clearup()
	{
	}


	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			DAccount.GetInstance().serialFromHttp(response);
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

		//发送序列化完成event
		DeserialFinishedEvent event = new DeserialFinishedEvent();
		m_eventBus.post(event);
	}
}
