/**
 * Copyright (c) 213Team
 *
 * @className : util.NurseOrder.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.util.nurseorder;

public class NurseOrderUtil
{
	public static boolean isIDValid(String inStrID)
	{
		if (inStrID == null)
			return false;

		if (inStrID.equals(""))
			return false;

		/**
		 * 逻辑判断
		 */

		return true;
	}
}
