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

import com.taixinkanghu.app.model.data.DNurseBasics;
import com.taixinkanghu.app.model.data.DNurseBasicsList;
import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseBasicListEvent;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.model.net.event.send.ReqNurseSeniorListEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class ResApoitNursingHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{
		//01. 反序列化json
		try
		{
			DNurseContainer.GetInstance().serialBasiclist(response);
		}
		catch (JsonSerializationException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
//			RegisterDialog.GetInstance().show();
			return;
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 解析成功，发送event，到SelectNurseActivity
		FinishedNurseBasicListEvent finishedNurseBasicListEvent = new FinishedNurseBasicListEvent();
		m_eventBus.post(finishedNurseBasicListEvent);

		//03. 发送event，请求nurse senior消息
		ReqNurseSeniorListEvent reqNurseSeniorListEvent = new ReqNurseSeniorListEvent();

		DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		ArrayList<DNurseBasics> nurseBasicsArrayList = nurseBasicsList.getNurseBasicses();
		if (nurseBasicsArrayList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasicsArrayList == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		ArrayList<Integer> nurseIDList = new ArrayList<>();
		for (DNurseBasics nurseBasics : nurseBasicsArrayList)
		{
			if (nurseBasics == null)
				continue;

			nurseIDList.add(nurseBasics.getID());
		}
		reqNurseSeniorListEvent.setNurseIDList(nurseIDList);
		m_eventBus.post(reqNurseSeniorListEvent);

		return;

	}
}
