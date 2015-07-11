package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.fragment.SelectDateFragment;
import com.taixinkanghu.app.ui.fragment.SelectScreeningFragment;
import com.taixinkanghu.app.ui.fragment.SelectSortFragment;
import com.taixinkanghu.app.ui.fragment.SelsctCityFragment;


public class ChooseWorkerActivity extends Activity implements View.OnClickListener {

    public TextView tv;
    public TextView tv_city;
    public TextView page_title;
    public ImageButton btn_back;
    private Button btn_goto_main;
    public int selected_city;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_worker);

        linearLayout = (LinearLayout) findViewById(R.id.worker_btn);
        linearLayout.setOnClickListener(this);


        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
        page_title = (TextView) findViewById(R.id.page_title);
        btn_back.setOnClickListener(this);
        btn_goto_main.setOnClickListener(this);
        page_title.setText("我自己选护理员");

        findViewById(R.id.select_city).setOnClickListener(this);
        findViewById(R.id.select_screening).setOnClickListener(this);
        findViewById(R.id.select_sort).setOnClickListener(this);
        findViewById(R.id.select_date).setOnClickListener(this);
        tv = (TextView) findViewById(R.id.showDate);
        tv_city = (TextView) findViewById(R.id.select_city);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_date:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectDateFragment()).commit();
                break;
            case R.id.select_city:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelsctCityFragment()).commit();
                break;
            case R.id.select_sort:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectSortFragment()).commit();
                break;
            case R.id.select_screening:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new SelectScreeningFragment()).commit();
                break;
            case R.id.worker_btn:
                startActivity(new Intent(ChooseWorkerActivity.this, WorkerInfoActivity.class));
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_goto_main:
                finish();
                break;
        }

    }

}
