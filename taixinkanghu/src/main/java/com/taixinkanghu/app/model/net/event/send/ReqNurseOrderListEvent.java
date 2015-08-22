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
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.send;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

import java.util.HashMap;

public class ReqNurseOrderListEvent extends BaseNetEvent
{
	private String m_userID = null;

	public ReqNurseOrderListEvent()
	{
		super(EventID.QUEST_NURSE_ORDER_LIST);
	}

	public String getUserID()
	{
		return m_userID;
	}

	public void setUserID(String userID)
	{
		m_userID = userID;
	}

	public HashMap<String, String> getHashMap()
	{
		HashMap<String, String> nurseOrderList = new HashMap<String, String>();
		nurseOrderList.put(NurseOrderConfig.USER_ID, m_userID);
		return nurseOrderList;
	}
}
