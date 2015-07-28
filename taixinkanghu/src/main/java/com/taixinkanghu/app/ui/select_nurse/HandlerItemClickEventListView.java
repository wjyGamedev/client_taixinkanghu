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
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.taixinkanghu.app.ui.nurse_info.NurseInfoActivity;

public class HandlerItemClickEventListView implements AdapterView.OnItemClickListener
{
	private Context m_context = null;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Activity activity = (Activity)m_context;
		Intent intent = new Intent(activity, NurseInfoActivity.class);
		activity.startActivity(intent);
	}

	public HandlerItemClickEventListView(Activity activity)
	{
		m_context = activity;
	}

	public Context getContext()
	{
		return m_context;
	}
}
