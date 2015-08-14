package com.taixinkanghu.app.ui.shopping_order_confirm_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.nurs_order_pay_page.NursOrderPayActivity;

/**
 * Created by Administrator on 2015/8/14.
 */
public class ShoppingOrderConfirmActivity extends Activity
{
	private TextView    m_pageTitleTextView;
	private ImageButton m_backBtn;

	private LinearLayout m_paymentBtn;

	private Intent m_toPaymentIntent;

	private HandlerClickEventShoppingOrderConfirm m_handlerClickEventShoppingOrderConfirm = null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_order_shopping);

		init();
		initModule();
		initListener();
	}

	private void init()
	{
		m_pageTitleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_paymentBtn = (LinearLayout)findViewById(R.id.btn_payment);

		m_handlerClickEventShoppingOrderConfirm = new HandlerClickEventShoppingOrderConfirm(this);

		m_toPaymentIntent = new Intent(ShoppingOrderConfirmActivity.this, NursOrderPayActivity.class);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventShoppingOrderConfirm);
		m_paymentBtn.setOnClickListener(m_handlerClickEventShoppingOrderConfirm);
	}

	private void initModule()
	{
		m_pageTitleTextView.setText(R.string.shopping_order_confirm_title);
	}

	public Intent getToPaymentIntent()
	{
		return m_toPaymentIntent;
	}
}
