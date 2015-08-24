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
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.dialog.register_page_dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.page.DGlobal;

public class RegisterDialog
{
	private static RegisterDialog s_registerDialog = new RegisterDialog();

	private AlertDialog.Builder m_builder = null;
	private String m_msg;

	private RegisterDialog()
	{
	}

	public static RegisterDialog GetInstance()
	{
		return s_registerDialog;
	}

	public void setMsg(String msg, Context context)
	{
		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			m_builder.setPositiveButton(context.getResources().getString(R.string.info_i_known), null);
			m_builder.setMessage(m_msg);
		}
	}

	public void setMsg(String msg)
	{
		Context context = DGlobal.GetInstance().getContext();
		if (context == null)
			return;

		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			m_builder.setPositiveButton(context.getResources().getString(R.string.info_i_known), null);
			m_builder.setMessage(m_msg);
		}
	}

	public void setMsg(String msg, Context context, DialogInterface.OnClickListener listener)
	{
		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			m_builder.setPositiveButton(context.getResources().getString(R.string.info_i_known), listener);
			m_builder.setMessage(m_msg);
		}
	}

	public void setMsg(String msg, DialogInterface.OnClickListener listener)
	{
		Context context = DGlobal.GetInstance().getContext();
		if (context == null)
			return;

		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			m_builder.setPositiveButton(context.getResources().getString(R.string.info_i_known), listener);
			m_builder.setMessage(m_msg);
		}
	}

	public void setMsg(String msg, Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener)
	{
		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			m_builder.setPositiveButton(context.getResources().getString(R.string.cancel_btn), confirmListener);
			m_builder.setNegativeButton(context.getResources().getString(R.string.confirm_btn), cancelListener);
			m_builder.setMessage(m_msg);
		}
	}

	public void setMsg(String msg, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener)
	{
		Context context = DGlobal.GetInstance().getContext();
		if (context == null)
			return;

		m_builder = new AlertDialog.Builder(context);
		m_msg = msg;
		if (m_builder != null)
		{
			m_builder.setPositiveButton(context.getResources().getString(R.string.cancel_btn), confirmListener);
			m_builder.setNegativeButton(context.getResources().getString(R.string.confirm_btn), cancelListener);
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

}
