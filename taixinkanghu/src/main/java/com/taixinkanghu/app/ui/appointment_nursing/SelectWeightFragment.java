package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		FragmentManager      fgManager           = getFragmentManager();
		android.app.Fragment fragment            = fgManager.findFragmentByTag(SelectWeightFragment.class.getName());
		FragmentTransaction  fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();

	}
}
