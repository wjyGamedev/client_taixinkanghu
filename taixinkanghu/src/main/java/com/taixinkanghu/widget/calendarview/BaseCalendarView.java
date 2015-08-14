/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.calendarview.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/7		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

public class BaseCalendarView extends View
{
	private final static String s_TAG = "BaseCalendarView";

	private Calendar m_calendar = null;
	private Date m_curMonth = null;    			// 当前日历显示的月
	private Date m_showFirstDate = null;			// 日历显示的第一个日期
	private Date m_showLastDate = null; 			// 日历显示的最后一个日期
	private Date m_today = null;        			// 今天的日期
	private int[] m_dateArray = new int[42]; 	// 日历显示数字
	private int m_curStartIndex = 0;
	private int m_curEndIndex = 0; 				// 当前显示的日历起始的索引

	private HandleTouchEvent m_handleTouchEvent = null;

	private BaseSurface m_baseSurface = null;


	public BaseCalendarView(Context context)
	{
		super(context);
		init();
	}

	public BaseCalendarView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
		initContent();
		initListener();
	}

	private void init()
	{
		m_curMonth = new Date();
		m_today = m_curMonth;
		m_calendar = Calendar.getInstance();
		m_calendar.setTime(m_curMonth);

		m_baseSurface = new BaseSurface(getContext());
		m_handleTouchEvent = new HandleTouchEvent(this);

	}

	private void initContent()
	{
		m_baseSurface.setDensity(getResources().getDisplayMetrics().density);
		setBackgroundColor(m_baseSurface.getBackGroundColor());
	}

	private void initListener()
	{
		setOnTouchListener(m_handleTouchEvent);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getResources().getDisplayMetrics().widthPixels;
		int height = (int) (getResources().getDisplayMetrics().heightPixels * 2 / 5);

		m_baseSurface.setWidth(width);
		m_baseSurface.setHeight(height);

		widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
		heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
							int bottom) {
		if (changed)
		{
			m_baseSurface.init();
		}
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		//01. 画框
		impDrawBorder(canvas);

		//02. 星期一到星期日
		impDrawWeek(canvas);

		//03.
		calculateDate();

		//04.
		impDrawCells(canvas);

		//05.
		int todayIndex = -1;
		m_calendar.setTime(m_curMonth);
		String curYearAndMonth = m_calendar.get(Calendar.YEAR) + "" + m_calendar.get(Calendar.MONTH);

		m_calendar.setTime(m_today);
		String todayYearAndMonth = m_calendar.get(Calendar.YEAR) + ""	+ m_calendar.get(Calendar.MONTH);
		if (curYearAndMonth.equals(todayYearAndMonth))
		{
			int todayNumber = m_calendar.get(Calendar.DAY_OF_MONTH);
			todayIndex = m_curStartIndex + todayNumber - 1;
		}

		int textColor = m_baseSurface.getTextColor();
		int borderColor = m_baseSurface.getBorderColor();
		int todayNumberColor = m_baseSurface.getTodayNumberColor();
		for (int index = 0; index < m_dateArray.length; index++) {
			int color = textColor;
			if (isLastMonth(index)) {
				color = borderColor;
			} else if (isNextMonth(index)) {
				color = borderColor;
			}
			if (todayIndex != -1 && index == todayIndex) {
				color = todayNumberColor;
			}
			drawCellText(canvas, index, m_dateArray[index] + "", color);
		}

		super.onDraw(canvas);
	}

	protected void impDrawBorder(Canvas canvas)
	{
		Path borderPath = m_baseSurface.getBorderPath();
		Paint borderPaint = m_baseSurface.getBorderPaint();
		canvas.drawPath(borderPath, borderPaint);
	}

	protected void impDrawWeek(Canvas canvas)
	{
		float monthHeight = m_baseSurface.getMonthHeight();
		float weekHeight = m_baseSurface.getWeekHeight();
		// 星期
		float weekTextY = monthHeight + weekHeight * 3 / 4f;
		// 星期背景
		//		surface.cellBgPaint.setColor(surface.textColor);
		//		canvas.drawRect(surface.weekHeight, surface.width, surface.weekHeight, surface.width, surface.cellBgPaint);
		float cellWidth = m_baseSurface.getCellWidth();
		Paint weekPaint = m_baseSurface.getWeekPaint();
		String weekEle = null;
		for (int index = 0; index < m_baseSurface.getWeekLength(); index++) {
			weekEle = m_baseSurface.getWeekContent(index);
			float weekTextX = index * cellWidth + (cellWidth - weekPaint.measureText(weekEle)) / 2f;
			canvas.drawText(weekEle, weekTextX, weekTextY, weekPaint);
		}
	}

	private void calculateDate()
	{
		m_calendar.setTime(m_curMonth);
		m_calendar.set(Calendar.DAY_OF_MONTH, 1);

		int dayInWeek = m_calendar.get(Calendar.DAY_OF_WEEK);

		int monthStart = dayInWeek;
		if (monthStart == 1) {
			monthStart = 8;
		}

		//以日为开头-1，以星期一为开头-2
		monthStart -= 1;
		m_curStartIndex = monthStart;
		m_dateArray[monthStart] = 1;

		// last month
		if (monthStart > 0) {
			m_calendar.set(Calendar.DAY_OF_MONTH, 0);
			int dayInmonth = m_calendar.get(Calendar.DAY_OF_MONTH);
			for (int i = monthStart - 1; i >= 0; i--) {
				m_dateArray[i] = dayInmonth;
				dayInmonth--;
			}
			m_calendar.set(Calendar.DAY_OF_MONTH, m_dateArray[0]);
		}

		m_showFirstDate = m_calendar.getTime();

		// this month
		m_calendar.setTime(m_curMonth);
		m_calendar.add(Calendar.MONTH, 1);
		m_calendar.set(Calendar.DAY_OF_MONTH, 0);

		int monthDay = m_calendar.get(Calendar.DAY_OF_MONTH);
		for (int i = 1; i < monthDay; i++) {
			m_dateArray[monthStart + i] = i + 1;
		}
		m_curEndIndex = monthStart + monthDay;

		// next month
		for (int i = monthStart + monthDay; i < m_dateArray.length; i++) {
			m_dateArray[i] = i - (monthStart + monthDay) + 1;
		}
		if (m_curEndIndex < m_dateArray.length) {
			// 显示了下一月的
			m_calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		m_calendar.set(Calendar.DAY_OF_MONTH, m_dateArray[41]);
		m_showLastDate = m_calendar.getTime();
	}

	private void impDrawCells(Canvas canvas)
	{

	}

	private void drawCellText(Canvas canvas, int index, String text, int color) {

		int x = getXByIndex(index);
		int y = getYByIndex(index);

		Paint datePaint = m_baseSurface.getDatePaint();
		datePaint.setColor(color);
		float monthHeight = m_baseSurface.getMonthHeight();
		float weekHeight = m_baseSurface.getWeekHeight();
		float cellHeight = m_baseSurface.getCellHeight();
		float cellWidth = m_baseSurface.getCellWidth();

		float cellY = monthHeight + weekHeight + (y - 1) * cellHeight + cellHeight * 3 / 4f;
		float cellX = (cellWidth * (x - 1)) + (cellWidth - datePaint.measureText(text)) / 2f;
		canvas.drawText(text, cellX, cellY, datePaint);

	}

	private boolean isLastMonth(int i) {
		if (i < m_curStartIndex) {
			return true;
		}
		return false;
	}

	private boolean isNextMonth(int i) {
		if (i >= m_curEndIndex) {
			return true;
		}
		return false;
	}

	private int getXByIndex(int i) {
		return i % 7 + 1; // 1 2 3 4 5 6 7
	}

	private int getYByIndex(int i) {
		return i / 7 + 1; // 1 2 3 4 5 6
	}
}
