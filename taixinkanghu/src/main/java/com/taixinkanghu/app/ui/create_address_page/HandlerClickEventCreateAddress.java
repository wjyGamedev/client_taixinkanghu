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

package com.taixinkanghu.app.ui.create_address_page;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

public class HandlerClickEventCreateAddress extends BaseHandleOnClickEvent
{
	public HandlerClickEventCreateAddress(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		CreateAddressActivity activity = (CreateAddressActivity)m_context;
		switch(v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_bottom:
			{
				activity.finish();
				Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
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
