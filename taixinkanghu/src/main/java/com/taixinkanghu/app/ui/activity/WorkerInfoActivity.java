package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.wjy.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/11.
 */
public class WorkerInfoActivity extends Activity implements View.OnClickListener {

    private Button btn_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_info);
        btn_selected = (Button) findViewById(R.id.btn_select);
        btn_selected.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select:
                startActivity(new Intent(this,AppointmentActivity.class));
                break;
        }
    }
}
