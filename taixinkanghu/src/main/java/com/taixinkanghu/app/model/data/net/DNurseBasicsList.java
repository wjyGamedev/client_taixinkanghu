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
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.config.ProtocalConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DNurseBasicsList
{
	private int                     m_Status        = ProtocalConfig.HTTP_OK;
	private ArrayList<DNurseBasics> m_nurseBasicses = new ArrayList<>();

	public synchronized boolean serialization(JSONObject response) throws JSONException
	{
		//01. clear up
		if (m_nurseBasicses != null && m_nurseBasicses.isEmpty() == false)
		{
			m_nurseBasicses.clear();
		}

		//02. http is ok
		m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);

		if (!LogicalUtil.IsHttpSuccess(m_Status))
		{
			String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
			throw new JsonSerializationException(errorMsg);
		}

		//03. 序列化json
		JSONArray jsonArray = response.getJSONArray(NurseBasicListConfig.LIST);

		if (jsonArray == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + NurseBasicListConfig.LIST);
		}

		JSONObject   jsonObject   = null;
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

	public synchronized ArrayList<DNurseBasics> getNurseBasicses()
	{
		return m_nurseBasicses;
	}

	public synchronized DNurseBasics getNurseBasicByID(int id)
	{
		if (m_nurseBasicses == null)
			return null;

		for (DNurseBasics nurseBasics : m_nurseBasicses)
		{
			if (nurseBasics == null)
				continue;

			if (nurseBasics.getID() == id)
			{
				return nurseBasics;
			}
		}

		return null;
	}

}
