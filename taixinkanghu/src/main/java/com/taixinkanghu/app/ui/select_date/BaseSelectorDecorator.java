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
 * 2015/8/14		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_date;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;

public abstract class BaseSelectorDecorator implements DayViewDecorator
{
	public interface OnShouldDecorateListener
	{
		boolean shouldDecorate(CalendarDay day);
	}
}
