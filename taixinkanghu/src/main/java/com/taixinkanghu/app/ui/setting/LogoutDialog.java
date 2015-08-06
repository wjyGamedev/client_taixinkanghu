/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.dialog.register_page_dialog.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.taixinkanghu.R;

import de.greenrobot.event.EventBus;

public class LogoutDialog
{
	private static LogoutDialog s_logoutDialog = new LogoutDialog();

	private AlertDialog.Builder m_builder = null;
	private String m_msg;
	private DetermineClickListener m_determineClickListener = new DetermineClickListener();

	private LogoutDialog()
	{
	}

	public static LogoutDialog GetInstance()
	{
		return s_logoutDialog;
	}

	public void setMsg(String msg, Context context)
	{
		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			Activity activity = (Activity)context;
			m_builder.setPositiveButton(context.getResources().getString(R.string.info_determine), m_determineClickListener);
			m_builder.setNegativeButton(context.getResources().getString(R.string.info_cancel), null);
			m_builder.setMessage(m_msg);
		}
	}

	public void show()
	{
		if (m_builder != null)
		{
			m_builder.show();
		}
	}

	class DetermineClickListener implements DialogInterface.OnClickListener
	{
		private EventBus m_eventBus = EventBus.getDefault();

		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			LogoutEvent logoutEvent = new LogoutEvent();
			m_eventBus.post(logoutEvent);
		}
	}


}
