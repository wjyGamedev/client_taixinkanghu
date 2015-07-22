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
import android.widget.ImageView;
import android.widget.TextView;

public class DropDownList
{
	private Activity  m_activity= null;
	private TextView  m_textView  = null;    //显示文字
	private ImageView m_imageView = null;//显示图片

	public DropDownList(Activity activity)
	{
		m_activity = activity;
	}

	public void init(int textID, int imgID)
	{
		m_textView = (TextView)m_activity.findViewById(textID);
		m_imageView = (ImageView)m_activity.findViewById(imgID);
	}

	public TextView getTextView()
	{
		return m_textView;
	}

	public ImageView getImageView()
	{
		return m_imageView;
	}
}
