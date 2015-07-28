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

package com.taixinkanghu.app.ui.listener.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;

public class HandlerClickEventNursingOrder extends BaseHandleOnClickEvent
{
	public HandlerClickEventNursingOrder(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		m_context.startActivity(new Intent(activity, SelectNurseActivity.class));
	}

}
