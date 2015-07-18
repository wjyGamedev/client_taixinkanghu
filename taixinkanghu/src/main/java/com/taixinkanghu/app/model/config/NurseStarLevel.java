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

public class NurseStarLevel
{
	private static final int s_iMinStar = 3;
	private static final int s_iMaxStar = 5;

	public static int GetStarLevel(int iStarLevel)
	{
		if (iStarLevel < s_iMinStar ||
				iStarLevel > s_iMaxStar)
		{
			//TODO:Log error
			return s_iMinStar;
		}

		return iStarLevel;
	}


}

