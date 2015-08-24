/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.event.send.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/23		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.send;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

import java.util.HashMap;

public class ReqNurseOrderCancelEvent extends BaseNetEvent
{
	private String m_nurseID = null;
	private String m_nurseOrderID = null;

	public ReqNurseOrderCancelEvent()
	{
		super(EventID.QUEST_NURSE_ORDER_CANCEL);
	}

	public HashMap<String, String> getHashMap()
	{
		HashMap<String, String> nurseOrderList = new HashMap<String, String>();
		nurseOrderList.put(NurseOrderConfig.USER_ID, m_nurseID);
		nurseOrderList.put(NurseOrderConfig.ORDER_ID, m_nurseOrderID);
		return nurseOrderList;
	}

	public String getNurseID()
	{
		return m_nurseID;
	}

	public void setNurseID(String nurseID)
	{
		m_nurseID = nurseID;
	}

	public String getNurseOrderID()
	{
		return m_nurseOrderID;
	}

	public void setNurseOrderID(String nurseOrderID)
	{
		m_nurseOrderID = nurseOrderID;
	}
}
