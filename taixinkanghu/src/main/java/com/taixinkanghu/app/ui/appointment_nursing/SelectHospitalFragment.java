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
import com.taixinkanghu.app.ui.activity.WorkerInfoMoreActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectHospitalFragment extends Fragment implements View.OnClickListener
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	private Button btn1, btn2, btn3, btn4;

	private static final int All_YIYUAN     = 1;
	private static final int CHAOYANGYIYUAN = 2;
	private static final int TIANTANYIYUAN  = 3;
	private static final int ZHONGLIUYIYUAN = 4;

	private int m_intoWay = 0;

	private LinearLayout titleLinearLayout;

	private View view;

	public void setIntoWay(int intoWay)
	{
		m_intoWay = intoWay;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		view = inflater.inflate(R.layout.fragment_select_hospital, container, false);
		view.setOnClickListener(this);

		btn1 = (Button)view.findViewById(R.id.all_item);
		btn2 = (Button)view.findViewById(R.id.beijingtiantan_item);
		btn3 = (Button)view.findViewById(R.id.beijingchaoyang_item);
		btn4 = (Button)view.findViewById(R.id.yikeyuanzhongliu_item);

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


		//        Activity activity = getActivity();
		//        activity.getComponentName();
		//        System.out.println("activity.getComponentName() = "+activity.getComponentName());


		if (m_intoWay == 1)
		{
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
		}
		else if (m_intoWay == 2)
		{
			WorkerInfoMoreActivity activity = (WorkerInfoMoreActivity)getActivity();
			switch (activity.selected_hospital)
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
		}

		return view;
	}

	@Override
	public void onClick(View v)
	{
		if (m_intoWay == 1)
		{
			ApoitNursingActivity activity = (ApoitNursingActivity)getActivity();
			switch (v.getId())
			{
				case R.id.all_item:
					btn1.setSelected(true);
					getFragmentManager().popBackStack();
					activity.getHospitalTv().setText(btn1.getText());
					activity.setSelected_hospital(All_YIYUAN);
					activity.getDwonHospital().setVisibility(View.INVISIBLE);
					break;
				case R.id.beijingtiantan_item:
					btn2.setSelected(true);
					getFragmentManager().popBackStack();
					activity.getHospitalTv().setText(btn2.getText());
					activity.setSelected_hospital(TIANTANYIYUAN);
					activity.getDwonHospital().setVisibility(View.INVISIBLE);
					break;
				case R.id.beijingchaoyang_item:
					btn3.setSelected(true);
					getFragmentManager().popBackStack();
					activity.getHospitalTv().setText(btn3.getText());
					activity.setSelected_hospital(ZHONGLIUYIYUAN);
					activity.getDwonHospital().setVisibility(View.INVISIBLE);
					break;
				case R.id.yikeyuanzhongliu_item:
					btn4.setSelected(true);
					getFragmentManager().popBackStack();
					activity.getHospitalTv().setText(btn4.getText());
					activity.setSelected_hospital(ZHONGLIUYIYUAN);
					activity.getDwonHospital().setVisibility(View.INVISIBLE);
					break;
				default:
					//蒙版点击一下之后消失的处理
					FragmentManager fgManager = getFragmentManager();
					Fragment fragment = fgManager.findFragmentById(R.id.appointment_nursing_page);
					FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
					fragmentTransaction.remove(fragment);
					String tag = null;
					fragmentTransaction.addToBackStack(tag);
					fragmentTransaction.commit();
					break;
			}

		}
		else if (m_intoWay == 2)
		{
			WorkerInfoMoreActivity activity = (WorkerInfoMoreActivity)getActivity();
			switch (v.getId())
			{
				case R.id.all_item:
					btn1.setSelected(true);
					getFragmentManager().popBackStack();
					activity.add_info.setText(btn1.getText());
					activity.selected_hospital = All_YIYUAN;
					break;
				case R.id.beijingtiantan_item:
					btn2.setSelected(true);
					getFragmentManager().popBackStack();
					activity.add_info.setText(btn2.getText());
					activity.selected_hospital = CHAOYANGYIYUAN;
					break;
				case R.id.beijingchaoyang_item:
					btn2.setSelected(true);
					getFragmentManager().popBackStack();
					activity.add_info.setText(btn3.getText());
					activity.selected_hospital = TIANTANYIYUAN;
					break;
				case R.id.yikeyuanzhongliu_item:
					btn2.setSelected(true);
					getFragmentManager().popBackStack();
					activity.add_info.setText(btn3.getText());
					activity.selected_hospital = ZHONGLIUYIYUAN;
					break;
				default:
					//蒙版点击一下之后消失的处理
					FragmentManager fgManager = getFragmentManager();
					Fragment fragment = fgManager.findFragmentById(R.id.title);
					FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
					fragmentTransaction.remove(fragment);
					String tag = null;
					fragmentTransaction.addToBackStack(tag);
					fragmentTransaction.commit();
					break;
			}
		}
	}
}