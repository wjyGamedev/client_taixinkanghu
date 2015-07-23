/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.drop_down_box.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/22		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.drop_down_list;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class DropDownList  implements View.OnClickListener
{
	protected Activity  m_activity  = null;
	private LinearLayout m_linearLayout = null;
	private   TextView  m_textView  = null;    //显示文字
	private   ImageView m_imageView = null;//显示图片
	private   Integer   m_ID        = 0;

	public DropDownList(Activity activity)
	{
		m_activity = activity;
	}

	public void init(int layoutID, int textID, int imgID)
	{
		m_linearLayout = (LinearLayout)m_activity.findViewById(layoutID);
		m_textView = (TextView)m_activity.findViewById(textID);
		m_imageView = (ImageView)m_activity.findViewById(imgID);

		m_linearLayout.setOnClickListener(this);
	}

	public TextView getTextView()
	{
		return m_textView;
	}

	public ImageView getImageView()
	{
		return m_imageView;
	}

	public Integer getID()
	{
		return m_ID;
	}

	public void setSelectedStatus(int iID, Object params)
	{
		m_ID = iID;
		String strText = (String)params;
		if (strText == null)
			return;

		m_textView.setText(strText);
	}
}
