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

import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.ui.nurse_info.NurseInfoActivity;
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
		SelectNurseActivity selectNurseActivity = (SelectNurseActivity)m_context;
		if (selectNurseActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("selectNurseActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		Intent intent = new Intent(selectNurseActivity, NurseInfoActivity.class);
		intent.putExtra(NurseBasicListConfig.NEW_ID, (int)id);
		intent.putExtra(NurseBasicListConfig.OLD_ID, selectNurseActivity.getOldNurseID());
		selectNurseActivity.startActivity(intent);
		return;

	}



	public Context getContext()
	{
		return m_context;
	}
}
