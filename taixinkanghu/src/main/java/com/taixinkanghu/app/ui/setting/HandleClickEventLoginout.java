/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.setting.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.register_page.RegisterActivity;

import de.greenrobot.event.EventBus;

public class HandleClickEventLoginout extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	public HandleClickEventLoginout(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		boolean bRegisterFlag = DAccount.GetInstance().isRegisterSuccess();

		//01. 未注册跳转到注册页面
		if (bRegisterFlag == false)
		{
			m_context.startActivity(new Intent(m_context, RegisterActivity.class));
		}
		//02. 已注册，点击后，注销。
		else
		{
			Activity activity = (Activity)m_context;
			//0201. 提示是否退出登录。
			LogoutDialog.GetInstance().setMsg(activity.getResources().getString(R.string.tips_loginout), activity);
			LogoutDialog.GetInstance().show();
		}

	}


}
