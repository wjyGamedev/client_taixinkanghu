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
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DScheduleList
{
	public boolean serialization(JSONObject response)
	{

		return true;
	}

	public void init(int iID)
	{
		m_iID = iID;
	}

	public int getiID()
	{
		return m_iID;
	}

	public HashMap<Integer, ArrayList<Integer>> getTimeListHashMap()
	{
		return m_timeListHashMap;
	}

	private int                                  m_iID             = 0;                            //ID
	private HashMap<Integer, ArrayList<Integer>> m_timeListHashMap = new HashMap<Integer, ArrayList<Integer>>();    //近日服务时间安排
}