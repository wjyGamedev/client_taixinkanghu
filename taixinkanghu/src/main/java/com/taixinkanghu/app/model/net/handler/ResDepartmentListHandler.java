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

import com.taixinkanghu.app.model.net.IResponseListener;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class ResDepartmentListHandler extends IResponseListener
{
	private EventBus m_eventBus = EventBus.getDefault();

	@Override
	public void onResponse(JSONObject response)
	{

	}
}
