/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.main_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/13		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.main_page;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.QuestionFeedBackActivity;

public class ServiceTabFragment extends Fragment {
    private ImageButton btn_back;
    private TextView page_title;
    private LinearLayout call;
    private LinearLayout question_feedback;

    private static final String PHONE_NUMBER = "4008517517";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.activity_customer_service, container, false);

        btn_back = (ImageButton) view.findViewById(R.id.btn_back);
        page_title = (TextView) view.findViewById(R.id.page_title);
        call = (LinearLayout) view.findViewById(R.id.call);
        question_feedback = (LinearLayout) view.findViewById(R.id.question_feedback);

        page_title.setText(R.string.service_title);

        btn_back.setVisibility(View.GONE);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage(PHONE_NUMBER)
                        .setNegativeButton("拨打", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //拨打电话
                                Toast.makeText(getActivity(), "拨打电话", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:4008517517"));
                                getActivity().startActivity(intent);
                            }
                        })
                        .setPositiveButton("取消", null)
                        .show();
            }
        });

        question_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QuestionFeedBackActivity.class));
            }
        });


        return view;
    }
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onActivityCreated(savedInstanceState);
//		TextView tv=(TextView)getActivity().findViewById(R.id.main_tab_item_text);
//		tv.setText(MainActivityConfig.MAIN_SERVICE_TAB_TEXT);
//	}
}
