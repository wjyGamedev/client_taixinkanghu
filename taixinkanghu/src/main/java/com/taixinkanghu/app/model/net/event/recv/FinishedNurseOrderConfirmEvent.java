/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.event.recv.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.recv;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

public class FinishedNurseOrderConfirmEvent  extends BaseNetEvent
{
	private int m_nurseID = DataConfig.DEFAULT_VALUE;    //护工ID
	private int m_orderID = DataConfig.DEFAULT_VALUE;    //订单ID，数据库主key，不是交易流水号。
	private String m_orderSerialNum = null;
	private int m_totalPrice = 0;

	public FinishedNurseOrderConfirmEvent()
	{
		super(EventID.FINISHED_NURSE_ORDER_CONFIRM);
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
	}

	public String getOrderSerialNum()
	{
		return m_orderSerialNum;
	}

	public void setOrderSerialNum(String orderSerialNum)
	{
		m_orderSerialNum = orderSerialNum;
	}

	public int getTotalPrice()
	{
		return m_totalPrice;
	}

	public void setTotalPrice(int totalPrice)
	{
		m_totalPrice = totalPrice;
	}
}
