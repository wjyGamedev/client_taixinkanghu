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

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.util.android.AppUtil;

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

	public boolean serialization(JSONObject response) throws JSONException
	{
		if (m_dHospitalHashMap != null &&
				m_dHospitalHashMap.size() != 0)
		{
			m_dHospitalHashMap.clear();
		}

		JSONArray jsonArray = response.getJSONArray(DataConfig.DHOSPITAL_LIST);
		if (jsonArray == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + "hospital_list");
		}

		JSONObject jsonObject = null;
		DHospital hospital = null;
		for (int index = 0; index < jsonArray.length(); index++)
		{
			jsonObject=(JSONObject)jsonArray.get(index);
			hospital = new DHospital();
			hospital.serialization(jsonObject);

			Integer iHospitalID = jsonObject.getInt(DataConfig.DHOSPITAL_ID);
			m_dHospitalHashMap.put(iHospitalID, hospital);
		}
		return  true;

	}

	private static DHospitalList           s_dHospitalList    = new DHospitalList();
	private        HashMap<Integer, DHospital> m_dHospitalHashMap = new HashMap<Integer, DHospital>();
}
