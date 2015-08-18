/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.appointment_nursing.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.DDepartment;
import com.taixinkanghu.app.model.data.DDepartmentList;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.ArrayList;

public class HandleClickEventDepartmentFragment extends BaseHandleOnClickEvent
{
	public HandleClickEventDepartmentFragment(Context context)
	{
		super(context);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		ApoitNursingActivity apoitNursingActivity = (ApoitNursingActivity)activity;
		if (apoitNursingActivity == null)
			return;

		//01. 非按钮控件，返回到activity
		switch (v.getId())
		{
			case R.id.header_bg_ll:
			case R.id.bottom_bg_ll:
			{
				cancelAction(apoitNursingActivity);
			}
			return;
		}

		//0201. 按钮控件，选择科室相关内容。
		String tag = v.getTag().toString();
		if (tag == null)
			return;
		Integer indexBtn = 0;
		int indexDepartment = 0;
		try
		{
			indexDepartment = Integer.valueOf(tag);
		}
		catch (NumberFormatException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}

		//departmentid
		ArrayList<DDepartment> departmentList = DDepartmentList.GetInstance().getDepartments();
		if (departmentList == null)
		{
			RegisterDialog.GetInstance().setMsg("departmentList == null", apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}
		if (indexDepartment >= departmentList.size())
		{
			RegisterDialog.GetInstance().setMsg("indexDepartment >= departmentList.size()[indexDepartment:="+indexDepartment+"][departmentList.size:=+"+departmentList.size()+"+]", apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}

		DDepartment department = departmentList.get(indexDepartment);
		int id = department.getID();
		apoitNursingActivity.setDepartmentID(id);

		//0202. 返回到activity
		cancelAction(apoitNursingActivity);
		return;

	}

	private void cancelAction(ApoitNursingActivity apoitNursingActivity)
	{
		if (apoitNursingActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("apoitNursingActivity == null", apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}

		FragmentManager     fgManager           = apoitNursingActivity.getFragmentManager();
		Fragment            fragment            = fgManager.findFragmentByTag(SelectDepartmentFragment.class.getName());
		FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();

	}

}
