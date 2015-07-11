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

import org.json.JSONException;
import org.json.JSONObject;

public class DHospital
{

	public boolean serialization(JSONObject response)
	{
		try
		{
			m_iID = response.getInt(DataConfig.DHOSPITAL_ID);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return  false;
		}
		try
		{
			m_strName = response.getString(DataConfig.DHOSPITAL_NAME);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return  false;
		}
		return  true;
	}

	public int getiID()
	{
		return m_iID;
	}

	public String getStrName()
	{
		return m_strName;
	}

	private int    m_iID     = 0;
	private String m_strName = "default";
}
