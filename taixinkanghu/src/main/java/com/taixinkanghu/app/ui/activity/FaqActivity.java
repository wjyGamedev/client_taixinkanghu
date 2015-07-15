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
public class FaqActivity extends Activity {
    private ImageButton btn_back;
    private TextView page_title;
    private Button btn_goto_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);

        page_title.setText(R.string.faq_title);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_goto_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
