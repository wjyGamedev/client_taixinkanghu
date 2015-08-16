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

package com.taixinkanghu.app.ui.address_management_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

public class HandlerClickEventAddressManagerment extends BaseHandleOnClickEvent
{
	public HandlerClickEventAddressManagerment(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		AddressManagementAcitvity activity = (AddressManagementAcitvity)m_context;
		switch(v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_bottom:
			{
				activity.startActivity(activity.getToCreateAddressIntent());
				break;
			}
			default:
			{
				//TODO:ERROR
			}
			return;
		}

		return;
	}
}
