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

package com.taixinkanghu.app.model.data.net;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.config.NurseSeniorListConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class DNurseSeniorList
{
	private int                     m_Status       = ProtocalConfig.HTTP_OK;
	private ArrayList<DNurseSenior> m_nurseSeniors = new ArrayList<>();

	public synchronized boolean serialization(JSONObject response) throws JSONException, ParseException
	{
		//01. clear up
		if (m_nurseSeniors != null && m_nurseSeniors.size() != 0)
		{
			m_nurseSeniors.clear();
		}

		//02. http is ok
		m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

		if (!LogicalUtil.IsHttpSuccess(m_Status))
		{
			String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
			throw new JsonSerializationException(errorMsg);
		}

		//03. 序列化json
		JSONArray jsonArray = response.getJSONArray(NurseSeniorListConfig.LIST);

		if (jsonArray == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + NurseSeniorListConfig.LIST);
		}

		JSONObject   jsonObject   = null;
		DNurseSenior dNurseSenior = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{
			jsonObject = (JSONObject)jsonArray.get(index);
			dNurseSenior = new DNurseSenior();
			dNurseSenior.serialization(jsonObject);

			m_nurseSeniors.add(dNurseSenior);
		}
		return  true;

	}

	public synchronized ArrayList<DNurseSenior> getNurseSeniors()
	{
		return m_nurseSeniors;
	}

	public synchronized DNurseSenior getNurseSeniorByID(int id)
	{
		if (m_nurseSeniors == null)
			return null;

		for (DNurseSenior nurseSenior : m_nurseSeniors)
		{
			if (nurseSenior == null)
				continue;

			if (nurseSenior.getID() == id)
				return nurseSenior;
		}

		return null;
	}


	public synchronized int getStatus()
	{
		return m_Status;
	}
}
