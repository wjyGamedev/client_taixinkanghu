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

import org.json.JSONException;
import org.json.JSONObject;

public class DCommentList
{
	private int m_ID = 0;	//ID

	public boolean serialization(JSONObject response) throws JSONException
	{
		return true;
	}

	public void init(int iID)
	{
		m_ID = iID;
	}

	public int getID()
	{
		return m_ID;
	}

}
