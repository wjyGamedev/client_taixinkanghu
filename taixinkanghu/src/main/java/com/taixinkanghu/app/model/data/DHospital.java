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

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DHospital
{
	private int    m_ID   = 0;
	private String m_name = null;

	public boolean serialization(JSONObject response) throws JSONException
	{
		m_ID = response.getInt(DataConfig.DHOSPITAL_ID);
		m_name = response.getString(DataConfig.DHOSPITAL_NAME);
		return true;
	}

	public int getID()
	{
		return m_ID;
	}

	public String getName()
	{
		return m_name;
	}


}
