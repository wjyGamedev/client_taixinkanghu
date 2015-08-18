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
import com.taixinkanghu.app.model.event.ui.UIEvent;
import com.taixinkanghu.app.model.data.DApoitNursing;

public class ConfirmSelectDateEvent extends UIEvent
{
	private DApoitNursing.DNursingDate m_dNursingDate = null;

	public ConfirmSelectDateEvent(DApoitNursing.DNursingDate dNursingDate)
	{
		super(EventID.UI_SELECT_DATE_SURE);
		m_dNursingDate = dNursingDate;
	}

	public DApoitNursing.DNursingDate getdNursingDate()
	{
		return m_dNursingDate;
	}
}
