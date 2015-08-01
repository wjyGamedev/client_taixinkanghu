/**
 * Copyright (c) 213Team
 *
 * @className : app.frame.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${泰心医护APP的数据wrapper类。}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.frame;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.taixinkanghu.app.model.net.NetService;
import com.taixinkanghu.net.BaseHttp;
import com.taixinkanghu.third.party.sms.SmsAutho;
import com.taixinkanghu.util.android.AppUtil;

import de.greenrobot.event.EventBus;

public class AppFrame extends Application
{
	private EventBus          m_eventBus          = null;
	private ServiceConnection m_serviceConnection = null;

	@Override
	public void onCreate()
	{
		super.onCreate();
		init();
	}

	@Override
	public void onTerminate()
	{
		unbindService(m_serviceConnection);
		super.onTerminate();
	}

	private void init()
	{
		/**
		 * 保证顺序
		 */
		onDataInit();
		onModuleInit();
		onServiceInit();
	}

	private void onDataInit()
	{
		m_eventBus = EventBus.getDefault();
		m_serviceConnection = new ServiceConnection()
		{
			@Override
			public void onServiceConnected(ComponentName name, IBinder service)
			{
				/**
				 * TODO:LOG诊断信息
				 */
				Log.e("success", "onServiceConnected");
			}

			@Override
			public void onServiceDisconnected(ComponentName name)
			{
				/**
				 * TODO:LOG诊断信息
				 */
				Log.e("failed", "onServiceDisconnected");
			}
		};
	}

	private void onModuleInit()
	{
		BaseHttp.getInstance().init(this);
		AppUtil.init(this);
	}

	private void onServiceInit()
	{
		startupService();
	}

	private void startupService()
	{
		final Intent intent = new Intent(this, NetService.class);
//		startService(intent);

		boolean bReturnFlag = bindService(intent, m_serviceConnection, Service.BIND_AUTO_CREATE);
		if (!bReturnFlag)
		{
			Log.e("error", "bindService failed!");
		}

	}

	/**
	 * connect/disconnect service
	 */








}
