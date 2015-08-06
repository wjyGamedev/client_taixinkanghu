package com.taixinkanghu.app.ui.nurs_order_confirm_page;

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

/**
 * Created by Administrator on 2015/7/28.
 */
public class SelectPatientStateFragment extends Fragment implements View.OnClickListener
{
	private TextView     m_careMyselfBtn;
	private TextView     m_halfCareMyselfBtn;
	private TextView     m_notCareMyselfBtn;
	private LinearLayout m_titleRegion;
	private LinearLayout m_bottomRegion;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_select_patient_state, container, false);

		view.setOnClickListener(this);

		m_careMyselfBtn = (TextView)view.findViewById(R.id.btn_state_care_myself);
		m_halfCareMyselfBtn = (TextView)view.findViewById(R.id.btn_state_half_care_myself);
		m_notCareMyselfBtn = (TextView)view.findViewById(R.id.btn_state_not_care_myself);
		m_titleRegion = (LinearLayout)view.findViewById(R.id.title_region);
		m_bottomRegion = (LinearLayout)view.findViewById(R.id.bottom_region);

		m_titleRegion.setBackgroundResource(R.color.all_null);
		m_bottomRegion.setBackgroundResource(R.color.mask);

		m_careMyselfBtn.setOnClickListener(this);
		m_halfCareMyselfBtn.setOnClickListener(this);
		m_notCareMyselfBtn.setOnClickListener(this);

		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)m_titleRegion.getLayoutParams();
		final float               scale        = getActivity().getResources().getDisplayMetrics().density;
		linearParams.height = (int)(323 * scale + 0.5f);
		m_titleRegion.setLayoutParams(linearParams);

		return view;
	}

	@Override
	public void onClick(View v)
	{
		OrderConfirmActivity activity = (OrderConfirmActivity)getActivity();
		switch (v.getId())
		{
			case R.id.btn_state_care_myself:
				activity.getPatientStateTv().setText(getString(R.string.btn_state_care_myself_text));
				activity.getDwonPatientState().setVisibility(View.INVISIBLE);
				break;
			case R.id.btn_state_half_care_myself:
				activity.getPatientStateTv().setText(getString(R.string.btn_state_half_care_myself_text));
				activity.getDwonPatientState().setVisibility(View.INVISIBLE);
				break;
			case R.id.btn_state_not_care_myself:
				activity.getPatientStateTv().setText(getString(R.string.btn_state_not_care_myself_text));
				activity.getDwonPatientState().setVisibility(View.INVISIBLE);
				break;
		}

		//蒙版点击一下之后消失的处理
		FragmentManager      fgManager           = getFragmentManager();
		Fragment fragment            = fgManager.findFragmentById(R.id.order_confirm_page);
		FragmentTransaction  fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		String tag = null;
		fragmentTransaction.addToBackStack(tag);
		fragmentTransaction.commit();

	}
}
