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

import com.taixinkanghu.app.model.config.EnumConfig;

public class DGlobal
{
	private static DGlobal s_dGlobal = new DGlobal();

	private Context m_context        = null;
	private Object  m_syncContentObj = new Object();

	/**
	 * logical data
	 */
	//同一个页面根据该字段的类型，来处理不同的流程。
	private EnumConfig.NursingModuleStatus m_nursingModuleStatus        = null;
	private Object                         m_syncNursingModuleStatusObj = new Object();

	private DGlobal()
	{}

	public static DGlobal GetInstance()
	{
		return s_dGlobal;
	}

	public void clearupDate()
	{
		m_nursingModuleStatus = null;
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

	public EnumConfig.NursingModuleStatus getNursingModuleStatus()
	{
		synchronized (m_syncNursingModuleStatusObj)
		{
			return m_nursingModuleStatus;
		}
	}

	public void trySetNursingModuleStatus(EnumConfig.NursingModuleStatus nursingModuleStatus)
	{
		synchronized (m_syncNursingModuleStatusObj)
		{
			if (m_nursingModuleStatus == null)
			{
				m_nursingModuleStatus = nursingModuleStatus;
			}
		}
	}

	public void SetNursingModuleStatus(EnumConfig.NursingModuleStatus nursingModuleStatus)
	{
		synchronized (m_syncNursingModuleStatusObj)
		{
			m_nursingModuleStatus = nursingModuleStatus;
		}
	}
}
