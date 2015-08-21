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

import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.model.data.net.DNurserOrderList;

import java.util.ArrayList;

public class OrdersWaitServiceAdapter extends BaseOrdersAdapter
{
	public OrdersWaitServiceAdapter(Context context)
	{
		super(context);
	}

	@Override
	public int getCount()
	{
		ArrayList<DNurseOrder> nurseOrdersWaitService = DNurserOrderList.GetInstance().getNurseOrdersWaitService();
		if (nurseOrdersWaitService == null || nurseOrdersWaitService.isEmpty())
			return 0;

		return nurseOrdersWaitService.size();
	}



}
