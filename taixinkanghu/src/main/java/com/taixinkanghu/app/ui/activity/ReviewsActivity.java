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
import android.widget.RatingBar;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.data.ReviewsData;
import com.taixinkanghu.app.ui.main_page.MainActivity;
import com.taixinkanghu.widget.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
public class ReviewsActivity extends Activity implements View.OnClickListener {

    private TextView tv_title;
    private Button btn_goto_main;
    private ImageButton btn_back;
    public ListView lv_reviews;
    private ArrayAdapter<ReviewsData> arrayAdapter_reviews;
    private List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        tv_title = (TextView) findViewById(R.id.page_title);

        tv_title.setText("服务评价");


        btn_goto_main.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        lv_reviews = (ListView) findViewById(R.id.listView_reviews);
        lv_reviews.setAdapter(arrayAdapter_reviews);

        mData = getData();
        ReviewsListViewAdapter adapter = new ReviewsListViewAdapter(this);
        lv_reviews.setAdapter(adapter);

    }
    public final class ViewHolder {
        public TextView iphoneNumber;
        public TextView reviewsDate;
        public TextView content;
        public TextView orderDate;
    }

    public class ReviewsListViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ReviewsListViewAdapter(Context context) {
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

                convertView = mInflater.inflate(R.layout.item_reviews_list, null);
                holder.iphoneNumber = (TextView) convertView.findViewById(R.id.iphoneNumber);
                holder.reviewsDate = (TextView) convertView.findViewById(R.id.reviewsDate);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                holder.orderDate = (TextView) convertView.findViewById(R.id.orderDate);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.iphoneNumber.setText((String) mData.get(position).get("iphoneNumber"));
            holder.reviewsDate.setText((String) mData.get(position).get("reviewsDate"));
            holder.content.setText((String) mData.get(position).get("content"));
            holder.orderDate.setText((String) mData.get(position).get("orderDate"));
            return convertView;
        }

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        ReviewsData reviews = new ReviewsData(1);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        map = new HashMap<String, Object>();
        reviews = new ReviewsData(2);
        map.put("iphoneNumber", reviews.iphoneNumber);
        map.put("reviewsDate", reviews.reviewsDate);
        map.put("content", reviews.content);
        map.put("orderDate", reviews.orderDate);
        list.add(map);
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_goto_main:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
