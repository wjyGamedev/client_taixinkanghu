package com.taixinkanghu.app.ui.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TabHost;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;
import com.taixinkanghu.app.model.controller.CMainPage;
import com.taixinkanghu.app.ui.activity.MySettingActivity;
import com.taixinkanghu.app.ui.activity.MyWealthActivity;
import com.taixinkanghu.app.ui.activity.NursOrderActivity;
import com.taixinkanghu.widget.fragmenttabhostex.FragmentTabHostEx;
import com.taixinkanghu.widget.fragmenttabhostex.FragmentTabHostEx.OnAfterTabChangeListener;
import com.taixinkanghu.widget.fragmenttabhostex.FragmentTabHostEx.OnBeforeTabChangeListener;
import com.taixinkanghu.widget.fragmenttabhostex.FragmentTabHostEx.OnTabClickListener;
import com.taixinkanghu.widget.tab_item.TabItem;

public class MainActivity extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		initData();
		initWidget();

    }

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

    public boolean onDown(MotionEvent e)
    {
        return false;
    }


	@Override
	protected void onStart()
	{
		super.onStart();
		m_fragmentTabHost.setOnTabChangedListener(m_impTabChangeListener);
		m_fragmentTabHost.setOnBeforeTabChangeListener(m_impBeforeTabChangeListener);
		m_fragmentTabHost.setOnAfterTabChangeListener(m_impAfterTabChangeListener);
		m_fragmentTabHost.setOnTabClickListener(m_impTabClickListener);
	}

	private void initData()
	{
		m_fragmentTabHost = (FragmentTabHostEx)findViewById(android.R.id.tabhost);
		m_impMenuItemClickListener = new ImpMenuItemClickListener();
		m_impTabClickListener = new ImpTabClickListener();
		m_impBeforeTabChangeListener = new ImpBeforeTabChangeListener();
		m_impAfterTabChangeListener = new ImpAfterTabChangeListener();
		m_impTabChangeListener = new ImpTabChangeListener();
	}

	private void initWidget()
	{

		m_fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

		TabHost.TabSpec tabSpec = null;

		tabSpec = initTabItem(	m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_HOME_TAB_FLAG),
							   	MainActivityConfig.MAIN_HOME_TAB_TEXT,
							   	R.drawable.main_tab_home_imgs
							  );
		m_fragmentTabHost.addTab(tabSpec, HomeTabContainer.class, null);

		tabSpec = initTabItem(	m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_PERSONAL_TAB_FLAG),
							  	MainActivityConfig.MAIN_PERSONAL_TAB_TEXT,
							  	R.drawable.main_tab_personal_imgs
							 );
		m_fragmentTabHost.addTab(tabSpec, PersonalTabContainer.class, null);

		tabSpec = initTabItem(	m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_SERVICE_TAB_FLAG),
							  	MainActivityConfig.MAIN_SERVICE_TAB_TEXT,
							  R.drawable.main_tab_service_imgs
							 );
		m_fragmentTabHost.addTab(tabSpec, ServiceTabContainer.class, null
					   );

		tabSpec = initTabItem(m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_COMPANT_TAB_FLAG),
							  MainActivityConfig.MAIN_COMPANT_TAB_TEXT,
							  R.drawable.main_tab_company_imgs
							  );
		m_fragmentTabHost.addTab(tabSpec, CompanyTabContainer.class, null);



	}

	private TabHost.TabSpec initTabItem(TabHost.TabSpec spec, String strText, int iconID) {
		TabItem tabItem = new TabItem(this);
		tabItem.setText(strText);
		tabItem.setImageResourceByID(iconID);
		return spec.setIndicator(tabItem.getView());
	}

	@Override
	public void onBackPressed() {
		boolean isPopFragment = false;
		String currentTabTag = m_fragmentTabHost.getCurrentTabTag();

		if (currentTabTag.equals(MainActivityConfig.MAIN_HOME_TAB_FLAG)) {
			isPopFragment = ((BaseTabContainer)getSupportFragmentManager().findFragmentByTag(MainActivityConfig.MAIN_HOME_TAB_FLAG)).popFragment();
		}
		else if (currentTabTag.equals(MainActivityConfig.MAIN_PERSONAL_TAB_FLAG)) {
			isPopFragment = ((BaseTabContainer)getSupportFragmentManager().findFragmentByTag(MainActivityConfig.MAIN_PERSONAL_TAB_FLAG)).popFragment();
		}
		else if (currentTabTag.equals(MainActivityConfig.MAIN_SERVICE_TAB_FLAG)) {
			isPopFragment = ((BaseTabContainer)getSupportFragmentManager().findFragmentByTag(MainActivityConfig.MAIN_SERVICE_TAB_FLAG)).popFragment();
		}
		else if (currentTabTag.equals(MainActivityConfig.MAIN_COMPANT_TAB_FLAG)) {
			isPopFragment = ((BaseTabContainer)getSupportFragmentManager().findFragmentByTag(MainActivityConfig.MAIN_COMPANT_TAB_FLAG)).popFragment();
		}

		if (!isPopFragment) {
			finish();
		}
	}

	private class ImpMenuItemClickListener implements OnMenuItemClickListener
	{

		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			switch (item.getItemId())
			{
			case R.id.nursing_order:
			{
				MainActivity.this.OpenNursingOrder();
			}
				return true;
			case R.id.shopping_order:
			{
				MainActivity.this.OpenProductOrder();
			}
				return true;
			case R.id.personal_wealth:
			{
				MainActivity.this.OpenMyWealth();
			}
				return true;
			case R.id.personal_setting:
			{
				MainActivity.this.OpenMySet();
			}
				return true;
			default:
				break;
			}
			return false;
		}
	}

	//陪护订单
	private void OpenNursingOrder()
	{
		startActivity(new Intent(MainActivity.this, NursOrderActivity.class));
	}

	//产品订单
	private void OpenProductOrder()
	{
		Toast.makeText(this,R.string.function_is_not_open, Toast.LENGTH_SHORT).show();
	}

	//我的财富
	private void OpenMyWealth()
	{
		startActivity(new Intent(MainActivity.this, MyWealthActivity.class));
	}

	//我的设置
	private void OpenMySet()
	{
		startActivity(new Intent(MainActivity.this, MySettingActivity.class));
	}

	private class ImpTabClickListener implements OnTabClickListener
	{
		@Override
		public void onTabClick(int index)
		{
			String currentTabTag = MainActivity.this.m_fragmentTabHost.getCurrentTabTag();

			if (index == MainActivityConfig.MAIN_PERSONAL_TAB_INDEX)
			{
				if (m_popupMenu != null)
				{
					if (CMainPage.getInstance().getMainPage().getCurrentTabTag() == currentTabTag)
					{
						m_popupMenu.show();
					}
				}
			}

			CMainPage.getInstance().getMainPage().setCurrentTabTag(currentTabTag);
		}

	}

	private class ImpBeforeTabChangeListener implements OnBeforeTabChangeListener
	{

		@Override
		public boolean onBeforeTabChanged(String tabId)
		{
			if (tabId.equals(MainActivityConfig.MAIN_PERSONAL_TAB_FLAG))
			{
				if (m_popupMenu == null)
				{
					m_popupMenu = new PopupMenu(MainActivity.this, m_fragmentTabHost.getCurrentTabView());
					Menu menu = m_popupMenu.getMenu();
					MenuInflater menuInflater = getMenuInflater();
					menuInflater.inflate(R.menu.main_personal_popup_menu, menu);
					m_popupMenu.setOnMenuItemClickListener(m_impMenuItemClickListener);
				}
				m_popupMenu.show();
			}
			return true;
		}
	}

	private class ImpAfterTabChangeListener implements OnAfterTabChangeListener
	{

		@Override
		public void onAfterTabChanged(String tabId)
		{

		}
	}

	private class ImpTabChangeListener implements TabHost.OnTabChangeListener
	{

		@Override
		public void onTabChanged(String tabId)
		{
			Log.w("onTabChanged", "test");
		}
	}

	private FragmentTabHostEx                           m_fragmentTabHost           = null;
	private PopupMenu                                   m_popupMenu                 = null;
	private ImpMenuItemClickListener                    m_impMenuItemClickListener  = null;
	private ImpTabClickListener                         m_impTabClickListener       = null;
	private ImpBeforeTabChangeListener 					 m_impBeforeTabChangeListener = null;
	private ImpAfterTabChangeListener                    m_impAfterTabChangeListener  = null;
	private ImpTabChangeListener		m_impTabChangeListener = null;

}
