package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.EnumConfig;

/**
 * Created by Administrator on 2015/7/28.
 */
public class SelectAgeFragment extends Fragment implements View.OnClickListener
{
	private TextView     m_0To15AgeBtn;
	private TextView     m_16To35AgeBtn;
	private TextView     m_36To55AgeBtn;
	private TextView     m_56To75AgeBtn;
	private TextView     m_above75AgeBtn;

	private LinearLayout m_titleLL;

	private Integer m_ageTitleHight = 0;


	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_select_age, container, false);

		view.setOnClickListener(this);

		m_0To15AgeBtn = (TextView)view.findViewById(R.id.btn_age_section_0_15);
		m_16To35AgeBtn = (TextView)view.findViewById(R.id.btn_age_section_16_35);
		m_36To55AgeBtn = (TextView)view.findViewById(R.id.btn_age_section_36_55);
		m_56To75AgeBtn = (TextView)view.findViewById(R.id.btn_age_section_56_75);
		m_above75AgeBtn = (TextView)view.findViewById(R.id.btn_age_section_above_75);

		m_0To15AgeBtn.setOnClickListener(this);
		m_16To35AgeBtn.setOnClickListener(this);
		m_36To55AgeBtn.setOnClickListener(this);
		m_56To75AgeBtn.setOnClickListener(this);
		m_above75AgeBtn.setOnClickListener(this);

		m_titleLL = (LinearLayout)view.findViewById(R.id.age_titleLL);

		//设置顶部LL控件高度
		if (m_ageTitleHight != 0)
		{
			LinearLayout.LayoutParams Lp = (LinearLayout.LayoutParams)m_titleLL.getLayoutParams();
			Lp.height = m_ageTitleHight;
			m_titleLL.setLayoutParams(Lp);
		}

		return view;
	}

	@Override
	public void onClick(View v)
	{
		ApoitNursingActivity activity = (ApoitNursingActivity)getActivity();
		switch (v.getId())
		{
			case R.id.btn_age_section_0_15:
				activity.setAgeRage(EnumConfig.AgeRage.AGE_0_15);
				break;
			case R.id.btn_age_section_16_35:
				activity.setAgeRage(EnumConfig.AgeRage.AGE_16_35);
				break;
			case R.id.btn_age_section_36_55:
				activity.setAgeRage(EnumConfig.AgeRage.AGE_36_55);
				break;
			case R.id.btn_age_section_56_75:
				activity.setAgeRage(EnumConfig.AgeRage.AGE_56_75);
				break;
			case R.id.btn_age_section_above_75:
				activity.setAgeRage(EnumConfig.AgeRage.AGE_MORE_THAN_76);
				break;
		}

		//蒙版点击一下之后消失的处理
		FragmentManager      fgManager           = getFragmentManager();
		android.app.Fragment fragment            = fgManager.findFragmentByTag(SelectAgeFragment.class.getName());
		FragmentTransaction  fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
	}

	public void setAgeTitleHight(Integer ageTitleHight)
	{
		m_ageTitleHight = ageTitleHight;
	}

}
