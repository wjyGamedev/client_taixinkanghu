package com.taixinkanghu.app.model.NurseOrder;

import java.util.HashMap;

import com.taixinkanghu.util.nursingorder.NurseOrderUtil;
import com.taixinkanghu.util.manager.Manager;

/**
 * Copyright (c) 213Team
 *
 * @author : WangJY
 * @version : 1.0.0
 * @className : app.model.NurseOrder.${type_name}
 * @description : ${TODO：陪护订单管理器}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */
public class NurseOrderManager extends Manager<String, NurseOrder>
{
	private NurseOrderManager()
	{
		init();
	}

	public static NurseOrderManager getInstance()
	{
		return s_nurseOrderManager;
	}



	@Override
	public void init()
	{
		m_nurseOrderHashMap = new HashMap<String, NurseOrder>();
	}

	@Override
	public void clear()
	{
		m_nurseOrderHashMap.clear();
	}

	@Override
	public boolean add(final NurseOrder inInstance)
	{
		final String tmpStrID = inInstance.getStrID();

		if (NurseOrderUtil.isIDValid(tmpStrID) == false)
			return false;

		NurseOrder mapValue = m_nurseOrderHashMap.put(tmpStrID, inInstance);
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
	public boolean remove(final NurseOrder inInstance)
	{
		NurseOrder mapValue = m_nurseOrderHashMap.remove(inInstance.getStrID());
		if (mapValue != null)
		{
			/**
			 * TODO:LOG, 输入陪护订单有误。
			 */
			return false;
		}

		return true;

	}

	public boolean remove(final String inStrID)
	{
		if (NurseOrderUtil.isIDValid(inStrID) == false)
			return false;

		NurseOrder mapValue = m_nurseOrderHashMap.remove(inStrID);
		if (mapValue != null)
		{
			/**
			 * TODO:LOG, 输入陪护订单有误。
			 */
			return false;
		}

		return true;

	}

	@Override
	public NurseOrder get(final String inType)
	{
		if (NurseOrderUtil.isIDValid(inType) == false)
			return null;

		return  m_nurseOrderHashMap.get(inType);
	}


	/**
	 * 数据区
	 */
	private static NurseOrderManager s_nurseOrderManager = new NurseOrderManager();

	private HashMap<String, NurseOrder> m_nurseOrderHashMap = null;
}
