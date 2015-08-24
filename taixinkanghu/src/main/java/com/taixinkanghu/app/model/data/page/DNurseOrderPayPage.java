/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/20		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.page;

import com.taixinkanghu.app.model.config.DataConfig;

public class DNurseOrderPayPage
{
	private String m_userID     = null;    //用户ID
	private Object m_syncUserID = new Object();

	private String m_orderID        = null;    //订单ID，数据库主key，不是交易流水号。
	private Object m_syncOrderID = new Object();

	private String m_orderSerialNum = null;
	private Object m_syncOrderSerialNum = new Object();

	private int    m_totalPrice     = DataConfig.DEFAULT_VALUE;
	private Object m_syncTotalPrice = new Object();

	public DNurseOrderPayPage()
	{
	}

	public void clearup()
	{
		synchronized (m_syncUserID)
		{
			m_userID = null;
		}

		synchronized (m_syncOrderID)
		{
			m_orderID = null;
		}

		synchronized (m_syncOrderSerialNum)
		{
			m_orderSerialNum = null;
		}

		synchronized (m_syncTotalPrice)
		{
			m_totalPrice = DataConfig.DEFAULT_VALUE;
		}

		return;
	}

	public String getUserID()
	{
		synchronized (m_syncUserID)
		{
			return m_userID;
		}
	}

	public void setUserID(String userID)
	{
		synchronized (m_syncUserID)
		{
			m_userID = userID;
		}
	}

	public String getOrderID()
	{
		synchronized (m_syncOrderID)
		{
			return m_orderID;
		}
	}

	public void setOrderID(int orderID)
	{
		synchronized (m_syncOrderID)
		{
			m_orderID = String.valueOf(orderID);
		}
	}

	public String getOrderSerialNum()
	{
		synchronized (m_syncOrderSerialNum)
		{
			return m_orderSerialNum;
		}
	}

	public void setOrderSerialNum(String orderSerialNum)
	{
		synchronized (m_syncOrderSerialNum)
		{
			m_orderSerialNum = orderSerialNum;
		}
	}

	public int getTotalPrice()
	{
		synchronized (m_syncTotalPrice)
		{
			return m_totalPrice;
		}
	}

	public void setTotalPrice(int totalPrice)
	{
		synchronized (m_syncTotalPrice)
		{
			m_totalPrice = totalPrice;
		}
	}

}
