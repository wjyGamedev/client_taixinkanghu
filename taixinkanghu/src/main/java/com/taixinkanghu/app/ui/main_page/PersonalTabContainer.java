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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;
import com.taixinkanghu.app.model.controller.CMainPage;

public class PersonalTabContainer extends BaseTabContainer
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_base_tab_framelayout, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		if (m_isHomeViewInited == false || m_isServiceViewInited == false || m_isCompanyViewInited == false) {
			initView();
//		}
	}

	private void initView() {
        String lastTabTag = CMainPage.getInstance().getMainPage().getCurrentTabTag();
		if (lastTabTag.equals(MainActivityConfig.MAIN_HOME_TAB_FLAG))
		{
			replaceFragment(new PersonalTabFragmentHome(), false);
//			m_isHomeViewInited = true;
		}
		else if (lastTabTag.equals(MainActivityConfig.MAIN_SERVICE_TAB_FLAG))
		{
			replaceFragment(new PersonalTabFragmentService(), false);
//			m_isServiceViewInited = true;
		}
		else if (lastTabTag.equals(MainActivityConfig.MAIN_COMPANT_TAB_FLAG))
		{
			replaceFragment(new PersonalTabFragmentCompany(), false);
//			m_isCompanyViewInited = true;
		}
		else
		{
			//nothing
		}
	}

//	private boolean m_isHomeViewInited;
//	private boolean m_isServiceViewInited;
//	private boolean m_isCompanyViewInited;
}
