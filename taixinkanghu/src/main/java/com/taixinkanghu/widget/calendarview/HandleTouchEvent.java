/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.calendarview.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/7		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.calendarview;

import android.view.MotionEvent;
import android.view.View;

public class HandleTouchEvent implements View.OnTouchListener
{
	private BaseCalendarView m_baseCalendarView = null;

	public HandleTouchEvent(BaseCalendarView baseCalendarView)
	{
		m_baseCalendarView = baseCalendarView;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return false;
	}
}
