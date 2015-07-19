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

public class DNurseSeniorList
{
	public boolean serialization(JSONObject response)
	{
		if (m_dNurseSeniorHashMap != null &&
				m_dNurseSeniorHashMap.size() != 0)
		{
			m_dNurseSeniorHashMap.clear();
		}

		JSONArray jsonArray = null;
		try
		{
			jsonArray = response.getJSONArray(DataConfig.NURSE_SENIOR_LIST);

			if (jsonArray == null)
				return false;

			JSONObject jsonObject = null;
			DNurseSenior dNurseSenior = null;
			for (int index = 0; index < jsonArray.length(); index++)
			{
				jsonObject=(JSONObject)jsonArray.get(index);
				dNurseSenior = new DNurseSenior();
				dNurseSenior.serialization(jsonObject);

				Integer iID = jsonObject.getInt(DataConfig.NURSE_ID);
				m_dNurseSeniorHashMap.put(iID, dNurseSenior);
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

	private HashMap<Integer, DNurseSenior> m_dNurseSeniorHashMap = new HashMap<Integer, DNurseSenior>();
}
