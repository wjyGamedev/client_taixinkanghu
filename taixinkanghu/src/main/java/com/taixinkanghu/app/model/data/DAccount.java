/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAccount
{
	private static DAccount s_dAccount = new DAccount();

	private String m_loginToken = null;

	private DAccount()
	{

	}

	public static DAccount GetInstance()
	{
		return s_dAccount;
	}

	public boolean serialization(JSONObject response)
	{
		JSONArray jsonArray = null;
		try
		{
			m_loginToken = response.getString(DataConfig.REGISTER_KEY);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return false;
		}

		return  true;

	}

	public String getLoginToken()
	{
		return m_loginToken;
	}
}
