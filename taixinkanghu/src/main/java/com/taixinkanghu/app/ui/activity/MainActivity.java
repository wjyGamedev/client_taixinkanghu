package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.adapter.MainRecyclerViewAdapter;
import com.taixinkanghu.app.ui.adapter.MainViewPagerAdapter;
import com.taixinkanghu.app.ui.data.MainButtonData;
import com.taixinkanghu.app.ui.fragment.MineFragment;
import com.taixinkanghu.app.ui.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RecyclerView rv;
    private LinearLayoutManager mLayoutManager;

    private ViewPager vp;
    private MainViewPagerAdapter vpAdapter;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏返回箭头
        findViewById(R.id.btn_back).setVisibility(View.GONE);

        //初始化控件
        initRecyclerView();
        initViewPager();
        initDots();

        initRadioGroup();//初始化底部按钮
        startAutoSliding();//开始自动滑动
    }

    private RadioGroup radiogroup;
    private RadioButton main_rg_main;
    private RadioButton main_rg_mine;
    private RadioButton main_rg_service;
    private RadioButton main_rg_about;

    public void initRadioGroup() {

        main_rg_main = (RadioButton) findViewById(R.id.main_rg_main);
        main_rg_mine = (RadioButton) findViewById(R.id.main_rg_mine);
        main_rg_service = (RadioButton) findViewById(R.id.main_rg_service);
        main_rg_about = (RadioButton) findViewById(R.id.main_rg_about);
        main_rg_main.setChecked(true);
        radiogroup = (RadioGroup) findViewById(R.id.rg_main);
        radiogroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == main_rg_mine.getId()) {
            getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.title, new MineFragment()).commit();
            main_rg_main.setChecked(false);
            main_rg_main.setChecked(true);
        } else if (checkedId == main_rg_service.getId()) {
            startActivity(new Intent(this, CustomerServiceActivity.class));
        } else if (checkedId == main_rg_about.getId()) {
            startActivity(new Intent(this, AboutUsActivity.class));
        }
    }


    private boolean isLoop = true;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (vp.getCurrentItem() == (views.size() - 1)) {
                vp.setCurrentItem(0);
            } else {
                vp.setCurrentItem(vp.getCurrentItem() + 1);
            }
        }
    };

    public void startAutoSliding() {
        // 自动切换页面功能
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (isLoop) {
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();
    }


    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }


    private ImageView pagerview_iv1, pagerview_iv2;

    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));

        vpAdapter = new MainViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);

        pagerview_iv1 = (ImageView) views.get(0).findViewById(R.id.pagerview_iv1);
        pagerview_iv2 = (ImageView) views.get(1).findViewById(R.id.pagerview_iv2);

        pagerview_iv1.setOnClickListener(this);
        pagerview_iv2.setOnClickListener(this);

        vp.setOnPageChangeListener(this);

    }

    private static final int CHOOSE_WORKER = 0;
    private MainButtonData[] data = new MainButtonData[]{
            new MainButtonData("预约陪护", CHOOSE_WORKER),
    };

    private void initRecyclerView() {

        mLayoutManager = new LinearLayoutManager(this);

        rv = (RecyclerView) findViewById(R.id.recyclerview);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        System.out.println(mLayoutManager.getWidth());
        System.out.println(mLayoutManager.getWidth());
        System.out.println(mLayoutManager.getWidth());
        System.out.println(mLayoutManager.getWidth());
        System.out.println(mLayoutManager.getWidth());

        rv.setLayoutManager(mLayoutManager);

        rv.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "长按" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case CHOOSE_WORKER:
                        //弹出fragment，选择自选、或者推荐
//                        getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment, new Select_the_way()).commit();
                        //直接打开自选界面
                        startActivity(new Intent(MainActivity.this, ChooseWorkerActivity.class));
                        break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(), "功能暂未开放", Toast.LENGTH_SHORT).show();
//                        break;
                }
            }
        }));

        rv.setAdapter(new MainRecyclerViewAdapter(data));

//另一种实现方式，但是无法区分点击的是哪个元素
//        rv.setAdapter(new MainRecyclerViewAdapter(data, new MainRecyclerViewAdapter.OnItemClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//                        Toast.makeText(getApplicationContext(), "单击", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this,ChooseWorkerActivity.class));
//                    }
//                },
//                        new MainRecyclerViewAdapter.OnItemLongClickListener() {
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
        Intent intent = new Intent(this, SaleInfoActivity.class);
        switch (v.getId()) {
            //点击广告1
            case R.id.pagerview_iv1:
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            //点击广告2
            case R.id.pagerview_iv2:
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
//            case 1:
//                startActivity(new Intent(this, ChooseWorkerActivity.class));
//                break;
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
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "在按一次退出",
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        //拦截MENU按钮点击事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
