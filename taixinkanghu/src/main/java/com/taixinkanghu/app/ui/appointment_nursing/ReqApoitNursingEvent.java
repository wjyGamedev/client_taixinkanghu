/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.appointment_nursing.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/16		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.appointment_nursing;

import android.content.Context;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

public class ReqApoitNursingEvent extends BaseNetEvent
{
	private Context m_context = null;

	public ReqApoitNursingEvent()
	{
		super(EventID.QUEST_APPPINTMENT_NURSING);
	}

	public void init(Context context)
	{
		m_context = context;
	}

	public Context getContext()
	{
		return m_context;
	}
}
