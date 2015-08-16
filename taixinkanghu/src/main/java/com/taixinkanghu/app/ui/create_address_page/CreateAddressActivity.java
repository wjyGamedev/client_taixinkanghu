package com.taixinkanghu.app.ui.create_address_page;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/8/16.
 */
public class CreateAddressActivity extends Activity
{
	private TextView    m_pageTitleTextView;
	private ImageButton m_backBtn;
	private Button      m_bottomBtn;

	private HandlerClickEventCreateAddress m_handlerClickEventCreateAddress = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_address);

		init();
		initModule();
		initListener();
	}

	private void init()
	{
		m_pageTitleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_bottomBtn = (Button)findViewById(R.id.btn_bottom);
		//		m_addressBtn = (LinearLayout)findViewById(R.id.address_region);

		m_handlerClickEventCreateAddress = new HandlerClickEventCreateAddress(this);

	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventCreateAddress);
		m_bottomBtn.setOnClickListener(m_handlerClickEventCreateAddress);
	}

	private void initModule()
	{
		m_pageTitleTextView.setText(R.string.new_address_title);
		m_bottomBtn.setText(R.string.save_btn_text);
	}

}
