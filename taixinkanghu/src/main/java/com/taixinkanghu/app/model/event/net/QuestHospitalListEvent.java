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

package com.taixinkanghu.app.model.event.net;

import com.taixinkanghu.app.model.config.EventID;

public class QuestHospitalListEvent extends NetEvent

{
	public QuestHospitalListEvent()
	{
		super(EventID.QUEST_HOSPITAL_LIST);
	}
}
