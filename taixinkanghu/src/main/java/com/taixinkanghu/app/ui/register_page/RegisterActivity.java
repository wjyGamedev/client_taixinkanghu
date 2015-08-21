/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.register_page;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.event.editevent.HandleEditActionEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.third.party.sms.SmsAutho;

import de.greenrobot.event.EventBus;

public class RegisterActivity extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;

	//
	private TextView               m_phoneNumTV             = null;
	private TextView               m_authTV                 = null;
	private Button                 m_verificationBtn        = null;
	private Button                 m_registerBtn            = null;
	//event
	private HandleEditActionEvent  m_handleEditActionEvent  = null;
	private HandlerEventOnRegister m_handlerEventOnRegister = null;

	//eventbus
	EventBus m_eventBus = EventBus.getDefault();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		init();
		initContent();
		initListener();
		initEvent();
	}

	@Override
	protected void onStart()
	{
		initGlobalData();
		super.onStart();
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	@Override
	protected void onStop()
	{
		clearupGlobalData();
		super.onStop();
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
	}

	private void initEvent()
	{
		SmsAutho.GetInstance().getSupportedCountries();
	}

	private void init()
	{
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		m_phoneNumTV = (TextView)findViewById(R.id.phone_num_tv);
		m_authTV = (TextView)findViewById(R.id.auth_code);
		m_verificationBtn = (Button)findViewById(R.id.verification_id_btn);
		m_registerBtn = (Button)findViewById(R.id.register_id_btn);

		m_handlerEventOnRegister = new HandlerEventOnRegister();
		m_handleEditActionEvent = new HandleEditActionEvent(this);

		SmsAutho.GetInstance().init(this);
		m_handlerEventOnRegister.init(this);

		m_eventBus.register(this);
	}

	private void initContent()
	{
		m_headerCommon.setTitle(R.string.title_register);
	}


	private void initListener()
	{
		m_phoneNumTV.setOnEditorActionListener(m_handleEditActionEvent);
		m_verificationBtn.setOnClickListener(m_handlerEventOnRegister);
		m_registerBtn.setOnClickListener(m_handlerEventOnRegister);
	}

	public TextView getAuthTV()
	{
		return m_authTV;
	}

	/**
	 * event handle
	 */
	public void onEventMainThread(DeserialFinishedEvent event)
	{
		//注册成功，关闭当前页面
		finish();
		return;

	}



}
