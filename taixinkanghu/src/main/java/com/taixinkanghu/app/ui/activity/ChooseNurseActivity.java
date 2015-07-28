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
import com.taixinkanghu.app.ui.owner_choose_nurse.DateList;
import com.taixinkanghu.app.ui.owner_choose_nurse.HospitalList;
import com.taixinkanghu.app.ui.owner_choose_nurse.ScreeningList;
import com.taixinkanghu.app.ui.owner_choose_nurse.SortList;


public class ChooseNurseActivity extends Activity
{
	private HospitalList  m_hospitalList  = null;
	private DateList      m_dateList      = null;
	private ScreeningList m_screeningList = null;
	private SortList      m_sortList      = null;

	public TextView m_hospitalListOld  = null;
	public TextView m_dateListOld      = null;
	public TextView m_sortListOld      = null;
	public TextView m_screeningListOld = null;

	public TextView tv;

	private TextView m_hospitalTv;

	public  TextView    page_title;
	public  ImageButton btn_back;
	private Button      btn_goto_main;

	private int      selected_hospital;
	public  int      selected_city;
	public  ListView lv_worker;


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
												 String name   = (String)tv_name.getText();
												 Intent intent = new Intent(ChooseNurseActivity.this, WorkerInfoActivity.class);
												 intent.putExtra("name", name);
												 startActivity(intent);

											 }
										 }
										);
	}

	private void init()
	{
		m_hospitalList = new HospitalList(this);
		m_hospitalList.init(R.id.hospital_region, R.id.select_hospital, R.id.dwon_hospital_img);

		m_dateList = new DateList(this);
		m_dateList.init(R.id.date_region, R.id.select_date, R.id.down_date_img);

		m_screeningList = new ScreeningList(this);
		m_screeningList.init(R.id.screening_region, R.id.select_screening, R.id.down_screening_img);

		m_sortList = new SortList(this);
		m_sortList.init(R.id.sort_region, R.id.select_sort, R.id.down_sort_img);

		m_hospitalTv = (TextView)findViewById(R.id.select_hospital);

		m_chooseNurseAdapter = new ChooseNurseAdapter(this);


		m_hospitalListOld = (TextView) findViewById(R.id.select_hospital);
		m_dateListOld = (TextView) findViewById(R.id.select_date);
		m_sortListOld = (TextView) findViewById(R.id.select_sort);
		m_screeningListOld = (TextView) findViewById(R.id.select_screening);


		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_goto_main = (Button) findViewById(R.id.btn_goto_main);
		page_title = (TextView) findViewById(R.id.page_title);
		tv = (TextView) findViewById(R.id.data_tips);


		lv_worker = (ListView) findViewById(R.id.nurse_display_list);

		m_handlerClickEventChooseNurse = new HandlerClickEventChooseNurse(this);



    }

	private void initListener()
	{

		btn_back.setOnClickListener(m_handlerClickEventChooseNurse);
		btn_goto_main.setOnClickListener(m_handlerClickEventChooseNurse);
	}

    private void initModule()
    {
		m_hospitalList.getTextView().setText(R.string.all_hospital_list);
		m_hospitalList.getImageView().setImageResource(R.mipmap.img_down);

		m_dateList.getTextView().setText(R.string.select_date_default);
		m_dateList.getImageView().setImageResource(R.mipmap.img_down);

		m_screeningList.getTextView().setText(R.string.select_sceening_default);
		m_screeningList.getImageView().setImageResource(R.mipmap.img_down);

		m_sortList.getTextView().setText(R.string.select_sort_default);
		m_sortList.getImageView().setImageResource(R.mipmap.img_down);


        page_title.setText(R.string.owner_choose_nurse);
        lv_worker.setAdapter(m_chooseNurseAdapter);

    }

	public HospitalList getHospitalList()
	{
		return m_hospitalList;
	}

	public DateList getDateList()
	{
		return m_dateList;
	}

	public ScreeningList getScreeningList()
	{
		return m_screeningList;
	}

	public SortList getSortList()
	{
		return m_sortList;
	}

	public void setSelected_hospital(int selected_hospital)
	{
		this.selected_hospital = selected_hospital;
	}

	public int getSelected_hospital()
	{
		return selected_hospital;
	}

	public TextView getHospitalTv()
	{
		return m_hospitalTv;
	}
}
