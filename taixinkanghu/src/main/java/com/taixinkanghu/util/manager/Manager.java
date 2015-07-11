/**
 * Copyright (c) 213Team
 *
 * @className : util.manager.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO：统计信息。}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.util.manager;

import com.taixinkanghu.app.model.exception.RuntimeExceptions.ManagerRTException;

public class Manager<T, V> implements IManager<T, V>
{
	@Override
	public void init()
	{
		throw new ManagerRTException("please implement init");
	}

	@Override
	public void clear()
	{
		throw new ManagerRTException("please implement init");
	}

	@Override
	public boolean add(final V inInstance)
	{
		throw new ManagerRTException("please implement add");
	}

	@Override
	public boolean remove(final V inInstance)
	{
		throw new ManagerRTException("please implement remove");
	}

	@Override
	public V get(final T inType)
	{
		throw new ManagerRTException("please implement get");
	}
}

