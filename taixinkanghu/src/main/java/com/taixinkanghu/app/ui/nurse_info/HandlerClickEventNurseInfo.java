/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_info.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_info;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.nurs_order_confirm_page.OrderConfirmActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.main_page.MainActivity;

public class HandlerClickEventNurseInfo  extends BaseHandleOnClickEvent
{
	public HandlerClickEventNurseInfo(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch(v.getId())
		{
			case R.id.btn_goto_main:
			{
				activity.startActivity(new Intent(activity, MainActivity.class));
			}
			break;
			case R.id.btn_select:
			{
				activity.startActivity(new Intent(activity, OrderConfirmActivity.class ));
			}
			break;
			default:
			{
				//TODO:ERROR
			}
			return;
		}

		return;
	}
}
