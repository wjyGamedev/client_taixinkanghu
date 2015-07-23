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
import com.taixinkanghu.app.ui.activity.ChooseNurseActivity;

public class SelectCityFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Button btn1, btn2;

    private static final int BEIJING = 1;
    private static final int TIANJIN = 2;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_select_city, container, false);
        view.setOnClickListener(this);

        btn1 = (Button) view.findViewById(R.id.item1);
        btn2 = (Button) view.findViewById(R.id.item2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        ChooseNurseActivity activity = (ChooseNurseActivity) getActivity();

        switch (activity.selected_city) {
            case BEIJING:
                btn1.setSelected(true);
                break;
            case TIANJIN:
                btn2.setSelected(true);
                break;

        }

        return view;
    }

    @Override
    public void onClick(View v) {
        ChooseNurseActivity activity = (ChooseNurseActivity) getActivity();
        switch (v.getId()) {
            case R.id.item1:
                btn1.setSelected(true);
                getFragmentManager().popBackStack();
//                activity.m_hospitalListOld.setText("服务地址              " + btn1.getText() + "  ∨");
//                activity.selected_city = BEIJING;
                break;
            case R.id.item2:
                btn2.setSelected(true);
                getFragmentManager().popBackStack();
//                activity.m_hospitalListOld.setText("服务地址              " + btn2.getText() + "  ∨");
//                activity.selected_city = TIANJIN;
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
