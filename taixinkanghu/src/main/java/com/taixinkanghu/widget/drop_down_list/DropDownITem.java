/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.drop_down_list.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/23		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.drop_down_list;

import android.content.Context;
import android.widget.LinearLayout;

public class DropDownItem extends LinearLayout
{
	private Integer m_columnNum = 0;

	public DropDownItem(Context context, int columnNum)
	{
		super(context);
		m_columnNum = columnNum;
		init();
		initWidget();
	}

	private void init()
	{

	}

	private void initWidget()
	{

	}

	public Integer getColumnNum()
	{
		return m_columnNum;
	}
}

