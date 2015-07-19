/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.listener.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.listener;

import android.content.Context;
import android.view.View;

import com.taixinkanghu.app.model.exception.RuntimeExceptions.HandlerRTException;

public class BaseHandleOnClickEvent extends IViewOnClickListener
{
	public BaseHandleOnClickEvent(Context context)
	{
		m_context = context;
	}

	@Override
	public void onClick(View v)
	{
		throw new HandlerRTException("extends BaseHandleOnClickEvent");
	}

	protected Context m_context = null;
}
