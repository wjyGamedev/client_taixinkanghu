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

import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DNurseOrder;

public class ListItemAll extends BaseListItem
{
	@Override
	public void initFuncWidget(DNurseOrder nurseOrder)
	{
		EnumConfig.NurseOrderStatus orderStatus = nurseOrder.getOrderStatus();
		if (orderStatus == EnumConfig.NurseOrderStatus.WAIT_PAYMENT)
		{
			waitPayfuncAction(this);
		}
		else if (orderStatus == EnumConfig.NurseOrderStatus.WAIT_SERVICE)
		{
			waitServicefuncAction(this);
		}
		else
		{
			moreThanWaitServicefuncAction(this);
		}
		return;
	}

}
