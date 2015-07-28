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
import com.taixinkanghu.app.ui.fragment.SelectHospitalOldFragment;
import com.taixinkanghu.widget.drop_down_list.DropDownList;

public class HospitalList extends DropDownList
{
	private SelectHospitalOldFragment m_selectHospitalOldFragment = null;

	public HospitalList(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		if (m_selectHospitalOldFragment == null)
		{
			m_selectHospitalOldFragment = new SelectHospitalOldFragment();
		}

//		m_activity.getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.owner_select_page, new SelectHospitalOldFragment()
//																					 ).commit();
		m_selectHospitalOldFragment.setIntoWay(1);

		FragmentTransaction transaction = m_activity.getFragmentManager().beginTransaction();
		transaction.replace(R.id.owner_select_page, m_selectHospitalOldFragment, m_selectHospitalOldFragment.getClass().getName());
		transaction.addToBackStack(m_selectHospitalOldFragment.getClass().getName());
		transaction.commit();
	}


}

