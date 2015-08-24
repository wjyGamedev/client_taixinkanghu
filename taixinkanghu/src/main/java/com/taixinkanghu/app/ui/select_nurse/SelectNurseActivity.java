/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_nurse.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_nurse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseBasicListEvent;
import com.taixinkanghu.app.ui.bottom.BottomCommon;
import com.taixinkanghu.app.ui.header.HeaderCommon;

import de.greenrobot.event.EventBus;

public class SelectNurseActivity  extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;

	//tips
	private TextView m_textView = null;

	//listview
	public  ListView                      m_NursesLV                      = null;
	private HandlerItemClickEventListView m_handlerItemClickEventListView = null;
	private SelectNurseAdapter            m_selectNurseAdapter            = null;
	private BottomCommon                  m_bottomCommon                  = null;

	//logical
	private EventBus m_eventBus = EventBus.getDefault();

	private int m_oldNurseID = DataConfig.DEFAULT_VALUE;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_nurse);

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

	private void init()
	{
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		//listview
		m_textView = (TextView)findViewById(R.id.tips_tv);

		//功能区
		m_NursesLV = (ListView)findViewById(R.id.nurse_display_list);
		m_handlerItemClickEventListView = new HandlerItemClickEventListView(this);
		m_selectNurseAdapter = new SelectNurseAdapter(this);

		//bottom
		m_bottomCommon = new BottomCommon(this);
		m_bottomCommon.init();
	}

	private void initContent()
	{
		//title
		m_headerCommon.setTitle(R.string.select_nurse_title);

		//tips
		m_textView.setText(R.string.select_nurse_tips);
		m_textView.setTextColor(getResources().getColor(R.color.main_color));
		//功能区
		m_NursesLV.setOnItemClickListener(m_handlerItemClickEventListView);
		m_NursesLV.setAdapter(m_selectNurseAdapter);

		//bottom
		m_bottomCommon.setTitle(R.string.content_main_page);

		//logical
		m_eventBus.register(this);

		//如果是更换，则保存原来的护工ID。
		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.CHANGE_NURSE)
		{
			Intent intent = getIntent();
			m_oldNurseID = intent.getIntExtra(NurseBasicListConfig.ID, DataConfig.DEFAULT_VALUE);
		}


	}

	private void updateContent()
	{
		m_selectNurseAdapter.notifyDataSetChanged();
	}

	public int getOldNurseID()
	{
		return m_oldNurseID;
	}

	/**
	 * EventBus  handler
	 */
	public void onEventMainThread(FinishedNurseBasicListEvent event)
	{
		updateContent();
	}


}
