/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.event.recv.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/25		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.recv;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

public class FinishedNurseOrderPayMoreEvent extends BaseNetEvent
{

	public FinishedNurseOrderPayMoreEvent()
	{
		super(EventID.FINISHED_NURSE_ORDER_PAY_MORE);
	}
}
