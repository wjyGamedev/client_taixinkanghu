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

import com.taixinkanghu.app.model.event.net.config.NurseSeniorListConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DNurseSenior
{
	/**
	 * 数据区
	 */
	private int m_iID = 0;                         //ID

	private int    m_iJobNum           = 0;       //工号
	private String m_strLanguageLevel  = null;   //语言水平
	private String m_strEducation      = null;   //文化程度
	private String m_strNation         = null;    //民族
	private String m_strIntro          = null;   //自我介绍
	private String m_strDepartments    = null;  //擅长科室
	private String m_strCertificate    = null;  //持有证书
	private String m_strServiceContent = null;  //服务内容

	private DScheduleList m_scheduleList = new DScheduleList();
	private DCommentList  m_commentList  = new DCommentList();

	public boolean serialization(JSONObject response) throws JSONException
	{
		m_iID = response.getInt(NurseSeniorListConfig.ID);
		m_iJobNum = response.getInt(NurseSeniorListConfig.JOB_NUM);
		m_strLanguageLevel = response.getString(NurseSeniorListConfig.LANGUAGE_LEVEL);
		m_strEducation = response.getString(NurseSeniorListConfig.EDUCATION);
		m_strNation = response.getString(NurseSeniorListConfig.NATION);
		m_strIntro = response.getString(NurseSeniorListConfig.EDUCATION);
		m_strDepartments = response.getString(NurseSeniorListConfig.DEPARTMENTS);
		m_strCertificate = response.getString(NurseSeniorListConfig.CERTIFICATE);
		m_strServiceContent = response.getString(NurseSeniorListConfig.SERVICE_CONTENT);

		m_scheduleList.init(m_iID);
		m_scheduleList.serialization(response);

		m_commentList.init(m_iID);
		m_commentList.serialization(response);
		return true;
	}

	public int getiID()
	{
		return m_iID;
	}

	public int getiJobNum()
	{
		return m_iJobNum;
	}

	public String getStrLanguageLevel()
	{
		return m_strLanguageLevel;
	}

	public String getStrEducation()
	{
		return m_strEducation;
	}

	public String getStrNation()
	{
		return m_strNation;
	}

	public String getStrIntro()
	{
		return m_strIntro;
	}

	public String getStrDepartments()
	{
		return m_strDepartments;
	}

	public String getStrCertificate()
	{
		return m_strCertificate;
	}

	public String getStrServiceContent()
	{
		return m_strServiceContent;
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
