package com.taixinkanghu.app.ui.nurse_order_pay_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNurseOrderConfirmPage;
import com.taixinkanghu.app.model.data.page.DNurseOrderPayPage;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.recv.FailedNurseOrderCheckEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishNurseOrderAlipayEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderCheckEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderAlipayEvent;
import com.taixinkanghu.app.ui.appointment_nursing.ReqApoitNursingEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;
import com.taixinkanghu.third.party.alipay.Config;
import com.taixinkanghu.third.party.alipay.Util;
import com.taixinkanghu.third.party.alipay.UtilRSA;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/3.
 */
public class NurseOrderPayActivity extends Activity
{
	private HeaderCommon m_headerCommon     = null;
	private TextView     m_orderSerialNumTV = null;
	private TextView     m_priceTV          = null;
	private RadioButton  m_cashRBtn         = null;
	private RadioButton  m_alipayRBtn       = null;
	private RadioButton  m_weixinRBtn       = null;
	private Button       m_payBtn           = null;

	//logical
	private HandlerClickEventNursOrderPay m_handlerClickEventNursOrderPay = null;

	private EventBus m_eventBus = EventBus.getDefault();


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_order_pay);

		init();
		initListener();
		initData();
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

	private void initData()
	{
		Intent intent = getIntent();
		String userID = DAccount.GetInstance().getId();
		DNurseOrderPayPage.GetInstance().setUserID(userID);

		int orderID = intent.getIntExtra(NurseOrderConfig.ORDER_ID, DataConfig.DEFAULT_VALUE);
		if (orderID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("nurseID is invalid[orderID:="+orderID+"]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		DNurseOrderPayPage.GetInstance().setOrderID(orderID);

	}

	private void updateContent()
	{
		String orderSerialNum = DNurseOrderPayPage.GetInstance().getOrderSerialNum();
		m_orderSerialNumTV.setText(orderSerialNum);
		int totalPrice = DNurseOrderPayPage.GetInstance().getTotalPrice();
		m_priceTV.setText(String.valueOf(totalPrice));
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
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();
		m_headerCommon.setTitle(R.string.title);

		m_orderSerialNumTV = (TextView)findViewById(R.id.order_serial_num_tv);
		m_priceTV = (TextView)findViewById(R.id.price_tv);
		m_cashRBtn = (RadioButton)findViewById(R.id.cash_rbtn);
		m_alipayRBtn = (RadioButton)findViewById(R.id.alipay_rbtn);
		m_weixinRBtn = (RadioButton)findViewById(R.id.weixin_rbtn);
		m_payBtn = (Button)findViewById(R.id.btn_bottom);
		m_payBtn.setText(R.string.determine_pay_title);

		m_handlerClickEventNursOrderPay = new HandlerClickEventNursOrderPay(this);
		m_eventBus.register(this);
	}

	private void initListener()
	{
		m_payBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
	}

	/**
	 * event bus handle
	 */
	//下订单失败，护工在服务中。
	public void onEventMainThread(FailedNurseOrderCheckEvent event)
	{
		//01. 清除下整个下订单流程中的数据
		DNurseOrderConfirmPage.GetInstance().clearup();
		DNurseOrderPayPage.GetInstance().clearup();

		//02. flush nurse basic list event
		ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
		m_eventBus.post(reqApoitNursingEvent);

		//03. 跳转到选择nurse页面
		startActivity(new Intent(this, SelectNurseActivity.class));

		return;
	}
	//下订单成功，调用支付宝模块
	public void onEventMainThread(FinishedNurseOrderCheckEvent event)
	{
		//01. 获取order info
		String orderID = DNurseOrderPayPage.GetInstance().getOrderID();
		int totalPrice = DNurseOrderPayPage.GetInstance().getTotalPrice();
		//String orderInfo = Util.GetNurseOrder(orderID, String.valueOf(totalPrice));
		//测试
		String orderInfo = Util.GetNurseOrder(orderID, "0.01");
		//02. 对订单做RSA 签名
		String sign = signByRSA(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			RegisterDialog.GetInstance().setMsg(e.toString(), this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//03. 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		//04. 发送pay event
		ReqNurseOrderAlipayEvent reqNurseOrderAlipayEvent = new ReqNurseOrderAlipayEvent();
		reqNurseOrderAlipayEvent.setPayInfo(payInfo);
		reqNurseOrderAlipayEvent.setActivity(this);
		m_eventBus.post(reqNurseOrderAlipayEvent);
		return;
	}

	//支付宝模块返回结果
	public void onEventMainThread(FinishNurseOrderAlipayEvent event)
	{
		//01. 解析支付结果。
		String result = event.getResult();
		JSONObject jsonObj = null;
		String resultStatus = null;
		int successID = Config.PayStatus.PAY_STATUS_SUCCESS.getId();
		int status = successID;
		try
		{
			jsonObj = new JSONObject(result);
			resultStatus = jsonObj.getString(NurseOrderConfig.RESULT_STATUS);
			status = Integer.valueOf(resultStatus);
		}
		catch (JSONException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), this);
			RegisterDialog.GetInstance().show();
			return;
		}
		catch (NumberFormatException e)
		{
			RegisterDialog.GetInstance().setMsg(e.toString(), this);
			RegisterDialog.GetInstance().show();
			return;
		}

		//02. 支付失败
		if (status != successID)
		{
			//TODO:打印log
		}

		//03. 支付成功
		//0301. 清除交易流程中的数据
		//0302. 跳转到主页面。


		return;

	}




	/**
	 * alipay
	 */
	//get the sign type we use. 获取签名方式
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}
	//对订单信息进行签名
	public String signByRSA(String orderInfo)
	{
		return UtilRSA.Sign(orderInfo, Config.RSA_PRIVATE);
	}



}
