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

public class NursingExp
{
	public static String GetExpByInteger(Integer iExp)
	{
		if (iExp < 0 )
		{
			//TODO:Log error
			return FragmentUtil.GetInstance().getResources().getText(R.string.no_nursing_exp).toString();
		}
		else if (iExp == 0)
		{
			return FragmentUtil.GetInstance().getResources().getText(R.string.no_nursing_exp).toString();
		}
		else
		{
			return (iExp.toString() + FragmentUtil.GetInstance().getResources().getText(R.string.year_nursing_exp).toString());
		}

	}
}
