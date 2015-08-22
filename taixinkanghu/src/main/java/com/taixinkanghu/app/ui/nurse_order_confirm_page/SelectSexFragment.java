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

/**
 * Created by Administrator on 2015/7/20.
 */
public class SelectSexFragment extends Fragment implements View.OnClickListener
{

	private TextView     btn_Male;
	private TextView     btn_Female;
	private LinearLayout m_titleLL;

	private Integer m_genderTitleHight = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View view = inflater.inflate(R.layout.fragment_select_gender, container, false);

		view.setOnClickListener(this);
		btn_Male = (TextView)view.findViewById(R.id.btn_Male);
		btn_Female = (TextView)view.findViewById(R.id.btn_Female);
		m_titleLL = (LinearLayout)view.findViewById(R.id.gender_titleLL);

		btn_Female.setOnClickListener(this);
		btn_Male.setOnClickListener(this);

		//设置顶部LL控件高度
		if (m_genderTitleHight != 0)
		{
			LinearLayout.LayoutParams Lp = (LinearLayout.LayoutParams)m_titleLL.getLayoutParams();
			Lp.height = m_genderTitleHight;
			m_titleLL.setLayoutParams(Lp);
		}

		return view;
	}

	@Override
	public void onClick(View v)
	{
		PatientActivity patientActivity = (PatientActivity)getActivity();
		if (patientActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("patientActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch (v.getId())
		{
			case R.id.btn_Male:
				patientActivity.setGenderStatus(EnumConfig.GenderStatus.MALE);
				break;
			case R.id.btn_Female:
				patientActivity.setGenderStatus(EnumConfig.GenderStatus.FEMALE);
				break;
		}
		//蒙版点击一下之后消失的处理
		FragmentManager     fgManager           = getFragmentManager();
		Fragment            fragment            = fgManager.findFragmentByTag(SelectSexFragment.class.getName());
		FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
	}

	public void setGenderTitleHight(Integer genderTitleHight)
	{
		m_genderTitleHight = genderTitleHight;
	}
}