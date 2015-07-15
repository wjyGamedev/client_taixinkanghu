package com.taixinkanghu.app.ui.main_page;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;


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





	private void initData()
	{
//		m_viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
//		m_gestureDetectorCompat = new GestureDetectorCompat(this, this);
	}

	private void initWidget()
	{
		m_fragmentTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		m_fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

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

		imageView.getLayoutParams().width = (int)getResources().getDimension(R.dimen.tab_imageview_width);
		imageView.getLayoutParams().height = (int)getResources().getDimension(R.dimen.tab_imageview_height);
		return spec.setIndicator(view);
	}


//	private GestureDetectorCompat m_gestureDetectorCompat = null;
//	private ViewFlipper           m_viewFlipper           = null;
	private FragmentTabHost       m_fragmentTabHost       = null;

}
