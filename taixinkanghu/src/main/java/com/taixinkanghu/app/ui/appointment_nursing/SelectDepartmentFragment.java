/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.appointment_nursing.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/17		WangJY		1.0.0		create
 */

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
import com.taixinkanghu.app.model.data.DDepartment;
import com.taixinkanghu.app.model.data.DDepartmentList;
import com.taixinkanghu.app.model.net.event.recv.FinishedDepartmentListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqDepartmentListEvent;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class SelectDepartmentFragment extends Fragment implements View.OnTouchListener
{
	//widget
	private LayoutInflater m_layoutInflater = null;
	private View m_view;
	private TextView          m_contentTipsTV     = null;
	private TextView          m_moreContentTipsTV = null;
	private GridLayout        m_gridLayout        = null;
	private LinearLayout      m_headerBgLL        = null;
	private LinearLayout      m_bottomBgLL        = null;
	private ArrayList<Button> m_buttons           = new ArrayList<>();

	//logical
	private HandleClickEventDepartmentFragment m_handleClickEventDepartmentFragment = null;
	private EventBus                           m_eventBus                           = EventBus.getDefault();

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

	//防止点击穿透
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
		m_handleClickEventDepartmentFragment = new HandleClickEventDepartmentFragment(getActivity());
	}

	private void initContent()
	{
		if (m_layoutInflater == null)
			return;

		//设置提示信息
		m_contentTipsTV.setText(getActivity().getResources().getString(R.string.department_content_tips));
		m_moreContentTipsTV.setText(getActivity().getResources().getString(R.string.department_more_content_tips));

		ArrayList<DDepartment> departments = DDepartmentList.GetInstance().getDepartments();

		//01. 没有科室列表，则重新发送
		if (departments.isEmpty())
		{
			ReqDepartmentListEvent departmentListEvent = new ReqDepartmentListEvent();
			m_eventBus.post(departmentListEvent);
			return;
		}

		//02. 科室列表，则在本地动态显示。
		int size = departments.size();
		int iMaxColumn = UIConfig.SELECT_DEPARTMENT_FRAGMENT_MAX_COLUMN;
		int iMaxRow = (size + iMaxColumn - 1)/iMaxColumn;
		int indexHospital = 0;
		int indexBtn = 0;
		DDepartment department = null;
		String tag = null;
		for (int indexRow = 0; indexRow < iMaxRow; ++indexRow)
		{
			for (int indexColumn = 0; indexColumn < iMaxColumn; ++indexColumn)
			{
				indexHospital = indexRow * iMaxColumn + indexColumn;
				if (indexHospital >= size)
				{
					return;
				}

				View view = m_layoutInflater.inflate(R.layout.fragment_select_list_item, m_gridLayout, false);
				tag = String.valueOf(indexBtn);
				Button btn = (Button)view.findViewById(R.id.item_id);
				btn.setTag(tag);
				department = departments.get(indexHospital);
				if (department == null)
					return;

				String name = department.getName();
				btn.setText(name);
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
			btn.setOnClickListener(m_handleClickEventDepartmentFragment);
		}
		m_headerBgLL.setOnClickListener(m_handleClickEventDepartmentFragment);
		m_bottomBgLL.setOnClickListener(m_handleClickEventDepartmentFragment);
		//防止点击穿透
		m_view.setOnTouchListener(this);
	}

	/**
	 * EventBus handler
	 */
	public void onEventMainThread(FinishedDepartmentListEvent event)
	{
		initContent();
		return;
	}
}
