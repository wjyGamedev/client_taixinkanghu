package com.taixinkanghu.app.ui.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.taixinkanghu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {


    public MineFragment() {
        // Required empty public constructor
    }

    private View view;
    private TextView tv_nurs_order;
    private TextView tv_product_order;
    private TextView tv_my_score;
    private TextView tv_my_set;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);

        tv_nurs_order = (TextView) view.findViewById(R.id.tv_nurs_order);
        tv_product_order = (TextView) view.findViewById(R.id.tv_product_order);
        tv_my_score = (TextView) view.findViewById(R.id.tv_my_score);
        tv_my_set = (TextView) view.findViewById(R.id.tv_my_set);

        view.setOnClickListener(this);
        tv_nurs_order.setOnClickListener(this);
        tv_product_order.setOnClickListener(this);
        tv_my_score.setOnClickListener(this);
        tv_my_set.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nurs_order:
                Toast.makeText(getActivity(), "你选择了" + tv_nurs_order.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_product_order:
                Toast.makeText(getActivity(), "你选择了" + tv_product_order.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_my_score:
                Toast.makeText(getActivity(), "你选择了" + tv_my_score.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_my_set:
                Toast.makeText(getActivity(), "你选择了" + tv_my_set.getText(), Toast.LENGTH_SHORT).show();
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
