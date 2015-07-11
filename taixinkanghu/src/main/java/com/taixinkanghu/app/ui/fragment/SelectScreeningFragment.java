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

import com.example.wjy.taixinkanghu.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectScreeningFragment extends Fragment implements View.OnClickListener {


    public SelectScreeningFragment() {

    }

    private ListView mainListView;
    private ListView subListView;

    private ArrayAdapter<String> arrayAdapter_main;
    private ArrayAdapter<String> arrayAdapter_sub_sex;
    private ArrayAdapter<String> arrayAdapter_sub_city;
    private ArrayAdapter<String> arrayAdapter_sub_level;

    private static final int SEX = 0;
    private static final int CITY = 1;
    private static final int LEVEL = 2;
    private ViewGroup Container;

    private int nowClickItemID = SEX;

    private View lastview = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_screening, container, false);
        view.setOnClickListener(this);
        Container = container;
        mainListView = (ListView) view.findViewById(R.id.mainListView);
        subListView = (ListView) view.findViewById(R.id.subListView);

        arrayAdapter_main = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, getMainListData());
        arrayAdapter_sub_sex = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, getSubListData(SEX));
        arrayAdapter_sub_city = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, getSubListData(CITY));
        arrayAdapter_sub_level = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, getSubListData(LEVEL));


        mainListView.setAdapter(arrayAdapter_main);

        subListView.setAdapter(arrayAdapter_sub_sex);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (lastview != null) {
                    lastview.setBackgroundColor(0xffffffff);
                }
                lastview = view;
                switch (position) {
                    case SEX:
                        if (nowClickItemID != SEX) {
                            subListView.setAdapter(arrayAdapter_sub_sex);
                            nowClickItemID = SEX;
                        }
                        break;
                    case CITY:
                        if (nowClickItemID != CITY) {
                            subListView.setAdapter(arrayAdapter_sub_city);
                            nowClickItemID = CITY;
                        }
                        break;
                    case LEVEL:
                        if (nowClickItemID != LEVEL) {
                            subListView.setAdapter(arrayAdapter_sub_level);
                            nowClickItemID = LEVEL;
                        }
                        break;
                }
                subListView.setBackgroundColor(0x10000000);
                view.setBackgroundColor(0x10000000);
            }
        });
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Container.getContext(), "你选择了：" + subListView.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private List<String> getMainListData() {
        List<String> data = new ArrayList<String>();
        data.add("性别");
        data.add("籍贯");
        data.add("护理员级别");
        return data;
    }

    private List<String> getSubListData(int type) {
        List<String> data = new ArrayList<String>();
        switch (type) {
            case SEX:
                data.add("全部");
                data.add("男");
                data.add("女");
                break;
            case CITY:
                data.add("全部");
                data.add("北京");
                data.add("天津");
                data.add("上海");
                data.add("重庆");
                data.add("河北");
                data.add("河南");
                data.add("云南");
                data.add("辽宁");
                data.add("黑龙江");
                data.add("湖南");
                data.add("安徽");
                data.add("山东");
                data.add("新疆");
                data.add("江苏");
                data.add("浙江");
                data.add("江西");
                data.add("湖北");
                data.add("广西");
                data.add("甘肃");
                data.add("山西");
                data.add("内蒙");
                data.add("陕西");
                data.add("吉林");
                data.add("福建");
                data.add("贵州");
                data.add("广东");
                break;
            case LEVEL:
                data.add("全部");
                data.add("高级");
                data.add("中级");
                data.add("低级");
                break;
        }

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
