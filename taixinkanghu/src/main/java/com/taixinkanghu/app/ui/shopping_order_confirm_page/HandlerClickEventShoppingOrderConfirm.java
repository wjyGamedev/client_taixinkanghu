package com.taixinkanghu.app.ui.shopping_order_confirm_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

/**
 * Created by Administrator on 2015/7/24.
 */
public class HandlerClickEventShoppingOrderConfirm extends BaseHandleOnClickEvent
{
	public HandlerClickEventShoppingOrderConfirm(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		ShoppingOrderConfirmActivity activity = (ShoppingOrderConfirmActivity)m_context;
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_payment:                //点击付款按钮
			{
				activity.startActivity(activity.getToPaymentIntent());
				break;
			}
		}
	}
}