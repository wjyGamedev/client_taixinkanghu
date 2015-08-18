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

import com.taixinkanghu.R;
import com.taixinkanghu.util.android.AppUtil;

public class EnumConfig
{
	//01. 性别：男，女
	public enum SexType {

		MALE(AppUtil.GetResources().getString(R.string.content_male), 1),
		FEMALE(AppUtil.GetResources().getString(R.string.content_female), 2);

		private String m_name = null;
		private int m_id = 1;

		private SexType(String name, int id)
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
	}

}
