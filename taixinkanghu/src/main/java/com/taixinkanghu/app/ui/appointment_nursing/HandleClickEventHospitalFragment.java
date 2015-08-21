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
 * 2015/8/16		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.net.DHospital;
import com.taixinkanghu.app.model.data.net.DHospitalList;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.ArrayList;

public class HandleClickEventHospitalFragment extends BaseHandleOnClickEvent
{
	public HandleClickEventHospitalFragment(Context context)
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

		//0201. 按钮控件，选择医院相关内容。
		String tag = v.getTag().toString();
		if (tag == null)
			return;
		Integer indexBtn = 0;
		int indexHospital = 0;
		try
		{
			indexBtn = Integer.valueOf(tag);
			indexHospital = indexBtn - 1;
		}
		catch (NumberFormatException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}

		//全部
		if (indexBtn == 0)
		{
			apoitNursingActivity.setHospitalID(0);
			cancelAction(apoitNursingActivity);
			return;
		}

		//hospitalid
		ArrayList<DHospital> hospitalLists = DHospitalList.GetInstance().getHospitals();
		if (hospitalLists == null)
		{
			RegisterDialog.GetInstance().setMsg("hospitalLists == null", apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}
		if (indexHospital >= hospitalLists.size())
		{
			RegisterDialog.GetInstance().setMsg("indexHospital >= hospitalLists.size()[indexHospital:="+indexHospital+"][hospitalLists.size:=+"+hospitalLists.size()+"+]", apoitNursingActivity);
			RegisterDialog.GetInstance().show();
			return;
		}

		DHospital dHospital = hospitalLists.get(indexHospital);
		int id = dHospital.getID();
		apoitNursingActivity.setHospitalID(id);

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

		FragmentManager fgManager           = apoitNursingActivity.getFragmentManager();
		Fragment fragment            = fgManager.findFragmentByTag(SelectHospitalFragment.class.getName());
		FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();

	}

}
