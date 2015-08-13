/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.select_date.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/12		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.select_date;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.widget.wheelview.TosGallery;
import com.taixinkanghu.widget.wheelview.WheelView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class SelectDateFragment extends Fragment
{
	protected class DateEle {

		public String m_content = null;
		public int     m_color      = Color.BLACK;
		public Date m_date = null;

		public DateEle(String text, Date date)
		{
			m_content = text;
			m_date = date;
		}

		public Date getDate()
		{
			return m_date;
		}
	}

	//widget
	private WheelView    m_beginDateWheel   = null;
	private WheelView    m_endDateWheel     = null;
	private Button       m_confirmBtn       = null;
	private Button       m_cancelBtn        = null;
	private LinearLayout m_headerBackground = null;
	private LinearLayout m_bottomBackground = null;

	//constant
	public final static String Tag = "SelectDateFragment";

	//logical
	private ViewGroup m_container = null;

	private EventBus                       m_eventBus                       = EventBus.getDefault();
	private Date                           m_beginDate                      = null;
	private Date                           m_endDate                        = null;
	private int                            m_beginIndexPos                  = 0;
	private int                            m_endIndexPos                    = 0;
	private ArrayList<DateEle>             m_beginDateDisplay               = new ArrayList<>();
	private ArrayList<DateEle>             m_endDateDisplay                 = new ArrayList<>();
	private WheelViewAdapter               m_beginAdapter                   = null;
	private WheelViewAdapter               m_endAdapter                     = null;
	private Calendar                       m_today                          = Calendar.getInstance();
	private SimpleDateFormat               m_simpleDateFormat               = new SimpleDateFormat(DataConfig.PATTERN_DATE_MONTH_DAY_WEEK);
	private HandleEndFlingOnBeginDateWheel m_handleEndFlingOnBeginDateWheel = new HandleEndFlingOnBeginDateWheel();
	private HandleEndFlingOnEndDateWheel   m_handleEndFlingOnEndDateWheel   = new HandleEndFlingOnEndDateWheel();
	private HandleClickEventOnFragment     m_handleClickEventOnFragment     = null;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_select_date_new, container, false);
		m_container = container;
		init(view);
		initContent();


		return view;
	}

	private void init(View view)
	{
		view.setTag(Tag);
		m_beginDateWheel = (WheelView)view.findViewById(R.id.start_date_wheelview);
		m_endDateWheel = (WheelView)view.findViewById(R.id.end_date_wheelview);
		m_confirmBtn = (Button)view.findViewById(R.id.confirm_btn);
		m_cancelBtn = (Button)view.findViewById(R.id.cancel_btn);
		m_headerBackground = (LinearLayout)view.findViewById(R.id.header_background_ll);
		m_bottomBackground = (LinearLayout)view.findViewById(R.id.bottom_background_ll);


		m_handleClickEventOnFragment = new HandleClickEventOnFragment(getActivity());
	}

	private void initContent()
	{
		m_confirmBtn.setOnClickListener(m_handleClickEventOnFragment);
		m_cancelBtn.setOnClickListener(m_handleClickEventOnFragment);
		m_headerBackground.setOnClickListener(m_handleClickEventOnFragment);
		m_bottomBackground.setOnClickListener(m_handleClickEventOnFragment);


		initBeginDate();
		initEndDate();

		m_beginDateWheel.setSoundEffectsEnabled(true);
		m_endDateWheel.setSoundEffectsEnabled(true);

		m_beginAdapter = new WheelViewAdapter(getActivity(), m_beginDateDisplay);
		m_endAdapter = new WheelViewAdapter(getActivity(), m_endDateDisplay);

		m_beginDateWheel.setAdapter(m_beginAdapter);
		m_endDateWheel.setAdapter(m_endAdapter);

		m_beginDateWheel.setSelection(m_beginIndexPos);
		m_endDateWheel.setSelection(m_endIndexPos);

		m_beginDateWheel.setOnEndFlingListener(m_handleEndFlingOnBeginDateWheel);
		m_endDateWheel.setOnEndFlingListener(m_handleEndFlingOnEndDateWheel);

	}

	private void initBeginDate()
	{
		//01. 清空容器
		m_beginDateDisplay.clear();

		//02. 填充容器,从今天开始
		int iCurMonth = 0;
		int iMaxDay = 0;
		int iDay = 0;
		for (int iMonth = 0; iMonth < DataConfig.MAX_SELECT_MONTH; ++iMonth)
		{
			Calendar tmpCalendar = Calendar.getInstance();

			//设置当前月份
			iCurMonth = tmpCalendar.get(Calendar.MONTH) + iMonth;
			tmpCalendar.set(Calendar.MONTH, iCurMonth);

			//获取本月最大天数
			iMaxDay = tmpCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			//设置当前开始日期
			//本月从当日开始
			if(iMonth == 0)
			{
				iDay = tmpCalendar.get(Calendar.DAY_OF_MONTH);
			}
			//之后的月份从1号开始
			else
			{
				iDay = 1;
			}
			tmpCalendar.set(Calendar.DAY_OF_MONTH, iDay);

			for(int index = 0; iDay <= iMaxDay; ++iDay, tmpCalendar.roll(Calendar.DAY_OF_MONTH, true), ++index)
			{
				Date tmpDate = tmpCalendar.getTime();
				String tmpContent = m_simpleDateFormat.format(tmpDate);
				DateEle tmpDateEle = new DateEle(tmpContent, tmpDate);
				m_beginDateDisplay.add(tmpDateEle);
			}
		}

		//03. 重置开始index pos
		m_beginIndexPos = 0;

	}

	private void initEndDate()
	{
		m_endIndexPos = 0;

		m_endDateDisplay.clear();

		for (int index = 1; index < m_beginDateDisplay.size(); ++index)
		{
			m_endDateDisplay.add(m_beginDateDisplay.get(index));
		}
	}

	private void resetEndDate(int startIndex)
	{
		//01. 清空容器
		m_endDateDisplay.clear();

		//02. 填充容器, 从m_beginDateDisplay+1开始
		for (int index = startIndex; index < m_beginDateDisplay.size(); ++index)
		{
			m_endDateDisplay.add(m_beginDateDisplay.get(index));
		}

		WheelViewAdapter adapter = (WheelViewAdapter)m_endDateWheel.getAdapter();
		adapter.setData(m_endDateDisplay);

		m_endDateWheel.setSelection(m_endIndexPos);
	}

	private void setBeginIndexPos(int indexPos)
	{
		if (m_beginIndexPos == indexPos)
			return;

		//01. 改变index pos
		m_beginIndexPos = indexPos;

		//02. 改变 m_endDateDisplay
		resetEndDate(m_beginIndexPos + 1);
	}

	private void setEndIndexPos(int indexPos)
	{
		if (m_endIndexPos == indexPos)
			return;

		m_endIndexPos = indexPos;
	}

	public Date getBeginDate()
	{
		if (m_beginIndexPos >= m_beginDateDisplay.size())
			return null;

		DateEle dateEle = m_beginDateDisplay.get(m_beginIndexPos);
		return dateEle.getDate();
	}

	public Date getEndDate()
	{
		if (m_endIndexPos >= m_endDateDisplay.size())
			return null;

		DateEle dateEle = m_endDateDisplay.get(m_endIndexPos);
		return dateEle.getDate();
	}

	public class HandleEndFlingOnBeginDateWheel implements TosGallery.OnEndFlingListener
	{

		@Override
		public void onEndFling(TosGallery v)
		{
			int pos = v.getSelectedItemPosition();
			setBeginIndexPos(pos);
		}
	}

	public class HandleEndFlingOnEndDateWheel implements TosGallery.OnEndFlingListener
	{

		@Override
		public void onEndFling(TosGallery v)
		{
			int pos = v.getSelectedItemPosition();
			setEndIndexPos(pos);
		}
	}

}
