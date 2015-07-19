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
 * 2015/7/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.config;

import com.taixinkanghu.R;
import com.taixinkanghu.util.android.FragmentUtil;

public class NursingLevel
{
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

		s_strJunior = FragmentUtil.GetInstance().getResources().getText(R.string.nurse_junior).toString();
		return s_strJunior;
	}

	private static String GetStrInterMediates()
	{
		if (s_strIntermediates != null)
		{
			return s_strIntermediates;
		}

		s_strIntermediates = FragmentUtil.GetInstance().getResources().getText(R.string.nurse_junior).toString();
		return s_strIntermediates;
	}

	private static String GetStrSenior()
	{
		if (s_strSenior != null)
		{
			return s_strSenior;
		}

		s_strSenior = FragmentUtil.GetInstance().getResources().getText(R.string.nurse_junior).toString();
		return s_strSenior;
	}

	private static String GetStrSuper()
	{
		if (s_strSuper != null)
		{
			return s_strSuper;
		}

		s_strSuper = FragmentUtil.GetInstance().getResources().getText(R.string.nurse_junior).toString();
		return s_strSuper;
	}

	public static String GetLevelByInteger(int iLevel)
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


}

