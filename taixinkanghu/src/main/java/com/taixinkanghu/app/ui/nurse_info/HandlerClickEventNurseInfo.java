/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.nurse_info.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/29		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_info;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.ui.activity.ReviewsActivity;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.app.ui.main_page.MainActivity;
import com.taixinkanghu.app.ui.nurse_order_confirm_page.OrderConfirmActivity;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class HandlerClickEventNurseInfo  extends BaseHandleOnClickEvent
{
	public HandlerClickEventNurseInfo(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		NurseInfoActivity nurseInfoActivity = (NurseInfoActivity)m_context;
		if (nurseInfoActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseInfoActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch(v.getId())
		{
			case R.id.btn_goto_main:
			{
				nurseInfoActivity.startActivity(new Intent(nurseInfoActivity, MainActivity.class));
			}
			return;
			case R.id.btn_select:
			{
				int nurseID = nurseInfoActivity.getNurseID();
				int oldNurseID = nurseInfoActivity.getOldNurseID();
				Intent intent = new Intent(nurseInfoActivity, OrderConfirmActivity.class);
				intent.putExtra(NurseBasicListConfig.NEW_ID, nurseID);
				intent.putExtra(NurseBasicListConfig.OLD_ID, oldNurseID);
				nurseInfoActivity.startActivity(intent);
			}
			return;
			case R.id.btn_reviews:
			{
				nurseInfoActivity.startActivity(new Intent(nurseInfoActivity, ReviewsActivity.class ));
			}
			return;
		}
		return;
	}
}
