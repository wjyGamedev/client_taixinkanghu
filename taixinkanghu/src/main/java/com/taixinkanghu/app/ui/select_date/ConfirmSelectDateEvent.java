/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_date.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/11		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_date;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.model.event.ui.UIEvent;

public class ConfirmSelectDateEvent extends UIEvent
{
	private DNursingDate m_nursingDate = null;

	public ConfirmSelectDateEvent(DNursingDate nursingDate)
	{
		super(EventID.UI_SELECT_DATE_SURE);
		m_nursingDate = nursingDate;
	}

	public DNursingDate getNursingDate()
	{
		return m_nursingDate;
	}
}
