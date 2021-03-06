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
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.net;

import com.taixinkanghu.app.model.net.config.DepartmentListConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class DDepartment implements Serializable
{
	private int    m_ID   = 0;
	private String m_name = null;

	public boolean serialization(JSONObject response) throws JSONException
	{
		m_ID = response.getInt(DepartmentListConfig.ID);
		m_name = response.getString(DepartmentListConfig.NAME);
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
