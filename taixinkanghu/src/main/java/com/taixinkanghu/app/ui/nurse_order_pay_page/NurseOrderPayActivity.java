package com.taixinkanghu.app.ui.nurse_order_pay_page;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/8/3.
 */
public class NurseOrderPayActivity extends Activity
{
	private ImageButton m_backBtn;
	private TextView    m_titleTextView;
	private Button      m_bottomBtn;

	private HandlerClickEventNursOrderPay m_handlerClickEventNursOrderPay = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_order_pay);
		init();
		initListener();
		initContent();
	}

	private void init()
	{
		m_titleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_bottomBtn = (Button)findViewById(R.id.btn_bottom);

		m_handlerClickEventNursOrderPay = new HandlerClickEventNursOrderPay(this);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
		m_bottomBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
	}

	private void initContent()
	{
		m_titleTextView.setText(R.string.nursing_order_pay_title);
		m_bottomBtn.setText(R.string.determine_pay_title);
	}
}
