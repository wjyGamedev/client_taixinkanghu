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
 * 2015/8/22		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.ui.adapter.IBaseAdapter;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.ArrayList;

public class BaseOrdersAdapter extends IBaseAdapter
{
	private LayoutInflater         m_layoutInflater = null;
	protected ArrayList<DNurseOrder> m_nurseOrderList = new ArrayList<>();

	public BaseOrdersAdapter(Context context)
	{
		super(context);
		init();
	}

	@Override
	public int getCount()
	{
		return 0;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		if (m_nurseOrderList == null || m_nurseOrderList.isEmpty())
		{
			return 0;
		}

		if (position >= m_nurseOrderList.size())
		{
			RegisterDialog.GetInstance().setMsg("position >= m_nurseOrderList.size()[position:=" + position + "][m_nurseOrderList.size():=" + m_nurseOrderList.size() + "]");
			RegisterDialog.GetInstance().show();
			return 0;
		}

		DNurseOrder tmpNurseOrder = m_nurseOrderList.get(position);
		return tmpNurseOrder.getOrderID();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		return null;
	}

	private void init()
	{
		m_layoutInflater = LayoutInflater.from(m_context);
	}

	public LayoutInflater getLayoutInflater()
	{
		return m_layoutInflater;
	}


}
