package com.taixinkanghu.app.ui.nurse_order_pay_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.data.DGlobal;
import com.taixinkanghu.app.model.data.DNurseOrderPay;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

/**
 * Created by Administrator on 2015/8/3.
 */
public class NurseOrderPayActivity extends Activity
{
	private HeaderCommon m_headerCommon     = null;
	private TextView     m_orderSerialNumTV = null;
	private TextView     m_priceTV          = null;
	private RadioButton  m_cashRBtn         = null;
	private RadioButton  m_alipayRBtn       = null;
	private RadioButton  m_weixinRBtn       = null;
	private Button       m_payBtn           = null;

	private HandlerClickEventNursOrderPay m_handlerClickEventNursOrderPay = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_order_pay);

		init();
		initListener();
		initData();
	}

	@Override
	protected void onStart()
	{
		updateContent();
		initGlobalData();
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		clearupGlobalData();
		super.onStop();
	}

	private void initData()
	{
		Intent intent = getIntent();
		int nurseID = intent.getIntExtra(NurseOrderConfig.NURSE_ID, DataConfig.DEFAULT_VALUE);
		if (nurseID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("nurseID is invalid[nurseID:="+nurseID+"]", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		DNurseOrderPay.GetInstance().setNurseID(nurseID);

		int orderID = intent.getIntExtra(NurseOrderConfig.ORDER_ID, DataConfig.DEFAULT_VALUE);
		if (orderID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("nurseID is invalid[orderID:="+orderID+"]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		DNurseOrderPay.GetInstance().setOrderID(orderID);

	}

	private void updateContent()
	{
		String orderSerialNum = DNurseOrderPay.GetInstance().getOrderSerialNum();
		m_orderSerialNumTV.setText(orderSerialNum);
		int totalPrice = DNurseOrderPay.GetInstance().getTotalPrice();
		m_priceTV.setText(String.valueOf(totalPrice));
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	private void init()
	{
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.title);

		m_orderSerialNumTV = (TextView)findViewById(R.id.order_serial_num_tv);
		m_priceTV = (TextView)findViewById(R.id.price_tv);
		m_cashRBtn = (RadioButton)findViewById(R.id.cash_rbtn);
		m_alipayRBtn = (RadioButton)findViewById(R.id.alipay_rbtn);
		m_weixinRBtn = (RadioButton)findViewById(R.id.weixin_rbtn);
		m_payBtn = (Button)findViewById(R.id.btn_bottom);
		m_payBtn.setText(R.string.determine_pay_title);

		m_handlerClickEventNursOrderPay = new HandlerClickEventNursOrderPay(this);
	}

	private void initListener()
	{
		m_payBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
	}

}
