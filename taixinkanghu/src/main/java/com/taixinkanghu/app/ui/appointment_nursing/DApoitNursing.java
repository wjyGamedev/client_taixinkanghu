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

package com.taixinkanghu.app.ui.appointment_nursing;

import com.taixinkanghu.app.model.config.EnumConfig;

import java.util.ArrayList;
import java.util.Date;

public class DApoitNursing
{
	private static DApoitNursing s_dApoitNursing = new DApoitNursing();

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
	private String m_departmenetName = null;

	//病人状态
	private EnumConfig.PatientState m_patientState = null;

	//护理时间
	public static class DNursingDate
	{
		private Date                          m_beingDate       = null;
		private Date                          m_endDate         = null;
		private ArrayList<ArrayList<Date>>    m_dateListAll     = new ArrayList<>();
		private ArrayList<ArrayList<Integer>> m_typeListAll     = new ArrayList<>();
		private String                        m_dateDescription = null;

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
	}
	private DNursingDate m_dNursingDate = null;

	private DApoitNursing()
	{
	}

	public static DApoitNursing GetInstance()
	{
		return s_dApoitNursing;
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

	public String getDepartmenetName()
	{
		return m_departmenetName;
	}

	public void setDepartmenetName(String departmenetName)
	{
		m_departmenetName = departmenetName;
	}

	public EnumConfig.PatientState getPatientState()
	{
		return m_patientState;
	}

	public void setPatientState(EnumConfig.PatientState patientState)
	{
		m_patientState = patientState;
	}

	public DNursingDate getdNursingDate()
	{
		return m_dNursingDate;
	}

	public void setdNursingDate(DNursingDate dNursingDate)
	{
		m_dNursingDate = dNursingDate;
	}
}
