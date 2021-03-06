/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.net;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.config.NurseSeniorListConfig;
import com.taixinkanghu.util.android.AppUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DScheduleList
{
	private ArrayList<Calendar> m_allCalendarList   = new ArrayList<>();
	private ArrayList<Calendar> m_dayCalendarList   = new ArrayList<>();
	private ArrayList<Calendar> m_nightCalendarList = new ArrayList<>();
	private SimpleDateFormat    m_simpleDateFormat  = new SimpleDateFormat(DateConfig.PATTERN_DATE_YEAR_MONTH_DAY);

	public boolean serialization(JSONObject response) throws JSONException, ParseException
	{
		JSONObject jsonDateList = response.getJSONObject(NurseSeniorListConfig.SCHEDULE_DATE_LIST);
		if (jsonDateList == null)
		{
			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
			throw new JsonSerializationException(errMsg + ":" + NurseSeniorListConfig.SCHEDULE_DATE_LIST);
		}

		m_allCalendarList = serializeDate(jsonDateList, NurseSeniorListConfig.DATE_ALL_LIST);
		m_dayCalendarList = serializeDate(jsonDateList, NurseSeniorListConfig.DATE_DAY_LIST);
		m_nightCalendarList = serializeDate(jsonDateList, NurseSeniorListConfig.DATE_NIGHT_LIST);

		return true;
	}

	private ArrayList<Calendar> serializeDate(JSONObject jsonDateList, String key) throws JSONException, ParseException
	{
		ArrayList<Calendar> calendarList = new ArrayList<>();
		JSONArray jsonDateAllList = jsonDateList.getJSONArray(key);
		String    strDate            = null;
		for (int index = 0; index < jsonDateAllList.length(); ++index)
		{
			strDate = (String)jsonDateAllList.get(index);
			Date date = m_simpleDateFormat.parse(strDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendarList.add(calendar);
		}
		return calendarList;
	}

	public ArrayList<Calendar> getAllCalendarList()
	{
		return m_allCalendarList;
	}

	public ArrayList<Calendar> getDayCalendarList()
	{
		return m_dayCalendarList;
	}

	public ArrayList<Calendar> getNightCalendarList()
	{
		return m_nightCalendarList;
	}

	public Date getBeginDate()
	{
		Date minDate = null;
		for (Calendar calendarAll : m_allCalendarList)
		{
			Date tmpAllDate = calendarAll.getTime();
			if (minDate == null)
			{
				minDate = tmpAllDate;
				continue;
			}

			if (tmpAllDate.getTime() <= minDate.getTime())
			{
				minDate = tmpAllDate;
				continue;
			}
		}

		for (Calendar calendarDay : m_dayCalendarList)
		{
			Date tmpDayDate = calendarDay.getTime();
			if (minDate == null)
			{
				minDate = tmpDayDate;
				continue;
			}

			if (tmpDayDate.getTime() <= minDate.getTime())
			{
				minDate = tmpDayDate;
				continue;
			}
		}

		for (Calendar calendarNight : m_nightCalendarList)
		{
			Date tmpNightDate = calendarNight.getTime();
			if (minDate == null)
			{
				minDate = tmpNightDate;
				continue;
			}

			if (tmpNightDate.getTime() <= minDate.getTime())
			{
				minDate = tmpNightDate;
				continue;
			}
		}

		return minDate;

	}

	public Date getEndDate()
	{
		Date maxDate = null;
		for (Calendar calendarAll : m_allCalendarList)
		{
			Date tmpAllDate = calendarAll.getTime();
			if (maxDate == null)
			{
				maxDate = tmpAllDate;
				continue;
			}

			if (tmpAllDate.getTime() >= maxDate.getTime())
			{
				maxDate = tmpAllDate;
				continue;
			}
		}

		for (Calendar calendarDay : m_dayCalendarList)
		{
			Date tmpDayDate = calendarDay.getTime();
			if (maxDate == null)
			{
				maxDate = tmpDayDate;
				continue;
			}

			if (tmpDayDate.getTime() >= maxDate.getTime())
			{
				maxDate = tmpDayDate;
				continue;
			}
		}

		for (Calendar calendarNight : m_nightCalendarList)
		{
			Date tmpNightDate = calendarNight.getTime();
			if (maxDate == null)
			{
				maxDate = tmpNightDate;
				continue;
			}

			if (tmpNightDate.getTime() >= maxDate.getTime())
			{
				maxDate = tmpNightDate;
				continue;
			}
		}

		return maxDate;
	}

}
