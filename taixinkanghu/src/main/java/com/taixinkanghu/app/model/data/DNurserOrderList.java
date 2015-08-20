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
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.NurseInServiceRTException;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class DNurserOrderList
{
	private static DNurserOrderList s_nurserOrderList = new DNurserOrderList();

	private int                    m_Status      = ProtocalConfig.HTTP_OK;
	private ArrayList<DNurseOrder> m_nurseOrders = new ArrayList<>();

	private DNurserOrderList()
	{
	}

	public static DNurserOrderList GetInstance()
	{
		return s_nurserOrderList;
	}

	public synchronized boolean serialization(JSONObject response) throws JSONException, ParseException
	{
		//01. clear up
		if (m_nurseOrders != null && m_nurseOrders.isEmpty() == false)
		{
			m_nurseOrders.clear();
		}

		//02. http is ok
		m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

		if (!LogicalUtil.IsHttpSuccess(m_Status))
		{
			String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
			if (m_Status == NurseOrderConfig.NURSE_IN_SERVICE)
			{
				throw new NurseInServiceRTException(errorMsg);
			}
			else
			{
				throw new JsonSerializationException(errorMsg);
			}
		}

		//03. 序列化json
//		JSONArray jsonArray = response.getJSONArray(NurseOrderConfig.NURSE_ORDER_LIST);
//		if (jsonArray == null)
//		{
//			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
//			throw new JsonSerializationException(errMsg + ":" + NurseOrderConfig.NURSE_ORDER_LIST);
//		}
//
//		JSONObject  jsonObject = null;
//		DNurseOrder nurseOrder = null;
//		for (int index = 0; index < jsonArray.length(); index++)
//		{
//			jsonObject = (JSONObject)jsonArray.get(index);
//			nurseOrder = new DNurseOrder();
//			nurseOrder.serialization(jsonObject);
//
//			m_nurseOrders.add(nurseOrder);
//		}
		//测试流程
		JSONObject jsonObject = response.getJSONObject(NurseOrderConfig.NURSE_ORDER_LIST);
		if (jsonObject == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + NurseOrderConfig.NURSE_ORDER_LIST);
		}
		DNurseOrder nurseOrder = new DNurseOrder();
		nurseOrder.serialization(jsonObject);
		m_nurseOrders.add(nurseOrder);
		return  true;

	}

	public synchronized ArrayList<DNurseOrder> getNurseOrders()
	{
		return m_nurseOrders;
	}

	public synchronized DNurseOrder getNurseOrderByID(int id)
	{
		if (m_nurseOrders == null)
			return null;

		for (DNurseOrder nurseOrder : m_nurseOrders)
		{
			if (nurseOrder == null)
				continue;

			if (nurseOrder.getOrderID() == id)
			{
				return nurseOrder;
			}
		}

		return null;
	}
}

