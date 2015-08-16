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

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DNurseBasicsList
{
	private HashMap<Integer, DNurseBasics> m_dNurseBasicsHashMap = new HashMap<Integer, DNurseBasics>();
	private ArrayList<DNurseBasics>        m_nurseBasicses       = new ArrayList<>();

	public boolean serialization(JSONObject response) throws JSONException
	{
		if (m_nurseBasicses != null && m_nurseBasicses.isEmpty() == false)
		{
			m_dNurseBasicsHashMap.clear();
		}

		JSONArray jsonArray = response.getJSONArray(DataConfig.NURSE_BASICS_LIST);

		if (jsonArray == null)
			return false;

		JSONObject jsonObject = null;
		DNurseBasics dNurseBasics = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{
			jsonObject = (JSONObject)jsonArray.get(index);
			dNurseBasics = new DNurseBasics();
			dNurseBasics.serialization(jsonObject);

			m_nurseBasicses.add(dNurseBasics);
		}

		return  true;
	}

	public HashMap<Integer, DNurseBasics> GetNurseBasicsHashMap()
	{
		return m_dNurseBasicsHashMap;
	}


}
