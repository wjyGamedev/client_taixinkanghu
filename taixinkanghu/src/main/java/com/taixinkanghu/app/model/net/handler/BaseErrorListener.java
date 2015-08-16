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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.IErrorListener;

public class BaseErrorListener extends IErrorListener
{
	public BaseErrorListener(Context context)
	{
		m_context = context;
	}

	@Override
	public void onErrorResponse(VolleyError error)
	{
		//TODO:error
		Toast.makeText(m_context, R.string.error_disconnect, Toast.LENGTH_SHORT).show();
	}

	private Context m_context = null;
}
