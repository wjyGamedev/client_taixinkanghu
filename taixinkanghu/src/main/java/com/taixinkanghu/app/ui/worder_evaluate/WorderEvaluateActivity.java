package com.taixinkanghu.app.ui.worder_evaluate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/8/19.
 */
public class WorderEvaluateActivity extends Activity
{
	private ImageButton m_backBtn;
	private TextView    m_titleTextView;
	private Button      m_comment;
	private Button      m_gotoMainBtn;

	private HandlerClickEventWorkerEvaluate m_handlerClickEventWorkerEvaluate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurs_order_reviews);
		init();
		initListener();
		initModule();
	}

	private void init()
	{
		m_titleTextView = (TextView)findViewById(R.id.page_title);
		m_backBtn = (ImageButton)findViewById(R.id.btn_back);
		m_gotoMainBtn = (Button)findViewById(R.id.btn_goto_main);
		m_comment = (Button)findViewById(R.id.btn_comment);

		m_handlerClickEventWorkerEvaluate = new HandlerClickEventWorkerEvaluate(this);
	}

	private void initListener()
	{
		m_backBtn.setOnClickListener(m_handlerClickEventWorkerEvaluate);
		m_gotoMainBtn.setOnClickListener(m_handlerClickEventWorkerEvaluate);
		m_comment.setOnClickListener(m_handlerClickEventWorkerEvaluate);
	}

	private void initModule()
	{
		m_titleTextView.setText(R.string.worker_evaluate_title);
	}
}
