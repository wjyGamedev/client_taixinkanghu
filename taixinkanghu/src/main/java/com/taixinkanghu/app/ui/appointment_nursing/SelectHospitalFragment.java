package com.taixinkanghu.app.ui.appointment_nursing;


import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.UIConfig;
import com.taixinkanghu.app.model.data.DHospital;
import com.taixinkanghu.app.model.data.DHospitalList;
import com.taixinkanghu.app.model.net.event.recv.FinishedHospitalListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqHospitalListEvent;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectHospitalFragment extends Fragment implements View.OnTouchListener
{
	//widget
	private LayoutInflater m_layoutInflater = null;
	private View m_view;
	private TextView m_contentTipsTV = null;
	private TextView m_moreContentTipsTV = null;
	private GridLayout   m_gridLayout = null;
	private LinearLayout m_headerBgLL = null;
	private LinearLayout m_bottomBgLL = null;
	private ArrayList<Button>       m_buttons  = new ArrayList<>();

	//logical
	private HandleClickEventHospitalFragment m_handleClickEventHospitalFragment = null;
	private EventBus                          m_eventBus                          = EventBus.getDefault();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		m_layoutInflater = inflater;
		m_view = m_layoutInflater.inflate(R.layout.fragment_select_list, container, false);
		init();
		initContent();
		initListener();
		return m_view;
	}


	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return true;
	}

	private void init()
	{
		m_contentTipsTV = (TextView)m_view.findViewById(R.id.content_tips);
		m_moreContentTipsTV = (TextView)m_view.findViewById(R.id.more_content_tips);
		m_gridLayout = (GridLayout)m_view.findViewById(R.id.widget_region_gl);
		m_headerBgLL = (LinearLayout)m_view.findViewById(R.id.header_bg_ll);
		m_bottomBgLL = (LinearLayout)m_view.findViewById(R.id.bottom_bg_ll);
		m_handleClickEventHospitalFragment = new HandleClickEventHospitalFragment(getActivity());
	}

	private void initContent()
	{
		if (m_layoutInflater == null)
			return;

		//设置提示信息
		m_contentTipsTV.setText(getActivity().getResources().getString(R.string.hospital_content_tips));
		m_moreContentTipsTV.setText(getActivity().getResources().getString(R.string.hospital_more_content_tips));

		ArrayList<DHospital> hospitals = DHospitalList.GetInstance().getHospitals();

		//01. 没有医院列表，则重新发送
		if (hospitals.isEmpty())
		{
			ReqHospitalListEvent hospitalListEvent = new ReqHospitalListEvent();
			m_eventBus.post(hospitalListEvent);
			return;
		}

		//02. 有医院列表，则在本地动态显示。
		int size = hospitals.size();
		int iMaxColumn = UIConfig.SELECT_HOSPITAL_FRAGMENT_MAX_COLUMN;
		int iMaxRow = (size + iMaxColumn - 1)/iMaxColumn;
		int indexHospital = 0;
		int indexBtn = 0;
		DHospital dHospital = null;
		String tag = null;
		for (int indexRow = 0; indexRow < iMaxRow; ++indexRow)
		{
			for (int indexColumn = 0; indexColumn < iMaxColumn; ++indexColumn)
			{
				indexBtn = indexRow * iMaxColumn + indexColumn;
				//因为第一个为全部，所以-1
				indexHospital = indexBtn - 1;
				if (indexHospital >= size)
				{
					return;
				}

				View view = m_layoutInflater.inflate(R.layout.fragment_select_list_item, m_gridLayout, false);
				tag = String.valueOf(indexBtn);
				Button btn = (Button)view.findViewById(R.id.item_id);
				btn.setTag(tag);
				if (indexRow == 0 && indexColumn == 0)
				{
					btn.setText(getActivity().getResources().getString(R.string.content_all));
				}
				else
				{
					dHospital = hospitals.get(indexHospital);
					if (dHospital == null)
						return;

					String name = dHospital.getName();
					btn.setText(name);
				}
				m_buttons.add(btn);

				//设置它的行和列
				GridLayout.Spec rowSpec = GridLayout.spec(indexRow);
				GridLayout.Spec columnSpec=GridLayout.spec(indexColumn);
				GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec,columnSpec);
				params.setGravity(Gravity.FILL);
				m_gridLayout.addView(view, params);
			}
		}
		return;

	}

	private void initListener()
	{
		for (Button btn : m_buttons)
		{
			btn.setOnClickListener(m_handleClickEventHospitalFragment);
		}
		m_headerBgLL.setOnClickListener(m_handleClickEventHospitalFragment);
		m_bottomBgLL.setOnClickListener(m_handleClickEventHospitalFragment);
		//防止点击穿透
		m_view.setOnTouchListener(this);
	}

	/**
	 * EventBus handler
	 */
	public void onEventMainThread(FinishedHospitalListEvent event)
	{
		initContent();
		return;
	}

}