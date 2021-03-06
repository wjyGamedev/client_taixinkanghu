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

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.app.ui.shopping_page.ShoppingActivity;

public class HandlerClickEventShopping extends BaseHandleOnClickEvent
{
	public HandlerClickEventShopping(Context context)
	{
		super(context);
	}

	@Override
	public void onClick(View v)
	{
//		Toast.makeText(m_context, R.string.function_is_not_open, Toast.LENGTH_SHORT).show();
		m_context.startActivity(new Intent(m_context, ShoppingActivity.class));
	}
}
