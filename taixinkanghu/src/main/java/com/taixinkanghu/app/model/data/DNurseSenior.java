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

import org.json.JSONException;
import org.json.JSONObject;

public class DNurseSenior
{
	public boolean serialization(JSONObject response)
	{
		try
		{
			m_iID = response.getInt(DataConfig.NURSE_ID);
			m_iJobNum = response.getInt(DataConfig.NURSE_JOB_NUM);
			m_strLanguageLevel = response.getString(DataConfig.NURSE_LANGUAGE_LEVEL);
			m_strEducation = response.getString(DataConfig.NURSE_EDUCATION);
			m_strNation = response.getString(DataConfig.NURSE_NATION);
			m_strIntro = response.getString(DataConfig.NURSE_EDUCATION);
			m_strDepartments = response.getString(DataConfig.NURSE_DEPARTMENTS);
			m_strCertificate = response.getString(DataConfig.NURSE_CERTIFICATE);
			m_strServiceContent = response.getString(DataConfig.NURSE_SERVICE_CONTENT);
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

	/**
	 * 数据区
	 */
	private int m_iID = 0;          					//ID

	private int    m_iJobNum          = 0;            //工号
	private String m_strLanguageLevel = null;    	//语言水平
	private String m_strEducation     = null;        //文化程度
	private String m_strNation = null;				//民族
	private String m_strIntro       = null; 		               	//自我介绍
	private String m_strDepartments = null; 	//擅长科室
	private String m_strCertificate = null; 	//持有证书
	private String m_strServiceContent = null;								//服务内容
}
