/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.bottom.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.bottom;

import android.app.Activity;
import android.widget.Button;

import com.taixinkanghu.R;

public class BottomCommon
{
	//activity
	private Activity m_activity = null;
	private Button   m_GotoMain = null;
	//logical
	private boolean	m_InitFlag   = false;


	public BottomCommon(Activity activity)
	{
		m_activity = activity;
	}

	public void init()
	{
		m_GotoMain = (Button)m_activity.findViewById(R.id.btn_goto_main);
		m_GotoMain.setOnClickListener(new HandlerClickEventGotoMain(m_activity));
		m_InitFlag = true;
	}

	public void setTitle(int resid)
	{
		if (isInitFlag() == false)
		{
			return;
		}
		m_GotoMain.setText(resid);
	}

	public boolean isInitFlag()
	{
		return m_InitFlag;
	}
}
