package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wjy.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/10.
 */

public class CustomerServiceActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        initRadioGroup();
    }


    private RadioGroup radiogroup;
    private RadioButton main_rg_main;
    private RadioButton main_rg_mine;
    private RadioButton main_rg_service;
    private RadioButton main_rg_about;

    public void initRadioGroup() {

        main_rg_main = (RadioButton) findViewById(R.id.main_rg_main);
        main_rg_mine = (RadioButton) findViewById(R.id.main_rg_mine);
        main_rg_service = (RadioButton) findViewById(R.id.main_rg_service);
        main_rg_about = (RadioButton) findViewById(R.id.main_rg_about);
        main_rg_service.setChecked(true);

        radiogroup = (RadioGroup) findViewById(R.id.rg_main);

        radiogroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == main_rg_main.getId()) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (checkedId == main_rg_mine.getId()) {
//            startActivity(new Intent(this, AboutUsActivity.class));
        } else if (checkedId == main_rg_about.getId()) {
            startActivity(new Intent(this, AboutUsActivity.class));
        }
    }

}

