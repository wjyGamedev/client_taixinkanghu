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
 * Created by Administrator on 2015/7/13.
 */
public class ReviewsActivity extends Activity implements View.OnClickListener {

    private TextView tv_title;
    private Button btn_goto_main;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        tv_title = (TextView) findViewById(R.id.page_title);

        tv_title.setText("服务评价");


        btn_goto_main.setOnClickListener(this);
        btn_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_goto_main:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
