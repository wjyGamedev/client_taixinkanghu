/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_order_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/23		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_page;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class HandlerItemClickEventListView implements AdapterView.OnItemClickListener
{
	private Context m_context = null;

	public HandlerItemClickEventListView(Activity activity)
	{
		m_context = activity;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		NurseOrderActivity nurseOrderActivity = (NurseOrderActivity)m_context;
		if (nurseOrderActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		//跳转到详细订单页面。
//		Intent intent = new Intent(nurseOrderActivity, NurseInfoActivity.class);
//		nurseOrderActivity.startActivity(intent);
		return;
	}
}
