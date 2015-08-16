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
 * 2015/8/16		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.model.event.net.recv.FinishedNurseBasicListEvent;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResApoitNursingHandler extends IResponseListener
{
	private int    m_Status  = DataConfig.S_HTTP_OK;
	private String m_errorMsg = null;
	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{
		try
		{
			DNurseContainer.GetInstance().serialBasiclist(response);
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
		FinishedNurseBasicListEvent finishedNurseBasicListEvent = new FinishedNurseBasicListEvent();
		m_eventBus.post(finishedNurseBasicListEvent);
		return;

	}
}
