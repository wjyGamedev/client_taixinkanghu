package com.taixinkanghu.app.ui.nurs_order_confirm_page;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/11.
 */
public class OrderConfirmActivity extends Activity
{

	private Button                            m_payBtn;
	private ImageButton                       m_backBtn;
	private TextView                          m_pageTitleTv;
	private TextView                          m_UserProtcolTv;
	private LinearLayout                      m_patientRegion;
	private LinearLayout                      m_patientStateRegion;
	private HandlerClickEventNursOrderConfirm m_handlerClickEventNursOrderConfirm;

	private TextView  m_patientStateTv;
	private ImageView m_dwonPatientState;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation_order);

		init();
		initListener();
		initModule();
	}

	private void init()
	{
		m_payBtn = (Button)findViewById(R.id.btn_pay);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_pageTitleTv = (TextView)findViewById(R.id.page_title);
		m_UserProtcolTv = (TextView)findViewById(R.id.user_protocol);
		m_patientRegion = (LinearLayout)findViewById(R.id.patient_region);
		m_patientStateRegion = (LinearLayout)findViewById(R.id.patient_state_region);
		m_patientStateTv = (TextView)findViewById(R.id.patient_state);
		m_dwonPatientState = (ImageView)findViewById(R.id.dwon_patient_state);

		m_handlerClickEventNursOrderConfirm = new HandlerClickEventNursOrderConfirm(this);
	}

	private void initListener()
	{

		m_payBtn.setOnClickListener(m_handlerClickEventNursOrderConfirm);
		m_backBtn.setOnClickListener(m_handlerClickEventNursOrderConfirm);
		m_UserProtcolTv.setOnClickListener(m_handlerClickEventNursOrderConfirm);
		m_patientRegion.setOnClickListener(m_handlerClickEventNursOrderConfirm);
		m_patientStateRegion.setOnClickListener(m_handlerClickEventNursOrderConfirm);

	}

	private void initModule()
	{
		m_pageTitleTv.setText(getString(R.string.determine_order_title));
		m_UserProtcolTv.append(Html.fromHtml("<a href=>" + "《用户协议》" + "</a> "));
	}

	public TextView getPatientStateTv()
	{
		return m_patientStateTv;
	}

	public ImageView getDwonPatientState()
	{
		return m_dwonPatientState;
	}

}
