/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.main_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/27		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.main_page;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.app.ui.activity.SaleInfoActivity;
import com.taixinkanghu.app.ui.company_show.CompanyShowActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

public class HandlerClickEventHomeTabFragment extends BaseHandleOnClickEvent
{
	public HandlerClickEventHomeTabFragment(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch((int)v.getTag())
		{
			case 0:
			{
				activity.startActivity(new Intent(activity, CompanyShowActivity.class ));
			}
			break;
			case 1:
			{
				activity.startActivity(new Intent(activity, SaleInfoActivity.class ));
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
