/**
 * Copyright (c) 213Team
 *
 * @className : net.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/9		WangJY		1.0.0		create
 */

package com.taixinkanghu.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.taixinkanghu.app.model.exception.RuntimeExceptions.NetRTException;

public class BaseHttp
{
	private BaseHttp()
	{
	}

	public static void init(Context context)
	{
		m_RequestQueue = Volley.newRequestQueue(context);
	}

	public static BaseHttp getInstance()
	{
		return s_baseHttp;
	}

	public static RequestQueue getRequestQueue()
	{
		if (m_RequestQueue != null)
		{
			return m_RequestQueue;
		}
		else
		{
			throw new NetRTException("RequestQueue not initialized");
		}
	}

	/**
	 * 数据区
	 */
	private static BaseHttp s_baseHttp = new BaseHttp();
	private static RequestQueue m_RequestQueue;

}
