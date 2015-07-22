/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.owner_choose_nurse.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/22		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.owner_choose_nurse;

import android.app.Activity;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.fragment.SelectScreeningFragment;
import com.taixinkanghu.widget.drop_down_list.DropDownList;

public class ScreeningList extends DropDownList
{
	public ScreeningList(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		m_activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectScreeningFragment()).commit();
	}
}
