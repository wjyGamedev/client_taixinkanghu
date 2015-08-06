package com.taixinkanghu.app.ui.goods_info_page;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

/**
 * Created by Administrator on 2015/7/24.
 */
public class HandlerClickEventGoodsInfo extends BaseHandleOnClickEvent
{
	public HandlerClickEventGoodsInfo(Activity activity)
	{
		super(activity);
	}


	@Override
	public void onClick(View v)
	{
		GoodsInfoActivity activity = (GoodsInfoActivity)m_context;
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_goto_main:				//点击首页按钮
			{
				activity.startActivity(new Intent(activity.getToMainIntent()));
				break;
			}
			case R.id.btn_shopping_cart:			//点击购物车按钮
			{
//				activity.startActivity(new Intent(activity.getToMainIntent()));
				break;
			}
			case R.id.btn_join_shopping_cart:		//点击加入购物车按钮
			{
//				activity.startActivity(new Intent(activity.getToMainIntent()));
				break;
			}
			case R.id.receive_volume_region:		//点击领卷按钮
			{
//				activity.startActivity(new Intent(activity.getToMainIntent()));
				break;
			}
			case R.id.comment_region:				//点击评价按钮
			{
//				activity.startActivity(new Intent(activity.getToMainIntent()));
				break;
			}
		}
	}
}