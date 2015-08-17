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

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DNurseBasicsList
{
	private ArrayList<DNurseBasics>        m_nurseBasicses       = new ArrayList<>();
	private int                         m_Status          = DataConfig.S_HTTP_OK;

	public boolean serialization(JSONObject response) throws JSONException
	{
		//01. clear up
		if (m_nurseBasicses != null && m_nurseBasicses.isEmpty() == false)
		{
			m_nurseBasicses.clear();
		}

		//02. http is ok
		m_Status = response.getInt(DataConfig.STATUS_KEY);

		if (!LogicalUtil.IsHttpSuccess(m_Status))
		{
			String errorMsg = response.getString(DataConfig.ERROR_MSG);
			throw new JsonSerializationException(errorMsg);
		}

		//03. 序列化json
		JSONArray jsonArray = response.getJSONArray(DataConfig.NURSE_BASICS_LIST);

		if (jsonArray == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + DataConfig.NURSE_BASICS_LIST);
		}

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

	public ArrayList<DNurseBasics> getNurseBasicses()
	{
		return m_nurseBasicses;
	}

	public int getStatus()
	{
		return m_Status;
	}
}
