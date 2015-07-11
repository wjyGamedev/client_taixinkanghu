package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/11.
 */
public class AppointmentActivity extends Activity implements View.OnClickListener {

    private Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select:
                startActivity(new Intent(this,OrderConfirmActivity.class));
                break;
        }
    }


}
