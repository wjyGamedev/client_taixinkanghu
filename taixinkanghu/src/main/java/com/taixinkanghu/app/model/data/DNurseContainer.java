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

import org.json.JSONException;
import org.json.JSONObject;

public class DNurseContainer
{
	private DNurseContainer()
	{
		initDataTest();
	}

	private void initDataTest()
	{

	}

	public static DNurseContainer GetInstance()
	{
		return s_dNurseList;
	}

	public boolean serialBasiclist(JSONObject response) throws JSONException
	{
		return m_dNurseBasicsList.serialization(response);
	}

	public boolean serialSeniorList(JSONObject response)
	{
		return m_dNurseSeniorList.serialization(response);
	}

	public DNurseBasicsList GetNurseBaisicsList()
	{
		return m_dNurseBasicsList;
	}

	/**
	 * 数据区
	 */
	private static DNurseContainer          s_dNurseList       = new DNurseContainer();

	private        DNurseBasicsList         m_dNurseBasicsList = new DNurseBasicsList();
	private        DNurseSeniorList         m_dNurseSeniorList = new DNurseSeniorList();


}
