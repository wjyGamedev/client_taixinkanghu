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

public class NurseServiceStatus
{
	private final static int s_iFree = 0;
	private final static int s_iServices = 1;

	public static String GetStatusByInteger(Integer iStatus)
	{
		if (iStatus < s_iFree ||
				iStatus > s_iServices)
		{
			//TODO:Log
			return FragmentUtil.GetInstance().getResources().getText(R.string.nurse_status_free).toString();
		}

		return FragmentUtil.GetInstance().getResources().getText(R.string.nurse_status_services).toString();
	}


}
