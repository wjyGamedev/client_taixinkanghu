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
 * 2015/7/15		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

public class DMainPage
{

	public String getCurrentTabTag()
	{
		return m_strCurrTabTag;
	}
	public String getLastTabTag() { return m_strLastTabTag; }
	public void setCurrentTabTag(final String tabTag)
	{
		if (m_strLastTabTag != m_strCurrTabTag)
		{
			m_strLastTabTag = m_strCurrTabTag;
		}
		m_strCurrTabTag = tabTag;
		if (m_strLastTabTag == null)
		{
			m_strLastTabTag = m_strCurrTabTag;
		}
	}
	/**
	 * 数据区
	 */
	private String m_strCurrTabTag = null;
	private String m_strLastTabTag = null;
}
