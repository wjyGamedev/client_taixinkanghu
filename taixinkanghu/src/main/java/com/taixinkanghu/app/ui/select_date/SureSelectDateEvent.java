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

public class SureSelectDateEvent extends UIEvent
{
	private String m_all = null;
	private String m_day = null;
	private String m_night = null;

	public SureSelectDateEvent()
	{
		super(EventID.UI_SELECT_DATE_SURE);
	}

	public String getAll()
	{
		return m_all;
	}

	public void setAll(String all)
	{
		m_all = all;
	}

	public String getDay()
	{
		return m_day;
	}

	public void setDay(String day)
	{
		m_day = day;
	}

	public String getNight()
	{
		return m_night;
	}

	public void setNight(String night)
	{
		m_night = night;
	}

}
