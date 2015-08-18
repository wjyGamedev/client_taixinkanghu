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
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonObjectRequestForm;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.config.NetConfig;
import com.taixinkanghu.app.model.event.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.event.send.ReqDepartmentListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqHospitalListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseSeniorListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqRegisterEvent;
import com.taixinkanghu.app.model.net.event.send.ReqShoppingBasicListEvent;
import com.taixinkanghu.app.model.net.handler.BaseErrorListener;
import com.taixinkanghu.app.model.net.handler.ResApoitNursingHandler;
import com.taixinkanghu.app.model.net.handler.ResDepartmentListHandler;
import com.taixinkanghu.app.model.net.handler.ResHospitalListHandler;
import com.taixinkanghu.app.model.net.handler.ResNurseSeniorListHandler;
import com.taixinkanghu.app.model.net.handler.ResRegisterHandler;
import com.taixinkanghu.app.model.net.handler.ResShoppingBasicListHandler;
import com.taixinkanghu.app.model.data.DApoitNursing;
import com.taixinkanghu.app.ui.appointment_nursing.ReqApoitNursingEvent;
import com.taixinkanghu.net.BaseHttp;
import com.taixinkanghu.third.party.sms.SmsConfig;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.HashMap;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

public class NetService extends Service
{
	/**
	 * 数据区
	 */
	private EventBus               m_eventBus               = EventBus.getDefault();
	private BaseErrorListener      m_baseErrorListener      = null;
	private ResHospitalListHandler m_resHospitalListHandler = null;
	private ResDepartmentListHandler m_resDepartmentListHandler = null;
	private ResRegisterHandler     m_resRegisterHandler     = null;
	private ResApoitNursingHandler m_resApoitNursingHandler = null;
	private ResNurseSeniorListHandler m_resNurseSeniorListHandler = null;


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
		m_resDepartmentListHandler = new ResDepartmentListHandler();
		m_resRegisterHandler = new ResRegisterHandler();
		m_resApoitNursingHandler = new ResApoitNursingHandler();
		m_resNurseSeniorListHandler = new ResNurseSeniorListHandler();

		m_resShoppingBasicListHandler = new ResShoppingBasicListHandler();
		m_requestQueue = BaseHttp.getInstance().getRequestQueue();
	}

	private void initModule()
	{
		m_eventBus.register(this);
	}

	private void initEvent()
	{
		ReqHospitalListEvent hospitalListEvent = new ReqHospitalListEvent();
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
		String name = DApoitNursing.GetInstance().getName();
		String phone = DApoitNursing.GetInstance().getPhone();

		EnumConfig.SexType sexType   = DApoitNursing.GetInstance().getSexType();
		int                sexTypeID = 0;
		if (sexType != null)
		{
			sexTypeID = sexType.getId();
		}

		String age = null;
		EnumConfig.AgeRage ageRage = DApoitNursing.GetInstance().getAgeRage();
		if (ageRage != null)
		{
			age = ageRage.getName();
		}

		String weight = null;
		EnumConfig.WeightRage weightRage = DApoitNursing.GetInstance().getWeightRage();
		if (weightRage != null)
		{
			weight = weightRage.getName();
		}

		int hospitalID = DApoitNursing.GetInstance().getHospitalID();
		int departmentID = DApoitNursing.GetInstance().getDepartmenetID();
		int patientStateID = 0;
		EnumConfig.PatientState patientState = DApoitNursing.GetInstance().getPatientState();
		if (patientState != null)
		{
			patientStateID = patientState.getId();
		}

		DApoitNursing.DNursingDate dNursingDate = DApoitNursing.GetInstance().getNursingDate();
		if (dNursingDate == null)
			return;

		String schedualAll = dNursingDate.getSchedualAllDescription();
		String schedualDay = dNursingDate.getSchedualDayDescription();
		String schedualNight = dNursingDate.getSchedualNightDescription();

		HashMap<String, String> registerData = new HashMap<String, String>();

		if (!TextUtils.isEmpty(name))
			registerData.put(NurseBasicListConfig.NAME, name);

		if (!TextUtils.isEmpty(phone))
			registerData.put(NurseBasicListConfig.PHONE_NUM, phone);

		if (sexType != null)
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
}
