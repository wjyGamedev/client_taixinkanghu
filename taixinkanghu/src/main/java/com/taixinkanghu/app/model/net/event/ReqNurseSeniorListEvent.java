/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.event.net.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event;

import com.taixinkanghu.app.model.config.EventID;

public class ReqNurseSeniorListEvent extends BaseNetEvent
{
	public ReqNurseSeniorListEvent()
	{
		super(EventID.QUEST_NURSE_SENIOR_LIST);
	}
}
