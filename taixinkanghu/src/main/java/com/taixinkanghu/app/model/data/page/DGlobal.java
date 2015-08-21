/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.page;

import android.content.Context;

public class DGlobal
{
	private static DGlobal s_dGlobal = new DGlobal();

	private Context m_context        = null;
	private Object  m_syncContentObj = new Object();


	private DGlobal()
	{}

	public static DGlobal GetInstance()
	{
		return s_dGlobal;
	}

	public Context getContext()
	{
		synchronized (m_syncContentObj)
		{
			return m_context;
		}
	}

	public void setContext(Context context)
	{
		synchronized (m_syncContentObj)
		{
			m_context = context;
		}
	}

	public void clearupContext(Context context)
	{
		synchronized (m_syncContentObj)
		{
			if (m_context == context)
			{
				m_context = null;
			}
		}
	}

}
