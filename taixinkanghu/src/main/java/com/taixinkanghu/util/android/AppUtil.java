/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.util.android.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.util.android;

import android.content.Context;
import android.content.res.Resources;

import com.taixinkanghu.app.model.exception.RuntimeExceptions.UtilRTException;

public class AppUtil
{

	public static void init(Context context)
	{
		m_context = context;
	}

	public static Resources getResources()
	{
		if (m_context == null)
		{
			throw new UtilRTException("m_context == null");
		}
		return  m_context.getResources();
	}

	private static Context m_context = null;
}
