package com.taixinkanghu.app.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.activity.ChooseNurseActivity;
import com.taixinkanghu.util.datewidget.DateWidgetUtils;
import com.taixinkanghu.widget.wheelview.TosGallery;
import com.taixinkanghu.widget.wheelview.WheelView;

import java.util.ArrayList;
import java.util.Calendar;

public class SelectDateFragment extends Fragment implements View.OnClickListener {

    ArrayList<TextInfo> mStartDates = new ArrayList<TextInfo>();
    ArrayList<TextInfo> mEndDates = new ArrayList<TextInfo>();

    WheelView startDateWheel = null;
    WheelView endDateWheel = null;

    int lastStartDate = 0;  //上一次开始日期
    int start_Date = 0;     //本次开始日期

    int end_Date = 1;       //本次结束日期
    int xStartDate = 0;     //本次开始日期与上次的差值

    int xEndPos = 0;        //本次结束日期与上次的差值
    int lastEndPos = 0;     //上一次结束Pos
    int endPosIndex = 0;    //结束位置索引

    private TosGallery.OnEndFlingListener mListener = new TosGallery.OnEndFlingListener() {
        @Override
        public void onEndFling(TosGallery v) {

            int pos = v.getSelectedItemPosition();

            //pos:数值
            if (v == startDateWheel) {
                start_Date = pos;

                if (lastStartDate != start_Date) {

                    xStartDate = start_Date - lastStartDate;
                    lastStartDate = start_Date;

                    end_Date += xStartDate;
                    EndIndex += xStartDate;
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            prepareData2(start_Date);
                        }
                    }, 500);
                }


            } else if (v == endDateWheel) {

                if (lastEndPos != pos) {
                    xEndPos = pos - endPosIndex;

                    end_Date = pos + EndIndex;
                    endPosIndex += xEndPos;
                    lastEndPos = pos;
                    lastEndPos = end_Date;
                }
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private ViewGroup mContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_date, container, false);

        view.setOnClickListener(this);

        mContainer = container;

        startDateWheel = (WheelView) view.findViewById(R.id.startWheelView);
        endDateWheel = (WheelView) view.findViewById(R.id.endWheelView);

        startDateWheel.setOnEndFlingListener(mListener);
        endDateWheel.setOnEndFlingListener(mListener);

        startDateWheel.setSoundEffectsEnabled(true);
        endDateWheel.setSoundEffectsEnabled(true);

        startDateWheel.setAdapter(new WheelTextAdapter(this));
        endDateWheel.setAdapter(new WheelTextAdapter(this));

        prepareData();

        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int days = end_Date - start_Date;
                getFragmentManager().popBackStack();
                ChooseNurseActivity activity = (ChooseNurseActivity) getActivity();
                activity.tv.setText("服务时间：" + dateArray[start_Date] + " - " + dateArray[end_Date] + " 共" + days + "天");
                activity.tv_date.setText("服务时间已选择");
                activity.tv.setTextColor(0xffcc0000);
            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;

    }

    private String[] dateArray = new String[2000];

    private void prepareData() {
        Calendar calendar = Calendar.getInstance();
        int year, month, day, week;

        for (int i = 0; i < 150; i++) {

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DATE);
            week = calendar.get(Calendar.DAY_OF_WEEK);

            String sWeek = null;

            switch (week) {
                case 1:
                    sWeek = "日";
                    break;
                case 2:
                    sWeek = "一";
                    break;
                case 3:
                    sWeek = "二";
                    break;
                case 4:
                    sWeek = "三";
                    break;
                case 5:
                    sWeek = "四";
                    break;
                case 6:
                    sWeek = "五";
                    break;
                case 7:
                    sWeek = "六";
                    break;
            }

            dateArray[i] = month + "月" + day + "日(星期" + sWeek + ")";

            if (i < 30) {
                mStartDates.add(new TextInfo(i, month + "月" + day + "日(星期" + sWeek + ")", false));
            }

            if (i != 0 && i < 101) {
                mEndDates.add(new TextInfo(i, month + "月" + day + "日(星期" + sWeek + ")", false));

            }

        }

        startDateWheel.setSelection(0);
        endDateWheel.setSelection(0);

        ((WheelTextAdapter) startDateWheel.getAdapter()).setData(mStartDates);
        ((WheelTextAdapter) endDateWheel.getAdapter()).setData(mEndDates);
    }

    private int EndIndex = 1;

    private void prepareData2(int startIndex) {
        mEndDates.clear();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, xStartDate);
        int year, month, day, week;

        for (int i = EndIndex; i < EndIndex + 100; i++) {
            mEndDates.add(new TextInfo(i, dateArray[i], (i == 100)));
        }
        endDateWheel.setSelection(endPosIndex);

        ((WheelTextAdapter) endDateWheel.getAdapter()).setData(mEndDates);


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


    protected class TextInfo {

        public int mIndex;
        public String mText;
        public boolean mIsSelected = false;
        public int mColor = Color.BLACK;

        public TextInfo(int index, String text, boolean isSelected) {
            mIndex = index;
            mText = text;
            mIsSelected = isSelected;
        }
    }

    protected class WheelTextAdapter extends BaseAdapter {
        ArrayList<TextInfo> mData = null;
        int mWidth = ViewGroup.LayoutParams.MATCH_PARENT;

        float scale = getActivity().getResources().getDisplayMetrics().density;

        int mHeight = (int) (28 * scale + 0.5f);

        Context mContext = null;

        public WheelTextAdapter(Context context) {
            mContext = context;
            mHeight = (int) DateWidgetUtils.pixelToDp(context, mHeight);
        }

        public WheelTextAdapter(SelectDateFragment select_date) {

        }

        public void setData(ArrayList<TextInfo> data) {
            mData = data;
            this.notifyDataSetChanged();
        }

        public void setItemSize(int width, int height) {
            mWidth = width;
            mHeight = (int) DateWidgetUtils.pixelToDp(mContext, height);
        }

        @Override
        public int getCount() {
            return (null != mData) ? mData.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = null;

            if (null == convertView) {
                convertView = new TextView(mContainer.getContext());
                convertView.setLayoutParams(new TosGallery.LayoutParams(mWidth, mHeight));
                textView = (TextView) convertView;
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                textView.setTextColor(Color.BLACK);
            }

            if (null == textView) {
                textView = (TextView) convertView;
            }

            TextInfo info = mData.get(position);
            textView.setText(info.mText);
            textView.setTextColor(info.mColor);

            return convertView;
        }
    }
}
