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
import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.model.data.net.DNurserOrderList;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class DNurseOrderPayPage
{
	private String m_userID     = null;    //用户ID
	private Object m_syncUserID = new Object();

	private String m_orderID        = null;    //订单ID，数据库主key，不是交易流水号。
	private String m_orderSerialNum = null;
	private int    m_totalPrice     = DataConfig.DEFAULT_VALUE;
	private Object m_syncOrderTotal = new Object();

	public DNurseOrderPayPage()
	{
	}

	public void clearup()
	{
		synchronized (m_syncUserID)
		{
			m_userID = null;
		}

		synchronized (m_syncOrderTotal)
		{
			m_orderID = null;
			m_orderSerialNum = null;
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
		return m_orderID;
	}

	public void setOrderID(int orderID)
	{
		synchronized (m_syncOrderTotal)
		{
			m_orderID = String.valueOf(orderID);

			DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByID(orderID);
			if (nurseOrder == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseOrder == null!Input order is invalid![orderID:=" + orderID + "]");
				RegisterDialog.GetInstance().show();
				return;
			}

			m_orderSerialNum = nurseOrder.getOrderSerialNum();
			m_totalPrice = nurseOrder.getTotalCharge();

			return;
		}
	}

	public String getOrderSerialNum()
	{
		synchronized (m_syncOrderTotal)
		{
			return m_orderSerialNum;
		}
	}

	public int getTotalPrice()
	{
		synchronized (m_syncOrderTotal)
		{
			return m_totalPrice;
		}
	}
}
