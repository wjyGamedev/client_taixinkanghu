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

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.config.NurseSeniorListConfig;
import com.taixinkanghu.util.android.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

public class DNurseSenior implements Serializable
{
	/**
	 * 数据区
	 */
	private int m_ID = 0;                         //ID

	private String m_jobNum         = null;       //工号
	private String m_languageLevel  = null;   //语言水平
	private String m_educationLevel = null;   //文化程度
	private String m_nation         = null;    //民族
	private String m_intro          = null;   //自我介绍
	private String m_departments    = null;  //擅长科室
	private String m_certificate    = null;  //持有证书
	private String m_serviceContent = null;  //服务内容

	private DScheduleList m_scheduleList = new DScheduleList();
	private DCommentList  m_commentList  = new DCommentList();

	public boolean serialization(JSONObject response) throws JSONException, ParseException
	{
		m_ID = response.getInt(NurseSeniorListConfig.ID);
		m_jobNum = response.getString(NurseSeniorListConfig.JOB_NUM);
		m_languageLevel = response.getString(NurseSeniorListConfig.LANGUAGE_LEVEL);
		m_educationLevel = response.getString(NurseSeniorListConfig.EDUCATION);
		m_nation = response.getString(NurseSeniorListConfig.NATION);
		m_intro = response.getString(NurseSeniorListConfig.INTRO);
		m_intro = AppUtil.GetResources().getString(R.string.content_self_intro) + m_intro;
		m_departments = response.getString(NurseSeniorListConfig.DEPARTMENTS);
		m_certificate = response.getString(NurseSeniorListConfig.CERTIFICATE);
		m_serviceContent = response.getString(NurseSeniorListConfig.SERVICE_CONTENT);
		m_serviceContent = AppUtil.GetResources().getString(R.string.content_service_content) + m_serviceContent;

		m_scheduleList.serialization(response);

		m_commentList.serialization(response);
		return true;
	}

	public int getID()
	{
		return m_ID;
	}

	public String getJobNum()
	{
		return m_jobNum;
	}

	public String getLanguageLevel()
	{
		return m_languageLevel;
	}

	public String getEducationLevel()
	{
		return m_educationLevel;
	}

	public String getNation()
	{
		return m_nation;
	}

	public String getIntro()
	{
		return m_intro;
	}

	public String getDepartments()
	{
		return m_departments;
	}

	public String getCertificate()
	{
		return m_certificate;
	}

	public String getServiceContent()
	{
		return m_serviceContent;
	}

	public DScheduleList getScheduleList()
	{
		return m_scheduleList;
	}

	public DCommentList getCommentList()
	{
		return m_commentList;
	}

}
