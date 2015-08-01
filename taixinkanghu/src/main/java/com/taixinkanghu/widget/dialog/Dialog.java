/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.dialog.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;

public class Dialog
{
	private AlertDialog.Builder m_builder = null;

	public void init(Context context)
	{
		m_builder = new AlertDialog.Builder(context);
	}
	public AlertDialog.Builder getBuilder()
	{
		return m_builder;
	}
}
