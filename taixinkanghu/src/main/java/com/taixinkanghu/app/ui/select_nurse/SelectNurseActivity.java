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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.header.HandlerClickEventBackBtn;

public class SelectNurseActivity  extends Activity
{
	//title
	private TextView                 m_HeaderTV                 = null;
	private ImageButton              m_BackImgBtn               = null;
	private HandlerClickEventBackBtn m_handlerClickEventBackBtn = null;

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
		m_HeaderTV = (TextView)findViewById(R.id.page_title);
		m_BackImgBtn = (ImageButton)findViewById(R.id.btn_back);
		m_handlerClickEventBackBtn = new HandlerClickEventBackBtn(this);

		//功能区
		m_NursesLV = (ListView)findViewById(R.id.nurse_display_list);
		m_handlerItemClickEventListView = new HandlerItemClickEventListView(this);
		m_selectNurseAdapter = new SelectNurseAdapter(this);
		//bottom
	}

	private void initModule()
	{
		//title
		m_HeaderTV.setText(R.string.owner_choose_nurse);
		m_BackImgBtn.setOnClickListener(m_handlerClickEventBackBtn);

		//功能区
		m_NursesLV.setOnItemClickListener(m_handlerItemClickEventListView);
		m_NursesLV.setAdapter(m_selectNurseAdapter);

		//bottom

	}

}
