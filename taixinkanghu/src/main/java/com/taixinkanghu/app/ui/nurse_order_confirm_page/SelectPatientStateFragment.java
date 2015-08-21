package com.taixinkanghu.app.ui.nurse_order_confirm_page;

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
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class SelectPatientStateFragment extends Fragment implements View.OnClickListener
{
	private TextView m_careMyselfBtn     = null;
	private TextView m_halfCareMyselfBtn = null;
	private TextView m_notCareMyselfBtn  = null;

	private LinearLayout m_titleLL  = null;
	private LinearLayout m_bottomLL = null;

	private Integer m_patientStateTitleHight = 0;

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

		m_titleLL = (LinearLayout)view.findViewById(R.id.title_region);
		m_bottomLL = (LinearLayout)view.findViewById(R.id.patient_state_bottomLL);

		//设置背景色
		m_titleLL.setBackgroundColor(getResources().getColor(R.color.all_null));
		m_bottomLL.setBackgroundColor(getResources().getColor(R.color.mask));

		//设置底部LL控件高度
		if (m_patientStateTitleHight != 0)
		{
			LinearLayout.LayoutParams Lp = (LinearLayout.LayoutParams)m_titleLL.getLayoutParams();
			Lp.height = m_patientStateTitleHight;
			m_titleLL.setLayoutParams(Lp);
		}

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

	public void setPatientStateTitleHight(Integer patientStateTitleHight)
	{
		m_patientStateTitleHight = patientStateTitleHight;
	}

}
