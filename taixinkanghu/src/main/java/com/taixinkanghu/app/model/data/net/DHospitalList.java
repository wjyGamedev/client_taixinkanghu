/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/11		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.net;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.config.HospitalListConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DHospitalList
{
	private static DHospitalList s_dHospitalList = new DHospitalList();

	private int                  m_Status    = ProtocalConfig.HTTP_OK;
	private ArrayList<DHospital> m_hospitals = new ArrayList<>();


	private DHospitalList()
	{
	}

	public static DHospitalList GetInstance()
	{
		return s_dHospitalList;
	}

	public synchronized boolean serialization(JSONObject response) throws JSONException
	{
		//01. 清空原来容器
		if (m_hospitals != null && m_hospitals.size() != 0)
		{
			m_hospitals.clear();
		}

		//02. http is ok
		m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

		if (!LogicalUtil.IsHttpSuccess(m_Status))
		{
			String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
			throw new JsonSerializationException(errorMsg);
		}

		//03. 序列化json
		JSONArray jsonArray = response.getJSONArray(HospitalListConfig.LIST);
		if (jsonArray == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + HospitalListConfig.LIST);
		}

		JSONObject jsonObject = null;
		DHospital hospital = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{
			jsonObject=(JSONObject)jsonArray.get(index);
			hospital = new DHospital();
			hospital.serialization(jsonObject);

			m_hospitals.add(hospital);
		}
		return  true;

	}

	public synchronized int getStatus()
	{
		return m_Status;
	}

	public synchronized ArrayList<DHospital> getHospitals()
	{
		return m_hospitals;
	}

	public synchronized DHospital getHospitalByID(int id)
	{
		for (DHospital hospital : m_hospitals)
		{
			if (hospital.getID() == id)
			{
				return hospital;
			}
		}
		return null;
	}
}
