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

import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.taixinkanghu.R;
import com.taixinkanghu.util.android.AppUtil;

public class SelectorBothDecorator extends BaseSelectorDecorator
{
	private final Drawable m_drawable;
	private OnShouldDecorateListener m_onShouldDecorateListener = null;

	public SelectorBothDecorator()
	{
		m_drawable = AppUtil.GetResources().getDrawable(R.drawable.dayall);
	}

	public void setOnShouldDecorateListener(OnShouldDecorateListener onShouldDecorateListener)
	{
		m_onShouldDecorateListener = onShouldDecorateListener;
	}

	@Override
	public boolean shouldDecorate(CalendarDay day)
	{
		return m_onShouldDecorateListener.shouldDecorate(day);
	}

	@Override
	public void decorate(DayViewFacade view)
	{
		view.setSelectionDrawable(m_drawable);
	}

}
