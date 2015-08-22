package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.data.ChooseWorkerData;
import com.taixinkanghu.app.ui.data.NursOrderData;
import com.taixinkanghu.app.ui.fragment.NursOrderScreeningFragment;
import com.taixinkanghu.widget.circleimageview.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/14.
 */
public class NursOrderActivity extends Activity implements View.OnClickListener {

    private ImageButton btn_back;
    private Button btn_goto_main;
    private TextView page_title;
    public ListView lv_order;
    private LinearLayout btn_screening;
    private List<Map<String, Object>> mData;
    private ArrayAdapter<ChooseWorkerData> arrayAdapter_worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_order);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);
        btn_screening = (LinearLayout) findViewById(R.id.btn_screening);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);

        lv_order = (ListView) findViewById(R.id.order_info_lv);
        lv_order.setAdapter(arrayAdapter_worker);

        page_title.setText(R.string.nurs_order_title);

        mData = getData();
        NursOrderListViewAdapter adapter = new NursOrderListViewAdapter(this);
        lv_order.setAdapter(adapter);

        btn_back.setOnClickListener(this);
        btn_screening.setOnClickListener(this);
        btn_goto_main.setOnClickListener(this);

        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_name = (TextView) lv_order.getAdapter().getView(position, view, parent).findViewById(R.id.name);
                String name = (String) tv_name.getText();
                Intent intent = new Intent(NursOrderActivity.this, NursOrderInfoActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });

//        initRadioGroup();
    }

//    private RadioGroup radiogroup;
//    private RadioButton main_rg_main;
//    private RadioButton main_rg_mine;
//    private RadioButton main_rg_service;
//    private RadioButton main_rg_about;
//
//    public void initRadioGroup() {
//
//        main_rg_main = (RadioButton) findViewById(R.id.main_rg_main);
//        main_rg_mine = (RadioButton) findViewById(R.id.main_rg_mine);
//        main_rg_service = (RadioButton) findViewById(R.id.main_rg_service);
//        main_rg_about = (RadioButton) findViewById(R.id.main_rg_about);
//        radiogroup = (RadioGroup) findViewById(R.id.rg_main);
//        radiogroup.setOnCheckedChangeListener(this);
//
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        if (checkedId == main_rg_mine.getId()) {
//            getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.title, new MineFragment()).commit();
//            main_rg_mine.setChecked(false);
//        } else if (checkedId == main_rg_main.getId()) {
//            startActivity(new Intent(this, MainActivity.class));
//        } else if (checkedId == main_rg_service.getId()) {
//            startActivity(new Intent(this, CustomerServiceActivity.class));
//        } else if (checkedId == main_rg_about.getId()) {
//            startActivity(new Intent(this, AboutUsActivity.class));
//        }
//    }

//    private CircleImageView civ_pic;
//    private TextView tv_name;
//    private RatingBar rb_star;
//    private TextView tv_price;
//    private LinearLayout btn;

    public final class ViewHolder {
        public TextView name;
        public TextView startdate;
        public TextView enddate;
        public TextView days;
        public CircleImageView pic;
    }

    public class NursOrderListViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public NursOrderListViewAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.item_order_info, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.pic = (CircleImageView) convertView.findViewById(R.id.pic);
                holder.startdate = (TextView) convertView.findViewById(R.id.startdate);
                holder.enddate = (TextView) convertView.findViewById(R.id.enddate);
                holder.days = (TextView) convertView.findViewById(R.id.days);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText((String) mData.get(position).get("name"));
            holder.pic.setImageResource((int) mData.get(position).get("pic"));
            holder.startdate.setText((String) mData.get(position).get("startdate"));
            holder.enddate.setText((String) mData.get(position).get("enddate"));
            holder.days.setText((String) mData.get(position).get("days"));

            return convertView;
        }
    }

    private SimpleDateFormat format;

    private List<Map<String, Object>> getData() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        NursOrderData order = new NursOrderData(1);
        map.put("name", order.worker.name);
        map.put("pic", order.worker.pic);
        map.put("startdate", format.format(order.date[0])+"");
        map.put("enddate", format.format(order.date[1])+"");
        map.put("days", order.days+"");
        list.add(map);
        map = new HashMap<String, Object>();
        order = new NursOrderData(2);
        map.put("name", order.worker.name);
        map.put("pic", order.worker.pic);
        map.put("startdate", format.format(order.date[0])+"");
        map.put("enddate", format.format(order.date[1])+"");
        map.put("days", order.days+"");
        list.add(map);
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_screening:
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.title, new NursOrderScreeningFragment()).commit();
                break;
            case R.id.btn_goto_main:
                finish();
                break;
        }
    }
}
