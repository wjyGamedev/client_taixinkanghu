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

package com.taixinkanghu.app.model.data.net;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;

import org.json.JSONArray;
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
			throw new JsonSerializationException(errorMsg);
		}

		//03. 序列化json
		JSONArray jsonArray = response.getJSONArray(NurseOrderConfig.NURSE_ORDER_LIST);
		if (jsonArray == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + NurseOrderConfig.NURSE_ORDER_LIST);
		}

		JSONObject  jsonObject = null;
		DNurseOrder nurseOrder = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{
			jsonObject = (JSONObject)jsonArray.get(index);
			nurseOrder = new DNurseOrder();
			nurseOrder.serialization(jsonObject);

			m_nurseOrders.add(nurseOrder);
		}
		return  true;

	}

	public synchronized ArrayList<DNurseOrder> getNurseOrders()
	{
		return m_nurseOrders;
	}

	public synchronized ArrayList<DNurseOrder> getNurseOrdersAll()
	{
		return m_nurseOrders;
	}

	public synchronized ArrayList<DNurseOrder> getNurseOrdersWaitPay()
	{
		ArrayList<DNurseOrder> nurseOrdersWaitPayment = getNurseOrdersByOrderStatus(EnumConfig.NurseOrderStatus.WAIT_PAYMENT);
		ArrayList<DNurseOrder> nurseOrdersWaitCashPayment = getNurseOrdersByOrderStatus(EnumConfig.NurseOrderStatus.WAIT_CASH_PAYMENT);
		ArrayList<DNurseOrder> nurseOrdersWait = new ArrayList<>();
		for (DNurseOrder nurseOrder : nurseOrdersWaitPayment)
		{
			nurseOrdersWait.add(nurseOrder);
		}
		for (DNurseOrder nurseOrder : nurseOrdersWaitCashPayment)
		{
			nurseOrdersWait.add(nurseOrder);
		}
		return nurseOrdersWait;
	}

	public synchronized ArrayList<DNurseOrder> getNurseOrdersWaitService()
	{
		return getNurseOrdersByOrderStatus(EnumConfig.NurseOrderStatus.WAIT_SERVICE);
	}

	private synchronized ArrayList<DNurseOrder> getNurseOrdersByOrderStatus(EnumConfig.NurseOrderStatus orderStatus)
	{
		ArrayList<DNurseOrder> nurseOrdersByOrderStatus = new ArrayList<>();
		if (m_nurseOrders == null || m_nurseOrders.isEmpty())
		{
			return null;
		}

		nurseOrdersByOrderStatus.clear();

		for (DNurseOrder nurseOrder : m_nurseOrders)
		{
			if (nurseOrder.getOrderStatus() == orderStatus)
			{
				nurseOrdersByOrderStatus.add(nurseOrder);
			}
		}

		return nurseOrdersByOrderStatus;
	}

	public synchronized DNurseOrder getNurseOrderByOrderID(int id)
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

	public synchronized DNurseOrder getNurseOrderByNurseID(int id)
	{
		if (m_nurseOrders == null)
			return null;

		for (DNurseOrder nurseOrder : m_nurseOrders)
		{
			if (nurseOrder == null)
				continue;

			if (nurseOrder.getNurseID() == id)
			{
				return nurseOrder;
			}
		}

		return null;
	}

}

