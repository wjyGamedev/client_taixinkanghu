package com.taixinkanghu.app.ui.shopping_car_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

/**
 * Created by Administrator on 2015/7/24.
 */
public class HandlerClickEventShoppingCar extends BaseHandleOnClickEvent
{
	public HandlerClickEventShoppingCar(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		ShoppingCarActivity activity = (ShoppingCarActivity)m_context;
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_settlement:                //点击结算按钮
			{
				activity.startActivity(activity.getToShoppingOrderConfirmIntent());
				break;
			}
		}
	}
}