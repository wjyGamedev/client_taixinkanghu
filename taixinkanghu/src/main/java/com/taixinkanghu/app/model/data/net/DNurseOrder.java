/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data.net;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.net.JsonSerializationException;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DNurseOrder
{
	private int                         m_hospitalID     = DataConfig.DEFAULT_VALUE;
	private int                         m_userID         = DataConfig.DEFAULT_VALUE;
	private int                         m_nurseID        = DataConfig.DEFAULT_VALUE;
	private String                      m_phoneNum       = null;
	private Calendar                    m_orderTime      = Calendar.getInstance();
	private String                      m_patientName    = null;
	private EnumConfig.GenderStatus     m_genderStatus   = null;
	private String                      m_patientAge     = null;
	private String                      m_patientWeight  = null;
	private EnumConfig.PatientState     m_patientState   = null;
	private String                      m_patientRemark  = null;
	private int                         m_totalCharge    = DataConfig.DEFAULT_VALUE;
	private EnumConfig.NurseOrderStatus m_orderStatus    = null;
	private int                         m_orderID        = DataConfig.DEFAULT_VALUE;
	private String                      m_orderSerialNum = null;

	private DScheduleList m_scheduleList = new DScheduleList();

	private SimpleDateFormat m_simpleDateFormat = new SimpleDateFormat(DateConfig.PATTERN_DATE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);


	public boolean serialization(JSONObject response) throws JSONException, ParseException
	{
		m_hospitalID = response.getInt(NurseOrderConfig.HOSPITAL_ID);
		m_userID = response.getInt(NurseOrderConfig.USER_ID);
		m_nurseID = response.getInt(NurseOrderConfig.NURSE_ID);
		m_phoneNum = response.getString(NurseOrderConfig.USER_PHONE_NUM);

		String orderTime = response.getString(NurseOrderConfig.ORDER_TIME);
		Date   date      = m_simpleDateFormat.parse(orderTime);
		m_orderTime.setTime(date);

		m_patientName = response.getString(NurseOrderConfig.PATIENT_NAME);

		int genderId = response.getInt(NurseOrderConfig.PATIENT_GENDER);
		m_genderStatus = EnumConfig.GenderStatus.valueOf(genderId);

		m_patientAge = response.getString(NurseOrderConfig.PATIENT_AGE);
		m_patientWeight = response.getString(NurseOrderConfig.PATIENT_WEIGHT);

		int patientStatus = response.getInt(NurseOrderConfig.PATIENT_STATUS);
		m_patientState = EnumConfig.PatientState.valueOf(patientStatus);

		m_patientRemark = response.getString(NurseOrderConfig.PATIENT_REMARK);
		m_totalCharge = response.getInt(NurseOrderConfig.ORDER_TOTAL_CHARGE);

		int orderStateID = response.getInt(NurseOrderConfig.ORDER_STATUS);
		if (orderStateID == EnumConfig.NurseOrderStatus.WAIT_PAYMENT.getId())
		{
			m_orderStatus = EnumConfig.NurseOrderStatus.WAIT_PAYMENT;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.WAIT_SERVICE.getId())
		{
			m_orderStatus = EnumConfig.NurseOrderStatus.WAIT_SERVICE;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.IN_SERVICE.getId())
		{
			m_orderStatus = EnumConfig.NurseOrderStatus.IN_SERVICE;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.FINISHED.getId())
		{
			m_orderStatus = EnumConfig.NurseOrderStatus.FINISHED;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.CANCELED.getId())
		{
			m_orderStatus = EnumConfig.NurseOrderStatus.CANCELED;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.WAIT_EVALUATION.getId())
		{
			m_orderStatus = EnumConfig.NurseOrderStatus.WAIT_EVALUATION;
		}
		else
		{
			throw new JsonSerializationException("orderStateID is invalid![orderStateID:=" + orderStateID + "]");
		}

		m_orderID = response.getInt(NurseOrderConfig.ORDER_ID);
		m_orderSerialNum = response.getString(NurseOrderConfig.ORDER_SERIAL_NUM);

		//service date
		m_scheduleList.serialization(response);

		return true;

	}

	public int getHospitalID()
	{
		return m_hospitalID;
	}

	public int getUserID()
	{
		return m_userID;
	}

	public int getNurseID()
	{
		return m_nurseID;
	}

	public String getPhoneNum()
	{
		return m_phoneNum;
	}

	public Calendar getOrderTime()
	{
		return m_orderTime;
	}

	public String getPatientName()
	{
		return m_patientName;
	}

	public EnumConfig.GenderStatus getGenderStatus()
	{
		return m_genderStatus;
	}

	public String getPatientAge()
	{
		return m_patientAge;
	}

	public String getPatientWeight()
	{
		return m_patientWeight;
	}

	public EnumConfig.PatientState getPatientState()
	{
		return m_patientState;
	}

	public String getPatientRemark()
	{
		return m_patientRemark;
	}

	public int getTotalCharge()
	{
		return m_totalCharge;
	}

	public EnumConfig.NurseOrderStatus getOrderStatus()
	{
		return m_orderStatus;
	}

	public int getOrderID()
	{
		return m_orderID;
	}

	public String getOrderSerialNum()
	{
		return m_orderSerialNum;
	}

	public DScheduleList getScheduleList()
	{
		return m_scheduleList;
	}
}
