package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/16.
 */
public class MySetActivity extends Activity {

    private ImageButton btn_back;
    private TextView page_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);

        page_title.setText(R.string.my_set_title);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
