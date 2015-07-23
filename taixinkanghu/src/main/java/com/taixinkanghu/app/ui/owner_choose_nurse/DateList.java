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
import android.app.FragmentTransaction;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.fragment.SelectDateFragment;
import com.taixinkanghu.widget.drop_down_list.DropDownList;

public class DateList extends DropDownList
{
	public DateList(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		SelectDateFragment selectDateFragment = new SelectDateFragment();
		FragmentTransaction        transaction = m_activity.getFragmentManager().beginTransaction();
		transaction.replace(R.id.owner_select_page, selectDateFragment, selectDateFragment.getClass().getName());
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
