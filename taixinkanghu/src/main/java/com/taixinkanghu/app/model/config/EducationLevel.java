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

public class EducationLevel
{
	private static final int s_iBeginLevel = 0;

	private static final int s_iJuniorHighSchoolLevelAndBelow = s_iBeginLevel;	//一般
	private static final int s_iSeniorMiddleSchoole = 1;	//良好

	private static final int s_iEndLevel = s_iSeniorMiddleSchoole;


	public static String GetStrLevelByInteger(Integer iLevel)
	{
		if (iLevel < s_iJuniorHighSchoolLevelAndBelow || iLevel > s_iEndLevel)
		{
			//TODO:error
			return FragmentUtil.GetInstance().getResources().getText(R.string.junior_high_school_level_and_below).toString();
		}

		if (iLevel == s_iJuniorHighSchoolLevelAndBelow)
		{
			return FragmentUtil.GetInstance().getResources().getText(R.string.junior_high_school_level_and_below).toString();
		}
		else if (iLevel == s_iSeniorMiddleSchoole)
		{
			return FragmentUtil.GetInstance().getResources().getText(R.string.senior_middle_schoole).toString();
		}
		else
		{
			//TODO:error
			return FragmentUtil.GetInstance().getResources().getText(R.string.junior_high_school_level_and_below).toString();
		}
	}

}
