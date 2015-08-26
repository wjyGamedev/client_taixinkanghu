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
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DNurseBasics;
import com.taixinkanghu.app.model.data.net.DNurseBasicsList;
import com.taixinkanghu.app.model.data.net.DNurseContainer;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseBasicListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqApoitNursingEvent;
import com.taixinkanghu.app.ui.bottom.BottomCommon;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;
import com.taixinkanghu.widget.timer.TimerTaskWrapper;

import java.util.ArrayList;

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

	//wait dialog
	private ProgressDialog   m_waitProgressDialog = null;
	//wait timer
	private TimerTaskWrapper m_waitTimerTask      = new TimerTaskWrapper();
	private TimerTaskHandler m_timerTaskHandler   = new TimerTaskHandler();
	private final static long DELAY_TIME_MILLISENCENDS = 5000;

	//logical
	private EventBus         m_eventBus           = EventBus.getDefault();

	private int m_oldNurseID = DataConfig.DEFAULT_VALUE;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_nurse);

		init();
		initContent();
		initWaitAction();
	}

	private void initWaitAction()
	{
		m_waitProgressDialog.show();
		m_waitTimerTask.schedule(DELAY_TIME_MILLISENCENDS);
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

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
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

		//wait dialog
		m_waitProgressDialog = new ProgressDialog(this);
		m_waitProgressDialog.setMessage(getString(R.string.wait_tips));

		//wait timer
		m_waitTimerTask.setTimerTaskListener(m_timerTaskHandler);
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
		//01. 更新数据
		m_selectNurseAdapter.notifyDataSetChanged();

		//02. 关闭wait dialog
		DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
		if (nurseBasicsList == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", SelectNurseActivity.this);
			RegisterDialog.GetInstance().show();
			return;
		}

		ArrayList<DNurseBasics> nurseBasicses = nurseBasicsList.getNurseBasicses();
		if (nurseBasicses == null || nurseBasicses.isEmpty() )
		{
			return;
		}

		m_waitProgressDialog.dismiss();

	}

	public int getOldNurseID()
	{
		return m_oldNurseID;
	}

	/**
	 * timer handler
	 */
	class TimerTaskHandler implements TimerTaskWrapper.TimerTaskListener
	{

		@Override
		public void execAction()
		{
			//01. 有效性判断
			DNurseBasicsList nurseBasicsList = DNurseContainer.GetInstance().getNurseBasicsList();
			if (nurseBasicsList == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseBasicsList == null", SelectNurseActivity.this);
				RegisterDialog.GetInstance().show();
				return;
			}

			//02. 判断是否需要重新发送消息，来获取护工显示列表
			ArrayList<DNurseBasics> nurseBasicses = nurseBasicsList.getNurseBasicses();
			if (nurseBasicses == null || nurseBasicses.isEmpty())
			{
				ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
				m_eventBus.post(reqApoitNursingEvent);
				return;
			}
		}
	}


	/**
	 * EventBus  handler
	 */
	public void onEventMainThread(FinishedNurseBasicListEvent event)
	{
		updateContent();
	}


}
