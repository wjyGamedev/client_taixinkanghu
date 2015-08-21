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

package com.taixinkanghu.app.model.data;

import java.io.Serializable;

public class DNurseOrderConfirmPage implements Serializable
{
	private static DNurseOrderConfirmPage s_nurseOrderConfirm = new DNurseOrderConfirmPage();

	private int    m_nurseID             = -1;    //护工ID
	private int    m_nurseHeaderImgResID = -1;    //护工头像ID
	private String m_nurseName           = null;    //护工名字
	private String m_nurseJobNum         = null;    //护工的工号
	private String m_nursingLevel        = null;    //护理级别
	private String m_serviceDate         = null;    //服务时间
	private String m_serviceAddress      = null;    //服务地点

	private int m_allNum   = 0;    //全天24小时服务的天数
	private int m_dayNum   = 0;    //白天12小时服务的天数
	private int m_nightNum = 0;    //夜间12小时服务的天数

	private int m_chargePerAll   = 0;    //全天24小时服务的单价
	private int m_chargePerDay   = 0;    //白天12小时服务的单价
	private int m_chargePerNight = 0;    //夜间12小时服务的单价
	private int m_totalCharge    = 0;    //合计

	private DNurseOrderConfirmPage()
	{}

	public static DNurseOrderConfirmPage GetInstance()
	{
		return s_nurseOrderConfirm;
	}

	public void clearup()
	{
		m_nurseID = -1;    //护工ID
		m_nurseHeaderImgResID = -1;    //护工头像ID
		m_nurseName = null;    //护工名字
		m_nurseJobNum = null;    //护工的工号
		m_nursingLevel = null;    //护理级别
		m_serviceDate = null;    //服务时间
		m_serviceAddress = null;    //服务地点

		m_allNum = 0;    //全天24小时服务的天数
		m_dayNum      = 0;    //白天12小时服务的天数
		m_nightNum    = 0;    //夜间12小时服务的天数

		m_chargePerAll = 0;	//全天24小时服务的单价
		m_chargePerDay = 0;	//白天12小时服务的单价
		m_chargePerNight = 0;	//夜间12小时服务的单价
		m_totalCharge = 0;    //合计
	}

	public boolean isInitialized()
	{
		return (m_nurseID != -1);
	}

	public int getNurseID()
	{
		return m_nurseID;
	}

	public void setNurseID(int nurseID)
	{
		m_nurseID = nurseID;
	}

	public int getNurseHeaderImgResID()
	{
		return m_nurseHeaderImgResID;
	}

	public void setNurseHeaderImgResID(int nurseHeaderImgResID)
	{
		m_nurseHeaderImgResID = nurseHeaderImgResID;
	}

	public String getNurseName()
	{
		return m_nurseName;
	}

	public void setNurseName(String nurseName)
	{
		m_nurseName = nurseName;
	}

	public String getNurseJobNum()
	{
		return m_nurseJobNum;
	}

	public void setNurseJobNum(String nurseJobNum)
	{
		m_nurseJobNum = nurseJobNum;
	}

	public String getNursingLevel()
	{
		return m_nursingLevel;
	}

	public void setNursingLevel(String nursingLevel)
	{
		m_nursingLevel = nursingLevel;
	}

	public String getServiceDate()
	{
		return m_serviceDate;
	}

	public void setServiceDate(String serviceDate)
	{
		m_serviceDate = serviceDate;
	}

	public String getServiceAddress()
	{
		return m_serviceAddress;
	}

	public void setServiceAddress(String serviceAddress)
	{
		m_serviceAddress = serviceAddress;
	}

	public int getAllNum()
	{
		return m_allNum;
	}

	public void setAllNum(int allNum)
	{
		m_allNum = allNum;
	}

	public int getDayNum()
	{
		return m_dayNum;
	}

	public void setDayNum(int dayNum)
	{
		m_dayNum = dayNum;
	}

	public int getNightNum()
	{
		return m_nightNum;
	}

	public void setNightNum(int nightNum)
	{
		m_nightNum = nightNum;
	}

	public int getChargePerAll()
	{
		return m_chargePerAll;
	}

	public void setChargePerAll(int chargePerAll)
	{
		m_chargePerAll = chargePerAll;
	}

	public int getChargePerDay()
	{
		return m_chargePerDay;
	}

	public void setChargePerDay(int chargePerDay)
	{
		m_chargePerDay = chargePerDay;
	}

	public int getChargePerNight()
	{
		return m_chargePerNight;
	}

	public void setChargePerNight(int chargePerNight)
	{
		m_chargePerNight = chargePerNight;
	}

	public int getTotalCharge()
	{
		return m_totalCharge;
	}

	public void setTotalCharge(int totalCharge)
	{
		m_totalCharge = totalCharge;
	}
}
