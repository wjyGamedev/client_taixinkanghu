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

package com.taixinkanghu.app.model.data;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class DNurseOrderPay
{
	private static DNurseOrderPay s_nurseOrderPay = new DNurseOrderPay();

	private int m_nurseID        = DataConfig.DEFAULT_VALUE;    //护工ID
	private int m_orderID        = DataConfig.DEFAULT_VALUE;    //订单ID，数据库主key，不是交易流水号。
	private String m_orderSerialNum = null;    //订单流水号
	private int m_totalPrice     = DataConfig.DEFAULT_VALUE;    //总价格

	private DNurseOrderPay()
	{
	}

	public static DNurseOrderPay GetInstance()
	{
		return s_nurseOrderPay;
	}

	public void clearup()
	{
		m_nurseID = -1;
		m_orderID = -1;
		m_totalPrice = -1;
		return;
	}

	public int getNurseID()
	{
		return m_nurseID;
	}

	public void setNurseID(int nurseID)
	{
		m_nurseID = nurseID;
	}

	public int getOrderID()
	{
		return m_orderID;
	}

	public void setOrderID(int orderID)
	{
		m_orderID = orderID;
		DNurseOrder nurseOrder = DNurserOrderList.GetInstance().getNurseOrderByID(orderID);
		if (nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrder == null!Input order is invalid![orderID:=" + orderID + "]");
			RegisterDialog.GetInstance().show();
			return;
		}

		m_orderSerialNum = nurseOrder.getOrderSerialNum();
		m_totalPrice = nurseOrder.getTotalCharge();

	}

	public String getOrderSerialNum()
	{
		return m_orderSerialNum;
	}

	public int getTotalPrice()
	{
		return m_totalPrice;
	}

}
