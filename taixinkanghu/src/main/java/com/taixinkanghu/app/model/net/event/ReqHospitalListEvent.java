/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.Event.net.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/9		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event;

import com.taixinkanghu.app.model.config.EventID;

public class ReqHospitalListEvent extends BaseNetEvent
{
	public ReqHospitalListEvent()
	{
		super(EventID.QUEST_HOSPITAL_LIST);
	}
}
