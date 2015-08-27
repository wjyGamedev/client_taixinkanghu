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
 * 2015/8/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.nurse_order_page;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DateConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.config.MainActivityConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.net.DNurseOrder;
import com.taixinkanghu.app.model.data.net.DScheduleList;
import com.taixinkanghu.app.model.data.page.DApoitNursingPage;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNursingDate;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.net.config.NurseBasicListConfig;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderCancelServiceEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderListEvent;
import com.taixinkanghu.app.model.net.event.send.ReqApoitNursingEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderCancelEvent;
import com.taixinkanghu.app.ui.appointment_nursing.ApoitNursingActivity;
import com.taixinkanghu.app.ui.bottom.BottomCommon;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.nurse_order_pay_more.NurseOrderPayMoreActivity;
import com.taixinkanghu.app.ui.nurse_order_pay_page.NurseOrderPayActivity;
import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;
import com.taixinkanghu.util.logcal.LogicalUtil;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class NurseOrderActivity extends Activity implements GestureDetector.OnGestureListener
{
	//func btn
	public static final String FUNC_BTN_TAG_CANCEL_ORDER   = "func_btn_tag_cancel_order";
	public static final String FUNC_BTN_TAG_PAY_ORDER      = "func_btn_tag_pay_order";
	public static final String FUNC_BTN_TAG_COMMENT_ORDER  = "func_btn_tag_comment_order";
	public static final String FUNC_BTN_TAG_REPEAT_ORDER   = "func_btn_tag_repeat_order";
	public static final String FUNC_BTN_TAG_CHANGE_NURSE   = "func_btn_tag_change_nurse";
	public static final String FUNC_BTN_TAG_CANCEL_SERVICE = "func_btn_tag_cancel_service";
	public static final String FUNC_BTN_TAG_PAY_MORE       = "func_btn_tag_pay_more";

	//widget
	private HeaderCommon    m_headerCommon    = null;    //title
	private RadioGroup      m_orderOptionRG   = null;
	private RadioButton     m_allRBtn         = null;            //全部
	private RadioButton     m_waitPayRBtn     = null;    //未支付
	private RadioButton     m_waitServiceRBtn = null;    //已完成
	private GestureDetector m_gestureDetector = null;
	private ListView        m_orderInfoLV     = null;    //list列表显示区域

	private BottomCommon m_bottomCommon = null;

	//logical
	private OrdersAllAdapter         m_ordersAllAdapter         = null;
	private OrdersWaitPayAdapter     m_ordersWaitPayAdapter     = null;
	private OrdersWaitServiceAdapter m_ordersWaitServiceAdapter = null;

	private HandlerItemClickEventListView m_handlerItemClickEventListView = null;
	private HandleClickEventOnNurseOrder  m_handleClickEventOnNurseOrder  = null;
	private EventBus                      m_eventBus                      = EventBus.getDefault();

	private SimpleDateFormat m_simpleDateFormat = new SimpleDateFormat(DateConfig.PATTERN_DATE_MONTH_DAY);
	private DNurseOrder      m_nurseOrder       = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_order);
		init();
		initListener();
		initContent();

	}

	@Override
	protected void onStart()
	{
		updateContent();
		initGlobalData();
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		clearupGlobalData();
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		m_eventBus.unregister(this);
		super.onDestroy();
	}

	private void initGlobalData()
	{
		DGlobal.GetInstance().setContext(this);
	}

	private void clearupGlobalData()
	{
		DGlobal.GetInstance().clearupContext(this);
	}

	private void init()
	{
		//title
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.header_title);

		m_orderOptionRG = (RadioGroup)findViewById(R.id.order_option_rg);
		m_allRBtn = (RadioButton)findViewById(R.id.all_rbtn);
		m_waitPayRBtn = (RadioButton)findViewById(R.id.wait_pay_rbtn);
		m_waitServiceRBtn = (RadioButton)findViewById(R.id.wait_service_rbtn);

		m_orderInfoLV = (ListView)findViewById(R.id.order_info_lv);

		m_bottomCommon = new BottomCommon(this);
		m_bottomCommon.init();
		m_bottomCommon.setTitle(R.string.bottom_title);

		m_ordersAllAdapter = new OrdersAllAdapter(this);
		m_ordersWaitPayAdapter = new OrdersWaitPayAdapter(this);
		m_ordersWaitServiceAdapter = new OrdersWaitServiceAdapter(this);

		m_handlerItemClickEventListView = new HandlerItemClickEventListView(this);
		m_handleClickEventOnNurseOrder = new HandleClickEventOnNurseOrder(this);

		m_gestureDetector = new GestureDetector(this, this);

		m_eventBus.register(this);
	}

	private void initListener()
	{
		//		m_allRBtn.setOnClickListener(m_handleClickEventOnNurseOrder);
		//		m_waitPayRBtn.setOnClickListener(m_handleClickEventOnNurseOrder);
		//		m_waitServiceRBtn.setOnClickListener(m_handleClickEventOnNurseOrder);

		m_orderInfoLV.setOnItemClickListener(m_handlerItemClickEventListView);


		m_orderOptionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
												   {
													   @Override
													   public void onCheckedChanged(RadioGroup group, int checkedId)
													   {
														   if (checkedId == m_allRBtn.getId())
														   {
															   allAction();
														   }
														   else if (checkedId == m_waitPayRBtn.getId())
														   {
															   waitPayAction();
														   }
														   else if (checkedId == m_waitServiceRBtn.getId())
														   {
															   waitServiceAction();
														   }
													   }
												   }
												  );


	}

	public boolean dispatchTouchEvent(MotionEvent event)
	{
		if (m_gestureDetector.onTouchEvent(event))
		{
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}

	private void initContent()
	{
		//01. 默认all为选中状态
		m_allRBtn.setChecked(true);
	}

	private void updateContent()
	{
		int id = m_orderOptionRG.getCheckedRadioButtonId();
		switchContentByTab(id);
	}

	private void switchContentByTab(int id)
	{
		if (id == R.id.all_rbtn)
		{
			updateAllContent();
		}
		else if (id == R.id.wait_pay_rbtn)
		{
			updateWaitPayContent();
		}
		else if (id == R.id.wait_service_rbtn)
		{
			updateWaitServiceyContent();
		}
		else
		{
			updateAllContent();
		}
		return;

	}

	private void updateAllContent()
	{
		//		m_orderInfoLV.setAdapter(null);

		m_orderInfoLV.setAdapter(m_ordersAllAdapter);
		m_ordersAllAdapter.notifyDataSetChanged();
	}

	private void updateWaitPayContent()
	{
		//		m_orderInfoLV.setAdapter(null);

		m_orderInfoLV.setAdapter(m_ordersWaitPayAdapter);
		m_ordersWaitPayAdapter.notifyDataSetChanged();
	}

	private void updateWaitServiceyContent()
	{
		//		m_orderInfoLV.setAdapter(null);

		m_orderInfoLV.setAdapter(m_ordersWaitServiceAdapter);
		m_ordersWaitServiceAdapter.notifyDataSetChanged();
	}

	public void setNurseOrder(DNurseOrder nurseOrder)
	{
		m_nurseOrder = nurseOrder;
	}


	private void loadDataForDApoitNursingPage()
	{
		DApoitNursingPage dApoitNursingPage = DNursingModule.GetInstance().getApoitNursingPage();
		dApoitNursingPage.clearup();
		dApoitNursingPage.setName(m_nurseOrder.getPatientName());
		dApoitNursingPage.setPhone(m_nurseOrder.getPhoneNum());
		dApoitNursingPage.setAgeRage(m_nurseOrder.getPatientAge());
		dApoitNursingPage.setWeightRage(m_nurseOrder.getPatientWeight());
		dApoitNursingPage.setHospitalID(m_nurseOrder.getHospitalID());
		dApoitNursingPage.setDepartmenetID(m_nurseOrder.getDepartmentID());
		dApoitNursingPage.setPatientState(m_nurseOrder.getPatientState());

		Date beginDate = m_nurseOrder.getBeginDate();
		Date endDate   = m_nurseOrder.getEndDate();

		String beginContent = m_simpleDateFormat.format(beginDate);
		String endContent   = m_simpleDateFormat.format(endDate);
		int    days         = LogicalUtil.GetDayNums(beginDate, endDate);
		String total        = getResources().getString(R.string.char_total);
		String day          = getResources().getString(R.string.char_day);
		String display      = beginContent + " - " + endContent + total + days + day;

		DScheduleList scheduleList = m_nurseOrder.getScheduleList();
		if (scheduleList == null)
		{
			RegisterDialog.GetInstance().setMsg("scheduleList == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		ArrayList<Calendar> allCalendarList   = scheduleList.getAllCalendarList();
		ArrayList<Calendar> dayCalendarList   = scheduleList.getDayCalendarList();
		ArrayList<Calendar> nightCalendarList = scheduleList.getNightCalendarList();

		DNursingDate nursingDate = new DNursingDate(beginDate, endDate, allCalendarList, dayCalendarList, nightCalendarList, display);
		dApoitNursingPage.setNursingDate(nursingDate);

		return;
	}


	/**
	 * action
	 */
	//01. tab btns:
	//全部订单
	public void allAction()
	{
		switchContentByTab(R.id.all_rbtn);
	}

	//未付款订单
	public void waitPayAction()
	{
		switchContentByTab(R.id.wait_pay_rbtn);
	}

	//已完成订单
	public void waitServiceAction()
	{
		switchContentByTab(R.id.wait_service_rbtn);
	}

	//02. func btn:
	//取消订单
	public void cancelOrder()
	{
		HandleClickEventOnDialog_CancelOrder cancelOrderListener = new HandleClickEventOnDialog_CancelOrder();
		RegisterDialog.GetInstance().setMsg(getString(R.string.nurse_order_cancel_tips), this, cancelOrderListener, cancelOrderListener);
		RegisterDialog.GetInstance().show();
		return;
	}

	public void cancelOrderAction()
	{
		if (m_nurseOrder == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrder == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		//发送取消event
		ReqNurseOrderCancelEvent event   = new ReqNurseOrderCancelEvent();
		String                   userID  = DAccount.GetInstance().getId();
		int                      orderID = m_nurseOrder.getOrderID();
		event.setUserID(userID);
		event.setNurseOrderID(String.valueOf(orderID));
		m_eventBus.post(event);
		return;
	}

	//确认付款
	public void payOrder()
	{
		int    nurseID        = m_nurseOrder.getNurseID();
		int    orderID        = m_nurseOrder.getOrderID();
		String orderSerialNum = m_nurseOrder.getOrderSerialNum();
		int    totalPrice     = m_nurseOrder.getTotalCharge();

		Intent intent = new Intent(this, NurseOrderPayActivity.class);
		intent.putExtra(NurseOrderConfig.NURSE_ID, nurseID);
		intent.putExtra(NurseOrderConfig.ORDER_ID, orderID);
		intent.putExtra(NurseOrderConfig.ORDER_SERIAL_NUM, orderSerialNum);
		intent.putExtra(NurseOrderConfig.ORDER_USER_PAY, totalPrice);

		startActivity(intent);
		return;

	}

	//评价
	public void comment()
	{

	}

	//续订
	public void repeatOrder()
	{
		//01. 填充DApoitNursingPage数据
		loadDataForDApoitNursingPage();

		//02. 跳转到预约陪护页面
		Intent intent  = new Intent(this, ApoitNursingActivity.class);
		int    nurseID = m_nurseOrder.getNurseID();
		intent.putExtra(NurseBasicListConfig.ID, nurseID);
		startActivity(intent);

		//03. 更新Globa data
		DGlobal.GetInstance().SetNursingModuleStatus(EnumConfig.NursingModuleStatus.REPEAT_ORDER);

		return;

	}

	//更换护理员
	public void changeNurse()
	{
		//01. 填充DApoitNursingPage数据
		loadDataForDApoitNursingPage();

		//02. 发送消息，获取新的nurse list
		ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
		m_eventBus.post(reqApoitNursingEvent);

		//03. 跳转到护理员列表页面。
		Intent intent     = new Intent(this, SelectNurseActivity.class);
		int    oldNurseID = m_nurseOrder.getNurseID();
		intent.putExtra(NurseBasicListConfig.ID, oldNurseID);
		startActivity(intent);

		//04. 更新Globa data
		DGlobal.GetInstance().SetNursingModuleStatus(EnumConfig.NursingModuleStatus.CHANGE_NURSE);
		return;

	}

	//退订
	public void cancelService()
	{
		//01. 发送退订event
		FinishedNurseOrderCancelServiceEvent event   = new FinishedNurseOrderCancelServiceEvent();
		String                               userID  = DAccount.GetInstance().getId();
		int                                  orderID = m_nurseOrder.getOrderID();
		event.setUserID(userID);
		event.setOrderID(String.valueOf(orderID));
		m_eventBus.post(event);

		//02. 发送消息，获取新的nurse list
		//		ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
		//		m_eventBus.post(reqApoitNursingEvent);

		return;
	}

	//补差价
	public void payMore()
	{
		//01. set DGlobal status
		DGlobal.GetInstance().SetNursingModuleStatus(EnumConfig.NursingModuleStatus.PAY_MORE);

		//02. 打开补差价的页面
		Intent intent = new Intent(this, NurseOrderPayMoreActivity.class);

		int    orderID       = m_nurseOrder.getOrderID();
		String orderSerialID = m_nurseOrder.getOrderSerialNum();
		intent.putExtra(NurseOrderConfig.ORDER_ID, orderID);
		intent.putExtra(NurseOrderConfig.ORDER_SERIAL_NUM, orderSerialID);
		startActivity(intent);
		return;
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{

		if (e2.getX() - e1.getX() > MainActivityConfig.DELTA_MOTION_EVENT)
		{             // 从左向右滑动（左进右出）
			if (m_orderOptionRG.getCheckedRadioButtonId() == m_allRBtn.getId())
			{
				m_waitPayRBtn.setChecked(true);
			}
			else if (m_orderOptionRG.getCheckedRadioButtonId() == m_waitPayRBtn.getId())
			{
				m_waitServiceRBtn.setChecked(true);
			}
			else if (m_orderOptionRG.getCheckedRadioButtonId() == m_waitServiceRBtn.getId())
			{
				m_allRBtn.setChecked(true);
			}
			return true;
		}
		else if (e2.getX() - e1.getX() < -MainActivityConfig.DELTA_MOTION_EVENT)
		{         // 从右向左滑动（右进左出）
			if (m_orderOptionRG.getCheckedRadioButtonId() == m_allRBtn.getId())
			{
				m_waitServiceRBtn.setChecked(true);
			}
			else if (m_orderOptionRG.getCheckedRadioButtonId() == m_waitPayRBtn.getId())
			{
				m_allRBtn.setChecked(true);
			}
			else if (m_orderOptionRG.getCheckedRadioButtonId() == m_waitServiceRBtn.getId())
			{
				m_waitPayRBtn.setChecked(true);
			}
			return true;
		}

		return false;
	}

	/**
	 * dialog listener
	 */
	public class HandleClickEventOnDialog_CancelOrder implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			//01. 确认，走取消支付流程。
			if (which == DialogInterface.BUTTON_POSITIVE)
			{
				cancelOrderAction();
			}
			//02. 取消,关闭弹出对话框。
			else if (which == DialogInterface.BUTTON_NEGATIVE)
			{
				dialog.dismiss();
			}
			return;
		}
	}

	/**
	 * event bus handler
	 */
	//flush nurse order list
	public void onEventMainThread(FinishedNurseOrderListEvent event)
	{
		updateContent();
	}


}
