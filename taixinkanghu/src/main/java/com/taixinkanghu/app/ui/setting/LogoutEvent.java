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

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.event.logical.LogicalEvent;

public class LogoutEvent extends LogicalEvent
{
	public LogoutEvent()
	{
		super(EventID.LOGICAL_LOGOUT);
	}
}
