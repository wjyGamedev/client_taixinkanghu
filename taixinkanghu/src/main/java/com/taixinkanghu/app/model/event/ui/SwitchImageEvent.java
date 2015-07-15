/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.event.ui.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/12		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.event.ui;

import com.taixinkanghu.app.model.config.EventID;

public class SwitchImageEvent extends UIEvent

{
	public SwitchImageEvent()
	{
		super(EventID.UI_SWITCH_IMAGE);
	}
}
