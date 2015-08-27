package com.taixinkanghu.app.ui.nurse_order_pay_page;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.config.EnumConfig;
import com.taixinkanghu.app.model.data.net.DAccount;
import com.taixinkanghu.app.model.data.page.DGlobal;
import com.taixinkanghu.app.model.data.page.DNurseOrderConfirmPage;
import com.taixinkanghu.app.model.data.page.DNurseOrderPayPage;
import com.taixinkanghu.app.model.data.page.DNursingModule;
import com.taixinkanghu.app.model.net.config.NurseOrderConfig;
import com.taixinkanghu.app.model.net.event.recv.FailedNurseOrderCheckEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishNurseOrderAlipayEvent;
import com.taixinkanghu.app.model.net.event.recv.FinishedNurseOrderCheckEvent;
import com.taixinkanghu.app.model.net.event.send.ReqApoitNursingEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderAlipayEvent;
import com.taixinkanghu.app.model.net.event.send.ReqNurseOrderCheckEvent;
import com.taixinkanghu.app.ui.header.HeaderCommon;
import com.taixinkanghu.app.ui.main_page.MainActivity;
import com.taixinkanghu.app.ui.select_nurse.SelectNurseActivity;
import com.taixinkanghu.third.party.alipay.Config;
import com.taixinkanghu.third.party.alipay.PayResult;
import com.taixinkanghu.third.party.alipay.Util;
import com.taixinkanghu.third.party.alipay.UtilRSA;
import com.taixinkanghu.widget.dialog.register_page_dialog.RegisterDialog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/3.
 */
public class NurseOrderPayActivity extends Activity
{
	private HeaderCommon m_headerCommon     = null;
	private TextView     m_orderSerialNumTV = null;
	private TextView     m_priceTV          = null;
	private LinearLayout m_cashRegionLL     = null;
	private RadioButton  m_cashRBtn         = null;
	private LinearLayout m_alipayRegionLL   = null;
	private RadioButton  m_alipayRBtn       = null;
	private LinearLayout m_weixinRegionLL   = null;
	private RadioButton  m_weixinRBtn       = null;
	private Button       m_payBtn           = null;

	//logical
	private ArrayList<RadioButton> m_radioButtons = new ArrayList<>();

	private HandlerClickEventNursOrderPay m_handlerClickEventNursOrderPay = null;
	private HandleClickEventOnDialog      m_handleClickEventOnDialog      = null;
	private EventBus                      m_eventBus                      = EventBus.getDefault();

	private DNurseOrderPayPage m_nurseOrderPayPage = DNursingModule.GetInstance().getNurseOrderPayPage();
	private int                m_selectedID        = DataConfig.DEFAULT_VALUE;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_order_pay);

		init();
		initListener();
		initData();
		initUI();
	}

	private void initRbtn()
	{

	}


	private void initUI()
	{
		m_weixinRegionLL.setVisibility(View.GONE);
		//补差价不允许现金交易
		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.PAY_MORE)
		{
			m_cashRegionLL.setVisibility(View.GONE);
		}
		else
		{
			m_cashRegionLL.setVisibility(View.VISIBLE);
		}
		m_alipayRegionLL.setVisibility(View.VISIBLE);
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			backAction();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	private void init()
	{
		m_headerCommon = new HeaderCommon(this);
		m_headerCommon.init();


		m_orderSerialNumTV = (TextView)findViewById(R.id.order_serial_num_tv);
		m_priceTV = (TextView)findViewById(R.id.price_tv);
		m_cashRegionLL = (LinearLayout)findViewById(R.id.cash_region_ll);
		m_cashRBtn = (RadioButton)findViewById(R.id.cash_rbtn);
		m_alipayRegionLL = (LinearLayout)findViewById(R.id.alipay_region_ll);
		m_alipayRBtn = (RadioButton)findViewById(R.id.alipay_rbtn);
		m_weixinRegionLL = (LinearLayout)findViewById(R.id.weixin_region_ll);
		m_weixinRBtn = (RadioButton)findViewById(R.id.weixin_rbtn);
		m_payBtn = (Button)findViewById(R.id.btn_bottom);
		m_payBtn.setText(R.string.determine_pay_title);

		m_radioButtons.add(m_cashRBtn);
		m_radioButtons.add(m_alipayRBtn);
		m_radioButtons.add(m_weixinRBtn);



		m_handlerClickEventNursOrderPay = new HandlerClickEventNursOrderPay(this);
		m_handleClickEventOnDialog = new HandleClickEventOnDialog();

		m_headerCommon.setTitle(R.string.title, m_handlerClickEventNursOrderPay);


		m_eventBus.register(this);
	}

	private void initListener()
	{
		m_payBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
		m_cashRBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
		m_alipayRBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
		m_weixinRBtn.setOnClickListener(m_handlerClickEventNursOrderPay);
	}

	private void initData()
	{
		Intent intent = getIntent();
		String userID = DAccount.GetInstance().getId();

		if (m_nurseOrderPayPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderPayPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		m_nurseOrderPayPage.setUserID(userID);

		int orderID = intent.getIntExtra(NurseOrderConfig.ORDER_ID, DataConfig.DEFAULT_VALUE);
		if (orderID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("orderID is invalid[orderID:=" + orderID + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderPayPage.setOrderID(orderID);

		String orderSerialNum = intent.getStringExtra(NurseOrderConfig.ORDER_SERIAL_NUM);
		if (orderSerialNum == null)
		{
			RegisterDialog.GetInstance().setMsg("orderSerialNum is invalid[orderSerialNum == null]");
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderPayPage.setOrderSerialNum(orderSerialNum);

		int totalPrice = DataConfig.DEFAULT_VALUE;
		if (DGlobal.GetInstance().getNursingModuleStatus() == EnumConfig.NursingModuleStatus.PAY_MORE)
		{
			//补差价
			totalPrice = intent.getIntExtra(NurseOrderConfig.ORDER_PAY_MORE_PRICE, DataConfig.DEFAULT_VALUE);
		}
		else
		{
			//总价格
			totalPrice = intent.getIntExtra(NurseOrderConfig.ORDER_USER_PAY, DataConfig.DEFAULT_VALUE);
		}

		if (totalPrice == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg("totalPrice is invalid[totalPrice:=" + totalPrice + "]", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		m_nurseOrderPayPage.setTotalPrice(totalPrice);

	}

	private void updateContent()
	{
		if (m_nurseOrderPayPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderPayPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		String orderSerialNum = m_nurseOrderPayPage.getOrderSerialNum();
		m_orderSerialNumTV.setText(orderSerialNum);
		int totalPrice = m_nurseOrderPayPage.getTotalPrice();
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


	/**
	 * action
	 */
	public void selectedAction(int selectedID)
	{
		for (RadioButton radioButton : m_radioButtons)
		{
			if (radioButton == null)
				continue;

			if (radioButton.getId() == selectedID)
			{
				radioButton.setChecked(true);
			}
			else
			{
				radioButton.setChecked(false);
			}

		}
		m_selectedID = selectedID;

	}
	public void backAction()
	{
		RegisterDialog.GetInstance().setMsg(getString(R.string.cancel_title), this, m_handleClickEventOnDialog, m_handleClickEventOnDialog);
		RegisterDialog.GetInstance().show();
		return;

	}

	public boolean IsSelectedValid()
	{
		if (m_selectedID == DataConfig.DEFAULT_VALUE)
		{
			RegisterDialog.GetInstance().setMsg(getString(R.string.error_tips_select_pay_option), this);
			RegisterDialog.GetInstance().show();
			return false;
		}

		if (m_selectedID == R.id.cash_rbtn	|| m_selectedID == R.id.alipay_rbtn)
		{
			return true;
		}


		RegisterDialog.GetInstance().setMsg(getString(R.string.error_tips_pay_aciton_invalid), this);
		RegisterDialog.GetInstance().show();
		return false;
	}

	public void confirmAction()
	{
		//01. 有效性判断
		if (!IsSelectedValid())
			return;

		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();

		//01. 补差价，直接调用支付action
		if (nursingModuleStatus == EnumConfig.NursingModuleStatus.PAY_MORE)
		{
			payAction();
			return;
		}
		//02.其他交易，先走check流程
		else
		{
			//02. 支付event之前的订单check
			DNurseOrderPayPage nurseOrderPayPage = DNursingModule.GetInstance().getNurseOrderPayPage();
			if (nurseOrderPayPage == null)
			{
				RegisterDialog.GetInstance().setMsg("nurseOrderPayPage == null");
				RegisterDialog.GetInstance().show();
				return;
			}

			ReqNurseOrderCheckEvent event = new ReqNurseOrderCheckEvent();

			String nurseID = nurseOrderPayPage.getUserID();
			event.setUserID(nurseID);

			String orderID = nurseOrderPayPage.getOrderID();
			event.setOrderID(orderID);

			String payType = null;
			//支付类型
			if (m_selectedID == R.id.cash_rbtn)
			{
				payType = "cash";
			}
			else if (m_selectedID == R.id.alipay_rbtn)
			{
				payType = "alipay";
			}
			else if (m_selectedID == R.id.weixin_rbtn)
			{
				payType = "weinxin";
			}
			else
			{
				RegisterDialog.GetInstance().setMsg("m_selectedID is invalid!", this);
				RegisterDialog.GetInstance().show();
				return;
			}

			event.setType(payType);


			m_eventBus.post(event);
			return;
		}

	}

	private void payAction()
	{
		if (m_nurseOrderPayPage == null)
		{
			RegisterDialog.GetInstance().setMsg("m_nurseOrderPayPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}

		EnumConfig.NursingModuleStatus nursingModuleStatus = DGlobal.GetInstance().getNursingModuleStatus();

		//01. 现金支付
		if (m_selectedID == R.id.cash_rbtn)
		{
			//现金支付目前不允许补差价
			if (nursingModuleStatus == EnumConfig.NursingModuleStatus.PAY_MORE)
			{
				RegisterDialog.GetInstance().setMsg(getString(R.string.error_tips_pay_more_aciton_no_cash));
				RegisterDialog.GetInstance().show();
			}

			RegisterDialog.GetInstance().setMsg("您的订单已经生成，服务人员会在1小时内和您联系，请您保持手机畅通.",this, new DialogInterface.OnClickListener()
												{
													@Override
													public void onClick(DialogInterface dialog, int which)
													{
														NurseOrderPayActivity.this.paySuccessAction();
													}
												}
											   );
			RegisterDialog.GetInstance().show();

			return;
		}
		//02. 支付宝支付
		else if (m_selectedID == R.id.alipay_rbtn)
		{
			//0201. 获取order info
			String orderID = m_nurseOrderPayPage.getOrderID();
			int totalPrice = m_nurseOrderPayPage.getTotalPrice();
			//测试
			String orderIDInfo = Util.GetNurseOrderInfo(orderID);
			String orderInfo = Util.GetNurseOrder(orderIDInfo, "0.01");
			//0202. 对订单做RSA 签名
			String sign = signByRSA(orderInfo);
			try {
				// 仅需对sign 做URL编码
				sign = URLEncoder.encode(sign, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				RegisterDialog.GetInstance().setMsg(e.toString(), this);
				RegisterDialog.GetInstance().show();
				return;
			}

			//0203. 完整的符合支付宝参数规范的订单信息
			final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

			//0204. 发送pay event
			ReqNurseOrderAlipayEvent reqNurseOrderAlipayEvent = new ReqNurseOrderAlipayEvent();
			reqNurseOrderAlipayEvent.setPayInfo(payInfo);
			reqNurseOrderAlipayEvent.setActivity(this);
			m_eventBus.post(reqNurseOrderAlipayEvent);
		}
		else
		{
			RegisterDialog.GetInstance().setMsg(getString(R.string.error_tips_pay_aciton_invalid), this);
			RegisterDialog.GetInstance().show();
			return;
		}
		return;
	}

	/**
	 * event bus handle
	 */
	//下订单失败，护工在服务中。
	public void onEventMainThread(FailedNurseOrderCheckEvent event)
	{
		//01. 清除下订单流程中，order confirm，order  pay的数据
		DNurseOrderConfirmPage nurseOrderConfirmPage = DNursingModule.GetInstance().getNurseOrderConfirmPage();
		if (nurseOrderConfirmPage == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderConfirmPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		DNurseOrderPayPage nurseOrderPayPage = DNursingModule.GetInstance().getNurseOrderPayPage();
		if (nurseOrderPayPage == null)
		{
			RegisterDialog.GetInstance().setMsg("nurseOrderPayPage == null", this);
			RegisterDialog.GetInstance().show();
			return;
		}
		nurseOrderConfirmPage.clearup();
		nurseOrderPayPage.clearup();

		//02. flush nurse basic list event
		ReqApoitNursingEvent reqApoitNursingEvent = new ReqApoitNursingEvent();
		m_eventBus.post(reqApoitNursingEvent);

		//03. 跳转到选择nurse页面
		finish();
		startActivity(new Intent(this, SelectNurseActivity.class));

		return;
	}
	//下订单成功，调用支付宝模块



	public void onEventMainThread(FinishedNurseOrderCheckEvent event)
	{
		payAction();
		return;
	}

	//支付宝模块返回结果
	public void onEventMainThread(FinishNurseOrderAlipayEvent event)
	{
		//01. 解析支付结果。
		String result = event.getResult();
		PayResult payResult = new PayResult(result);

		// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
		String resultInfo = payResult.getResult();

		String resultStatus = payResult.getResultStatus();

		// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
		if (TextUtils.equals(resultStatus, String.valueOf(Config.PayStatus.PAY_STATUS_SUCCESS.getId())))
		{
//			RegisterDialog.GetInstance().setMsg(Config.PayStatus.PAY_STATUS_SUCCESS.getName(), this);
//			RegisterDialog.GetInstance().show();
			//继续进行，支付成功。
		}
		// 判断resultStatus 为非“9000”则代表可能支付失败
		else
		{
			// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
			if (TextUtils.equals(resultStatus, String.valueOf(Config.PayStatus.PAY_STATUS_PAY_RESULT_WAIT_CONFIRM.getId())))
			{
				RegisterDialog.GetInstance().setMsg(Config.PayStatus.PAY_STATUS_PAY_RESULT_WAIT_CONFIRM.getName(), this);
				RegisterDialog.GetInstance().show();
				//继续进行，等待异步结果。
			}
			// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
			else
			{
				//支付失败，则允许继续进行支付。
				RegisterDialog.GetInstance().setMsg(getResources().getString(R.string.pay_status_failed) , this);
				RegisterDialog.GetInstance().show();
				return;
			}
		}

		//03. 支付成功,
		paySuccessAction();

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




	/**
	 * dialog listener
	 */
	public class HandleClickEventOnDialog implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			//01. 确认，走取消支付流程。
			if (which == DialogInterface.BUTTON_POSITIVE)
			{
				cancelPayAction();
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
	 * action
	 */
	private void cancelPayAction()
	{
		//01. 清除交易流程中的数据
		DNursingModule.GetInstance().clearup();

		//02. 跳转到主页面。
		finish();
		startActivity(new Intent(this, MainActivity.class));
	}
	private void paySuccessAction()
	{
		//01. 清除交易流程中的数据
		DNursingModule.GetInstance().clearup();

		//02. 跳转到主页面。
		startActivity(new Intent(this, MainActivity.class));
		finish();

	}

}
