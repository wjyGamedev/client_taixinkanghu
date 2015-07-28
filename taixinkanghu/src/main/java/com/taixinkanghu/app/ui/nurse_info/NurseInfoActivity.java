/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_info;

import android.app.Activity;
import android.os.Bundle;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.header.HeaderCommon;

public class NurseInfoActivity extends Activity
{
	//title
	private HeaderCommon m_headerCommon = null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_info);

		init();
		initModule();
	}

	private void init()
	{
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();

	}

	private void initModule()
	{
		//title
		m_headerCommon.setTitle(R.string.nurse_info);

	}
}
