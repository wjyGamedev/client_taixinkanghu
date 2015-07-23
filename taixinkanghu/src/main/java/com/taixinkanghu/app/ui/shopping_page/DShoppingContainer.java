package com.taixinkanghu.app.ui.shopping_page;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/22.
 */
public class DShoppingContainer
{
	private DShoppingContainer()
	{
		initDataTest();
	}

	private void initDataTest()
	{

	}

	public static DShoppingContainer GetInstance()
	{
		return s_dShoppingList;
	}

	public DShoppingBasicsList GetShoppingBaisicsList()
	{
		return m_dShoppingBasicsList;
	}


	public boolean serialBasiclist(JSONObject response)
	{
		return m_dShoppingBasicsList.serialization(response);
	}

	public boolean serialSeniorList(JSONObject response)
	{
		return m_dShoppingBasicsList.serialization(response);
	}

	/**
	 * 数据区
	 */
	private        DShoppingBasicsList m_dShoppingBasicsList = new DShoppingBasicsList();
	private static DShoppingContainer  s_dShoppingList       = new DShoppingContainer();

}
