/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_pay_more.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/25		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_pay_more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.event.editevent.HandleEditActionEvent;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderPayMoreEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderPayMoreEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

public class NurseOrderPayMoreActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon    = null;
	private RadioButton m_reasonOptionRbtn = null;
	private RadioGroup   m_reasonValueRGrp = null;
	private RadioButton  m_careRBtn        = null;
	private RadioButton  m_halfCareRBtn    = null;
	private RadioButton  m_canntCareRBtn   = null;
	private EditText     m_priceET           = null;
	private Button       m_confirmBtn      = null;

	//logical
	private HandleClickEventOnNurseOrderPayMore m_handleClickEventOnNurseOrderPayMore = null;
	private HandleEditActionEvent               m_handleEditActionEvent               = null;

	private EventBus                            m_eventBus                            = EventBus.getDefault();

	private int    m_orderID        = DataConfig.DEFAULT_VALUE;
	private String m_orderSerialNum = null;
	private int    m_selectValueID  = DataConfig.DEFAULT_VALUE;
	private int m_payMorePrice = DataConfig.DEFAULT_VALUE;

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

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
	}


	private void init()
	{
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.pay_more_title);

		m_reasonOptionRbtn = (RadioButton)findViewById(R.id.reason_option_rbtn);
		m_reasonValueRGrp = (RadioGroup)findViewById(R.id.reason_value_region_rgrp);
		m_careRBtn = (RadioButton)findViewById(R.id.reason_value_care_rbtn);
		m_halfCareRBtn = (RadioButton)findViewById(R.id.reason_value_half_care_rbtn);
		m_canntCareRBtn = (RadioButton)findViewById(R.id.reason_value_cannt_care_rbtn);
		m_priceET = (EditText)findViewById(R.id.price_tv);

		m_confirmBtn = (Button)findViewById(R.id.btn_bottom);

		m_handleClickEventOnNurseOrderPayMore = new HandleClickEventOnNurseOrderPayMore(this);
		m_handleEditActionEvent = new HandleEditActionEvent(this);

		m_eventBus.register(this);
	}

	private void initListener()
	{
		m_confirmBtn.setOnClickListener(m_handleClickEventOnNurseOrderPayMore);
		m_careRBtn.setOnClickListener(m_handleClickEventOnNurseOrderPayMore);
		m_halfCareRBtn.setOnClickListener(m_handleClickEventOnNurseOrderPayMore);
		m_canntCareRBtn.setOnClickListener(m_handleClickEventOnNurseOrderPayMore);
		m_priceET.setOnEditorActionListener(m_handleEditActionEvent);
	}

	private void initData()
	{
		Intent intent = getIntent();
		int m_orderID = intent.getIntExtra(NurseOrderConfig.ORDER_ID, DataConfig.DEFAULT_VALUE);
		if (m_orderID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("nurseID is invalid[orderID:=" + m_orderID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		String m_orderSerialNum = intent.getStringExtra(NurseOrderConfig.ORDER_SERIAL_NUM);
		if (m_orderSerialNum == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseID is invalid[m_orderSerialNum == null]");
			RegisterDialog.GetInstance().show();
			return;
		}

	}

	private void updateContent()
	{
		m_reasonOptionRbtn.setChecked(true);

		if (m_selectValueID != DataConfig.DEFAULT_VALUE)
		{
			if (m_selectValueID == R.id.reason_value_care_rbtn)
			{
				m_careRBtn.setChecked(true);
				m_halfCareRBtn.setChecked(false);
				m_canntCareRBtn.setChecked(false);
			}
			else if (m_selectValueID == R.id.reason_value_half_care_rbtn)
			{
				m_careRBtn.setChecked(false);
				m_halfCareRBtn.setChecked(true);
				m_canntCareRBtn.setChecked(false);
			}
			else if (m_selectValueID == R.id.reason_value_cannt_care_rbtn)
			{
				m_careRBtn.setChecked(false);
				m_halfCareRBtn.setChecked(false);
				m_canntCareRBtn.setChecked(true);
			}
		}

		if (m_payMorePrice != DataConfig.DEFAULT_VALUE)
		{
			m_priceET.setText(m_payMorePrice);
		}
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	public void reasonValueClickAction()
	{
		m_selectValueID = m_reasonValueRGrp.getCheckedRadioButtonId();
	}

	public void confirmAction()
	{
		//01. 判断差价金额
		String payMoreprice = m_priceET.getText().toString();
		if (TextUtils.isEmpty(payMoreprice))
		{
			RegisterDialog.GetInstance().setMsg(getString(R.string.pay_more_tips_price));
			RegisterDialog.GetInstance().show();
			return;
		}
		m_payMorePrice = Integer.valueOf(payMoreprice);

		if (m_selectValueID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg(getString(R.string.pay_more_tips_reason));
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 发送event
		String reasonOption = getString(R.string.pay_more_reason_option);
		String reasonValue = null;
		if (m_selectValueID == R.id.reason_value_care_rbtn)
		{
			reasonValue = EnumConfig.PatientState.PATIENT_STATE_CARE_MYSELF.getName();
		}
		else if (m_selectValueID == R.id.reason_value_half_care_rbtn)
		{
			reasonValue = EnumConfig.PatientState.PATIENT_STATE_HALF_CARE_MYSELF.getName();
		}
		else if (m_selectValueID == R.id.reason_value_cannt_care_rbtn)
		{
			reasonValue = EnumConfig.PatientState.PATIENT_STATE_CANNT_CARE_MYSELF.getName();
		}
		else
		{
			RegisterDialog.GetInstance().setMsg("m_selectValueID is invalid!");
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 发送Event
		ReqNurseOrderPayMoreEvent event = new ReqNurseOrderPayMoreEvent();
		event.setUserID(DAccount.GetInstance().getId());
		event.setOrderID(String.valueOf(m_orderID));
		event.setTotalPrice(m_payMorePrice);
		event.setReasonOption(reasonOption);
		event.setReasonValue(reasonValue);

		m_eventBus.post(event);


	}

	/**
	 * event handler
 	 */
	//补差价成功，跳转到支付页面。
	public void onEventMainThread(FinishedNurseOrderPayMoreEvent event)
	{

		return;
	}




}
