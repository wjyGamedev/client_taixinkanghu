/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.config.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/15		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.config;

import android.text.TextUtils;

import com.taixinkanghu.R;
import com.taixinkanghu.util.android.AppUtil;

public class EnumConfig
{
	//01. 性别：男，女
	public enum GenderStatus
	{
		MALE(AppUtil.GetResources().getString(R.string.gender_male), 1),
		FEMALE(AppUtil.GetResources().getString(R.string.gender_female), 2);

		private String m_name = null;
		private int m_id = 1;

		private GenderStatus(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return (m_name + ":" + m_id);
		}

		public static GenderStatus valueOf(int id) {    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return MALE;
			case 2:
				return FEMALE;
			default:
				return null;
			}
		}
	}

	//02. 年龄
	public enum AgeRage{

		AGE_0_15(AppUtil.GetResources().getString(R.string.btn_age_section_0_15_text), 1),
		AGE_16_35(AppUtil.GetResources().getString(R.string.btn_age_section_16_35_text), 2),
		AGE_36_55(AppUtil.GetResources().getString(R.string.btn_age_section_36_55_text), 3),
		AGE_56_75(AppUtil.GetResources().getString(R.string.btn_age_section_56_75_text), 4),
		AGE_MORE_THAN_76(AppUtil.GetResources().getString(R.string.btn_age_section_above_75_text), 5);

		private String m_name = null;
		private int m_id = 1;

		private AgeRage(String name , int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("id:" + m_id);
		}

		public static AgeRage valueOf(int id)
		{    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return AGE_0_15;
			case 2:
				return AGE_16_35;
			case 3:
				return AGE_36_55;
			case 4:
				return AGE_56_75;
			case 5:
				return AGE_MORE_THAN_76;
			default:
				return null;
			}
		}

		public static AgeRage valueOfFromString(String name)
		{    //    手写的从int到enum的转换函数
			if (TextUtils.equals(name, AGE_0_15.getName()))
			{
				return AGE_0_15;
			}
			else if (TextUtils.equals(name, AGE_16_35.getName()))
			{
				return AGE_16_35;
			}
			else if (TextUtils.equals(name, AGE_36_55.getName()))
			{
				return AGE_36_55;
			}
			else if (TextUtils.equals(name, AGE_56_75.getName()))
			{
				return AGE_56_75;
			}
			else if (TextUtils.equals(name, AGE_MORE_THAN_76.getName()))
			{
				return AGE_MORE_THAN_76;
			}
			else
			{
				return AGE_0_15;
			}
		}

	}

	//03. 体重:千克/公斤
	public enum WeightRage{

		WEIGHT_0_35(AppUtil.GetResources().getString(R.string.btn_weight_section_0_35_text), 1),
		WEIGHT_35_50(AppUtil.GetResources().getString(R.string.btn_weight_section_35_50_text), 2),
		WEIGHT_50_80(AppUtil.GetResources().getString(R.string.btn_weight_section_50_80_text), 3),
		WEIGHT_80_120(AppUtil.GetResources().getString(R.string.btn_weight_section_80_120_text), 4),
		WEIGHT_MORE_THAN_120(AppUtil.GetResources().getString(R.string.btn_weight_section_above_120_text), 5);

		private String m_name = null;
		private int m_id = 1;

		private WeightRage(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("id:" + m_id);
		}

		public static WeightRage valueOf(int id)
		{    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return WEIGHT_0_35;
			case 2:
				return WEIGHT_35_50;
			case 3:
				return WEIGHT_50_80;
			case 4:
				return WEIGHT_80_120;
			case 5:
				return WEIGHT_MORE_THAN_120;
			default:
				return null;
			}
		}

		public static WeightRage valueOfFromString(String name)
		{    //    手写的从int到enum的转换函数
			if (TextUtils.equals(name, WEIGHT_0_35.getName()))
			{
				return WEIGHT_0_35;
			}
			else if (TextUtils.equals(name, WEIGHT_35_50.getName()))
			{
				return WEIGHT_35_50;
			}
			else if (TextUtils.equals(name, WEIGHT_50_80.getName()))
			{
				return WEIGHT_50_80;
			}
			else if (TextUtils.equals(name, WEIGHT_80_120.getName()))
			{
				return WEIGHT_80_120;
			}
			else if (TextUtils.equals(name, WEIGHT_MORE_THAN_120.getName()))
			{
				return WEIGHT_MORE_THAN_120;
			}
			else
			{
				return WEIGHT_0_35;
			}
		}

	}

	//04. 病人状态：可自理，半自理，不可自理
	public enum PatientState{

		PATIENT_STATE_CARE_MYSELF(AppUtil.GetResources().getString(R.string.content_care_myself), 1),
		PATIENT_STATE_HALF_CARE_MYSELF(AppUtil.GetResources().getString(R.string.content_half_care_myself), 2),
		PATIENT_STATE_CANNT_CARE_MYSELF(AppUtil.GetResources().getString(R.string.content_not_care_myself), 3);

		private String m_name = null;
		private int m_id = 1;

		private PatientState(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("[id:=" + m_id + "][name:" +m_name+"]");
		}

		public static PatientState valueOf(int id) {    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return PATIENT_STATE_CARE_MYSELF;
			case 2:
				return PATIENT_STATE_HALF_CARE_MYSELF;
			case 3:
				return PATIENT_STATE_CANNT_CARE_MYSELF;
			default:
				return null;
			}
		}
	}

	//05. 订单状态
	public enum NurseOrderStatus
	{
		WAIT_PAYMENT(AppUtil.GetResources().getString(R.string.nurse_order_status_wait_payment), 1),
		WAIT_SERVICE(AppUtil.GetResources().getString(R.string.nurse_order_status_wait_service), 2),
		IN_SERVICE(AppUtil.GetResources().getString(R.string.nurse_order_status_in_service), 3),
		WAIT_EVALUATION(AppUtil.GetResources().getString(R.string.nurse_order_status_wait_evaluation), 4),
		FINISHED(AppUtil.GetResources().getString(R.string.nurse_order_status_finished), 5),
		CANCELED(AppUtil.GetResources().getString(R.string.nurse_order_status_canceled), 6),
		WAIT_CASH_PAYMENT(AppUtil.GetResources().getString(R.string.nurse_order_status_wait_cash_payment), 7);



		private String m_name = null;
		private int m_id = 0;

		private NurseOrderStatus(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("[id:=" + m_id + "][name:" +m_name+"]");
		}

		public static NurseOrderStatus valueOf(int id) {    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return WAIT_PAYMENT;
			case 2:
				return WAIT_SERVICE;
			case 3:
				return IN_SERVICE;
			case 4:
				return WAIT_EVALUATION;
			case 5:
				return FINISHED;
			case 6:
				return CANCELED;
			case 7:
				return WAIT_CASH_PAYMENT;
			default:
				return null;
			}
		}
	}

	//06. 护工服务状态
	public enum NurseServiceStatus
	{
		FREE(AppUtil.GetResources().getString(R.string.nurse_service_status_free), 1),
		IN_SERVICE(AppUtil.GetResources().getString(R.string.nurse_service_status_in_service), 2),
		CANNT_SERVICE(AppUtil.GetResources().getString(R.string.nurse_service_status_cannt_service), 3);

		private String m_name = null;
		private int m_id = 0;

		private NurseServiceStatus(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("[id:=" + m_id + "][name:" +m_name+"]");
		}

		public static NurseServiceStatus valueOf(int id) {    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return FREE;
			case 2:
				return IN_SERVICE;
			case 3:
				return CANNT_SERVICE;
			default:
				return null;
			}
		}
	}

	//07. 选择护理时间的类型
	public enum NurseServiceDayStatus
	{
		ALL(AppUtil.GetResources().getString(R.string.nurse_service_day_status_all), 0),
		DAY(AppUtil.GetResources().getString(R.string.nurse_service_day_status_day), 1),
		NIGHT(AppUtil.GetResources().getString(R.string.nurse_service_day_status_night), 2);


		private String m_name = null;
		private int m_id = 0;

		private NurseServiceDayStatus(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("[id:=" + m_id + "][name:" +m_name+"]");
		}

		public static NurseServiceDayStatus valueOf(int id) {    //    手写的从int到enum的转换函数
			switch (id) {
			case 0:
				return ALL;
			case 1:
				return DAY;
			case 2:
				return NIGHT;
			default:
				return null;
			}
		}


	}

	//08. 护理模块的业务类型
	//不同的状态，每个page的action不同。
	public enum NursingModuleStatus
	{
		APIOT_NURSING(AppUtil.GetResources().getString(R.string.nuring_module_status_apiot_nursing), 1),
		REPEAT_ORDER(AppUtil.GetResources().getString(R.string.nuring_module_repeat_order), 2),
		CHANGE_NURSE(AppUtil.GetResources().getString(R.string.nuring_module_change_nurse), 3),
		PAY_MORE(AppUtil.GetResources().getString(R.string.nuring_module_pay_more), 4);


		private String m_name = null;
		private int m_id = 0;

		private NursingModuleStatus(String name, int id)
		{
			m_name = name;
			m_id = id;
		}

		public String getName()
		{
			return m_name;
		}

		public int getId()
		{
			return m_id;
		}

		@Override
		public String toString()
		{
			return ("[id:=" + m_id + "][name:" +m_name+"]");
		}

		public static NursingModuleStatus valueOf(int id) {    //    手写的从int到enum的转换函数
			switch (id) {
			case 1:
				return APIOT_NURSING;
			case 2:
				return REPEAT_ORDER;
			case 3:
				return CHANGE_NURSE;
			case 4:
				return PAY_MORE;
			default:
				return null;
			}
		}

	}

}
