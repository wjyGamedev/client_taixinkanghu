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
 * 2015/7/11		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DHospitalList
{
	private DHospitalList()
	{
	}

	public static DHospitalList getInstance()
	{
		return s_dHospitalList;
	}

	public boolean serialization(JSONObject response)
	{
		if (m_dHospitalHashMap != null &&
				m_dHospitalHashMap.size() != 0)
		{
			m_dHospitalHashMap.clear();
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
		DHospital hospital = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{

			try
			{
				jsonObject=(JSONObject)jsonArray.get(index);
				hospital = new DHospital();
				hospital.serialization(jsonObject);

				Integer iHospitalID = jsonObject.getInt(DataConfig.DHOSPITAL_ID);
				m_dHospitalHashMap.put(iHospitalID, hospital);

			}
			catch (JSONException e)
			{
				e.printStackTrace();
				Log.e("error", e.getMessage().toString());
				return false;
			}

		}
		return  true;

	}

	private static DHospitalList           s_dHospitalList    = new DHospitalList();
	private        HashMap<Integer, DHospital> m_dHospitalHashMap = new HashMap<Integer, DHospital>();
}
