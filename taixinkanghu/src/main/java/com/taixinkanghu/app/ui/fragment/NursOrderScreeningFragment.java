package com.taixinkanghu.app.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.taixinkanghu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/18.
 */
public class NursOrderScreeningFragment extends Fragment implements View.OnClickListener {

    private ListView mListView;

    private ArrayAdapter<String> arrayAdapter;

    private static final int ALL = 0;               //全部
    private static final int WAIT_PAY = 1;          //待支付
    private static final int WAIT_EVALUATION = 2;   //待评价
    private static final int COMPLETE = 3;          //已完成
    private static final int CANCEL = 4;            //已取消


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nurs_order_screening, container, false);
        view.setOnClickListener(this);
        mListView = (ListView) view.findViewById(R.id.mListView);
        arrayAdapter = new ArrayAdapter<String>(container.getContext(), R.layout.item_text_select_screening, getListData());
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case ALL:
                        Toast.makeText(getActivity(), "您选择了全部", Toast.LENGTH_SHORT).show();
                        break;
                    case WAIT_PAY:
                        Toast.makeText(getActivity(), "您选择了待支付", Toast.LENGTH_SHORT).show();
                        break;
                    case WAIT_EVALUATION:
                        Toast.makeText(getActivity(), "您选择了待评价", Toast.LENGTH_SHORT).show();
                        break;
                    case COMPLETE:
                        Toast.makeText(getActivity(), "您选择了已完成", Toast.LENGTH_SHORT).show();
                        break;
                    case CANCEL:
                        Toast.makeText(getActivity(), "您选择了已取消", Toast.LENGTH_SHORT).show();
                        break;
                }

                //蒙版点击一下之后消失的处理
                FragmentManager fgManager = getFragmentManager();
                android.app.Fragment fragment = fgManager.findFragmentById(R.id.title);
                FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                String tag = null;
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private List<String> getListData() {
        List<String> data = new ArrayList<String>();
        data.add("全部");
        data.add("待支付");
        data.add("待评价");
        data.add("已完成");
        data.add("已评论");
        return data;
    }

    @Override
    public void onClick(View v) {
        //蒙版点击一下之后消失的处理
        FragmentManager fgManager = getFragmentManager();
        android.app.Fragment fragment = fgManager.findFragmentById(R.id.title);
        FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        String tag = null;
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

}
