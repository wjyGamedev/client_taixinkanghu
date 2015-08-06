package com.taixinkanghu.app.ui.shopping_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.goods_info_page.GoodsInfoActivity;

/**
 * Created by Administrator on 2015/7/22.
 */
public class ShoppingActivity extends Activity
{

	private ImageButton m_backBtn;
	private TextView    m_titleTextView;
	private Button      m_mainBtn;
	private ListView    m_shoppingListView;

	private TextView m_srceeningTextView;

	private ShoppingScreeningList m_shoppingScreeningList;

	private ShoppingAdapter           m_shoppingAdapter           = null;
	private HandlerClickEventShopping m_handlerClickEventShopping = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping);
		init();
		initListener();
		initModule();
	}

	private void init()
	{
		m_shoppingScreeningList = new ShoppingScreeningList(this);
		m_shoppingScreeningList.init(R.id.screening_btn, R.id.screening_condition_text, R.id.drop_down_arrow);

		m_titleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_mainBtn = (Button)findViewById(R.id.btn_goto_main);
		m_shoppingListView = (ListView)findViewById(R.id.shopping_list_listview);

		m_srceeningTextView = (TextView)findViewById(R.id.screening_condition_text);

		m_handlerClickEventShopping = new HandlerClickEventShopping(this);
		m_shoppingAdapter = new ShoppingAdapter(this);

		m_shoppingListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
										 {
											 @Override
											 public void onItemClick(AdapterView<?> parent, View view, int position, long id)
											 {
//												 Integer id   = (Integer)m_dShoppingBasicsArrayList.get(position);
												 Intent intent = new Intent(ShoppingActivity.this, GoodsInfoActivity.class);
//												 intent.putExtra("name", name);
												 startActivity(intent);

											 }
										 }
										);

	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventShopping);
		m_mainBtn.setOnClickListener(m_handlerClickEventShopping);
	}

	private void initModule()
	{
		m_titleTextView.setText(R.string.shopping_title);
		m_shoppingListView.setAdapter(m_shoppingAdapter);
	}

	public TextView getSrceeningTextView()
	{
		return m_srceeningTextView;
	}
}
