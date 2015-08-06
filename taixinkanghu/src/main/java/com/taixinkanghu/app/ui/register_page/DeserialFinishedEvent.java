/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.register_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/3		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.register_page;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.config.NetConfig;
import com.taixinkanghu.app.model.event.ui.UIEvent;

public class DeserialFinishedEvent extends UIEvent
{
	private boolean m_serialFlag = false;

	public DeserialFinishedEvent()
	{
		super(EventID.UI_SMS_DESERIALIZATION_FINISHED);
	}

	public void init(boolean serialFlag)
	{
		m_serialFlag = serialFlag;
	}

	public boolean isSerialFlag()
	{
		return m_serialFlag;
	}

}
