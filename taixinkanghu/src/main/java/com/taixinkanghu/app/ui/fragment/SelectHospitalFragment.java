package com.taixinkanghu.app.ui.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.ChooseWorkerActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectHospitalFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Button btn1, btn2, btn3;

    private static final int CHAOYANGYIYUAN = 1;
    private static final int TIANTANYIYUAN = 2;
    private static final int ZHONGLIUYIYUAN = 3;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_select_hospital, container, false);
        view.setOnClickListener(this);

        btn1 = (Button) view.findViewById(R.id.item1);
        btn2 = (Button) view.findViewById(R.id.item2);
        btn3 = (Button) view.findViewById(R.id.item3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        ChooseWorkerActivity activity = (ChooseWorkerActivity) getActivity();

        switch (activity.selected_hospital) {
            case CHAOYANGYIYUAN:
                btn1.setSelected(true);
                break;
            case TIANTANYIYUAN:
                btn2.setSelected(true);
                break;
            case ZHONGLIUYIYUAN:
                btn3.setSelected(true);
                break;
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        ChooseWorkerActivity activity = (ChooseWorkerActivity) getActivity();
        switch (v.getId()) {
            case R.id.item1:
                btn1.setSelected(true);
                getFragmentManager().popBackStack();
                activity.tv_hospital.setText("服务地址  " + btn1.getText() + "  ∨");
                activity.selected_hospital = CHAOYANGYIYUAN;
                break;
            case R.id.item2:
                btn2.setSelected(true);
                getFragmentManager().popBackStack();
                activity.tv_hospital.setText("服务地址  " + btn2.getText() + "  ∨");
                activity.selected_hospital = TIANTANYIYUAN;
                break;
            case R.id.item3:
                btn2.setSelected(true);
                getFragmentManager().popBackStack();
                activity.tv_hospital.setText("服务地址  " + btn3.getText() + "  ∨");
                activity.selected_hospital = ZHONGLIUYIYUAN;
                break;
            default:
                //蒙版点击一下之后消失的处理
                FragmentManager fgManager = getFragmentManager();
                Fragment fragment = fgManager.findFragmentById(R.id.title);
                FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                String tag = null;
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.commit();
                break;
        }
    }
}