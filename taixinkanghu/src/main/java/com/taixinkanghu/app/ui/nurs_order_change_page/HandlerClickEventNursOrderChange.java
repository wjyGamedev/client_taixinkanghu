package com.taixinkanghu.app.ui.nurs_order_change_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

/**
 * Created by Administrator on 2015/7/24.
 */
public class HandlerClickEventNursOrderChange extends BaseHandleOnClickEvent
{
	public HandlerClickEventNursOrderChange(Activity activity)
	{
		super(activity);
	}


	@Override
	public void onClick(View v)
	{
		NursOrderChangeActivity activity = (NursOrderChangeActivity)m_context;
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_bottom:                //点击支付按钮
			{
				activity.startActivity(activity.getToPayIntent());
				break;
			}
		}
	}
}