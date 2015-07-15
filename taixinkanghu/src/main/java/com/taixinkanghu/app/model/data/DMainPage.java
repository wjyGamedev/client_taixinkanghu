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
		return  m_stringTabTag;
	}
	public void setCurrentTabTag(final String tabTag)
	{
		m_stringTabTag = tabTag;
	}
	/**
	 * 数据区
	 */
	private String m_stringTabTag = null;
}
