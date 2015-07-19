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
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.util.nurse;

import com.taixinkanghu.R;
import com.taixinkanghu.util.android.AppUtil;

public class NurseUtil
{


	/**
	 * 教育程度
	 */
	private static final int s_iBeginLevel                    = 0;
	private static final int s_iJuniorHighSchoolLevelAndBelow = s_iBeginLevel;    //一般
	private static final int s_iSeniorMiddleSchoole           = 1;    //良好
	private static final int s_iEndLevel                      = s_iSeniorMiddleSchoole;

	public static String GetEducationLevelByInteger(Integer iLevel)
	{
		if (iLevel < s_iJuniorHighSchoolLevelAndBelow || iLevel > s_iEndLevel)
		{
			//TODO:error
			return AppUtil.getResources().getText(R.string.junior_high_school_level_and_below).toString();
		}

		if (iLevel == s_iJuniorHighSchoolLevelAndBelow)
		{
			return AppUtil.getResources().getText(R.string.junior_high_school_level_and_below).toString();
		}
		else if (iLevel == s_iSeniorMiddleSchoole)
		{
			return AppUtil.getResources().getText(R.string.senior_middle_schoole).toString();
		}
		else
		{
			//TODO:error
			return AppUtil.getResources().getText(R.string.junior_high_school_level_and_below).toString();
		}
	}

	public static String GetEducationLevelByString(String strLevel)
	{
		if (strLevel == null)
		{
			return "";
		}
		return strLevel;
	}

	/**
	 * 语言能力
	 */
	private static final int s_iGeneral = 0;    //一般
	private static final int s_iGood    = 1;    //良好
	private static final int s_iSkilled = 2;    //熟练
	private static final int s_iCN      = 1;    //汉语
	private static final int s_iEN      = 2;    //英语

	public static String GetLanguageLevelByInteger(Integer iLevel)
	{
		Integer iBits = iLevel % 10;

		//能力
		String strBits = null;
		if (iBits == s_iGeneral)
		{
			strBits = AppUtil.getResources().getText(R.string.language_level_general).toString();
		}
		else if (iBits == s_iGood)
		{
			strBits = AppUtil.getResources().getText(R.string.language_level_good).toString();
		}
		else if (iBits == s_iSkilled)
		{
			strBits = AppUtil.getResources().getText(R.string.language_level_skilled).toString();
		}
		else
		{
			//TODO:ERROR
			strBits = AppUtil.getResources().getText(R.string.language_level_general).toString();
		}

		//语种
		Integer iLanguageType = iLevel - iBits;
		String  strType       = null;
		if (iLanguageType == s_iCN)
		{
			strType = AppUtil.getResources().getText(R.string.language_type_cn).toString();
		}
		else if (iLanguageType == s_iEN)
		{
			strType = AppUtil.getResources().getText(R.string.language_type_en).toString();
		}
		else
		{
			//TODO:ERROR
			strType = AppUtil.getResources().getText(R.string.language_type_cn).toString();
		}

		return (strBits + strType);
	}

	public static String GetLanguageLevelByString(String strLevel)
	{
		if (strLevel == null)
		{
			return "";
		}
		return strLevel;
	}

	/**
	 * 服务状态
	 */
	private final static int s_iFree     = 0;
	private final static int s_iServices = 1;

	public static String GetStatusByInteger(Integer iStatus)
	{
		if (iStatus < s_iFree || iStatus > s_iServices)
		{
			//TODO:Log
			return AppUtil.getResources().getText(R.string.nurse_status_free).toString();
		}

		if (iStatus == s_iFree)
		{
			return AppUtil.getResources().getText(R.string.nurse_status_free).toString();
		}
		else if (iStatus == s_iServices)
		{
			return AppUtil.getResources().getText(R.string.nurse_status_services).toString();
		}
		else
		{
			//TODO:ERROR
			return AppUtil.getResources().getText(R.string.nurse_status_free).toString();
		}
	}

	public static String GetStatusByString(String strStatus)
	{
		if (strStatus == null)
		{
			return "";
		}
		return strStatus;
	}

	/**
	 *	服务星级
	 */
	private static final int s_iMinStar = 3;
	private static final int s_iMaxStar = 5;

	public static int GetStarLevelByInteger(int iStarLevel)
	{
		if (iStarLevel < s_iMinStar ||
				iStarLevel > s_iMaxStar)
		{
			//TODO:Log error
			return s_iMinStar;
		}

		return iStarLevel;
	}

	/**
	 * 服务经验
	 */
	public static String GetServiceExpByInteger(Integer iExp)
	{
		if (iExp < 0 )
		{
			//TODO:Log error
			return AppUtil.getResources().getText(R.string.no_nursing_exp).toString();
		}
		else if (iExp == 0)
		{
			return AppUtil.getResources().getText(R.string.no_nursing_exp).toString();
		}
		else
		{
			return (iExp.toString() + AppUtil.getResources().getText(R.string.year_nursing_exp).toString());
		}

	}

	/**
	 * 服务等级
	 */
	public static final int NURSING_LEVEL_JUNIOR = 0;
	public static final int NURSING_LEVEL_INTERMEDIATES = 1;
	public static final int NURSING_LEVEL_SENIOR = 2;
	public static final int NURSING_LEVEL_SUPER = 3;

	private static String s_strJunior = null;
	private static String s_strIntermediates = null;
	private static String s_strSenior = null;
	private static String s_strSuper = null;


	private static String GetStrJunior()
	{
		if (s_strJunior != null)
		{
			return s_strJunior;
		}

		s_strJunior = AppUtil.getResources().getText(R.string.nurse_junior).toString();
		return s_strJunior;
	}

	private static String GetStrInterMediates()
	{
		if (s_strIntermediates != null)
		{
			return s_strIntermediates;
		}

		s_strIntermediates = AppUtil.getResources().getText(R.string.nurse_junior).toString();
		return s_strIntermediates;
	}

	private static String GetStrSenior()
	{
		if (s_strSenior != null)
		{
			return s_strSenior;
		}

		s_strSenior = AppUtil.getResources().getText(R.string.nurse_junior).toString();
		return s_strSenior;
	}

	private static String GetStrSuper()
	{
		if (s_strSuper != null)
		{
			return s_strSuper;
		}

		s_strSuper = AppUtil.getResources().getText(R.string.nurse_junior).toString();
		return s_strSuper;
	}

	public static String GetNursingLevelByInteger(int iLevel)
	{
		switch(iLevel)
		{
			case NURSING_LEVEL_JUNIOR:
			{
				return GetStrJunior();
			}
			case NURSING_LEVEL_INTERMEDIATES:
			{
				return GetStrInterMediates();
			}
			case NURSING_LEVEL_SENIOR:
			{
				return GetStrSenior();
			}
			case NURSING_LEVEL_SUPER:
			{
				return GetStrSuper();
			}
			default:
			{
				return GetStrJunior();
			}
		}
	}

	/**
	 * 自我介绍
	 */
	public static String GetIntroByString(String strIntro)
	{
		return  (strIntro + AppUtil.getResources().getText(R.string.nurse_intro).toString());
	}

	/**
	 * 擅长科室
	 */
	public static String GetDepartmentsByString(String strDepartments)
	{
		return  (strDepartments + AppUtil.getResources().getText(R.string.nurse_departments).toString());
	}

	/**
	 * 持有证书
	 */
	public static String GetCertificateByString(String strCertificate)
	{
		return  (strCertificate + AppUtil.getResources().getText(R.string.nurse_certificate).toString());
	}

	/**
	 * 服务内容
	 */
	public static String GetServiceContentByString(String strServiceContent)
	{
		return  (strServiceContent + AppUtil.getResources().getText(R.string.nurse_service_content).toString());
	}

}
