package com.taixinkanghu.app.ui.main_page;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;
import com.taixinkanghu.widget.fragmenttabhostex.FragmentTabHostEx;


public class MainActivity extends FragmentActivity implements FragmentTabHostEx.OnBeforeTabChangeListener, FragmentTabHostEx.OnAfterTabChangeListener
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





	private void initData()
	{
		m_fragmentTabHost = (FragmentTabHostEx)findViewById(android.R.id.tabhost);
		m_impMenuItemClickListener = new ImpMenuItemClickListener();
	}

	private void initWidget()
	{

		m_fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		m_fragmentTabHost.setOnTabChangedListener(this);

		TabHost.TabSpec tabSpec = null;
		tabSpec = setIndicator(this,
							  m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_HOME_TAB_FLAG),
							   MainActivityConfig.MAIN_HOME_TAB_TEXT,
							   R.drawable.main_tab_home_imgs
							 );
		m_fragmentTabHost.addTab(tabSpec, HomeTabContainer.class, null);

		tabSpec = setIndicator(this,
							   m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_PERSONAL_TAB_FLAG),
							   MainActivityConfig.MAIN_PERSONAL_TAB_TEXT,
							   R.drawable.main_tab_personal_imgs
							  );
		m_fragmentTabHost.addTab(tabSpec, PersonalTabContainer.class, null);

		tabSpec = setIndicator(MainActivity.this,
							   m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_SERVICE_TAB_FLAG),
							   MainActivityConfig.MAIN_SERVICE_TAB_TEXT,
							   R.drawable.main_tab_service_imgs
							  );
		m_fragmentTabHost.addTab(tabSpec, ServiceTabContainer.class, null
					   );

		tabSpec = setIndicator(MainActivity.this,
							   m_fragmentTabHost.newTabSpec(MainActivityConfig.MAIN_COMPANT_TAB_FLAG),
							   MainActivityConfig.MAIN_COMPANT_TAB_TEXT,
							   R.drawable.main_tab_company_imgs
							  );
		m_fragmentTabHost.addTab(tabSpec, CompanyTabContainer.class, null);
	}

	@Override
	public boolean onBeforeTabChanged(String tabId)
	{
		//弹出菜单
		if (tabId.equals(MainActivityConfig.MAIN_PERSONAL_TAB_FLAG))
		{
			if (m_popupMenu == null)
			{
				m_popupMenu = new PopupMenu(this, m_fragmentTabHost.getCurrentTabView());
				Menu menu = m_popupMenu.getMenu();
				MenuInflater menuInflater = getMenuInflater();
				menuInflater.inflate(R.menu.main_personal_popup_menu, menu);
			}
			m_popupMenu.show();
			m_popupMenu.setOnMenuItemClickListener(m_impMenuItemClickListener);
		}
		return true;

	}

	@Override
	public void onAfterTabChanged(String tabId)
	{

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

	private TabHost.TabSpec setIndicator(Context ctx, TabHost.TabSpec spec, String inString, int iIcon) {
		View view = LayoutInflater.from(ctx).inflate(R.layout.main_tab_item, null);
		TextView textView = (TextView)view.findViewById(R.id.main_tab_textview);
		ImageView imageView = (ImageView)view.findViewById(R.id.main_tab_imgview);

		textView.setText(inString);
		imageView.setBackgroundResource(iIcon);
		return spec.setIndicator(view);
	}

	private class ImpMenuItemClickListener implements OnMenuItemClickListener
	{

		@Override
		public boolean onMenuItemClick(MenuItem item)
		{
			switch (item.getItemId())
			{
			case R.id.nursing_order:
				return true;
			case R.id.shopping_order:
				return true;
			case R.id.personal_wealth:
				return true;
			case R.id.personal_setting:
				return true;
			default:
				break;
			}
			return false;
		}
	}

	private class ImpClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{

		}
	}

	private FragmentTabHostEx m_fragmentTabHost = null;
	private PopupMenu m_popupMenu = null;
	private ImpMenuItemClickListener m_impMenuItemClickListener = null;
}
