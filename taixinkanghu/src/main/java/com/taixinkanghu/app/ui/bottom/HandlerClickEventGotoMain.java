/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.bottom.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.bottom;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.main_page.MainActivity;

public class HandlerClickEventGotoMain extends BaseHandleOnClickEvent
{

	public HandlerClickEventGotoMain(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		activity.finish();
		activity.startActivity(new Intent(m_context, MainActivity.class));
	}

}
