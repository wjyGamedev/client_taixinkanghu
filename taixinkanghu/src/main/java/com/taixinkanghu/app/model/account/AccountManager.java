package com.taixinkanghu.app.model.account;

import com.taixinkanghu.util.account.AccountUtil;
import com.taixinkanghu.util.manager.Manager;

import java.util.HashMap;

/**
 * Copyright (c) 213Team
 *
 * @author : WangJY
 * @version : 1.0.0
 * @className : app.model.account.${AccountManager}
 * @description : ${TODO:账号管理类。单线程。}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/5		WangJY		1.0.0		create
 */

class AccountManager extends Manager<String, Account>
{
	public static AccountManager getInstance()
	{
		return s_accountManager;
	}

	private AccountManager()
	{
		init();
	}

	@Override
	public void init()
	{
		super.init();
		m_accountHashMap = new HashMap<String, Account>();
	}

	@Override
	public void clear()
	{
		super.clear();
		m_accountHashMap.clear();
	}

	@Override
	public boolean add(final Account inAccount)
	{
		final String tmpStrID = inAccount.getStrUID();

		if (AccountUtil.isIDValid(tmpStrID) == false)
		{
			return false;
		}

		Account mapValue = m_accountHashMap.put(tmpStrID, inAccount);
		if (mapValue != null)
		{
			/**
			 * TODO:LOG, ID重复
			 */
			return false;
		}

		return true;

	}

	@Override
	public boolean remove(final Account inAccount)
	{
		Account mapValue = m_accountHashMap.remove(inAccount.getStrUID());
		if (mapValue != null)
		{
			/**
			 * TODO:LOG, 输入账号有误。
			 */
			return false;
		}

		return true;

	}

	public boolean remove(final String inStrID)
	{
		if (AccountUtil.isIDValid(inStrID) == false)
			return false;

		Account mapValue = m_accountHashMap.remove(inStrID);
		if (mapValue != null)
		{
			/**
			 * TODO:LOG, 输入账号有误。
			 */
			return false;
		}

		return true;

	}

	@Override
	public Account get(final String inStrID)
	{
		if (AccountUtil.isIDValid(inStrID) == false)
			return null;

		return  m_accountHashMap.get(inStrID);

	}

	public int Count()
	{
		return  m_accountHashMap.size();
	}

	private static AccountManager s_accountManager = new AccountManager();

	private HashMap<String, Account> m_accountHashMap = null;


}
