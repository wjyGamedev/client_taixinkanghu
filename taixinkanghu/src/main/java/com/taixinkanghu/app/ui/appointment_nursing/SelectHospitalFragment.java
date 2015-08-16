package com.taixinkanghu.app.ui.appointment_nursing;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.taixinkanghu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectHospitalFragment extends Fragment implements View.OnClickListener
{
	private Button btn1, btn2, btn3, btn4;
	private static final int All_YIYUAN     = 1;
	private static final int CHAOYANGYIYUAN = 2;
	private static final int TIANTANYIYUAN  = 3;
	private static final int ZHONGLIUYIYUAN = 4;
	private LinearLayout titleLinearLayout;
	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		view = inflater.inflate(R.layout.fragment_select_hospital, container, false);
		view.setOnClickListener(this);

		btn1 = (Button)view.findViewById(R.id.all_item);
		btn2 = (Button)view.findViewById(R.id.item_1);
		btn3 = (Button)view.findViewById(R.id.item_2);
		btn4 = (Button)view.findViewById(R.id.item_3);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);

		titleLinearLayout = (LinearLayout)view.findViewById(R.id.title_linear_layout);
		titleLinearLayout.setBackgroundResource(getResources().getColor(R.color.all_null));

		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)titleLinearLayout.getLayoutParams(); // 取控件mGrid当前的布局参数
		final float               scale        = getActivity().getResources().getDisplayMetrics().density;
		linearParams.height = (int)(317 * scale + 0.5f);// 当控件的高强制设成50象素
		titleLinearLayout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件myGrid

		ApoitNursingActivity activity = (ApoitNursingActivity)getActivity();
		switch (activity.getSelected_hospital())
		{
			case All_YIYUAN:
				btn1.setSelected(true);
				break;
			case CHAOYANGYIYUAN:
				btn2.setSelected(true);
				break;
			case TIANTANYIYUAN:
				btn3.setSelected(true);
				break;
			case ZHONGLIUYIYUAN:
				btn4.setSelected(true);
				break;
		}

		return view;
	}

	@Override
	public void onClick(View v)
	{
		ApoitNursingActivity activity = (ApoitNursingActivity)getActivity();
		switch (v.getId())
		{
			case R.id.all_item:
				btn1.setSelected(true);
				getFragmentManager().popBackStack();
				activity.getHospitalTv().setText(btn1.getText());
				activity.setSelected_hospital(All_YIYUAN);
				break;
			case R.id.item_1:
				btn2.setSelected(true);
				getFragmentManager().popBackStack();
				activity.getHospitalTv().setText(btn2.getText());
				activity.setSelected_hospital(TIANTANYIYUAN);
				break;
			case R.id.item_2:
				btn3.setSelected(true);
				getFragmentManager().popBackStack();
				activity.getHospitalTv().setText(btn3.getText());
				activity.setSelected_hospital(ZHONGLIUYIYUAN);
				break;
			case R.id.item_3:
				btn4.setSelected(true);
				getFragmentManager().popBackStack();
				activity.getHospitalTv().setText(btn4.getText());
				activity.setSelected_hospital(ZHONGLIUYIYUAN);
				break;
			default:
				break;
		}

		//蒙版点击一下之后消失的处理
		FragmentManager      fgManager           = getFragmentManager();
		android.app.Fragment fragment            = fgManager.findFragmentByTag(SelectHospitalFragment.class.getName());
		FragmentTransaction  fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
	}
}