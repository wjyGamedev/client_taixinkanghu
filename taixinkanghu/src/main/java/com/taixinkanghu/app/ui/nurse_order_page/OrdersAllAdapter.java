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
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

public class OrdersAllAdapter extends BaseOrdersAdapter
{



	public OrdersAllAdapter(Context context)
	{
		super(context);
	}

	@Override
	public int getCount()
	{
		if (m_nurseOrderList == null || m_nurseOrderList.isEmpty())
		{
			m_nurseOrderList = DNurserOrderList.GetInstance().getNurseOrdersAll();
		}

		if (m_nurseOrderList == null || m_nurseOrderList.isEmpty())
		{
			return 0;
		}

		return m_nurseOrderList.size();
	}


}
