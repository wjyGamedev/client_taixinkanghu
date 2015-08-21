/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/21		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.page;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DNursingDate
{
	private Date                          m_beingDate        = null;
	private Date                          m_endDate          = null;
	private ArrayList<ArrayList<Date>>    m_dateListAll      = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> m_typeListAll      = new ArrayList<>();
	private String                        m_dateDescription  = null;
	private SimpleDateFormat              m_simpleDateFormat = new SimpleDateFormat(DateConfig.PATTERN_DATE_YEAR_MONTH_DAY);

	private int m_allNum   = 0;
	private int m_dayNum   = 0;
	private int m_nightNum = 0;

	public DNursingDate(Date beingDate, Date endDate, ArrayList<ArrayList<Date>> dateListAll, ArrayList<ArrayList<Integer>> typeListAll,
						String dateDescription)
	{
		m_beingDate = beingDate;
		m_endDate = endDate;
		m_dateListAll = dateListAll;
		m_typeListAll = typeListAll;
		m_dateDescription = dateDescription;
	}

	public Date getBeingDate()
	{
		return m_beingDate;
	}

	public Date getEndDate()
	{
		return m_endDate;
	}

	public ArrayList<ArrayList<Date>> getDateListAll()
	{
		return m_dateListAll;
	}

	public ArrayList<ArrayList<Integer>> getTypeListAll()
	{
		return m_typeListAll;
	}

	public String getDateDescription()
	{
		return m_dateDescription;
	}

	private String getDateDescription(int selectType)
	{
		if (m_dateListAll.size() != m_typeListAll.size())
			return null;

		String schedualDate = null;
		String dateString   = null;
		for (int iMonth = 0; iMonth < m_typeListAll.size(); iMonth++)
		{
			ArrayList<Integer> typeArrayList = m_typeListAll.get(iMonth);
			ArrayList<Date> dateArrayList = m_dateListAll.get(iMonth);

			if (m_typeListAll.size() != m_dateListAll.size())
				return null;

			for (int iDay = 0; iDay < typeArrayList.size(); ++iDay)
			{
				//DataConfig.SELECT_DAY_TYEP_ALL,SELECT_DAY_TYEP_DAY,SELECT_DAY_TYEP_NIGHT
				if (typeArrayList.get(iDay) != selectType)
					continue;

				Date date = dateArrayList.get(iDay);
				dateString = m_simpleDateFormat.format(date);
				if (schedualDate == null)
				{
					schedualDate = (dateString + NurseBasicListConfig.SCHEDULE_SPLIT);
				}
				else
				{
					schedualDate += (dateString + NurseBasicListConfig.SCHEDULE_SPLIT);
				}
			}
		}

		return schedualDate;
	}


	public String getSchedualAllDescription()
	{
		return getDateDescription(DataConfig.SELECT_DAY_TYEP_ALL);
	}

	public String getSchedualDayDescription()
	{
		return getDateDescription(DataConfig.SELECT_DAY_TYEP_DAY);
	}

	public String getSchedualNightDescription()
	{
		return getDateDescription(DataConfig.SELECT_DAY_TYEP_NIGHT);
	}

	public int getAllNum()
	{
		if (m_allNum != 0)
			return m_allNum;

		for (ArrayList<Integer> integerList : m_typeListAll)
		{
			for (Integer integer : integerList)
			{
				if (integer == DataConfig.SELECT_DAY_TYEP_ALL)
				{
					m_allNum++;
					continue;
				}
			}
		}
		return m_allNum;

	}

	public int getDayNum()
	{
		if (m_dayNum != 0)
			return m_dayNum;

		for (ArrayList<Integer> integerList : m_typeListAll)
		{
			for (Integer integer : integerList)
			{
				if (integer == DataConfig.SELECT_DAY_TYEP_DAY)
				{
					m_dayNum++;
					continue;
				}
			}
		}
		return m_dayNum;
	}

	public int getNightNum()
	{
		if (m_nightNum != 0)
			return m_nightNum;

		for (ArrayList<Integer> integerList : m_typeListAll)
		{
			for (Integer integer : integerList)
			{
				if (integer == DataConfig.SELECT_DAY_TYEP_NIGHT)
				{
					m_nightNum++;
					continue;
				}
			}
		}
		return m_nightNum;
	}
}
