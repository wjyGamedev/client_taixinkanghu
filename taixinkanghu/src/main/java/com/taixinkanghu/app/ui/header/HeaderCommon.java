/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.header.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.header;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;

public class HeaderCommon
{
	//activity
	private Activity     m_activity   = null;
	//title
	private TextView    m_HeaderTV   = null;
	private ImageButton m_BackImgBtn = null;
	//logical
	private boolean     m_InitFlag   = false;

	public HeaderCommon(Activity activity)
	{
		m_activity = activity;
	}

	public void init()
	{
		m_HeaderTV = (TextView)m_activity.findViewById(R.id.page_title);
		m_BackImgBtn = (ImageButton)m_activity.findViewById(R.id.btn_back);

		m_BackImgBtn.setOnClickListener(new HandlerClickEventBackBtn(m_activity));
		m_InitFlag = true;
	}

	public void setTitle(int resid)
	{
		if (isInitFlag() == false)
		{
			return;
		}
		m_HeaderTV.setText(resid);
	}

	public void setTitle(int resid, View.OnClickListener backBtnListener)
	{
		if (isInitFlag() == false)
		{
			return;
		}
		m_HeaderTV.setText(resid);

		if (backBtnListener != null)
		{
			m_BackImgBtn.setOnClickListener(backBtnListener);
		}
	}

	public boolean isInitFlag()
	{
		return m_InitFlag;
	}


}
