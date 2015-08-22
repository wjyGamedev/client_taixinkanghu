/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_page;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.ui.bottom.BottomCommon;
import com.taixinkanghu.app.ui.header.HeaderCommon;

import de.greenrobot.event.EventBus;

public class NurseOrderActivity extends Activity
{
	//widget
	private HeaderCommon m_headerCommon    = null;    //title
	private RadioButton  m_allRBtn         = null;            //全部
	private RadioButton  m_waitPayRBtn     = null;    //未支付
	private RadioButton  m_waitServiceRBtn = null;    //已完成
	private ListView	m_orderInfoLV = null;	//list列表显示区域

	private BottomCommon m_bottomCommon = null;

	//logical
	private EventBus     m_eventBus     = EventBus.getDefault();


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_order);
		init();
		initContent();

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
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.header_title);

		m_allRBtn = (RadioButton)findViewById(R.id.all_rbtn);
		m_waitPayRBtn = (RadioButton)findViewById(R.id.wait_pay_rbtn);
		m_waitServiceRBtn = (RadioButton)findViewById(R.id.wait_service_rbtn);

		m_bottomCommon = new BottomCommon(this);
		m_bottomCommon.init();
		m_bottomCommon.setTitle(R.string.bottom_title);

	}

	private void initContent()
	{

	}

	private void updateContent()
	{

	}


	/**
	 * action
	 */
	public void allAction()
	{

	}

	public void waitPayAction()
	{

	}

	public void waitServiceAction()
	{

	}



}
