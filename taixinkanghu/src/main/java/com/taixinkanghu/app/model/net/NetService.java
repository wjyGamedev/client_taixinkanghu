/**
 * Copyright (c) 213Team
 *
 * @className : app.frame.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.taixinkanghu.app.model.config.NetConfig;
import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.event.net.QuestHospitalListEvent;
import com.taixinkanghu.net.BaseHttp;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

public class NetService extends Service
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		m_eventBus.register(this);
		onCreateEvent();
	}

	@Override
	public void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
		Log.e("NetService", "NetService onDestroy");
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		Log.e("NetService", "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent)
	{
		super.onRebind(intent);
		Log.e("NetService", "onRebind");
	}



	private void onCreateEvent()
	{
		QuestHospitalListEvent hospitalListEvent = new QuestHospitalListEvent();
		try
		{
			m_eventBus.post(hospitalListEvent);
		}
		catch (EventBusException e)
		{
			Log.e("error", e.getMessage().toString());
		}

	}

	/**
	 * event handler
	 */
	public void onEventAsync(QuestHospitalListEvent event)
	{
		RequestQueue queue = BaseHttp.getInstance().getRequestQueue();

		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.s_testAddress,
														null,
														onReqHospitalListSuccessListener(),
														onReqHospitalListErrorListener());

		queue.add(myReq);
	}

	public Response.Listener<JSONObject> onReqHospitalListSuccessListener()
	{
		return new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				boolean bReturnFlag = DHospitalList.getInstance().serialization(response);
				if (bReturnFlag == false)
				{
					Log.w("error", "bReturnFlag == false");
				}
			}
		};
	}

	public Response.ErrorListener onReqHospitalListErrorListener()
	{
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.w("error", error.getMessage());
			}
		};
	}
	/**
	 * 数据区
	 */
	private EventBus m_eventBus = EventBus.getDefault();

}
