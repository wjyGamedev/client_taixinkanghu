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
 * 2015/7/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import org.json.JSONObject;

public class DNurse
{
	public boolean serialization(JSONObject response)
	{
		if (m_dNurseBasics.serialization(response) == false)
			return false;

		if (m_dNurseSenior.serialization(response) == false)
			return false;

		return true;
	}

	public DNurseBasics getdNurseBasics()
	{
		return m_dNurseBasics;
	}

	public DNurseSenior getdNurseSenior()
	{
		return m_dNurseSenior;
	}

	private DNurseBasics m_dNurseBasics = new DNurseBasics();
	private DNurseSenior m_dNurseSenior = new DNurseSenior();
}
