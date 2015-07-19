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

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.NurseServiceStatus;
import com.taixinkanghu.app.model.config.NursingExp;
import com.taixinkanghu.app.model.config.NursingLevel;

import org.json.JSONException;
import org.json.JSONObject;

public class DNurseBasics
{
	public boolean serialization(JSONObject response)
	{
		try
		{
			m_iID = response.getInt(DataConfig.NURSE_ID);
			m_iHospitalID = response.getInt(DataConfig.NURSE_HOSPITAL_ID);
			m_strName = response.getString(DataConfig.NURSE_NAME);
			m_iAge = response.getInt(DataConfig.NURSE_AGE);
			m_strHomeTown = response.getString(DataConfig.NURSE_HOMETOWN);

			int itmp = 0;
			itmp = response.getInt(DataConfig.NURSE_NURING_EXP);
			m_strNursingExp = NursingExp.GetExpByInteger(itmp);

			itmp = response.getInt(DataConfig.NURSE_NURING_LEVEL);
			m_strNursingLevel = NursingLevel.GetLevelByInteger(itmp);

			m_iServiceChargePerDay = response.getInt(DataConfig.NURSE_SERVICE_CHARGE_PER_DAY);

			itmp = response.getInt(DataConfig.NURSE_SERVICE_STATUS);
			m_strServiceStatus = NurseServiceStatus.GetStatusByInteger(itmp);


		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return  false;
		}
		return true;
	}

	public int getiID()
	{
		return m_iID;
	}

	public int getiHospitalID()
	{
		return m_iHospitalID;
	}

	public String getStrName()
	{
		return m_strName;
	}

	public int getiAge()
	{
		return m_iAge;
	}

	public String getStrHomeTown()
	{
		return m_strHomeTown;
	}

	public String getStrNursingExp()
	{
		return m_strNursingExp;
	}

	public String getStrNursingLevel()
	{
		return m_strNursingLevel;
	}

	public int getiServiceChargePerDay()
	{
		return m_iServiceChargePerDay;
	}

	public String getStrServiceStatus()
	{
		return m_strServiceStatus;
	}

	/**
	 * 数据区
	 */
	private int    m_iID             = 0;          //ID
	private int	m_iHospitalID	   = 0;			 //hospital ID
	private String m_strName         = null;      //姓名
	private int    m_iAge            = 0;         //年龄
	private String m_strHomeTown     = null; 	//籍贯
	private String m_strNursingExp   = null;		//护理经验
	private String m_strNursingLevel = null;    //护理级别
	private int	m_iServiceChargePerDay = 0;  //每天的服务费
	private String m_strServiceStatus = null;	//服务状态

}
