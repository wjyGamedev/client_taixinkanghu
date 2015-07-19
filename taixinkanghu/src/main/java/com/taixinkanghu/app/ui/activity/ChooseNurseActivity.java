package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.adapter.list_view.ChooseNurseAdapter;
import com.taixinkanghu.app.ui.listener.view.HandlerClickEventChooseNurse;

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
    private List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_worker);

        init();
		initListener();
        initModule();

        lv_worker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_name = (TextView) lv_worker.getAdapter().getView(position, view, parent).findViewById(R.id.name);
                String name = (String) tv_name.getText();
                Intent intent = new Intent(ChooseNurseActivity.this, WorkerInfoActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });
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
        m_chooseNurseAdapter = new ChooseNurseAdapter(this);
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

    private void initModule()
    {
        page_title.setText(R.string.owner_choose_nurse);
        lv_worker.setAdapter(m_chooseNurseAdapter);
    }

    private HandlerClickEventChooseNurse m_handlerClickEventChooseNurse = null;
    private ChooseNurseAdapter           m_chooseNurseAdapter           = null;
}
