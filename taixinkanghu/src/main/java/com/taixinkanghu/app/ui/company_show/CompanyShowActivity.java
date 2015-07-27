/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.company_show.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/27		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.company_show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.taixinkanghu.R;

public class CompanyShowActivity extends Activity implements View.OnClickListener
{
	private ImageView   img_info;
	private TextView    info_title;
	private TextView    info_text_red;
	private TextView    info_content;
	private TextView    page_title;
	private Intent      intent;
	private int         position;
	private ImageButton btn_back;
	private Button      btn_goto_main;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_info);

		intent = this.getIntent();
		position = intent.getIntExtra("position", 0);

		img_info = (ImageView) findViewById(R.id.img_info);
		info_title = (TextView) findViewById(R.id.info_title);
		info_text_red = (TextView) findViewById(R.id.info_text_red);
		info_content = (TextView) findViewById(R.id.info_content);
		page_title = (TextView) findViewById(R.id.page_title);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_goto_main = (Button) findViewById(R.id.btn_goto_main);

		switch (position) {
		case 1:
			img_info.setBackground(getResources().getDrawable(R.mipmap.img_company));
			break;
		case 2:
			img_info.setBackground(getResources().getDrawable(R.mipmap.img_promotions));
			break;
		}


		info_title.setText("优惠信息标题");
		info_text_red.setText("红色标题" + position);
		info_content.setText("优惠信息内容" + position);
		page_title.setText("优惠信息" + position);

		btn_back.setOnClickListener(this);
		btn_goto_main.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_goto_main:
			finish();
			break;
		}
	}
}
