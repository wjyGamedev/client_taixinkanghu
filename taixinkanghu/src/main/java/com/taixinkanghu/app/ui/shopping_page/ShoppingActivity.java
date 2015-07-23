package com.taixinkanghu.app.ui.shopping_page;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/22.
 */
public class ShoppingActivity extends Activity
{

	private ImageButton m_backBtn;
	private TextView    m_titleTextView;
	private Button      m_MainBtn;
	private ListView    m_shoppingListView;

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
		m_titleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_MainBtn = (Button)findViewById(R.id.btn_goto_main);
		m_shoppingListView = (ListView)findViewById(R.id.shopping_list_listview);

		m_handlerClickEventShopping = new HandlerClickEventShopping(this);
		m_shoppingAdapter = new ShoppingAdapter(this);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventShopping);
		m_MainBtn.setOnClickListener(m_handlerClickEventShopping);
	}

	private void initModule()
	{
		m_titleTextView.setText(R.string.shopping_title);
		m_shoppingListView.setAdapter(m_shoppingAdapter);
	}

}
