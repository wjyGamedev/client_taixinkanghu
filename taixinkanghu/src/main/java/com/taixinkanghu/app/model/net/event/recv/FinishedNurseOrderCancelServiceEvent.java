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
 * 2015/8/25		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.recv;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

import java.util.HashMap;

public class FinishedNurseOrderCancelServiceEvent  extends BaseNetEvent
{
	private String m_userID = null;
	private String m_orderID = null;

	public FinishedNurseOrderCancelServiceEvent()
	{
		super(EventID.QUEST_NURSE_ORDER_CANCEL_SERVICE);
	}

	public String getUserID()
	{
		return m_userID;
	}

	public void setUserID(String userID)
	{
		m_userID = userID;
	}

	public String getOrderID()
	{
		return m_orderID;
	}

	public void setOrderID(String orderID)
	{
		m_orderID = orderID;
	}

	public HashMap<String, String> getHashMap()
	{
		HashMap<String, String> nurseOrderList = new HashMap<String, String>();
		nurseOrderList.put(NurseOrderConfig.USER_ID, m_userID);
		nurseOrderList.put(NurseOrderConfig.ORDER_ID, m_orderID);
		return nurseOrderList;
	}
}
