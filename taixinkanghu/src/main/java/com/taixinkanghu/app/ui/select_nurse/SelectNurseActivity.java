/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_nurse.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_nurse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.header.HeaderCommon;

public class SelectNurseActivity  extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;

	//tips
	private TextView m_textView = null;

	//listview
	public  ListView                      m_NursesLV                      = null;
	private HandlerItemClickEventListView m_handlerItemClickEventListView = null;
	private SelectNurseAdapter            m_selectNurseAdapter            = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_nurse);

		init();
		initModule();
	}

	private void init()
	{
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

		//listview
		m_textView = (TextView)findViewById(R.id.tips_tv);

		//功能区
		m_NursesLV = (ListView)findViewById(R.id.nurse_display_list);
		m_handlerItemClickEventListView = new HandlerItemClickEventListView(this);
		m_selectNurseAdapter = new SelectNurseAdapter(this);

		//bottom
	}

	private void initModule()
	{
		//title
		m_headerCommon.setTitle(R.string.select_nurse_title);

		//tips
		m_textView.setText(R.string.select_nurse_tips);
		m_textView.setTextColor(getResources().getColor(R.color.main_color));
		//功能区
		m_NursesLV.setOnItemClickListener(m_handlerItemClickEventListView);
		m_NursesLV.setAdapter(m_selectNurseAdapter);

		//bottom

	}

}
