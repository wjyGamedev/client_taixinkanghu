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
 * 2015/8/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.page;

import java.io.Serializable;

public class DNurseOrderConfirmPage implements Serializable
{
	private int    m_nurseID             = -1;    //护工ID
	private Object m_syncNurseID = new Object();

	private int    m_nurseHeaderImgResID = -1;    //护工头像ID
	private Object m_syncNurseHeaderImgResID = new Object();

	private String m_nurseName           = null;    //护工名字
	private Object m_syncNurseName = new Object();

	private String m_nurseJobNum         = null;    //护工的工号
	private Object m_syncNurseJobNum = new Object();

	private String m_nursingLevel        = null;    //护理级别
	private Object m_syncNursingLevel = new Object();

	private String m_serviceDate         = null;    //服务时间
	private Object m_syncServiceDate = new Object();

	private String m_serviceAddress      = null;    //服务地点
	private Object m_syncServiceAddress = new Object();

	private int m_allNum   = 0;    //全天24小时服务的天数
	private Object m_syncAllNum = new Object();

	private int m_dayNum   = 0;    //白天12小时服务的天数
	private Object m_syncDayNum = new Object();

	private int m_nightNum = 0;    //夜间12小时服务的天数
	private Object m_syncNightNum = new Object();

	private int m_chargePerAll   = 0;    //全天24小时服务的单价
	private Object m_syncChargePerAll = new Object();

	private int m_chargePerDay   = 0;    //白天12小时服务的单价
	private Object m_syncChargePerDay = new Object();

	private int m_chargePerNight = 0;    //夜间12小时服务的单价
	private Object m_syncChargePerNight = new Object();

	private int m_totalCharge    = 0;    //合计
	private Object m_syncTotalCharge = new Object();

	public DNurseOrderConfirmPage()
	{}

	public void clearup()
	{
		synchronized (m_syncNurseID)
		{
			m_nurseID = -1;    //护工ID
		}

		synchronized (m_syncNurseHeaderImgResID)
		{
			m_nurseHeaderImgResID = -1;    //护工头像ID
		}

		synchronized (m_syncNurseName)
		{
			m_nurseName = null;    //护工名字
		}

		synchronized (m_syncNurseJobNum)
		{
			m_nurseJobNum = null;    //护工的工号
		}

		synchronized (m_syncNursingLevel)
		{
			m_nursingLevel = null;    //护理级别
		}

		synchronized (m_syncServiceDate)
		{
			m_serviceDate = null;    //服务时间
		}

		synchronized (m_syncServiceAddress)
		{
			m_serviceAddress = null;    //服务地点
		}

		synchronized (m_syncAllNum)
		{
			m_allNum = 0;    //全天24小时服务的天数
		}

		synchronized (m_syncDayNum)
		{
			m_dayNum = 0;    //白天12小时服务的天数
		}

		synchronized (m_syncNightNum)
		{
			m_nightNum = 0;    //夜间12小时服务的天数
		}

		synchronized (m_syncChargePerAll)
		{
			m_chargePerAll = 0;    //全天24小时服务的单价
		}

		synchronized (m_syncChargePerDay	)
		{
			m_chargePerDay = 0;    //白天12小时服务的单价
		}

		synchronized (m_syncChargePerNight)
		{
			m_chargePerNight = 0;    //夜间12小时服务的单价
		}

		synchronized (m_syncTotalCharge)
		{
			m_totalCharge = 0;    //合计
		}
	}

	public int getNurseID()
	{
		synchronized (m_syncNurseID)
		{
			return m_nurseID;
		}
	}

	public void setNurseID(int nurseID)
	{
		synchronized (m_syncNurseID)
		{
			m_nurseID = nurseID;
		}
	}

	public int getNurseHeaderImgResID()
	{
		synchronized (m_syncNurseHeaderImgResID)
		{
			return m_nurseHeaderImgResID;
		}
	}

	public void setNurseHeaderImgResID(int nurseHeaderImgResID)
	{
		synchronized (m_syncNurseHeaderImgResID)
		{
			m_nurseHeaderImgResID = nurseHeaderImgResID;
		}
	}

	public String getNurseName()
	{
		synchronized (m_syncNurseName)
		{
			return m_nurseName;
		}
	}

	public void setNurseName(String nurseName)
	{
		synchronized (m_syncNurseName)
		{
			m_nurseName = nurseName;
		}
	}

	public String getNurseJobNum()
	{
		synchronized (m_syncNurseJobNum)
		{
			return m_nurseJobNum;
		}
	}

	public void setNurseJobNum(String nurseJobNum)
	{
		synchronized (m_syncNurseJobNum)
		{
			m_nurseJobNum = nurseJobNum;
		}
	}

	public String getNursingLevel()
	{
		synchronized (m_syncNursingLevel)
		{
			return m_nursingLevel;
		}
	}

	public void setNursingLevel(String nursingLevel)
	{
		synchronized (m_syncNursingLevel)
		{
			m_nursingLevel = nursingLevel;
		}
	}

	public String getServiceDate()
	{
		synchronized (m_syncServiceDate)
		{
			return m_serviceDate;
		}
	}

	public void setServiceDate(String serviceDate)
	{
		synchronized (m_syncServiceDate)
		{
			m_serviceDate = serviceDate;
		}
	}

	public String getServiceAddress()
	{
		synchronized (m_syncServiceAddress)
		{
			return m_serviceAddress;
		}
	}

	public void setServiceAddress(String serviceAddress)
	{
		synchronized (m_syncServiceAddress)
		{
			m_serviceAddress = serviceAddress;
		}
	}

	public int getAllNum()
	{
		synchronized (m_syncAllNum)
		{
			return m_allNum;
		}
	}

	public void setAllNum(int allNum)
	{
		synchronized (m_syncAllNum)
		{
			m_allNum = allNum;
		}
	}

	public int getDayNum()
	{
		synchronized (m_syncDayNum)
		{
			return m_dayNum;
		}
	}

	public void setDayNum(int dayNum)
	{
		synchronized (m_syncDayNum)
		{
			m_dayNum = dayNum;
		}
	}

	public int getNightNum()
	{
		synchronized (m_syncNightNum)
		{
			return m_nightNum;
		}
	}

	public void setNightNum(int nightNum)
	{
		synchronized (m_syncNightNum)
		{
			m_nightNum = nightNum;
		}
	}

	public int getChargePerAll()
	{
		synchronized (m_syncChargePerAll)
		{
			return m_chargePerAll;
		}
	}

	public void setChargePerAll(int chargePerAll)
	{
		synchronized (m_syncChargePerAll)
		{
			m_chargePerAll = chargePerAll;
		}
	}

	public int getChargePerDay()
	{
		synchronized (m_syncChargePerDay)
		{
			return m_chargePerDay;
		}
	}

	public void setChargePerDay(int chargePerDay)
	{
		synchronized (m_syncChargePerDay)
		{
			m_chargePerDay = chargePerDay;
		}
	}

	public int getChargePerNight()
	{
		synchronized (m_syncChargePerNight)
		{
			return m_chargePerNight;
		}
	}

	public void setChargePerNight(int chargePerNight)
	{
		synchronized (m_syncChargePerNight)
		{
			m_chargePerNight = chargePerNight;
		}
	}

	public int getTotalCharge()
	{
		synchronized (m_syncTotalCharge)
		{
			return m_totalCharge;
		}
	}

	public void setTotalCharge(int totalCharge)
	{
		synchronized (m_syncTotalCharge)
		{
			m_totalCharge = totalCharge;
		}
	}
}
