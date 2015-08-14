package com.taixinkanghu.app.ui.nurs_order_change_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.nurs_order_pay_page.NursOrderPayActivity;

/**
 * Created by Administrator on 2015/8/14.
 */
public class NursOrderChangeActivity extends Activity
{
	private TextView    m_pageTitleTextView;
	private ImageButton m_backBtn;

	private Button m_bottomBtn;

	private Intent m_toPayIntent;

	private HandlerClickEventNursOrderChange m_handlerClickEventNursOrderChange = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurs_order_change);

		init();
		initModule();
		initListener();
	}

	private void init()
	{
		m_pageTitleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_bottomBtn = (Button)findViewById(R.id.btn_bottom);

		m_handlerClickEventNursOrderChange = new HandlerClickEventNursOrderChange(this);
		m_toPayIntent = new Intent(NursOrderChangeActivity.this, NursOrderPayActivity.class);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventNursOrderChange);
		m_bottomBtn.setOnClickListener(m_handlerClickEventNursOrderChange);
	}

	private void initModule()
	{
		m_pageTitleTextView.setText(R.string.nurs_order_change_title);
		m_bottomBtn.setText(R.string.determine_pay_title);
	}

	public Intent getToPayIntent()
	{
		return m_toPayIntent;
	}
}
