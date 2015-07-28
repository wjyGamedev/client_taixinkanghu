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
import android.content.Context;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;

public class HeaderCommon
{
	//activity
	private Context m_context = null;
	//title
	private TextView                 m_HeaderTV                 = null;
	private ImageButton              m_BackImgBtn               = null;
	//logical
	private boolean m_InitFlag = false;

	public HeaderCommon(Activity activity)
	{
		m_context = activity;
	}

	public void init()
	{
		Activity activity = (Activity)m_context;
		m_HeaderTV = (TextView)activity.findViewById(R.id.page_title);
		m_BackImgBtn = (ImageButton)activity.findViewById(R.id.btn_back);

		m_BackImgBtn.setOnClickListener(new HandlerClickEventBackBtn(activity));
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

	public boolean isInitFlag()
	{
		return m_InitFlag;
	}


}
