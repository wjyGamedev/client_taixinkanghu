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

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.ChooseNurseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectSortFragment extends Fragment implements View.OnClickListener {


    public SelectSortFragment() {
        // Required empty public constructor
    }

    private ListView sortListView;

    private ArrayAdapter<String> arrayAdapter_sort;

    private ViewGroup Container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Container = container;
        View view = inflater.inflate(R.layout.fragment_select_sort, container, false);
        view.setOnClickListener(this);
        sortListView = (ListView) view.findViewById(R.id.listView_sort);
        arrayAdapter_sort = new ArrayAdapter<String>(container.getContext(), R.layout.item_text_select_screening, getSortListData());
        sortListView.setAdapter(arrayAdapter_sort);

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFragmentManager().popBackStack();
                ChooseNurseActivity activity = (ChooseNurseActivity) getActivity();
                activity.m_sortList.setText("排序条件:" + sortListView.getAdapter().getItem(position));
            }
        });

        return view;
    }

    private List<String> getSortListData() {
        List<String> data = new ArrayList<String>();
        data.add("好评最多");
        data.add("等级最高");
        data.add("价钱最低");
        data.add("年龄最小");
        return data;
    }

    @Override
    public void onClick(View v) {
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
