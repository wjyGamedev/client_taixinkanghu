package com.taixinkanghu.app.ui.goods_info_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.main_page.MainActivity;
import com.taixinkanghu.app.ui.shopping_car_page.ShoppingCarActivity;

/**
 * Created by Administrator on 2015/7/24.
 */
public class GoodsInfoActivity extends Activity
{
	private TextView    m_pageTitleTextView;
	private ImageButton m_backBtn;
	private Button      m_toMainBtn;
	private Button      m_shoppingCartBtn;
	private Button      m_joinShoppingCartBtn;

	private LinearLayout m_receiveVolumeRegion;
	private LinearLayout m_commentRegion;

	private GoodsInfoAdapter           m_goodsInfoAdapter          = null;
	private HandlerClickEventGoodsInfo m_handlerClickEventShopping = null;

	private Intent m_toMainIntent;
	private Intent m_toShoppingCarIntent;

	private SlidingMenu m_goodsInformation;

	private GoodsInfoScrollViewPageOne m_scrollViewPageOne;
	private SlidingMenu                m_goodsInfoInformation;

	public GoodsInfoActivity() {}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_info);

		init();
		initModule();
		initListener();
	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}

	private void init()
	{
		m_pageTitleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_toMainBtn = (Button)findViewById(R.id.btn_goto_main);
		m_shoppingCartBtn = (Button)findViewById(R.id.btn_shopping_cart);
		m_joinShoppingCartBtn = (Button)findViewById(R.id.btn_join_shopping_cart);

		m_receiveVolumeRegion = (LinearLayout)findViewById(R.id.receive_volume_region);
		m_commentRegion = (LinearLayout)findViewById(R.id.comment_region);

		m_toMainIntent = new Intent(GoodsInfoActivity.this, MainActivity.class);
		m_toShoppingCarIntent = new Intent(GoodsInfoActivity.this, ShoppingCarActivity.class);

		m_handlerClickEventShopping = new HandlerClickEventGoodsInfo(this);
		//		m_goodsInfoAdapter = new GoodsInfoAdapter(this);

		m_goodsInformation = (SlidingMenu)findViewById(R.id.goods_info_information);

		m_scrollViewPageOne = (GoodsInfoScrollViewPageOne)findViewById(R.id.scroll_view_page_one);

	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventShopping);
		m_toMainBtn.setOnClickListener(m_handlerClickEventShopping);
		m_shoppingCartBtn.setOnClickListener(m_handlerClickEventShopping);
		m_joinShoppingCartBtn.setOnClickListener(m_handlerClickEventShopping);

		m_receiveVolumeRegion.setOnClickListener(m_handlerClickEventShopping);
		m_commentRegion.setOnClickListener(m_handlerClickEventShopping);

	}

	public void toggleMenu(View v)
	{
		m_goodsInformation.toggleMenu();
	}

	private void initModule()
	{
		m_pageTitleTextView.setText(R.string.goods_info_title);
	}

	public Intent getToMainIntent()
	{
		return m_toMainIntent;
	}

	public Intent getToShoppingCarIntent()
	{
		return m_toShoppingCarIntent;
	}

}
