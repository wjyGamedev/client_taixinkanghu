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

import java.util.ArrayList;

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
		if (m_dHospitals != null &&
				m_dHospitals.size() != 0)
		{
			m_dHospitals.clear();
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
			}
			catch (JSONException e)
			{
				e.printStackTrace();
				Log.e("error", e.getMessage().toString());
				return false;
			}

			hospital = new DHospital();
			hospital.serialization(jsonObject);
			m_dHospitals.add(hospital);
		}
		return  true;

	}

	private static DHospitalList s_dHospitalList = new DHospitalList();
	private ArrayList<DHospital> m_dHospitals = new ArrayList<>();
}
