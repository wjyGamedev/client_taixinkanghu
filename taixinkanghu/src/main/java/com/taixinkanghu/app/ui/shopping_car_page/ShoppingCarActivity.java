package com.taixinkanghu.app.ui.shopping_car_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.shopping_order_confirm_page.ShoppingOrderConfirmActivity;

/**
 * Created by Administrator on 2015/8/13.
 */
public class ShoppingCarActivity extends Activity
{

	private Intent m_toShoppingOrderConfirmIntent;

	private ImageButton  m_backBtn;
	private TextView     m_titleTextView;
	private LinearLayout m_btnSettlement;

	private HandlerClickEventShoppingCar m_handlerClickEventShoppingCar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_car);

		init();
		initListener();
		initModule();
	}

	private void init()
	{
		m_titleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_btnSettlement = (LinearLayout) findViewById(R.id.btn_settlement);

		m_handlerClickEventShoppingCar = new HandlerClickEventShoppingCar(this);

		m_toShoppingOrderConfirmIntent = new Intent(ShoppingCarActivity.this, ShoppingOrderConfirmActivity.class);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventShoppingCar);
		m_btnSettlement.setOnClickListener(m_handlerClickEventShoppingCar);
	}

	private void initModule()
	{
		m_titleTextView.setText(R.string.shopping_car_title);
	}


	public Intent getToShoppingOrderConfirmIntent()
	{
		return m_toShoppingOrderConfirmIntent;
	}
}
