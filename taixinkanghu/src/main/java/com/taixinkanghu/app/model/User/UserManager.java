/**
 * Copyright (c) 213Team
 *
 * @className : app.model.account.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${用户信息封装类。}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.User;

import com.taixinkanghu.util.manager.Manager;

import java.util.HashMap;

public class UserManager extends Manager<String, User>
{
	private UserManager()
	{
		init();
	}

	public static UserManager getInstance()
	{
		return s_userManager;
	}

	@Override
	public void init()
	{
		m_UserHashMap = new HashMap<String, User>();
	}

	@Override
	public void clear()
	{
		m_UserHashMap.clear();
	}

	@Override
	public boolean add(User inInstance)
	{
		return super.add(inInstance);
	}

	@Override
	public boolean remove(User inInstance)
	{
		return super.remove(inInstance);
	}

	@Override
	public User get(String inType)
	{
		return super.get(inType);
	}


	/**
	 * 数据区
	 */
	private static UserManager s_userManager = new UserManager();

	private HashMap<String, User> m_UserHashMap = null;

}
