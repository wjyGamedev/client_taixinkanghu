package com.taixinkanghu.app.ui.shopping_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

/**
 * Created by Administrator on 2015/7/22.
 */
public class HandlerClickEventShopping extends BaseHandleOnClickEvent
{
	public HandlerClickEventShopping(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch (v.getId())
		{
		case R.id.btn_back:
		{
			activity.finish();
			break;
		}
		case R.id.btn_goto_main:
		{
			activity.finish();
			break;
		}
		}
	}
}