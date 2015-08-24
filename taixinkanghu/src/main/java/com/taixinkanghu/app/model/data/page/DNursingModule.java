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

	private DApoitNursingPage m_apoitNursingPage      = new DApoitNursingPage();
//	private Object m_syncDApoitNursingPage = new Object();

	private DNurseOrderConfirmPage m_nurseOrderConfirmPage = new DNurseOrderConfirmPage();
//	private Object m_syncDNurseOrderConfirmPage = new Object();

	private DNurseOrderPayPage m_nurseOrderPayPage = new DNurseOrderPayPage();
//	private Object m_syncDNurseOrderPayPage = new Object();




	private DNursingModule()
	{
	}

	public static DNursingModule GetInstance()
	{
		return m_nursingModule;
	}

	public DApoitNursingPage getApoitNursingPage()
	{
		return m_apoitNursingPage;
	}

	public DNurseOrderConfirmPage getNurseOrderConfirmPage()
	{
		return m_nurseOrderConfirmPage;
	}

	public DNurseOrderPayPage getNurseOrderPayPage()
	{
		return m_nurseOrderPayPage;
	}

	public void clearup()
	{
		if (m_apoitNursingPage != null)
		{
			m_apoitNursingPage.clearup();
		}

		if (m_nurseOrderConfirmPage != null)
		{
			m_nurseOrderConfirmPage.clearup();
		}

		if (m_nurseOrderPayPage != null)
		{
			m_nurseOrderPayPage.clearup();
		}
	}

}
