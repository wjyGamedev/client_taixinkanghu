/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.event.net.recv.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/16		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.event.net.recv;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.event.net.BaseNetEvent;

public class FinishedNurseBasicListEvent  extends BaseNetEvent
{
	public FinishedNurseBasicListEvent()
	{
		super(EventID.FINISHED_NURSE_BASIC_LIST);
	}
}
