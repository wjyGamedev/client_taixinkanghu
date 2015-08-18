/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.handler.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/16		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.recv;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

public class FinishedHospitalListEvent  extends BaseNetEvent
{
	public FinishedHospitalListEvent()
	{
		super(EventID.FINISHED_HOSPITAL_LIST);
	}
}
