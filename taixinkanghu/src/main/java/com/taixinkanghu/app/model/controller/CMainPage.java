/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.controller.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/15		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.controller;

import com.taixinkanghu.app.model.data.DMainPage;

public class CMainPage
{

	private CMainPage()
	{
		init();
	}

	private void init()
	{
		m_dMainPage = new DMainPage();
	}

	public DMainPage getMainPage()
	{
		return m_dMainPage;
	}

	public static CMainPage getInstance()
	{
		return s_cMainPage;
	}

	/**
	 * 数据区
	 */
	private static CMainPage s_cMainPage = new CMainPage();
	private        DMainPage m_dMainPage = null;
}
