package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.fragment.MineFragment;
import com.taixinkanghu.app.ui.main_page.MainActivity;

/**
 * Created by Administrator on 2015/7/14.
 */
public class MineOrderActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageButton btn_back;
    private TextView page_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_order);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);

        page_title.setText("陪护订单");

        btn_back.setOnClickListener(this);

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
        radiogroup = (RadioGroup) findViewById(R.id.rg_main);
        radiogroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == main_rg_mine.getId()) {
            getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.title, new MineFragment()).commit();
            main_rg_mine.setChecked(false);
//            main_rg_mine.setChecked(true);
        } else if (checkedId == main_rg_main.getId()) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (checkedId == main_rg_service.getId()) {
            startActivity(new Intent(this, CustomerServiceActivity.class));
        } else if (checkedId == main_rg_about.getId()) {
            startActivity(new Intent(this, AboutUsActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
