package com.taixinkanghu.app.ui.nurse_order_pay_page;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

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
		NurseOrderPayActivity nurseOrderPayActivity = (NurseOrderPayActivity)m_context;
		if (nurseOrderPayActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("activity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch (v.getId())
		{
		case R.id.cash_rbtn:
		case R.id.alipay_rbtn:
		case R.id.weixin_rbtn:
		{
			nurseOrderPayActivity.selectedAction();
		}
		return;
		case R.id.btn_back:
		{
			nurseOrderPayActivity.backAction();
		}
		return;
		case R.id.btn_bottom:
		{
			nurseOrderPayActivity.confirmAction();
		}
		}

		return;

	}
}