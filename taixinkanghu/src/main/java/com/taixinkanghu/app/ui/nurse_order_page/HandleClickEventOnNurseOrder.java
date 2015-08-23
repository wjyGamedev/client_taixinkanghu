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
import android.text.TextUtils;
import android.view.View;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import de.greenrobot.event.EventBus;

public class HandleClickEventOnNurseOrder extends BaseHandleOnClickEvent
{
	private EventBus m_eventBus = EventBus.getDefault();

	public HandleClickEventOnNurseOrder(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		NurseOrderActivity nurseOrderActivity = (NurseOrderActivity)m_context;
		if (nurseOrderActivity == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderActivity == null");
			RegisterDialog.GetInstance().show();
			return;
		}

		switch (v.getId())
		{
		//01. tab btns:
		//0101. tab：全部
		case R.id.all_rbtn:
		{
			nurseOrderActivity.allAction();
		}
		return;
		//0102. tab:未付款
		case R.id.wait_pay_rbtn:
		{
			nurseOrderActivity.waitPayAction();
		}
		return;
		//0103. tab:已完成
		case R.id.wait_service_rbtn:
		{
			nurseOrderActivity.waitServiceAction();
		}
		return;
		}

		//02. func btn:
		FuncBtnTabObject funcBtnTabObject = (FuncBtnTabObject)v.getTag();
		String funcTag = null;
		DNurseOrder nurseOrder = null;
		if (funcBtnTabObject != null)
		{
			funcTag = funcBtnTabObject.getFuncTag();
			nurseOrder = funcBtnTabObject.getNurseOrder();
		}
		nurseOrderActivity.setNurseOrder(nurseOrder);

		//0201. 取消订单
		if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_CANCEL_ORDER))
		{
			nurseOrderActivity.cancelOrder();
			return;
		}
		//0202. 确认支付
		else if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_PAY_ORDER))
		{
			nurseOrderActivity.payOrder();
			return;
		}
		//0203. 评价
		else if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_COMMENT_ORDER))
		{
			nurseOrderActivity.comment();
			return;
		}
		//0204. 续订
		else if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_REPEAT_ORDER))
		{
			nurseOrderActivity.repeatOrder();
			return;
		}
		//0205. 更换
		else if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_CHANGE_NURSE))
		{
			nurseOrderActivity.changeNurse();
			return;
		}
		//0206. 退订
		else if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_CANCEL_SERVICE))
		{
			nurseOrderActivity.cancelService();
			return;
		}
		//0207. 补差价
		else if (TextUtils.equals(funcTag, NurseOrderActivity.FUNC_BTN_TAG_PAY_MORE))
		{
			nurseOrderActivity.payMore();
			return;
		}
		return;

	}

}
