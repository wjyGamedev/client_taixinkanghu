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

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonObjectRequestForm;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.config.NetConfig;
import com.taixinkanghu.app.model.data.page.DApoitNursingPage;
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.config.NurseSeniorListConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishNurseOrderAlipayEvent;
import com.taixinkanghu.app.model.net.event.send.ReqApoitNursingEvent;
import com.taixinkanghu.app.model.net.event.send.ReqDepartmentListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqHospitalListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderAlipayEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderCancelEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderCheckEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderConfirmForChangeNurse;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseSeniorListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqRegisterEvent;
import com.taixinkanghu.app.model.net.event.send.ReqShoppingBasicListEvent;
import com.taixinkanghu.app.model.net.handler.BaseErrorListener;
import com.taixinkanghu.app.model.net.handler.ResApoitNursingHandler;
import com.taixinkanghu.app.model.net.handler.ResDepartmentListHandler;
import com.taixinkanghu.app.model.net.handler.ResHospitalListHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseOrderCancelHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseOrderCheckHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseOrderConfirmHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseOrderListHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseSeniorListHandler;
import com.taixinkanghu.app.model.net.handler.ResRegisterHandler;
import com.taixinkanghu.app.model.net.handler.ResShoppingBasicListHandler;
import com.taixinkanghu.net.BaseHttp;
import com.taixinkanghu.third.party.sms.SmsConfig;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.ArrayList;
import java.util.HashMap;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

public class NetService extends Service
{
	/**
	 * 数据区
	 */
	private EventBus                    m_eventBus                    = EventBus.getDefault();
	private BaseErrorListener           m_baseErrorListener           = null;
	private ResHospitalListHandler      m_resHospitalListHandler      = null;
	private ResDepartmentListHandler    m_resDepartmentListHandler    = null;
	private ResRegisterHandler          m_resRegisterHandler          = null;
	private ResApoitNursingHandler      m_resApoitNursingHandler      = null;
	private ResNurseSeniorListHandler   m_resNurseSeniorListHandler   = null;
	private ResNurseOrderConfirmHandler m_resNurseOrderConfirmHandler = null;
	private ResNurseOrderCheckHandler   m_resNurseOrderCheckHandler   = null;

	private ResNurseOrderListHandler m_resNurseOrderListHandler = null;
	private ResNurseOrderCancelHandler m_resNurseOrderCancelHandler = null;

	private ResShoppingBasicListHandler m_resShoppingBasicListHandler = null;

	private RequestQueue m_requestQueue = null;

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
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent)
	{
		super.onRebind(intent);
	}


	private void init()
	{
		m_eventBus = EventBus.getDefault();
		m_baseErrorListener = new BaseErrorListener(this);
		m_resHospitalListHandler = new ResHospitalListHandler();
		m_resDepartmentListHandler = new ResDepartmentListHandler();
		m_resRegisterHandler = new ResRegisterHandler();
		m_resApoitNursingHandler = new ResApoitNursingHandler();
		m_resNurseSeniorListHandler = new ResNurseSeniorListHandler();
		m_resNurseOrderConfirmHandler = new ResNurseOrderConfirmHandler();
		m_resNurseOrderCheckHandler = new ResNurseOrderCheckHandler();
		m_resNurseOrderListHandler = new ResNurseOrderListHandler();
		m_resNurseOrderCancelHandler = new ResNurseOrderCancelHandler();
		m_resShoppingBasicListHandler = new ResShoppingBasicListHandler();
		m_requestQueue = BaseHttp.getInstance().getRequestQueue();
	}

	private void initModule()
	{
		m_eventBus.register(this);
	}

	private void initEvent()
	{
		ReqHospitalListEvent   hospitalListEvent   = new ReqHospitalListEvent();
		ReqDepartmentListEvent departmentListEvent = new ReqDepartmentListEvent();
		try
		{
			m_eventBus.post(hospitalListEvent);
			m_eventBus.post(departmentListEvent);
		}
		catch (EventBusException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString());
			RegisterDialog.GetInstance().show();
		}
		return;
	}

	private void cleanupModule()
	{
		m_eventBus.unregister(this);
	}

	/**
	 * event handler
	 */
	//医院列表
	public void onEventAsync(ReqHospitalListEvent event)
	{
		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.S_NORMAL_HOSPITALLIST_ADDRESS,
														null,
														m_resHospitalListHandler,
														m_baseErrorListener
		);

		m_requestQueue.add(myReq);
	}

	//科室列表
	public void onEventAsync(ReqDepartmentListEvent event)
	{
		JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
														NetConfig.S_NORMAL_DEPARTMENTLIST_ADDRESS,
														null,
														m_resDepartmentListHandler,
														m_baseErrorListener
		);

		m_requestQueue.add(myReq);
	}

	//注册
	public void onEventAsync(ReqRegisterEvent event)
	{
		String countryZipCode = event.getCountryZipCode();
		String phoneNum       = event.getPhoneNum();
		String authCode       = event.getAuthCode();

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

	//预约陪护 nurse basic list
	public void onEventAsync(ReqApoitNursingEvent event)
	{
		DApoitNursingPage apoitNursingPage = DNursingModule.GetInstance().getApoitNursingPage();
		if (apoitNursingPage == null)
		{
			RegisterDialog.GetInstance().setMsg("apoitNursingPage == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		String name = apoitNursingPage.getName();
		String phone = apoitNursingPage.getPhone();

		EnumConfig.GenderStatus genderStatus = apoitNursingPage.getGenderStatus();
		int                sexTypeID = 0;
		if (genderStatus != null)
		{
			sexTypeID = genderStatus.getId();
		}

		String age = null;
		EnumConfig.AgeRage ageRage = apoitNursingPage.getAgeRage();
		if (ageRage != null)
		{
			age = ageRage.getName();
		}

		String weight = null;
		EnumConfig.WeightRage weightRage = apoitNursingPage.getWeightRage();
		if (weightRage != null)
		{
			weight = weightRage.getName();
		}

		int hospitalID = apoitNursingPage.getHospitalID();
		int departmentID = apoitNursingPage.getDepartmenetID();
		int patientStateID = 0;
		EnumConfig.PatientState patientState = apoitNursingPage.getPatientState();
		if (patientState != null)
		{
			patientStateID = patientState.getId();
		}

		DNursingDate nursingDate = apoitNursingPage.getNursingDate();
		if (nursingDate == null)
			return;

		String schedualAll = nursingDate.getSchedualAllDescription();
		String schedualDay = nursingDate.getSchedualDayDescription();
		String schedualNight = nursingDate.getSchedualNightDescription();

		HashMap<String, String> registerData = new HashMap<String, String>();

		if (!TextUtils.isEmpty(name))
			registerData.put(NurseBasicListConfig.NAME, name);

		if (!TextUtils.isEmpty(phone))
			registerData.put(NurseBasicListConfig.PHONE_NUM, phone);

		if (genderStatus != null)
			registerData.put(NurseBasicListConfig.SEX_ID, String.valueOf(sexTypeID));

		if (ageRage != null)
			registerData.put(NurseBasicListConfig.AGE, age);

		if (weightRage != null)
			registerData.put(NurseBasicListConfig.WEIGHT, weight);

		//由于下面是必填项目，所以不判断是否为空，直接填充信息。
		registerData.put(NurseBasicListConfig.HOSPITAL_ID, String.valueOf(hospitalID));
		registerData.put(NurseBasicListConfig.DEPARTMENT_NAME, String.valueOf(departmentID));
		registerData.put(NurseBasicListConfig.PATIENT_STATE_ID, String.valueOf(patientStateID));

		if (!TextUtils.isEmpty(schedualAll))
			registerData.put(NurseBasicListConfig.SCHEDULE_ALL, schedualAll);

		if (!TextUtils.isEmpty(schedualDay))
			registerData.put(NurseBasicListConfig.SCHEDULE_DAY, schedualDay);

		if (!TextUtils.isEmpty(schedualNight))
			registerData.put(NurseBasicListConfig.SCHEDULE_NIGHT, schedualNight);

		//过滤条件
		registerData.put(NurseBasicListConfig.STRICT, String.valueOf(0));

		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
																NetConfig.s_appointmentNursingAddress,
																registerData,
																m_resApoitNursingHandler,
																m_baseErrorListener);

		m_requestQueue.add(myReq);

	}

	//nurse senior list
	public void onEventAsync(ReqNurseSeniorListEvent event)
	{
		ArrayList<Integer> nurseIDList = event.getNurseIDList();

		String strIDList = null;
		for (Integer id : nurseIDList)
		{
			if (TextUtils.isEmpty(strIDList))
			{
				strIDList = String.valueOf(id) + NurseSeniorListConfig.SPLIT;
			}
			else
			{
				strIDList += String.valueOf(id) + NurseSeniorListConfig.SPLIT;
			}
		}
		HashMap<String, String> nurseIDHashMap = new HashMap<String, String>();
		nurseIDHashMap.put(NurseSeniorListConfig.ID_LIST, strIDList);

		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
														NetConfig.s_nurseSeniorListAddress,
														nurseIDHashMap,
														m_resNurseSeniorListHandler,
														m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	//nurse order confirm
	public void onEventAsync(ReqNurseOrderConfirmEvent event)
	{
		HashMap<String, String> nurseOrderConfirmMap = event.getHashMap();

		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
																NetConfig.s_nurseOrderConfirmAddress,
																nurseOrderConfirmMap,
																m_resNurseOrderConfirmHandler,
														m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	public void onEventAsync(ReqNurseOrderConfirmForChangeNurse event)
	{
		HashMap<String, String> nurseOrderConfirmMap = event.getHashMap();

		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
																NetConfig.s_changeNurseAddress,
																nurseOrderConfirmMap,
																m_resNurseOrderConfirmHandler,
																m_baseErrorListener);

		m_requestQueue.add(myReq);
	}


	//nurse order check
	public void onEventAsync(ReqNurseOrderCheckEvent event)
	{
		HashMap<String, String> nurseOrderCheck = event.getHashMap();

		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
																NetConfig.s_nurseOrderCheckAddress,
																nurseOrderCheck,
																m_resNurseOrderCheckHandler,
																m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	//nurse order alipay
	public void onEventAsync(ReqNurseOrderAlipayEvent event)
	{
		String payInfo = event.getPayInfo();
		Activity activity = event.getActivity();
		//01. 构造PayTask 对象
		PayTask alipay = new PayTask(activity);

		//02. 调用支付接口，获取支付结果
		String result = alipay.pay(payInfo);

		//03. 将支付结果发送到原来的页面中。
		FinishNurseOrderAlipayEvent finishNurseOrderAlipayEvent = new FinishNurseOrderAlipayEvent();
		finishNurseOrderAlipayEvent.setResult(result);
		m_eventBus.post(finishNurseOrderAlipayEvent);
		return;

	}

	//nurse order list
	public void onEventAsync(ReqNurseOrderListEvent event)
	{
		HashMap<String, String> nurseOrderList = event.getHashMap();


		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
																NetConfig.s_nurseOrderListAddress,
																nurseOrderList,
																m_resNurseOrderListHandler,
																m_baseErrorListener);

		m_requestQueue.add(myReq);
	}

	//订单取消，在待支付的条件下
	public void onEventAsync(ReqNurseOrderCancelEvent event)
	{
		HashMap<String, String> nurseOrderCancel = event.getHashMap();


		JsonObjectRequestForm myReq = new JsonObjectRequestForm(Request.Method.POST,
																NetConfig.s_nurseOrderCancel,
																nurseOrderCancel,
																m_resNurseOrderCancelHandler,
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



}
