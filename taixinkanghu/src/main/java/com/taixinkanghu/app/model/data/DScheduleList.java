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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DScheduleList
{
	private int                                  m_ID             = 0;                            //ID
	private HashMap<Integer, ArrayList<Integer>> m_timeListHashMap = new HashMap<Integer, ArrayList<Integer>>();    //近日服务时间安排

	public boolean serialization(JSONObject response) throws JSONException
	{
		return true;

//		JSONArray jsonArray = response.getJSONArray(DataConfig.NURSE_SHEDULE_LIST);
//		if (jsonArray == null)
//			return false;
//
//		JSONObject jsonObject = null;
//		DNurseBasics dNurseBasics = null;
//		for (int index = 0; index < jsonArray.length(); index++)
//		{
//		}
//		return true;
	}

	public void init(int iID)
	{
		m_ID = iID;
	}

	public int getID()
	{
		return m_ID;
	}



}
