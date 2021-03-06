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
public class SelectWeightFragment extends Fragment implements View.OnClickListener
{
	private TextView m_0To35WeightBtn;
	private TextView m_35To50WeightBtn;
	private TextView m_50To80WeightBtn;
	private TextView m_80To120WeightBtn;
	private TextView m_above120WeightBtn;

	private LinearLayout m_titleLL;

	private Integer m_weightTitleHight = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_select_weight, container, false);

		view.setOnClickListener(this);

		m_0To35WeightBtn = (TextView)view.findViewById(R.id.btn_weight_section_0_35);
		m_35To50WeightBtn = (TextView)view.findViewById(R.id.btn_age_section_35_50);
		m_50To80WeightBtn = (TextView)view.findViewById(R.id.btn_age_section_50_80);
		m_80To120WeightBtn = (TextView)view.findViewById(R.id.btn_age_section_80_120);
		m_above120WeightBtn = (TextView)view.findViewById(R.id.btn_age_section_above_120);

		m_0To35WeightBtn.setOnClickListener(this);
		m_35To50WeightBtn.setOnClickListener(this);
		m_50To80WeightBtn.setOnClickListener(this);
		m_80To120WeightBtn.setOnClickListener(this);
		m_above120WeightBtn.setOnClickListener(this);

		m_titleLL = (LinearLayout)view.findViewById(R.id.weight_titleLL);

		//设置顶部LL控件高度
		if (m_weightTitleHight != 0)
		{

			LinearLayout.LayoutParams Lp = (LinearLayout.LayoutParams)m_titleLL.getLayoutParams();
			Lp.height = m_weightTitleHight;
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
			case R.id.btn_weight_section_0_35:
				activity.setWeightRage(EnumConfig.WeightRage.WEIGHT_0_35);
				break;
			case R.id.btn_age_section_35_50:
				activity.setWeightRage(EnumConfig.WeightRage.WEIGHT_35_50);
				break;
			case R.id.btn_age_section_50_80:
				activity.setWeightRage(EnumConfig.WeightRage.WEIGHT_50_80);
				break;
			case R.id.btn_age_section_80_120:
				activity.setWeightRage(EnumConfig.WeightRage.WEIGHT_80_120);
				break;
			case R.id.btn_age_section_above_120:
				activity.setWeightRage(EnumConfig.WeightRage.WEIGHT_MORE_THAN_120);
				break;
		}

		//蒙版点击一下之后消失的处理
		FragmentManager     fgManager           = getFragmentManager();
		Fragment            fragment            = fgManager.findFragmentByTag(SelectWeightFragment.class.getName());
		FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();

	}

	public void setWeightTitleHight(Integer weightTitleHight)
	{
		m_weightTitleHight = weightTitleHight;
	}
}
