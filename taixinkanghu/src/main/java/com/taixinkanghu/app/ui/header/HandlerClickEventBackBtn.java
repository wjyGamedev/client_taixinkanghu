/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.header.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.header;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

public class HandlerClickEventBackBtn extends BaseHandleOnClickEvent
{
	public HandlerClickEventBackBtn(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		activity.finish();
	}
}
