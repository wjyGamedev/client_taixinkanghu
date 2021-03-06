/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_date.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/7		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_date;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.MonthView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.util.logcal.LogicalUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class SelectDateActivity extends Activity
{
	//widget
	private HeaderCommon         m_headerCommon     = null;//title
	private MaterialCalendarView m_calendarView     = null;    //calendarview
	private LinearLayout         m_selectDataLayout = null;    //select date
	private TextView             m_selectDateTV     = null;
	private Button               m_bottomBtn        = null;    //bottom

	//logicaldata
	private HandleClickEventOnActivity    m_handleClickEventOnActivity  = null;
	private Calendar                      m_beginDateCalendar           = Calendar.getInstance();
	private Calendar                      m_endDateCalendar             = Calendar.getInstance();
	private Date                          m_beginDate                   = null;
	private Date                          m_endDate                     = null;
	private ArrayList<ArrayList<Date>>    m_schedularDateListAll        = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> m_schedularTypeListAll        = new ArrayList<>();
	private SimpleDateFormat              m_simpleDateFormat            = new SimpleDateFormat(DateConfig.PATTERN_DATE_MONTH_DAY);
	private HandleOnMonthChangedEvent     m_handleOnMonthChangedEvent   = new HandleOnMonthChangedEvent();
	private SelectorBothDecorator         m_selectorBothDecorator       = new SelectorBothDecorator();
	private SelectorDayDecorator          m_selectorDayDecorator        = new SelectorDayDecorator();
	private SelectorNightDecorator        m_selectorNightDecorator      = new SelectorNightDecorator();
	private HandleBothDecorateListener    m_handleBothDecorateListener  = new HandleBothDecorateListener();
	private HandleDayDecorateListener     m_handleDayDecorateListener   = new HandleDayDecorateListener();
	private HandleNightDecorateListener   m_handleNightDecorateListener = new HandleNightDecorateListener();
	private HandleDayClickEvent           m_handleDayClickEvent         = new HandleDayClickEvent();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_select);

		init();
		initContent();
		initListener();
		initEvent();
	}

	private void init()
	{
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		m_bottomBtn = (Button)findViewById(R.id.btn_bottom);
		m_calendarView = (MaterialCalendarView)findViewById(R.id.calendar_view);
		m_selectDataLayout = (LinearLayout)findViewById(R.id.date_select_region);
		m_selectDateTV = (TextView)findViewById(R.id.select_date_id);

		m_handleClickEventOnActivity = new HandleClickEventOnActivity(this);

	}

	private void initContent()
	{
		m_headerCommon.setTitle(R.string.title_select_date);
		m_bottomBtn.setText(R.string.content_sure);

		m_calendarView.setShowOtherDates(true);
		m_calendarView.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large);
		m_calendarView.setDateTextAppearance(R.style.TextAppearance_AppCompat_Medium);
		m_calendarView.setWeekDayTextAppearance(R.style.TextAppearance_AppCompat_Medium);

	}

	private void initListener()
	{
		m_selectDataLayout.setOnClickListener(m_handleClickEventOnActivity);
		m_bottomBtn.setOnClickListener(m_handleClickEventOnActivity);
		m_calendarView.setOnMonthChangedListener(m_handleOnMonthChangedEvent);

		m_selectorBothDecorator.setOnShouldDecorateListener(m_handleBothDecorateListener);
		m_selectorDayDecorator.setOnShouldDecorateListener(m_handleDayDecorateListener);
		m_selectorNightDecorator.setOnShouldDecorateListener(m_handleNightDecorateListener);
		m_calendarView.addDecorators(m_selectorBothDecorator, m_selectorDayDecorator, m_selectorNightDecorator);
		m_calendarView.setOnDateChangedListener(m_handleDayClickEvent);

	}

	private void initEvent()
	{

	}

	public String getDateDescription()
	{
		if (m_beginDate == null || m_endDate == null)
			return null;

		String beginContent = m_simpleDateFormat.format(m_beginDate);
		String endContent = m_simpleDateFormat.format(m_endDate);
		int days = LogicalUtil.GetDayNums(m_beginDate, m_endDate);
		String total = getResources().getString(R.string.char_total);
		String day = getResources().getString(R.string.char_day);
		String display = beginContent + " - " + endContent + total + days + day;
		return display;
	}

	public void setBeginDate(Date beginDate)
	{
		m_beginDate = beginDate;
		m_beginDateCalendar.setTime(m_beginDate);
	}

	public void setEndDate(Date endDate)
	{
		m_endDate = endDate;
		m_endDateCalendar.setTime(m_endDate);
	}

	public void confirmDateAction()
	{
		//01. text display
		String beginContent = m_simpleDateFormat.format(m_beginDate);
		String endContent = m_simpleDateFormat.format(m_endDate);
		int days = LogicalUtil.GetDayNums(m_beginDate, m_endDate);
		String total = getResources().getString(R.string.char_total);
		String day = getResources().getString(R.string.char_day);
		String display = beginContent + " - " + endContent + total + days + day;
		m_selectDateTV.setText(display);

		//02. schedular_all
		m_schedularDateListAll.clear();
		m_schedularTypeListAll.clear();

		int beginMonth = m_beginDateCalendar.get(Calendar.MONTH);
		int endMonth = m_endDateCalendar.get(Calendar.MONTH);
		int beginDay = m_beginDateCalendar.get(Calendar.DAY_OF_MONTH);
		int endDay = m_endDateCalendar.get(Calendar.DAY_OF_MONTH);
		int iCurMonth = 0;
		int iMaxDay = 0;
		int iDay = 0;
		for(int iMonth = beginMonth; iMonth <= endMonth; ++iMonth)
		{
			Calendar tmpCalendar = Calendar.getInstance();
			tmpCalendar.set(Calendar.MONTH, iMonth);
			iMaxDay = tmpCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if(iMonth == endMonth)
			{
				iMaxDay = endDay;
			}

			if(iMonth == beginMonth)
			{
				iDay = beginDay;
			}
			else
			{
				iDay = 1;
			}
			tmpCalendar.set(Calendar.DAY_OF_MONTH, iDay);

			ArrayList<Date> dateMonthListAll = new ArrayList<>();
			ArrayList<Integer> typeMonthListAll = new ArrayList<>();
			for(int index = 0; iDay <= iMaxDay; ++iDay, tmpCalendar.roll(Calendar.DAY_OF_MONTH, true), ++index)
			{
				Date tmpDate = tmpCalendar.getTime();
				dateMonthListAll.add(tmpDate);
				typeMonthListAll.add(DataConfig.SELECT_DAY_TYEP_ALL);
			}
			m_schedularDateListAll.add(dateMonthListAll);
			m_schedularTypeListAll.add(typeMonthListAll);
		}

		m_calendarView.loadDateList(m_schedularDateListAll, m_schedularTypeListAll);
	}

	public Date getBeginDate()
	{
		return m_beginDate;
	}

	public Date getEndDate()
	{
		return m_endDate;
	}

	public ArrayList<ArrayList<Date>> getSchedularDateListAll()
	{
		return m_schedularDateListAll;
	}

	public ArrayList<ArrayList<Integer>> getSchedularTypeListAll()
	{
		return m_schedularTypeListAll;
	}

	protected class HandleOnMonthChangedEvent implements OnMonthChangedListener
	{

		@Override
		public void onMonthChanged(MaterialCalendarView widget, CalendarDay date)
		{
			LinkedList<MonthView> monthViewLinkedList = widget.getMonthViewList();
			int iMonth = date.getMonth();

			ArrayList<ArrayList<CalendarDay>> dateMonthlist = widget.getDateMonthList();
			ArrayList<ArrayList<Integer>> typeMonthList = widget.getTypeMonthList();
			if (dateMonthlist == null || typeMonthList == null)
				return;

			if (dateMonthlist.size() != typeMonthList.size())
				return;

			ArrayList<CalendarDay> calendarDayArrayList = new ArrayList<>();
			ArrayList<Integer> typeArrayList = new ArrayList<>();
			for (int index = 0; index < dateMonthlist.size(); ++index)
			{
				calendarDayArrayList = dateMonthlist.get(index);
				if (calendarDayArrayList == null)
					continue;

				if (calendarDayArrayList.isEmpty())
					continue;

				if (calendarDayArrayList.get(0).getMonth() == iMonth)
				{
					typeArrayList = typeMonthList.get(index);
					break;
				}
			}

			for (MonthView monthView : monthViewLinkedList)
			{
				if (monthView.getMonth().getMonth() == iMonth)
				{
					monthView.loadDateList(calendarDayArrayList, typeArrayList, dateMonthlist);
					monthView.invalidateDecorators();
				}
			}
		}
	}

	class HandleBothDecorateListener implements BaseSelectorDecorator.OnShouldDecorateListener
	{
		@Override
		public boolean shouldDecorate(CalendarDay day)
		{
			if (m_schedularDateListAll.size() != m_schedularTypeListAll.size())
				return false;

			ArrayList<Date> dateArrayList = new ArrayList<>();
			ArrayList<Integer> typeArrayList = new ArrayList<>();
			Date date = new Date();
			for (int index = 0; index < m_schedularDateListAll.size(); ++index)
			{
				dateArrayList = m_schedularDateListAll.get(index);
				typeArrayList = m_schedularTypeListAll.get(index);
				if (dateArrayList.size() != typeArrayList.size())
					return false;

				for (int indexDay = 0; indexDay < dateArrayList.size(); indexDay++)
				{
					date = dateArrayList.get(indexDay);
					CalendarDay calendarDay = CalendarDay.from(date);
					if (calendarDay.getMonth() != day.getMonth())
						break;

					if (calendarDay.getDay() != day.getDay())
						continue;

					Integer type = typeArrayList.get(indexDay);
					return (type == DataConfig.SELECT_DAY_TYEP_ALL);
				}
			}

			return false;
		}
	}

	class HandleDayDecorateListener implements BaseSelectorDecorator.OnShouldDecorateListener
	{

		@Override
		public boolean shouldDecorate(CalendarDay day)
		{
			if (m_schedularDateListAll.size() != m_schedularTypeListAll.size())
				return false;

			ArrayList<Date> dateArrayList = new ArrayList<>();
			ArrayList<Integer> typeArrayList = new ArrayList<>();
			Date date = new Date();
			for (int index = 0; index < m_schedularDateListAll.size(); ++index)
			{
				dateArrayList = m_schedularDateListAll.get(index);
				typeArrayList = m_schedularTypeListAll.get(index);
				if (dateArrayList.size() != typeArrayList.size())
					return false;

				for (int indexDay = 0; indexDay < dateArrayList.size(); indexDay++)
				{
					date = dateArrayList.get(indexDay);
					CalendarDay calendarDay = CalendarDay.from(date);
					if (calendarDay.getMonth() != day.getMonth())
						break;

					if (calendarDay.getDay() != day.getDay())
						continue;

					Integer type = typeArrayList.get(indexDay);
					return (type == DataConfig.SELECT_DAY_TYEP_DAY);
				}
			}

			return false;
		}
	}

	class HandleNightDecorateListener implements BaseSelectorDecorator.OnShouldDecorateListener
	{

		@Override
		public boolean shouldDecorate(CalendarDay day)
		{
			if (m_schedularDateListAll.size() != m_schedularTypeListAll.size())
				return false;

			ArrayList<Date> dateArrayList = new ArrayList<>();
			ArrayList<Integer> typeArrayList = new ArrayList<>();
			Date date = new Date();
			for (int index = 0; index < m_schedularDateListAll.size(); ++index)
			{
				dateArrayList = m_schedularDateListAll.get(index);
				typeArrayList = m_schedularTypeListAll.get(index);
				if (dateArrayList.size() != typeArrayList.size())
					return false;

				for (int indexDay = 0; indexDay < dateArrayList.size(); indexDay++)
				{
					date = dateArrayList.get(indexDay);
					CalendarDay calendarDay = CalendarDay.from(date);
					if (calendarDay.getMonth() != day.getMonth())
						break;

					if (calendarDay.getDay() != day.getDay())
						continue;

					Integer type = typeArrayList.get(indexDay);
					return (type == DataConfig.SELECT_DAY_TYEP_NIGHT);
				}
			}

			return false;
		}
	}

	class HandleDayClickEvent implements OnDateChangedListener
	{
		@Override
		public void onDateChanged(
				@NonNull
				MaterialCalendarView widget,
				@Nullable
				CalendarDay date)
		{
			//01. 判断是否可点击,在可选日期列表中
			int month = date.getMonth();
			ArrayList<Date> dateArrayList = new ArrayList<>();
			ArrayList<Integer> typeArrayList = new ArrayList<>();
			Date tmpDate = new Date();
			LinkedList<MonthView> monthViewLinkedList = widget.getMonthViewList();
			boolean bFindFlag = false;
			for (int index = 0; index < m_schedularDateListAll.size(); ++index)
			{
				dateArrayList = m_schedularDateListAll.get(index);
				typeArrayList = m_schedularTypeListAll.get(index);
				if (dateArrayList.size() != typeArrayList.size())
					return;

				for (int indexDay = 0; indexDay < dateArrayList.size(); indexDay++)
				{
					tmpDate = dateArrayList.get(indexDay);
					CalendarDay calendarDay = CalendarDay.from(tmpDate);
					if (calendarDay.getMonth() != date.getMonth())
						break;

					if (calendarDay.getDay() != date.getDay())
						continue;

					Integer type = typeArrayList.get(indexDay);
					int iNext = (++type) % DataConfig.MAX_SELECT_DAY_TYEP;
					typeArrayList.set(indexDay, iNext);

					for (MonthView monthView : monthViewLinkedList)
					{
						if (monthView.getMonth().getMonth() == month)
						{
							monthView.setTypeList(typeArrayList);
							bFindFlag = true;
							break;
						}
					}

					if (bFindFlag)
					{
						break;
					}
				}

				if (bFindFlag)
				{
					break;
				}
			}

			for (MonthView monthView : monthViewLinkedList)
			{
				monthView.invalidateDecorators();
			}
			return;
		}
	}
}
