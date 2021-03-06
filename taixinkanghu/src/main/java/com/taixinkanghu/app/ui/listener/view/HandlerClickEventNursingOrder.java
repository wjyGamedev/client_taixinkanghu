/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.listener.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.listener.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.ui.appointment_nursing.ApoitNursingActivity;
import com.taixinkanghu.app.ui.register_page.RegisterActivity;

public class HandlerClickEventNursingOrder extends BaseHandleOnClickEvent
{
	public HandlerClickEventNursingOrder(Context context)
	{
		super(context);
	}

	@Override
	public void onClick(View v)
	{
		if (DAccount.GetInstance().isRegisterSuccess() == false)
		{
			m_context.startActivity(new Intent(m_context, RegisterActivity.class));
		}
		else
		{
			DGlobal.GetInstance().SetNursingModuleStatus(EnumConfig.NursingModuleStatus.APIOT_NURSING);
			m_context.startActivity(new Intent(m_context, ApoitNursingActivity.class));
		}

	}

}
