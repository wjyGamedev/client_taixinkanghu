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

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.model.net.IResponseListener;

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
		boolean bReturnFlag = DNurseContainer.GetInstance().serialBasiclist(response);
		if (bReturnFlag == false)
		{
			Log.w("error", "bReturnFlag == false");
		}
//		try
//		{
//			m_Status = response.getInt(DataConfig.STATUS_KEY);
//			if (m_Status != DataConfig.S_HTTP_OK)
//			{
//				m_errorMsg = response.getString(DataConfig.ERROR_MSG);
//			}
//
//
//
//		}
//		catch (JSONException e)
//		{
//			e.printStackTrace();
//		}

	}
}
