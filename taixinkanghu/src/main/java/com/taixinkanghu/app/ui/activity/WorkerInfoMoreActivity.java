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
import com.taixinkanghu.app.ui.appointment_nursing.SelectHospitalFragment;
import com.taixinkanghu.app.ui.main_page.MainActivity;
import com.taixinkanghu.widget.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2015/7/11.
 */
public class WorkerInfoMoreActivity extends Activity implements View.OnClickListener {

    private Button btn_selected;
    private Button btn_goto_main;
    private Intent intent;
    private int position;
    private String name;

    public int selected_hospital;

    private TextView tv_name;
    private CircleImageView civ_pic;

    private TextView tv_title;
    public TextView add_info;
    private ImageButton btn_back;
    private LinearLayout btn_reviews;
    private LinearLayout btn_address;

    private LinearLayout worker_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_info);

        intent = this.getIntent();
        name = intent.getStringExtra("name");

        tv_name = (TextView) findViewById(R.id.name);
        add_info = (TextView) findViewById(R.id.add_info);
        civ_pic = (CircleImageView) findViewById(R.id.pic);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_reviews = (LinearLayout) findViewById(R.id.btn_reviews);
        btn_address = (LinearLayout) findViewById(R.id.btn_address);
        worker_date = (LinearLayout) findViewById(R.id.worker_date);
        tv_title = (TextView) findViewById(R.id.page_title);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
        btn_selected = (Button) findViewById(R.id.btn_select);

        btn_address.setVisibility(View.VISIBLE);

        tv_title.setText("护理员详情");

        if (name!=null){
            if (name.equals("谢征")) {
                tv_name.setText(name);
                civ_pic.setImageResource(R.mipmap.face_img2);
            } else if (name.equals("王瑾瑜")) {
                tv_name.setText(name);
                civ_pic.setImageResource(R.mipmap.face_img);
            }
        }

        btn_goto_main.setOnClickListener(this);
        btn_address.setOnClickListener(this);
        btn_selected.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_reviews.setOnClickListener(this);
        worker_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select:
                startActivity(new Intent(this, AppointmentActivity.class));
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_reviews:
                startActivity(new Intent(this, ReviewsActivity.class));
                break;
            case R.id.btn_goto_main:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.worker_date:
                startActivity(new Intent(this, WorkerDateActivity.class));
                break;
            case R.id.btn_address:
                SelectHospitalFragment selectHospitalOldFragment = new SelectHospitalFragment();
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, selectHospitalOldFragment).commit();
                break;
        }
    }
}
