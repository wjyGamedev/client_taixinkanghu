package com.taixinkanghu.app.ui.shopping_page;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.widget.drop_down_list.DropDownList;

/**
 * Created by Administrator on 2015/7/23.
 */
public class ShoppingScreeningList extends DropDownList
{

	private ShoppingSrceeningFragment m_shoppingSrceeningFragment = null;

	public ShoppingScreeningList(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		if (m_shoppingSrceeningFragment == null)
		{
			m_shoppingSrceeningFragment = new ShoppingSrceeningFragment();
		}
		FragmentTransaction transaction = m_activity.getFragmentManager().beginTransaction();
		transaction.replace(R.id.shopping_page, m_shoppingSrceeningFragment, m_shoppingSrceeningFragment.getClass().getName());
		transaction.addToBackStack(m_shoppingSrceeningFragment.getClass().getName());
		transaction.commit();
	}

}
