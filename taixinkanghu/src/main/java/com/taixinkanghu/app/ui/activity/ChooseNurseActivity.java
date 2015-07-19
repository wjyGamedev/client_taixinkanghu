package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import com.taixinkanghu.app.model.listener.HandlerClickEventChooseNurse;
import com.taixinkanghu.app.ui.data.ChooseWorkerData;
import com.taixinkanghu.widget.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChooseNurseActivity extends Activity
{

    public TextView tv;
    public TextView tv_hospital;
    public TextView tv_date;
    public TextView tv_sort;
    public TextView tv_screening;
    public TextView page_title;
    public ImageButton btn_back;
    private Button btn_goto_main;
    public int selected_hospital;
    public int selected_city;
    public ListView lv_worker;
    private ArrayAdapter<ChooseWorkerData> arrayAdapter_worker;
    private List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_worker);

        init();
		initListener();



        DisplayMetrics metrics=new DisplayMetrics();
        int widthPixels=metrics.widthPixels;
        int heightPixels=metrics.heightPixels;
        getWindowManager().getDefaultDisplay().getMetrics(metrics);





        page_title.setText("我自己选护理员");





        lv_worker.setAdapter(arrayAdapter_worker);

        mData = getData();
        ChooseWorkerListViewAdapter adapter = new ChooseWorkerListViewAdapter(this);
        lv_worker.setAdapter(adapter);

        lv_worker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_name = (TextView) lv_worker.getAdapter().getView(position, view, parent).findViewById(R.id.name);
                String name = (String) tv_name.getText();
                System.out.println("name = " + name);

                Intent intent = new Intent(ChooseNurseActivity.this, WorkerInfoActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });
    }

    public final class ViewHolder {
        public CircleImageView civ_pic;
        public TextView tv_name;
        public RatingBar rb_star;
        public TextView tv_price;
        public LinearLayout btn;
    }


    private CircleImageView civ_pic;
    private TextView tv_name;
    private RatingBar rb_star;
    private TextView tv_price;
    private LinearLayout btn;

    public class ChooseWorkerListViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ChooseWorkerListViewAdapter(Context context) {
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

                convertView = mInflater.inflate(R.layout.item_worker_list, null);
                holder.btn = (LinearLayout) convertView.findViewById(R.id.worker_btn);
                holder.civ_pic = (CircleImageView) convertView.findViewById(R.id.pic);
                holder.tv_name = (TextView) convertView.findViewById(R.id.name);
                holder.rb_star = (RatingBar) convertView.findViewById(R.id.star);
                holder.tv_price = (TextView) convertView.findViewById(R.id.price);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_name.setText((String) mData.get(position).get("name"));
            holder.tv_price.setText((String) mData.get(position).get("price"));
            holder.rb_star.setRating((float) mData.get(position).get("star"));
            holder.civ_pic.setImageResource((int) mData.get(position).get("pic"));
            return convertView;
        }

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        ChooseWorkerData worker = new ChooseWorkerData(1);
        map.put("name", worker.name);
        map.put("pic", worker.pic);
        map.put("star", worker.star);
        map.put("price", worker.price + "");
        list.add(map);
        map = new HashMap<String, Object>();
        worker = new ChooseWorkerData(2);
        map.put("name", worker.name);
        map.put("pic", worker.pic);
        map.put("star", worker.star);
        map.put("price", worker.price + "");
        list.add(map);
        return list;
    }

    private void init()
    {
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
		page_title = (TextView) findViewById(R.id.page_title);
		tv = (TextView) findViewById(R.id.showDate);
		tv_hospital = (TextView) findViewById(R.id.select_hospital);
		tv_date = (TextView) findViewById(R.id.select_date);
		tv_sort = (TextView) findViewById(R.id.select_sort);
		tv_screening = (TextView) findViewById(R.id.select_screening);
		lv_worker = (ListView) findViewById(R.id.listView_workerinfo);

		m_handlerClickEventChooseNurse = new HandlerClickEventChooseNurse(this);
    }

	private void initListener()
	{
		btn_back.setOnClickListener(m_handlerClickEventChooseNurse);
		btn_goto_main.setOnClickListener(m_handlerClickEventChooseNurse);
		tv_hospital.setOnClickListener(m_handlerClickEventChooseNurse);
		tv_screening.setOnClickListener(m_handlerClickEventChooseNurse);
		tv_sort.setOnClickListener(m_handlerClickEventChooseNurse);
		tv_date.setOnClickListener(m_handlerClickEventChooseNurse);
	}
    private HandlerClickEventChooseNurse m_handlerClickEventChooseNurse = null;

}
