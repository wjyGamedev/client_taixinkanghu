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
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.data.net.DDepartmentList;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.event.recv.FinishedDepartmentListEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResDepartmentListHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			DDepartmentList.GetInstance().serialization(response);
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

		//解析成功，发送event
		FinishedDepartmentListEvent finishedDepartmentListEvent = new FinishedDepartmentListEvent();
		m_eventBus.post(finishedDepartmentListEvent);
		return;

	}
}
