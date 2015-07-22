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
	public TextView m_hospitalList = null;
	public TextView m_dateList = null;
	public TextView m_sortList = null;
	public TextView m_screeningList = null;

	public TextView tv;


	public  TextView                  page_title;
	public  ImageButton               btn_back;
	private Button                    btn_goto_main;
	public  int                       selected_hospital;
	public  int                       selected_city;
	public  ListView                  lv_worker;


	private HandlerClickEventChooseNurse m_handlerClickEventChooseNurse = null;
	private ChooseNurseAdapter           m_chooseNurseAdapter           = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_worker);

		init();
		initListener();
		initModule();

		lv_worker.setOnItemClickListener(new AdapterView.OnItemClickListener()
										 {
											 @Override
											 public void onItemClick(AdapterView<?> parent, View view, int position, long id)
											 {
												 TextView tv_name = (TextView)lv_worker.getAdapter().getView(position, view, parent
                                                                                                            ).findViewById(R.id.name);
                                                 String   name    = (String)tv_name.getText();
                Intent intent = new Intent(ChooseNurseActivity.this, WorkerInfoActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });
    }

    private void init()
    {
		m_hospitalList = (TextView) findViewById(R.id.select_hospital);
		m_dateList = (TextView) findViewById(R.id.select_date);
		m_sortList = (TextView) findViewById(R.id.select_sort);
		m_screeningList = (TextView) findViewById(R.id.select_screening);


		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
		page_title = (TextView) findViewById(R.id.page_title);
		tv = (TextView) findViewById(R.id.showDate);


		lv_worker = (ListView) findViewById(R.id.listView_workerinfo);

		m_handlerClickEventChooseNurse = new HandlerClickEventChooseNurse(this);
        m_chooseNurseAdapter = new ChooseNurseAdapter(this);
    }

	private void initListener()
	{
		m_hospitalList.setOnClickListener(m_handlerClickEventChooseNurse);
		btn_back.setOnClickListener(m_handlerClickEventChooseNurse);
		btn_goto_main.setOnClickListener(m_handlerClickEventChooseNurse);

		m_screeningList.setOnClickListener(m_handlerClickEventChooseNurse);
		m_sortList.setOnClickListener(m_handlerClickEventChooseNurse);
		m_dateList.setOnClickListener(m_handlerClickEventChooseNurse);
	}

    private void initModule()
    {
        page_title.setText(R.string.owner_choose_nurse);
        lv_worker.setAdapter(m_chooseNurseAdapter);
    }

}
