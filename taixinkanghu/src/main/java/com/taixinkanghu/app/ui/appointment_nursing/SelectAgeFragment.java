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

/**
 * Created by Administrator on 2015/7/28.
 */
public class SelectAgeFragment extends Fragment implements View.OnClickListener
{
	private TextView m_0To15AgeBtn;
	private TextView m_16To35AgeBtn;
	private TextView m_36To55AgeBtn;
	private TextView m_56To75AgeBtn;
	private TextView m_above75AgeBtn;

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

		return view;
	}

	@Override
	public void onClick(View v)
	{
		ApoitNursingActivity activity = (ApoitNursingActivity)getActivity();
		switch (v.getId())
		{
			case R.id.btn_age_section_0_15:
				activity.getAgeTv().setText(getString(R.string.btn_age_section_0_15_text));
//				activity.getDwonAge().setVisibility(View.INVISIBLE);
				break;
			case R.id.btn_age_section_16_35:
				activity.getAgeTv().setText(getString(R.string.btn_age_section_16_35_text));
//				activity.getDwonAge().setVisibility(View.INVISIBLE);
				break;
			case R.id.btn_age_section_36_55:
				activity.getAgeTv().setText(getString(R.string.btn_age_section_36_55_text));
//				activity.getDwonAge().setVisibility(View.INVISIBLE);
				break;
			case R.id.btn_age_section_56_75:
				activity.getAgeTv().setText(getString(R.string.btn_age_section_56_75_text));
//				activity.getDwonAge().setVisibility(View.INVISIBLE);
				break;
			case R.id.btn_age_section_above_75:
				activity.getAgeTv().setText(getString(R.string.btn_age_section_above_75_text));
//				activity.getDwonAge().setVisibility(View.INVISIBLE);
				break;
		}
		//蒙版点击一下之后消失的处理
		FragmentManager      fgManager           = getFragmentManager();
		android.app.Fragment fragment            = fgManager.findFragmentById(R.id.appointment_nursing_page);
		FragmentTransaction  fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		String tag = null;
		fragmentTransaction.addToBackStack(tag);
		fragmentTransaction.commit();
	}


}
