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
 * Created by Administrator on 2015/7/11.
 */
public class AppointmentActivity extends Activity implements View.OnClickListener {

    private ImageButton btn_back;
    private TextView page_title;
    private Button btn_goto_main;
    private Button btn_submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
        page_title = (TextView) findViewById(R.id.page_title);
        btn_back = (ImageButton) findViewById(R.id.btn_back);

        page_title.setText("预约护理员");

        btn_submit.setOnClickListener(this);
        btn_goto_main.setOnClickListener(this);
        btn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                startActivity(new Intent(this,OrderConfirmActivity.class));
                break;
            case R.id.btn_goto_main:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

}
