/**
 * Copyright (c) 213Team
 *
 * @className : app.model.account.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO:账号信息封装类。手机号码，登录凭证。}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.account;

import com.taixinkanghu.app.model.imodel.IDataModel;

public class Account implements IDataModel
{
	/**
	 * 信息的序列化和反序列化
	 */



	/**
	 * 成员变量get/set
	 */

	public String getStrMobileNum()
	{
		return m_strMobileNum;
	}

	public void setStrMobileNum(String strMobileNum)
	{
		m_strMobileNum = strMobileNum;
	}

	public String getStrToken()
	{
		return m_strToken;
	}

	public void setStrToken(String strToken)
	{
		m_strToken = strToken;
	}

	public String getStrUID()
	{

		return m_strUID;
	}

	public void setStrUID(String strUID)
	{
		m_strUID = strUID;
	}


	/**
	 * 账号基本信息。
	 */
	private String m_strMobileNum;
	private String m_strToken;
	private String m_strUID;


	/**
	 * 来自于android system accountmanagerservice的info。
	 */

}
