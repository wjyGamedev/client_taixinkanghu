package com.taixinkanghu.app.ui.fragment;


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
import com.taixinkanghu.app.ui.activity.ChooseNurseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectHospitalFragment extends Fragment implements View.OnClickListener
{
	private Button m_allBtn = null;
	private Button m_beiJingTiantanBtn = null;
	private Button m_beiJingChaoYangBtn = null;
	private Button m_yiKeZhongLiuBtn = null;

	private LinearLayout m_titleLinearLayout = null;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	private Button btn1, btn2, btn3;

	private static final int CHAOYANGYIYUAN = 1;
	private static final int TIANTANYIYUAN  = 2;
	private static final int ZHONGLIUYIYUAN = 3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_select_hospital, container, false);
		view.setOnClickListener(this);
		return view;
//
//		if (way == 1)
//		{
//			ChooseNurseActivity activity = (ChooseNurseActivity)getActivity();
//			switch (activity.selected_hospital)
//			{
//			case CHAOYANGYIYUAN:
//				btn1.setSelected(true);
//				break;
//			case TIANTANYIYUAN:
//				btn2.setSelected(true);
//				break;
//			case ZHONGLIUYIYUAN:
//				btn3.setSelected(true);
//				break;
//			}
//		}
//		else if (way == 2)
//		{
//			WorkerInfoMoreActivity activity = (WorkerInfoMoreActivity)getActivity();
//			switch (activity.selected_hospital)
//			{
//			case CHAOYANGYIYUAN:
//				btn1.setSelected(true);
//				break;
//			case TIANTANYIYUAN:
//				btn2.setSelected(true);
//				break;
//			case ZHONGLIUYIYUAN:
//				btn3.setSelected(true);
//				break;
//			}
//		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initData();
		initModule();
		initView();
	}

	private void initView()
	{
		ChooseNurseActivity activity = (ChooseNurseActivity)getActivity();
		switch (activity.getHospitalList().getID())
		{
		case R.id.all_item:
		{
			m_allBtn.setSelected(true);
		}
		break;
		case R.id.beijingtiantan_item:
		{
			m_beiJingTiantanBtn.setSelected(true);
		}
		break;
		case R.id.beijingchaoyang_item:
		{
			m_beiJingChaoYangBtn.setSelected(true);
		}
		break;
		case R.id.yikeyuanzhongliu_item:
		{
			m_beiJingChaoYangBtn.setSelected(true);
		}
		break;
		default:
		{
			//TODO:ERROR
		}
		break;
		}

	}

	private void initData()
	{
		m_allBtn = (Button)getActivity().findViewById(R.id.all_item);
		m_beiJingTiantanBtn = (Button)getActivity().findViewById(R.id.beijingtiantan_item);
		m_beiJingChaoYangBtn = (Button)getActivity().findViewById(R.id.beijingchaoyang_item);
		m_yiKeZhongLiuBtn = (Button)getActivity().findViewById(R.id.yikeyuanzhongliu_item);
		m_titleLinearLayout = (LinearLayout)getActivity().findViewById(R.id.title_linear_layout);
	}

	private void initModule()
	{
		m_titleLinearLayout.setBackgroundColor(R.string.color_all_null);

		m_allBtn.setOnClickListener(this);
		m_beiJingTiantanBtn.setOnClickListener(this);
		m_beiJingChaoYangBtn.setOnClickListener(this);
		m_yiKeZhongLiuBtn.setOnClickListener(this);
	}


	@Override
	public void onClick(View v)
	{
		ChooseNurseActivity activity = (ChooseNurseActivity)getActivity();
		switch (v.getId())
		{
		case R.id.all_item:
		{
			m_allBtn.setSelected(true);
			getFragmentManager().popBackStack();
			activity.getHospitalList().setSelectedStatus(R.id.all_item, m_allBtn.getText());
		}
		break;
		case R.id.beijingtiantan_item:
		{
			m_beiJingTiantanBtn.setSelected(true);
			getFragmentManager().popBackStack();
			activity.getHospitalList().setSelectedStatus(R.id.beijingtiantan_item, m_beiJingTiantanBtn.getText());
		}
		break;
		case R.id.beijingchaoyang_item:
		{
			m_beiJingChaoYangBtn.setSelected(true);
			getFragmentManager().popBackStack();
			activity.getHospitalList().setSelectedStatus(R.id.beijingchaoyang_item, m_beiJingChaoYangBtn.getText());
		}
		break;
		case R.id.yikeyuanzhongliu_item:
		{
			m_beiJingChaoYangBtn.setSelected(true);
			getFragmentManager().popBackStack();
			activity.getHospitalList().setSelectedStatus(R.id.yikeyuanzhongliu_item, m_beiJingChaoYangBtn.getText());
		}
		break;
		default:
		{
			//蒙版点击一下之后消失的处理
			FragmentManager fgManager = getFragmentManager();
			Fragment fragment = fgManager.findFragmentById(R.id.owner_select_page);
			FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
			fragmentTransaction.remove(fragment);
			String tag = null;
			fragmentTransaction.addToBackStack(tag);
			fragmentTransaction.commit();
		}
		break;

		}
//		if (way == 1)
//		{
//
//
//		}
//		else if (way == 2)
//		{
//			WorkerInfoMoreActivity activity = (WorkerInfoMoreActivity)getActivity();
//			switch (v.getId())
//			{
//			case R.id.item1:
//				btn1.setSelected(true);
//				getFragmentManager().popBackStack();
//				activity.add_info.setText(btn1.getText());
//				activity.selected_hospital = CHAOYANGYIYUAN;
//				break;
//			case R.id.item2:
//				btn2.setSelected(true);
//				getFragmentManager().popBackStack();
//				activity.add_info.setText(btn2.getText());
//				activity.selected_hospital = TIANTANYIYUAN;
//				break;
//			case R.id.item3:
//				btn2.setSelected(true);
//				getFragmentManager().popBackStack();
//				activity.add_info.setText(btn3.getText());
//				activity.selected_hospital = ZHONGLIUYIYUAN;
//				break;
//			default:
//				//蒙版点击一下之后消失的处理
//				//                    FragmentManager fgManager = getFragmentManager();
//				Fragment fragment = getFragmentManager().findFragmentById(R.id.title);
//				FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//				fragmentTransaction.remove(fragment);
//				String tag = null;
//				fragmentTransaction.addToBackStack(tag);
//				fragmentTransaction.commit();
//				break;
//			}
//		}
	}





}