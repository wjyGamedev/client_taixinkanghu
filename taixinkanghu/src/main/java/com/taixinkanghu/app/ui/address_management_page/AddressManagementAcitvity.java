package com.taixinkanghu.app.ui.address_management_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.create_address_page.CreateAddressActivity;

/**
 * Created by Administrator on 2015/8/16.
 */
public class AddressManagementAcitvity extends Activity
{
	private TextView    m_pageTitleTextView;
	private ImageButton m_backBtn;
	private Button      m_bottomBtn;

	private Intent m_toCreateAddressIntent;

	private HandlerClickEventAddressManagerment m_handlerClickEventAddressManagerment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_management);

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

		m_handlerClickEventAddressManagerment = new HandlerClickEventAddressManagerment(this);

		m_toCreateAddressIntent = new Intent(AddressManagementAcitvity.this, CreateAddressActivity.class);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventAddressManagerment);
		m_bottomBtn.setOnClickListener(m_handlerClickEventAddressManagerment);
	}

	private void initModule()
	{
		m_pageTitleTextView.setText(R.string.address_management_title);
		m_bottomBtn.setText(R.string.new_address_btn_text);
	}

	public Intent getToCreateAddressIntent()
	{
		return m_toCreateAddressIntent;
	}

}
