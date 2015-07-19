package com.taixinkanghu.app.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.AppointmentActivity;

/**
 * Created by Administrator on 2015/7/20.
 */
public class SelectGenderFragment extends Fragment implements View.OnClickListener {

    private TextView btn_Male;
    private TextView btn_Female;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_gender, container, false);

        view.setOnClickListener(this);
        btn_Male = (TextView) view.findViewById(R.id.btn_Male);
        btn_Female = (TextView) view.findViewById(R.id.btn_Female);

        btn_Female.setOnClickListener(this);
        btn_Male.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        AppointmentActivity activity = (AppointmentActivity) getActivity();
        switch (v.getId()) {
            case R.id.btn_Male:
                activity.gender.setText("男");
                activity.dwon_gender.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_Female:
                activity.gender.setText("女");
                activity.dwon_gender.setVisibility(View.INVISIBLE);
                break;
        }
        //蒙版点击一下之后消失的处理
        FragmentManager fgManager = getFragmentManager();
        Fragment fragment = fgManager.findFragmentById(R.id.title);
        FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        String tag = null;
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }
}
