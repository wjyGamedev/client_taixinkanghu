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
 * 2015/7/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import com.taixinkanghu.app.model.event.net.config.NurseBasicListConfig;
import com.taixinkanghu.util.nurse.NurseUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class DNurseBasics
{
	/**
	 * 数据区
	 */
	private int    m_iID             = 0;          //ID
	private int    m_iHospitalID     = 0;             //hospital ID
	private String m_strName         = null;      //姓名
	private int    m_iStarLevel      = 0;            //星级
	private int    m_iAge            = 0;         //年龄
	private String m_strHomeTown     = null;    //籍贯
	private String m_strNursingExp   = null;        //护理经验
	private String m_strNursingLevel = null;    //护理级别

	private int m_serviceChargePerAllCare      = 0;            //24小时，可自理
	private int m_serviceChargePerAllHalfCare  = 0;        //24小时，半自理
	private int m_serviceChargePerAllCanntCare = 0;        //24小时，不可自理

	private int m_serviceChargePerDayCare      = 0;            //12白，可自理
	private int m_serviceChargePerDayHalfCare  = 0;        //12白，半自理
	private int m_serviceChargePerDayCanntCare = 0;        //12白，不可自理

	private int m_serviceChargePerNightCare      = 0;            //12黑，可自理
	private int m_serviceChargePerNightHalfCare  = 0;        //12黑，半自理
	private int m_serviceChargePerNightCanntCare = 0;    //黑，不可自理

	private String m_strServiceStatus = null;    //服务状态


	public boolean serialization(JSONObject response) throws JSONException
	{
		m_iID = response.getInt(NurseBasicListConfig.ID);
		m_iHospitalID = response.getInt(NurseBasicListConfig.HOSPITAL_ID);
		m_strName = response.getString(NurseBasicListConfig.NAME);
		m_iStarLevel = response.getInt(NurseBasicListConfig.STAR_LEVEL);
		m_iAge = response.getInt(NurseBasicListConfig.AGE);
		m_strHomeTown = response.getString(NurseBasicListConfig.HOMETOWN);

		int itmp = response.getInt(NurseBasicListConfig.NURING_EXP);
		m_strNursingExp = NurseUtil.GetServiceExpByInteger(itmp);

		itmp = response.getInt(NurseBasicListConfig.NURING_LEVEL);
		m_strNursingLevel = NurseUtil.GetNursingLevelByInteger(itmp);

		m_serviceChargePerAllCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_ALL_CARE);
		m_serviceChargePerAllHalfCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_ALL_HALF_CARE);
		m_serviceChargePerAllCanntCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_ALL_CANNT_CARE);

		m_serviceChargePerDayCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_DAY_CARE);
		m_serviceChargePerDayHalfCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_DAY_HALF_CARE);
		m_serviceChargePerDayCanntCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_DAY_CANNT_CARE);

		m_serviceChargePerNightCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_NIGHT_CARE);
		m_serviceChargePerNightHalfCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_NIGHTL_HALF_CARE);
		m_serviceChargePerNightCanntCare = response.getInt(NurseBasicListConfig.SERVICE_CHARGE_PER_NIGHT_CANNT_CARE);

		itmp = response.getInt(NurseBasicListConfig.SERVICE_STATUS);
		m_strServiceStatus = NurseUtil.GetStatusByInteger(itmp);

		return true;
	}

	public int getID()
	{
		return m_iID;
	}

	public int getHospitalID()
	{
		return m_iHospitalID;
	}

	public String getName()
	{
		return m_strName;
	}

	public int getStarLevel()
	{
		return m_iStarLevel;
	}

	public Integer getAge()
	{
		return m_iAge;
	}

	public String getHomeTown()
	{
		return m_strHomeTown;
	}

	public String getNursingExp()
	{
		return m_strNursingExp;
	}

	public String getNursingLevel()
	{
		return m_strNursingLevel;
	}

	public int getServiceChargePerAllCare()
	{
		return m_serviceChargePerAllCare;
	}

	public int getServiceChargePerAllHalfCare()
	{
		return m_serviceChargePerAllHalfCare;
	}

	public int getServiceChargePerAllCanntCare()
	{
		return m_serviceChargePerAllCanntCare;
	}

	public int getServiceChargePerDayCare()
	{
		return m_serviceChargePerDayCare;
	}

	public int getServiceChargePerDayHalfCare()
	{
		return m_serviceChargePerDayHalfCare;
	}

	public int getServiceChargePerDayCanntCare()
	{
		return m_serviceChargePerDayCanntCare;
	}

	public int getServiceChargePerNightCare()
	{
		return m_serviceChargePerNightCare;
	}

	public int getServiceChargePerNightHalfCare()
	{
		return m_serviceChargePerNightHalfCare;
	}

	public int getServiceChargePerNightCanntCare()
	{
		return m_serviceChargePerNightCanntCare;
	}

	public String getServiceStatus()
	{
		return m_strServiceStatus;
	}


}
