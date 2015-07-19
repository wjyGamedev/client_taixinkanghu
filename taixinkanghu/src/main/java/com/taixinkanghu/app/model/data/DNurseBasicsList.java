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

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DNurseBasicsList
{
	public boolean serialization(JSONObject response)
	{
		if (m_dNurseBasicsHashMap != null &&
				m_dNurseBasicsHashMap.size() != 0)
		{
			m_dNurseBasicsHashMap.clear();
		}

		JSONArray jsonArray = null;
		try
		{
			jsonArray = response.getJSONArray(DataConfig.NURSE_BASICS_LIST);

			if (jsonArray == null)
				return false;

			JSONObject jsonObject = null;
			DNurseBasics dNurseBasics = null;
			for (int index = 0; index < jsonArray.length(); index++)
			{
				jsonObject=(JSONObject)jsonArray.get(index);
				dNurseBasics = new DNurseBasics();
				dNurseBasics.serialization(jsonObject);

				Integer iID = jsonObject.getInt(DataConfig.NURSE_ID);
				m_dNurseBasicsHashMap.put(iID, dNurseBasics);
			}

		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return false;
		}

		return  true;
	}

	public HashMap<Integer, DNurseBasics> GetNurseBasicsHashMap()
	{
		return m_dNurseBasicsHashMap;
	}

	private HashMap<Integer, DNurseBasics> m_dNurseBasicsHashMap = new HashMap<Integer, DNurseBasics>();
}
