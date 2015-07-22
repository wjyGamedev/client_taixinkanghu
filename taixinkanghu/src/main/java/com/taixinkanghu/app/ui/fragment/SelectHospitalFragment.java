package com.taixinkanghu.app.ui.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.ChooseNurseActivity;
import com.taixinkanghu.app.ui.activity.WorkerInfoMoreActivity;

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

    public int way = 0;

    private LinearLayout titleLinearLayout;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_select_hospital, container, false);
        view.setOnClickListener(this);

        btn1 = (Button) view.findViewById(R.id.all_item);
        btn2 = (Button) view.findViewById(R.id.beijingtiantan_item);
        btn3 = (Button) view.findViewById(R.id.beijingchaoyang_item);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        titleLinearLayout = (LinearLayout)view.findViewById(R.id.titleLinearLayout);
        titleLinearLayout.setBackground(getResources().getDrawable(R.color.all_null));


//        Activity activity = getActivity();
//        activity.getComponentName();
//        System.out.println("activity.getComponentName() = "+activity.getComponentName());


        if (way == 1) {
            ChooseNurseActivity activity = (ChooseNurseActivity) getActivity();
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
        }else if (way == 2) {
            WorkerInfoMoreActivity activity = (WorkerInfoMoreActivity) getActivity();
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
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (way == 1) {
            ChooseNurseActivity activity = (ChooseNurseActivity) getActivity();
            switch (v.getId()) {
                case R.id.item1:
                    btn1.setSelected(true);
                    getFragmentManager().popBackStack();
                    activity.m_hospitalListOld.setText(btn1.getText());
                    activity.selected_hospital = CHAOYANGYIYUAN;
                    break;
                case R.id.item2:
                    btn2.setSelected(true);
                    getFragmentManager().popBackStack();
                    activity.m_hospitalListOld.setText(btn2.getText());
                    activity.selected_hospital = TIANTANYIYUAN;
                    break;
                case R.id.item3:
                    btn2.setSelected(true);
                    getFragmentManager().popBackStack();
                    activity.m_hospitalListOld.setText(btn3.getText());
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

        } else if (way == 2) {
            WorkerInfoMoreActivity activity = (WorkerInfoMoreActivity) getActivity();
            switch (v.getId()) {
                case R.id.item1:
                    btn1.setSelected(true);
                    getFragmentManager().popBackStack();
                    activity.add_info.setText(btn1.getText());
                    activity.selected_hospital = CHAOYANGYIYUAN;
                    break;
                case R.id.item2:
                    btn2.setSelected(true);
                    getFragmentManager().popBackStack();
                    activity.add_info.setText(btn2.getText());
                    activity.selected_hospital = TIANTANYIYUAN;
                    break;
                case R.id.item3:
                    btn2.setSelected(true);
                    getFragmentManager().popBackStack();
                    activity.add_info.setText(btn3.getText());
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
}