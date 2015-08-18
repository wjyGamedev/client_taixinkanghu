package com.taixinkanghu.app.ui.nurse_order_confirm_page;

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
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class SelectPatientStateFragment extends Fragment implements View.OnClickListener
{
	private TextView m_careMyselfBtn     = null;
	private TextView m_halfCareMyselfBtn = null;
	private TextView m_notCareMyselfBtn  = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_select_patient_state, container, false);

		view.setOnClickListener(this);

		m_careMyselfBtn = (TextView)view.findViewById(R.id.btn_state_care_myself);
		m_halfCareMyselfBtn = (TextView)view.findViewById(R.id.btn_state_half_care_myself);
		m_notCareMyselfBtn = (TextView)view.findViewById(R.id.btn_state_not_care_myself);

		m_careMyselfBtn.setOnClickListener(this);
		m_halfCareMyselfBtn.setOnClickListener(this);
		m_notCareMyselfBtn.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v)
	{
		OrderConfirmActivity activity = (OrderConfirmActivity)getActivity();
		if (activity == null)
		{
			RegisterDialog.GetInstance().setMsg("activity == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		switch (v.getId())
		{
			case R.id.btn_state_care_myself:
				activity.setPatientState(EnumConfig.PatientState.PATIENT_STATE_CARE_MYSELF);
				break;
			case R.id.btn_state_half_care_myself:
				activity.setPatientState(EnumConfig.PatientState.PATIENT_STATE_HALF_CARE_MYSELF);
				break;
			case R.id.btn_state_not_care_myself:
				activity.setPatientState(EnumConfig.PatientState.PATIENT_STATE_CANNT_CARE_MYSELF);
				break;
		}

		//蒙版点击一下之后消失的处理
		FragmentManager      fgManager           = getFragmentManager();
		android.app.Fragment fragment            = fgManager.findFragmentByTag(SelectPatientStateFragment.class.getName());
		FragmentTransaction  fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();

	}
}
