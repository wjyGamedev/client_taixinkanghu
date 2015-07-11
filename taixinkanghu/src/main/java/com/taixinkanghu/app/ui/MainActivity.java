package com.taixinkanghu.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wjy.taixinkanghu.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener
{

	private RecyclerView        rv;
	private LinearLayoutManager mLayoutManager;

	private ViewPager        vp;
//	private ViewPagerAdapter vpAdapter;
	private List<View>       views;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initRecyclerView();
		initViewPager();
		initDots();

		initRadioGroup();//初始化底部按钮

		startAutoSliding();//开始自动滑动


	}

	private RadioGroup radiogroup;

	public void initRadioGroup()
	{

		radiogroup = (RadioGroup)findViewById(R.id.rg_main);

		radiogroup.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		System.out.println("checkedId = " + checkedId);
		checkedId = checkedId % 4;
		if (checkedId == 0)
		{
			checkedId = 4;
		}
		switch (checkedId)
		{
		case 1:
			startActivity(new Intent(this, MainActivity.class));
			finish();
			break;
		case 2:
//			startActivity(new Intent(this, dingdan.class));
			finish();
			break;
		case 3:
//			startActivity(new Intent(this, faxian.class));
			finish();
			break;
		case 4:
//			startActivity(new Intent(this, kefu.class));
			finish();
			break;
		}
	}


	private boolean isLoop = true;

	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			if (vp.getCurrentItem() == (views.size() - 1))
			{
				vp.setCurrentItem(0);
			}
			else
			{
				vp.setCurrentItem(vp.getCurrentItem() + 1);
			}
		}
	};

	public void startAutoSliding()
	{

		// 自动切换页面功能
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (isLoop)
				{
					SystemClock.sleep(3000);

					handler.sendEmptyMessage(0);
				}
			}
		}
		).start();
	}


	private void initDots()
	{
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++)
		{
			dots[i] = (ImageView)findViewById(ids[i]);
		}
	}


	private ImageView pagerview_iv1, pagerview_iv2;

	private void initViewPager()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
//		views.add(inflater.inflate(R.layout.one, null));
//		views.add(inflater.inflate(R.layout.two, null));

//		vpAdapter = new ViewPagerAdapter(views, this);
//		vp = (ViewPager)findViewById(R.id.viewpager);
//		vp.setAdapter(vpAdapter);

//		pagerview_iv1 = (ImageView)views.get(0).findViewById(R.id.pagerview_iv1);
//		pagerview_iv2 = (ImageView)views.get(1).findViewById(R.id.pagerview_iv2);

		pagerview_iv1.setOnClickListener(this);
		pagerview_iv2.setOnClickListener(this);

		vp.setOnPageChangeListener(this);

	}

//	private CellData[] data = new CellData[]{new CellData("找护工", 1), new CellData("功能暂未开放", 1),};

	private void initRecyclerView()
	{

		mLayoutManager = new LinearLayoutManager(this);

		rv = (RecyclerView)findViewById(R.id.recyclerview);

		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		rv.setLayoutManager(mLayoutManager);

//		rv.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
//																rv,
//																new RecyclerItemClickListener.OnItemClickListener()
//																{
//
//																	@Override
//																	public void onItemLongClick(View view, int position)
//																	{
//																		//                Toast.makeText(getApplicationContext(), "长按" +
//																		// position, Toast.LENGTH_SHORT).show();
//																	}
//
//																	@Override
//																	public void onItemClick(View view, int position)
//																	{
//																		switch (position)
//																		{
//																		case 0:
//																			//                        startActivity(new Intent
//																			// (MainActivity.this, find_hugong.class));
//																			getFragmentManager().beginTransaction().addToBackStack(null)
//																								.add(R.id.fragment, new select_the_way())
//																								.commit();
//																			break;
//																		//                    case 1:
//																		//                        Toast.makeText(getApplicationContext(), "功能暂未开放", Toast.LENGTH_SHORT).show();
//																		//                        break;
//																		default:
//																			Toast.makeText(getApplicationContext(), "功能暂未开放", Toast.LENGTH_SHORT).show();
//																			break;
//																		}
//																	}
//																}));
//
//		rv.setAdapter(new MyAdapter(data));

		//        rv.setAdapter(new MyAdapter(data, new MyAdapter.OnItemClickListener() {
		//
		//                    @Override
		//                    public void onClick(View v) {
		//
		//                        Toast.makeText(getApplicationContext(), "单击", Toast.LENGTH_SHORT).show();
		//                        startActivity(new Intent(MainActivity.this,find_hugong.class));
		//                    }
		//                },
		//                        new MyAdapter.OnItemLongClickListener() {
		//
		//                            @Override
		//                            public void onLongClick(View v) {
		//                                Button info = (Button) v.findViewById(R.id.ib_text);
		//                                Toast.makeText(getApplicationContext(), "长按", Toast.LENGTH_SHORT).show();
		//                            }
		//                        }
		//                )
		//        );

		rv.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
//		case R.id.pagerview_iv1:
//			startActivity(new Intent(this, GG_01.class));
//			break;
//		case R.id.pagerview_iv2:
//			startActivity(new Intent(this, GG_02.class));
//			break;
//		case 1:
//			startActivity(new Intent(this, find_hugong.class));
//			break;
		}

	}


	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}


	private ImageView[] dots;
	private int[] ids = {R.id.iv1, R.id.iv2};

	@Override
	public void onPageSelected(int arg0) {

		for (int i = 0; i < ids.length; i++) {
			if (arg0 == i) {
				dots[i].setImageResource(R.mipmap.login_point_selected);
			} else {
				dots[i].setImageResource(R.mipmap.login_point);
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}


	//按两次返回退出app
	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//            if(side_drawer.isMenuShowing() ||side_drawer.isSecondaryMenuShowing()){
			//                side_drawer.showContent();
			//            }else {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "在按一次退出",
							   Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			//            }
			return true;
		}
		//拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	//    private WheelView wv;
}
