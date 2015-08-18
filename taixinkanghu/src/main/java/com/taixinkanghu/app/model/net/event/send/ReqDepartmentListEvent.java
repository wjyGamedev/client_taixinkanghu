/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.event.net.send.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.send;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

public class ReqDepartmentListEvent extends BaseNetEvent
{
	public ReqDepartmentListEvent()
	{
		super(EventID.QUEST_HOSPITAL_LIST);
	}
}
