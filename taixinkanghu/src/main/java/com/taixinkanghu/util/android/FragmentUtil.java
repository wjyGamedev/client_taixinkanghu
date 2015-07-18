/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.util.android.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.util.android;

import android.app.Fragment;

public class FragmentUtil extends Fragment
{

	public static Fragment GetInstance()
	{
		return  s_fragment;
	}




	private static Fragment s_fragment = new FragmentUtil();
}
