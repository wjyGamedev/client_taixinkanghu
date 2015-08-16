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
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.event.net.recv.FinishedHospitalListEvent;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResHospitalListHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			DHospitalList.GetInstance().serialization(response);
		}
		catch (JsonSerializationException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), AppUtil.getContext());
			RegisterDialog.GetInstance().show();
			return;
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), AppUtil.getContext());
			RegisterDialog.GetInstance().show();
			return;
		}

		//解析成功，发送event
		FinishedHospitalListEvent finishedHospitalListEvent = new FinishedHospitalListEvent();
		m_eventBus.post(finishedHospitalListEvent);
		return;

	}
}
