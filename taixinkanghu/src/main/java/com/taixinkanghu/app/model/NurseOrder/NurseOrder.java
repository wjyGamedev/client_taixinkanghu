/**
 * Copyright (c) 213Team
 *
 * @className : app.model.NurseOrder.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO:陪护订单}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.NurseOrder;

public class NurseOrder
{

	/**
	 * 信息的序列化和反序列化
	 */

	/**
	 * 成员变量get/set
	 */
	public String getStrID()
	{
		return m_strID;
	}

	public void setStrID(String strID)
	{
		m_strID = strID;
	}

	/**
	 * 陪护账单基本信息
	 */
	private String m_strID = null;
}
