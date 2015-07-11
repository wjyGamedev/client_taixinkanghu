/**
 * Copyright (c) 213Team
 *
 * @className : app.ui.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/9		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.wjy.taixinkanghu.R;
import com.taixinkanghu.app.model.event.net.QuestHospitalListEvent;

import de.greenrobot.event.EventBus;

public class Welcome extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		mHandler.sendEmptyMessageDelayed(GO_MAIN, GO_TIME);

	}

	@Override
	protected void onStart()
	{
		super.onStart();
		m_eventBus.post(new QuestHospitalListEvent());
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		m_eventBus.post(new QuestHospitalListEvent());
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		m_eventBus.post(new QuestHospitalListEvent());
	}

	private Handler  mHandler   = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case GO_MAIN:
				goMain();
				break;
			}
		}
	};

	private void goMain(){
//		Intent i = new Intent(this, MainActivity.class);
//		startActivity(i);
		finish();
	}
	private EventBus m_eventBus = EventBus.getDefault();
	private static final int GO_MAIN = 1000;
	private static final int GO_TIME = 3000;
}
