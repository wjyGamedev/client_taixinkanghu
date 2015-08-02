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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonObjectRequestForm;
import com.taixinkanghu.app.model.config.NetConfig;
import com.taixinkanghu.app.model.net.event.ReqHospitalListEvent;
import com.taixinkanghu.app.model.net.event.ReqNurseBasicListEvent;
import com.taixinkanghu.app.model.net.event.ReqNurseSeniorListEvent;
import com.taixinkanghu.app.model.net.event.ReqRegisterEvent;
import com.taixinkanghu.app.model.net.event.ReqShoppingBasicListEvent;
import com.taixinkanghu.app.model.net.exception.BaseErrorListener;
import com.taixinkanghu.app.model.net.handler.ResHospitalListHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseBasicListHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseSeniorListHandler;
import com.taixinkanghu.app.model.net.handler.ResRegisterHandler;
import com.taixinkanghu.app.model.net.handler.ResShoppingBasicListHandler;
import com.taixinkanghu.net.BaseHttp;
import com.taixinkanghu.third.party.sms.SmsConfig;

import java.util.HashMap;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

public class NetService extends Service
{
	/**
	 * 数据区
	 */
	private EventBus                    m_eventBus                    = null;//EventBus.getDefault();
	private BaseErrorListener           m_baseErrorListener           = null;
	private ResHospitalListHandler      m_resHospitalListHandler      = null;
	private ResNurseBasicListHandler    m_resNurseBasicListHandler    = null;
	private ResNurseSeniorListHandler   m_resNurseSeniorListHandler   = null;
	private ResShoppingBasicListHandler m_resShoppingBasicListHandler = null;
	private ResRegisterHandler m_resRegisterHandler = null;
	private RequestQueue                m_requestQueue                = null;
	private JsonObjectRequest           m_jsonObjectRequest           = null;

	@Override
	public void onCreate()
	{
		super.onCreate();

		init();
		initModule();
		initEvent();

	}




	@Override
	public void onDestroy()
	{
		cleanupModule();
		super.onDestroy();
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


	private void init()
	{
		m_eventBus = EventBus.getDefault();
		m_baseErrorListener = new BaseErrorListener(this);
		m_resHospitalListHandler = new ResHospitalListHandler();
		m_resNurseBasicListHandler = new ResNurseBasicListHandler();
		m_resNurseSeniorListHandler = new ResNurseSeniorListHandler();
		m_resShoppingBasicListHandler = new ResShoppingBasicListHandler();
		m_resRegisterHandler = new ResRegisterHandler();
		m_requestQueue = BaseHttp.getInstance().getRequestQueue();
	}
	private void initModule()
	{
		m_eventBus.register(this);
	}
	private void initEvent()
	{
		ReqHospitalListEvent hospitalListEvent = new ReqHospitalListEvent();
		ReqNurseBasicListEvent nurseBasicList = new ReqNurseBasicListEvent();
		ReqNurseSeniorListEvent nurseSeniorList = new ReqNurseSeniorListEvent();
		ReqShoppingBasicListEvent shoppingBasicList = new ReqShoppingBasicListEvent();
		try
		{
			m_eventBus.post(hospitalListEvent);
			m_eventBus.post(nurseBasicList);
			m_eventBus.post(nurseSeniorList);
			m_eventBus.post(shoppingBasicList);
		}
		catch (EventBusException e)
		{
			Log.e("error", e.getMessage().toString());
		}
	}

	private void cleanupModule()
	{
		m_eventBus.unregister(this);
	}

	/**
	 * event handler
	 */
	public void onEventAsync(ReqNurseBasicListEvent event)
	{
		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.s_hospitalListAddress,
														null,
														m_resHospitalListHandler,
														m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	public void onEventAsync(ReqNurseSeniorListEvent event)
	{
		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.s_nurseBasicsListAddress,
														null,
														m_resNurseBasicListHandler,
														m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	public void onEventAsync(ReqHospitalListEvent event)
	{
		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.s_nurseSeniorListAddress,
														null,
														m_resNurseSeniorListHandler,
														m_baseErrorListener);

		m_requestQueue.add(myReq);
	}


	//康复用品
	public void onEventAsync(ReqShoppingBasicListEvent event)
	{
		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.s_ShoppingBasicsListAddress,
														null,
														m_resShoppingBasicListHandler,
														m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	//注册
	public void onEventAsync(ReqRegisterEvent event)
	{
		String countryZipCode = event.getCountryZipCode();
		String phoneNum = event.getPhoneNum();
		String authCode = event.getAuthCode();

		HashMap<String, String> registerData = new HashMap<String, String>();
		registerData.put(SmsConfig.ZONE_KEY, countryZipCode);
		registerData.put(SmsConfig.PHONE_KEY, phoneNum);
		registerData.put(SmsConfig.CODE_KEY, authCode);

		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
													NetConfig.s_registerAddress,
													registerData,
													m_resRegisterHandler,
													m_baseErrorListener);

		m_requestQueue.add(myReq);
	}
}
