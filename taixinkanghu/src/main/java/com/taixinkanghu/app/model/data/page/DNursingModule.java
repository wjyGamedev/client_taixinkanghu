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
 * 2015/8/21		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.page;

public class DNursingModule
{
	private static DNursingModule m_nursingModule = new DNursingModule();

	private DApoitNursingPage m_apoitNursingPage = new DApoitNursingPage();


	private DNursingModule()
	{
	}

	public static DNursingModule GetInstance()
	{
		return m_nursingModule;
	}

}