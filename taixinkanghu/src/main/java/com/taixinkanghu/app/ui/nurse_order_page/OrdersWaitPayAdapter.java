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

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.model.data.net.DNurserOrderList;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.util.ArrayList;

public class OrdersWaitPayAdapter extends BaseOrdersAdapter
{
	public OrdersWaitPayAdapter(Activity activity)
	{
		super(activity);
	}

	@Override
	public int getCount()
	{
		ArrayList<DNurseOrder> nurseOrdersWaitPay = DNurserOrderList.GetInstance().getNurseOrdersWaitPay();
		if (nurseOrdersWaitPay == null || nurseOrdersWaitPay.isEmpty())
			return 0;

		return nurseOrdersWaitPay.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		BaseListItem viewHolder = null;
		NurseOrderActivity nurseOrderActivity = (NurseOrderActivity)m_context;
		if (nurseOrderActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderActivity == null");
			RegisterDialog.GetInstance().show();
			return null;
		}

		ArrayList<DNurseOrder> nurseOrders = DNurserOrderList.GetInstance().getNurseOrdersWaitPay();
		if (position >= nurseOrders.size())
		{
			RegisterDialog.GetInstance().setMsg("position >= nurseOrders.size()[position:="+position+"][nurseOrders.size():="+nurseOrders.size()+"]");
			RegisterDialog.GetInstance().show();
			return null;
		}

		DNurseOrder nurseOrder = nurseOrders.get(position);
		if (convertView == null)
		{

			viewHolder = new ListItemWaitPay();
			convertView = m_layoutInflater.inflate(R.layout.item_order_info, null);
			convertView.setTag(viewHolder);

			viewHolder.initWidget(convertView, nurseOrderActivity, nurseOrder);
		}
		else
		{
			viewHolder = (ListItemWaitPay)convertView.getTag();
		}

		viewHolder.initContent(nurseOrder);
		return convertView;
	}

}
