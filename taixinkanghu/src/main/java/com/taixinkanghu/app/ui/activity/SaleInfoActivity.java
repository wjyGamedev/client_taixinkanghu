package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.taixinkanghu.R;


public class SaleInfoActivity extends Activity implements View.OnClickListener
{

	private ImageView   m_saleInfoImg;
	private TextView    m_contentTitleTv;
	private TextView    m_contentRedTitleTv;
	private TextView    m_contentTv;
	private TextView    m_pageTitleTv;
	private ImageButton m_backBtn;
	private Button      m_gotoMainBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_info);

		m_saleInfoImg = (ImageView)findViewById(R.id.img_info);
		m_contentTitleTv = (TextView)findViewById(R.id.info_title);
		m_contentRedTitleTv = (TextView)findViewById(R.id.info_text_red);
		m_contentTv = (TextView)findViewById(R.id.info_content);
		m_pageTitleTv = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_gotoMainBtn = (Button)findViewById(R.id.btn_goto_main);

		m_saleInfoImg.setBackgroundResource(R.drawable.img_promotions);

		m_contentTitleTv.setText(getResources().getString(R.string.company_black_text));
		m_pageTitleTv.setText(getResources().getString(R.string.company_show_title));
		m_contentRedTitleTv.setText(getResources().getString(R.string.company_show_red_text));
		m_contentTv.setText(getResources().getString(R.string.company_show_content_text));

		m_backBtn.setOnClickListener(this);
		m_gotoMainBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				finish();
				break;
			}
			case R.id.btn_goto_main:
			{
				finish();
				break;
			}
		}
	}
}
