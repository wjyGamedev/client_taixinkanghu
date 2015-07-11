package com.taixinkanghu.util.manager;

/**
 * Copyright (c) 213Team
 *
 * @author : WangJY
 * @version : 1.0.0
 * @className : util.manager.${type_name}
 * @description : ${管理器基类。}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */
public interface IManager<T, V>
{
	public void init();
	public void clear();

	public boolean add(final V inInstance);
	public boolean remove(final V inInstance);
	public V get(final T inType);
}
