package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.fragment.ContactInformationFragment;
import com.taixinkanghu.app.ui.fragment.SelectSortFragment;

/**
 * Created by Administrator on 2015/7/16.
 */
public class QuestionFeedBackActivity extends Activity {

    private ImageButton btn_back;
    private TextView page_title;
    private LinearLayout btn_open_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_feedback);

        page_title = (TextView) findViewById(R.id.page_title);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_open_fragment = (LinearLayout) findViewById(R.id.btn_open_fragment);

        page_title.setText(R.string.question_feed_back_title);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_open_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new ContactInformationFragment()).commit();
            }
        });

    }
}
