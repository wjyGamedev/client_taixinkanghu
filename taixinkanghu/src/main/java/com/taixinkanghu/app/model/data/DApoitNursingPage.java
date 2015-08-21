/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.appointment_nursing.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/15		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DApoitNursingPage implements Serializable
{
	private static DApoitNursingPage s_dApoitNursingPage = new DApoitNursingPage();

	//姓名
	private String m_name = null;

	//手机号码：
	private String m_phone = null;

	//性别：男，女
	private EnumConfig.SexType m_sexType = null;

	//年龄
	private EnumConfig.AgeRage m_ageRage = null;

	//体重
	private EnumConfig.WeightRage m_weightRage = null;

	//医院
	private int m_hospitalID = -1;

	//科室
	private int m_departmenetID = -1;

	//病人状态
	private EnumConfig.PatientState m_patientState = null;

	//护理时间
	private DNursingDate m_nursingDate = null;

	public static class DNursingDate
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

		public DNursingDate(Date beingDate, Date endDate, ArrayList<ArrayList<Date>> dateListAll, ArrayList<ArrayList<Integer>>
				typeListAll, String dateDescription)
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

	private DApoitNursingPage()
	{
	}

	public static DApoitNursingPage GetInstance()
	{
		return s_dApoitNursingPage;
	}

	//function:get/set
	public String getName()
	{
		return m_name;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public String getPhone()
	{
		return m_phone;
	}

	public void setPhone(String phone)
	{
		m_phone = phone;
	}

	public EnumConfig.SexType getSexType()
	{
		return m_sexType;
	}

	public void setSexType(EnumConfig.SexType sexType)
	{
		m_sexType = sexType;
	}

	public EnumConfig.AgeRage getAgeRage()
	{
		return m_ageRage;
	}

	public void setAgeRage(EnumConfig.AgeRage ageRage)
	{
		m_ageRage = ageRage;
	}

	public EnumConfig.WeightRage getWeightRage()
	{
		return m_weightRage;
	}

	public void setWeightRage(EnumConfig.WeightRage weightRage)
	{
		m_weightRage = weightRage;
	}

	public int getHospitalID()
	{
		return m_hospitalID;
	}

	public void setHospitalID(int hospitalID)
	{
		m_hospitalID = hospitalID;
	}

	public int getDepartmenetID()
	{
		return m_departmenetID;
	}

	public void setDepartmenetID(int departmenetID)
	{
		m_departmenetID = departmenetID;
	}

	public EnumConfig.PatientState getPatientState()
	{
		return m_patientState;
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		m_patientState = patientState;
	}

	public DNursingDate getNursingDate()
	{
		return m_nursingDate;
	}

	public void setNursingDate(DNursingDate nursingDate)
	{
		m_nursingDate = nursingDate;
	}
}
