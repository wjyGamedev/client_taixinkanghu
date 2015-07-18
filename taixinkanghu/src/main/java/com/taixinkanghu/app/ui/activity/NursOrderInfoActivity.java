package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.main_page.MainActivity;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NursOrderInfoActivity extends Activity{

    private ImageButton btn_back;
    private TextView page_title;
    private Button btn_goto_main;
    private Button btn_goto_reviews;
    private Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurs_order_info);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
        btn_goto_reviews = (Button) findViewById(R.id.btn_goto_reviews);
        btn_continue = (Button) findViewById(R.id.btn_continue);

        page_title.setText(R.string.nurs_order_info_title);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_goto_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NursOrderInfoActivity.this, MainActivity.class));
            }
        });
        btn_goto_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NursOrderInfoActivity.this, NursOrderReviewsActivity.class));
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NursOrderInfoActivity.this, WorkerInfoMoreActivity.class));
            }
        });
    }



}
