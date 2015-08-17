/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.main_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/13		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.main_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;
import com.taixinkanghu.app.model.controller.CMainPage;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.ui.UIRTException;

public class PersonalTabContainer extends BaseTabContainer
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.main_base_tab_framelayout, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView()
	{
		String lastTabTag = CMainPage.getInstance().getMainPage().getCurrentTabTag();
		if (lastTabTag.equals(MainActivityConfig.MAIN_HOME_TAB_FLAG))
		{
			if (m_homeTabFragment == null)
			{
				m_homeTabFragment = new HomeTabFragment();
			}
			setCurrentFragment(m_homeTabFragment, false);
		}
		else if (lastTabTag.equals(MainActivityConfig.MAIN_SERVICE_TAB_FLAG))
		{
			if (m_serviceTabFragment == null)
			{
				m_serviceTabFragment = new ServiceTabFragment();
			}
			setCurrentFragment(m_serviceTabFragment, false);
		}
		else if (lastTabTag.equals(MainActivityConfig.MAIN_COMPANT_TAB_FLAG))
		{
			if (m_companyTabFragment == null)
			{
				m_companyTabFragment = new CompanyTabFragment();
			}
			setCurrentFragment(m_companyTabFragment, false);
		}
		else
		{
			//TODO:error
			throw new UIRTException("lastTabTag is invalid![lastTabTag:=" + lastTabTag + "]");
		}
	}

	private Fragment m_homeTabFragment    = null;
	private Fragment m_serviceTabFragment = null;
	private Fragment m_companyTabFragment = null;

}
