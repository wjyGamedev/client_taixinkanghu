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

package com.taixinkanghu.app.model.config;

import com.taixinkanghu.R;
import com.taixinkanghu.util.android.FragmentUtil;

public class LanguageLevel
{
	private static final int s_iGeneral = 0;	//一般
	private static final int s_iGood = 1;	//良好
	private static final int s_iSkilled = 2;	//熟练

	private static final int s_iCN = 1;	//汉语
	private static final int s_iEN = 2;	//英语

	public static String GetLevelByInteger(Integer iLevel)
	{
		Integer iBits = iLevel%10;

		//能力
		String strBits = null;
		if (iBits == s_iGeneral)
		{
			strBits = FragmentUtil.GetInstance().getResources().getText(R.string.language_level_general).toString();
		}
		else if (iBits == s_iGood)
		{
			strBits = FragmentUtil.GetInstance().getResources().getText(R.string.language_level_good).toString();
		}
		else if (iBits == s_iSkilled)
		{
			strBits = FragmentUtil.GetInstance().getResources().getText(R.string.language_level_skilled).toString();
		}
		else
		{
			//TODO:ERROR
			strBits = FragmentUtil.GetInstance().getResources().getText(R.string.language_level_general).toString();
		}

		//语种
		Integer iLanguageType = iLevel - iBits;
		String strType = null;
		if (iLanguageType == s_iCN)
		{
			strType = FragmentUtil.GetInstance().getResources().getText(R.string.language_type_cn).toString();
		}
		else if (iLanguageType == s_iEN)
		{
			strType = FragmentUtil.GetInstance().getResources().getText(R.string.language_type_en).toString();
		}
		else
		{
			//TODO:ERROR
			strType = FragmentUtil.GetInstance().getResources().getText(R.string.language_type_cn).toString();
		}

		return (strBits + strType);
	}


}
