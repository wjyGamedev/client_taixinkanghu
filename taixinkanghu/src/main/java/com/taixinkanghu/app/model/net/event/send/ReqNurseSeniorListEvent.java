/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.event.net.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.send;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

import java.util.ArrayList;

public class ReqNurseSeniorListEvent extends BaseNetEvent
{
	private ArrayList<Integer> m_nurseIDList = new ArrayList<>();

	public ReqNurseSeniorListEvent()
	{
		super(EventID.QUEST_NURSE_SENIOR_LIST);
	}

	public ArrayList<Integer> getNurseIDList()
	{
		return m_nurseIDList;
	}

	public void setNurseIDList(ArrayList<Integer> nurseIDList)
	{
		m_nurseIDList = nurseIDList;
	}
}
