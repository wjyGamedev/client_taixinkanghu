package com.taixinkanghu.app.ui.shopping_page;

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/7/22.
 */
public class DShoppingBasicsList
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
			jsonArray = response.getJSONArray(DataConfig.GOODS_BASICS_LIST);

			if (jsonArray == null)
				return false;

			JSONObject jsonObject = null;
			DShoppingBasics dShoppingBasics = null;
			for (int index = 0; index < jsonArray.length(); index++)
			{
				jsonObject=(JSONObject)jsonArray.get(index);
				dShoppingBasics = new DShoppingBasics();
				dShoppingBasics.serialization(jsonObject);

				Integer iID = jsonObject.getInt(DataConfig.GOODS_ID);
				m_dNurseBasicsHashMap.put(iID, dShoppingBasics);
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

	public HashMap<Integer, DShoppingBasics> GetNurseBasicsHashMap()
	{
		return m_dNurseBasicsHashMap;
	}

	private HashMap<Integer, DShoppingBasics> m_dNurseBasicsHashMap = new HashMap<Integer, DShoppingBasics>();
}
