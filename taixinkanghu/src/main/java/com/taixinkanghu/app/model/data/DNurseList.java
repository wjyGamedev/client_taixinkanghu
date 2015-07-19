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
 * 2015/7/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DNurseList
{
	private DNurseList()
	{
		initDataTest();
	}

	private void initDataTest()
	{

	}

	public static DNurseList getInstance()
	{
		return s_dNurseList;
	}

	public boolean serialization(JSONObject response) throws JSONException
	{
		if (m_dNurseHashMap != null &&
				m_dNurseHashMap.size() != 0)
		{
			m_dNurseHashMap.clear();
		}

		JSONArray jsonArray = null;
		try
		{
			jsonArray = response.getJSONArray(DataConfig.JSON_CONTAINER_KEY);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
		}

		if (jsonArray == null)
			return false;

		JSONObject jsonObject = null;
		DNurse dNurse = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{

			try
			{
				jsonObject=(JSONObject)jsonArray.get(index);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
				Log.e("error", e.getMessage().toString());
				return false;
			}

			dNurse = new DNurse();
			dNurse.serialization(jsonObject);

			Integer iHospitalID = null;
			try
			{
				iHospitalID = jsonObject.getInt(DataConfig.NURSE_ID);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
				Log.e("error", e.getMessage().toString());
				return false;
			}

			m_dNurseHashMap.put(iHospitalID, dNurse);
		}
		return  true;

	}
	/**
	 * 数据区
	 */
	private static DNurseList               s_dNurseList    = new DNurseList();
	private        HashMap<Integer, DNurse> m_dNurseHashMap = new HashMap<Integer, DNurse>();


}
