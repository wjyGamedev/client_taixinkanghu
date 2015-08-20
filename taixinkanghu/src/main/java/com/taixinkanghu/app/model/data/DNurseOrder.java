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

package com.taixinkanghu.app.model.data;

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
	private int                         m_hospitalID       = -1;
	private int                         m_userID           = -1;
	private int                         m_nurseID          = -1;
	private String                      m_phoneNum         = null;
	private Calendar                    m_orderTime        = Calendar.getInstance();
	private String                      m_patientName      = null;
	private EnumConfig.SexType          m_sexType          = null;
	private String                      m_patientAge       = null;
	private String                      m_patientWeight    = null;
	private EnumConfig.PatientState     m_patientState     = null;
	private String                      m_patientRemark    = null;
	private int                         m_totalCharge      = 0;
	private EnumConfig.NurseOrderStatus m_nurseOrderStatus = null;
	private int                         m_orderID          = -1;
	private String                      m_orderSerialNum   = null;

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
		if (genderId == EnumConfig.SexType.MALE.getId())
		{
			m_sexType = EnumConfig.SexType.MALE;
		}
		else if (genderId == EnumConfig.SexType.FEMALE.getId())
		{
			m_sexType = EnumConfig.SexType.FEMALE;
		}
		else
		{
			throw new JsonSerializationException("genderId is invalid![genderId:=" + genderId + "]");
		}

		m_patientAge = response.getString(NurseOrderConfig.PATIENT_AGE);
		m_patientWeight = response.getString(NurseOrderConfig.PATIENT_WEIGHT);

		int patientStatus = response.getInt(NurseOrderConfig.PATIENT_STATUS);
		if (patientStatus == EnumConfig.PatientState.PATIENT_STATE_CARE_MYSELF.getId())
		{
			m_patientState = EnumConfig.PatientState.PATIENT_STATE_CARE_MYSELF;
		}
		else if (patientStatus == EnumConfig.PatientState.PATIENT_STATE_HALF_CARE_MYSELF.getId())
		{
			m_patientState = EnumConfig.PatientState.PATIENT_STATE_HALF_CARE_MYSELF;
		}
		else if (patientStatus == EnumConfig.PatientState.PATIENT_STATE_CANNT_CARE_MYSELF.getId())
		{
			m_patientState = EnumConfig.PatientState.PATIENT_STATE_CANNT_CARE_MYSELF;
		}
		else
		{
			throw new JsonSerializationException("patientStatus is invalid![patientStatus:=" + genderId + "]");
		}

		m_patientRemark = response.getString(NurseOrderConfig.PATIENT_REMARK);
		m_totalCharge = response.getInt(NurseOrderConfig.ORDER_TOTAL_CHARGE);

		int orderStateID = response.getInt(NurseOrderConfig.ORDER_STATUS);
		if (orderStateID == EnumConfig.NurseOrderStatus.WAIT_PAYMENT.getId())
		{
			m_nurseOrderStatus = EnumConfig.NurseOrderStatus.WAIT_PAYMENT;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.WAIT_SERVICE.getId())
		{
			m_nurseOrderStatus = EnumConfig.NurseOrderStatus.WAIT_SERVICE;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.IN_SERVICE.getId())
		{
			m_nurseOrderStatus = EnumConfig.NurseOrderStatus.IN_SERVICE;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.FINISHED.getId())
		{
			m_nurseOrderStatus = EnumConfig.NurseOrderStatus.FINISHED;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.CANCELED.getId())
		{
			m_nurseOrderStatus = EnumConfig.NurseOrderStatus.CANCELED;
		}
		else if (orderStateID == EnumConfig.NurseOrderStatus.WAIT_EVALUATION.getId())
		{
			m_nurseOrderStatus = EnumConfig.NurseOrderStatus.WAIT_EVALUATION;
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

	public EnumConfig.SexType getSexType()
	{
		return m_sexType;
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

	public EnumConfig.NurseOrderStatus getNurseOrderStatus()
	{
		return m_nurseOrderStatus;
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
