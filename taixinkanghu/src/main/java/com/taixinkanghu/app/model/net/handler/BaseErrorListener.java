/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.exception.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import android.content.Context;

import com.android.volley.VolleyError;
import com.taixinkanghu.app.model.net.IErrorListener;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class BaseErrorListener extends IErrorListener
{
	public BaseErrorListener(Context context)
	{
		m_context = context;
	}

	@Override
	public void onErrorResponse(VolleyError error)
	{
		RegisterDialog.GetInstance().setMsg(error.getMessage().toString());
		RegisterDialog.GetInstance().show();
	}

	private Context m_context = null;
}
