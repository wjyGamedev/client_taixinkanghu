package com.taixinkanghu.app.ui.nurs_order_pay_page;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.main_page.MainActivity;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventNursOrderPay extends BaseHandleOnClickEvent
{
	public HandlerClickEventNursOrderPay(Activity activity)
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
			case R.id.btn_bottom:
			{
				new AlertDialog.Builder(activity).setTitle("支付提示").setMessage("确认支付？").setPositiveButton("确定",
																										 new DialogInterface
																												 .OnClickListener()
																										 {
																											 public void onClick
																													 (DialogInterface
																															  dialog, int
																															  which)
																											 {

																												 Intent intent = new
																														 Intent(
																														 m_context,
																														 MainActivity.class
																												 );
																												 Toast.makeText(m_context
																																		.getApplicationContext(),
																																"支付成功",
																																Toast
																																		.LENGTH_SHORT
																															   ).show();
																												 m_context.startActivity(
																														 intent
																																		);
																											 }
																										 }
																										).setNegativeButton("取消", null
																														   ).show();
				break;
			}
		}
	}
}