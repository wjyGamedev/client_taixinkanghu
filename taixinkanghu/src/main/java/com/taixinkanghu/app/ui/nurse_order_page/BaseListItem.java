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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DNurseBasics;
import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.model.data.net.DNurseSenior;
import com.taixinkanghu.app.model.data.page.DFaceImages;
import com.taixinkanghu.util.android.AppUtil;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.circleimageview.CircleImageView;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class BaseListItem
{
	//widget
	protected Activity m_activity = null;
	protected View            m_view               = null;
	protected LinearLayout    m_orderCliceRegionLL = null;    //订单点击region
	protected CircleImageView m_nurseHeaderImgIV   = null;        //头像
	protected TextView        m_nurseNameTV        = null;            //名字
	protected TextView        m_orderStatus        = null;            //订单状态
	protected TextView        m_orderBeginDate     = null;            //服务的起始时间
	protected TextView        m_orderEndDate       = null;            //服务的结束时间
	protected TextView        m_totalDayNum        = null;            //服务总天数。
	protected TextView        m_totalPrice         = null;                //总价格
	protected Button          m_funcBtn_1          = null;    //功能按钮1
	protected Button          m_funcBtn_2          = null;    //功能按钮2
	protected Button          m_funcBtn_3          = null;    //功能按钮3
	protected Button          m_funcBtn_4          = null;    //功能按钮4
	protected Button          m_funcBtn_5          = null;    //功能按钮5

	//logical
	private DNurseOrder m_nurseOrder = null;

	private SimpleDateFormat m_simpleDateFormat = new SimpleDateFormat(DateConfig.PATTERN_DATE_YEAR_MONTH_DAY);

	protected HandleClickEventOnNurseOrder m_handleClickEventOnNurseOrder = null;
	protected EventBus                     m_eventBus                     = EventBus.getDefault();


	public void initWidget(View view, Activity activity, DNurseOrder nurseOrder)
	{
		m_activity = activity;
		m_view = view;
		m_orderCliceRegionLL = (LinearLayout)view.findViewById(R.id.order_click_region_ll);
		m_nurseHeaderImgIV = (CircleImageView)view.findViewById(R.id.header_img_iv);
		m_nurseNameTV = (TextView)view.findViewById(R.id.nurse_name_tv);
		m_orderStatus = (TextView)view.findViewById(R.id.order_state_tv);
		m_orderBeginDate = (TextView)view.findViewById(R.id.order_begin_date_tv);
		m_orderEndDate = (TextView)view.findViewById(R.id.order_end_date_tv);
		m_totalDayNum = (TextView)view.findViewById(R.id.total_day_num_tv);
		m_totalPrice = (TextView)view.findViewById(R.id.total_price_tv);
		m_funcBtn_1 = (Button)view.findViewById(R.id.func_btn_1);
		m_funcBtn_2 = (Button)view.findViewById(R.id.func_btn_2);
		m_funcBtn_3 = (Button)view.findViewById(R.id.func_btn_3);
		m_funcBtn_4 = (Button)view.findViewById(R.id.func_btn_4);
		m_funcBtn_5 = (Button)view.findViewById(R.id.func_btn_5);

		m_handleClickEventOnNurseOrder = new HandleClickEventOnNurseOrder(activity);
		m_funcBtn_1.setOnClickListener(m_handleClickEventOnNurseOrder);
		m_funcBtn_2.setOnClickListener(m_handleClickEventOnNurseOrder);
		m_funcBtn_3.setOnClickListener(m_handleClickEventOnNurseOrder);
		m_funcBtn_4.setOnClickListener(m_handleClickEventOnNurseOrder);
		m_funcBtn_5.setOnClickListener(m_handleClickEventOnNurseOrder);

	}

	//派生类重载， 用来区分功能区。
	public void initFuncWidget(DNurseOrder nurseOrder)
	{
		return;
	}

	public void initContent(DNurseOrder nurseOrder)
	{
		//01. common content
		if (nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrder == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		DNurseBasics nurseBasics = nurseOrder.getNurseBasics();
		if (nurseBasics == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseBasics == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		DNurseSenior nurseSenior = nurseOrder.getNurseSenior();
		if (nurseSenior == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseSenior == null");
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrder = nurseOrder;

		int headerImgResID = DFaceImages.getInstance().getImgResIDbyID(m_nurseOrder.getNurseID());
		if (headerImgResID == DataConfig.DEFAULT_VALUE)
		{
			headerImgResID = DFaceImages.DEFAULT_IMAGE_RES_ID;
		}
		m_nurseHeaderImgIV.setImageResource(headerImgResID);

		String nurseName = nurseBasics.getName();
		m_nurseNameTV.setText(nurseName);

		EnumConfig.NurseOrderStatus orderStatus = m_nurseOrder.getOrderStatus();
		if (orderStatus != null)
		{
			m_orderStatus.setText(orderStatus.getName());
		}

		Date beginDate = m_nurseOrder.getBeginDate();
		String dateDescription = null;
		if (beginDate != null)
		{
			dateDescription = m_simpleDateFormat.format(beginDate);
			m_orderBeginDate.setText(dateDescription);
		}

		Date endDate = m_nurseOrder.getEndDate();
		if (endDate != null)
		{
			dateDescription = m_simpleDateFormat.format(endDate);
			m_orderEndDate.setText(dateDescription);
		}

		int days = LogicalUtil.GetDayNums(beginDate, endDate);
		m_totalDayNum.setText(String.valueOf(days));

		int totalPrice = m_nurseOrder.getTotalCharge();
		m_totalPrice.setText(String.valueOf(totalPrice));

		//02. special content
		initFuncWidget(nurseOrder);
		return;
	}

	public DNurseOrder getNurseOrder()
	{
		return m_nurseOrder;
	}

	/**
	 * func action
	 */
	protected void waitPayfuncAction(BaseListItem baseListItem)
	{
		//只有确认支付，取消订单两个按钮
		m_funcBtn_1.setVisibility(View.VISIBLE);
		m_funcBtn_2.setVisibility(View.VISIBLE);
		m_funcBtn_3.setVisibility(View.GONE);
		m_funcBtn_4.setVisibility(View.GONE);
		m_funcBtn_5.setVisibility(View.GONE);

		//确认支付
		m_funcBtn_1.setText(AppUtil.GetResources().getString(R.string.confirm_pay));

		FuncBtnTabObject funcBtnTabObject01 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_PAY_ORDER);
		m_funcBtn_1.setTag(funcBtnTabObject01);
		m_funcBtn_1.setOnClickListener(m_handleClickEventOnNurseOrder);

		//取消订单
		m_funcBtn_2.setText(AppUtil.GetResources().getString(R.string.cancel_order));

		FuncBtnTabObject funcBtnTabObject02 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_CANCEL_ORDER);
		m_funcBtn_2.setTag(funcBtnTabObject02);
		m_funcBtn_2.setOnClickListener(m_handleClickEventOnNurseOrder);

		return;
	}

	protected void waitServicefuncAction(BaseListItem baseListItem)
	{
		//续订、更换、补差价、退订
		m_funcBtn_1.setVisibility(View.VISIBLE);
		m_funcBtn_2.setVisibility(View.VISIBLE);
		m_funcBtn_3.setVisibility(View.VISIBLE);
		m_funcBtn_4.setVisibility(View.VISIBLE);
		m_funcBtn_5.setVisibility(View.GONE);

		//续订
		m_funcBtn_1.setText(AppUtil.GetResources().getString(R.string.repeat_order));

		FuncBtnTabObject funcBtnTabObject01 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_REPEAT_ORDER);
		m_funcBtn_1.setTag(funcBtnTabObject01);
		m_funcBtn_1.setOnClickListener(m_handleClickEventOnNurseOrder);

		//更换
		m_funcBtn_2.setText(AppUtil.GetResources().getString(R.string.change_nurse));

		FuncBtnTabObject funcBtnTabObject02 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_CHANGE_NURSE);
		m_funcBtn_2.setTag(funcBtnTabObject02);
		m_funcBtn_2.setOnClickListener(m_handleClickEventOnNurseOrder);

		//补差价
		m_funcBtn_3.setText(AppUtil.GetResources().getString(R.string.pay_more));

		FuncBtnTabObject funcBtnTabObject03 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_PAY_MORE);
		m_funcBtn_3.setTag(funcBtnTabObject03);
		m_funcBtn_3.setOnClickListener(m_handleClickEventOnNurseOrder);

		//退订
		m_funcBtn_4.setText(AppUtil.GetResources().getString(R.string.cancel_service));

		FuncBtnTabObject funcBtnTabObject04 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_CANCEL_SERVICE);
		m_funcBtn_4.setTag(funcBtnTabObject04);
		m_funcBtn_4.setOnClickListener(m_handleClickEventOnNurseOrder);

	}

	public void moreThanWaitServicefuncAction(BaseListItem baseListItem)
	{
		//续订、更换、补差价、评论，退订
		m_funcBtn_1.setVisibility(View.VISIBLE);
		m_funcBtn_2.setVisibility(View.VISIBLE);
		m_funcBtn_3.setVisibility(View.VISIBLE);
		m_funcBtn_4.setVisibility(View.VISIBLE);
		m_funcBtn_5.setVisibility(View.VISIBLE);

		//续订
		m_funcBtn_1.setText(AppUtil.GetResources().getString(R.string.repeat_order));

		FuncBtnTabObject funcBtnTabObject01 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_REPEAT_ORDER);
		m_funcBtn_1.setTag(funcBtnTabObject01);
		m_funcBtn_1.setOnClickListener(m_handleClickEventOnNurseOrder);

		//更换
		m_funcBtn_2.setText(AppUtil.GetResources().getString(R.string.change_nurse));

		FuncBtnTabObject funcBtnTabObject02 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_CHANGE_NURSE);
		m_funcBtn_2.setTag(funcBtnTabObject02);
		m_funcBtn_2.setOnClickListener(m_handleClickEventOnNurseOrder);

		//补差价
		m_funcBtn_3.setText(AppUtil.GetResources().getString(R.string.pay_more));

		FuncBtnTabObject funcBtnTabObject03 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_PAY_MORE);
		m_funcBtn_3.setTag(funcBtnTabObject03);
		m_funcBtn_3.setOnClickListener(m_handleClickEventOnNurseOrder);

		//评论
		m_funcBtn_4.setText(AppUtil.GetResources().getString(R.string.commente_order));

		FuncBtnTabObject funcBtnTabObject04 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_COMMENT_ORDER);
		m_funcBtn_4.setTag(funcBtnTabObject04);
		m_funcBtn_4.setOnClickListener(m_handleClickEventOnNurseOrder);

		//退订
		m_funcBtn_5.setText(AppUtil.GetResources().getString(R.string.cancel_service));

		FuncBtnTabObject funcBtnTabObject05 = new FuncBtnTabObject(baseListItem.getNurseOrder(), NurseOrderActivity.FUNC_BTN_TAG_CANCEL_SERVICE);
		m_funcBtn_5.setTag(funcBtnTabObject05);
		m_funcBtn_5.setOnClickListener(m_handleClickEventOnNurseOrder);


	}

}
