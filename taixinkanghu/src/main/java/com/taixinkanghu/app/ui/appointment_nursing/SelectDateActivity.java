package com.taixinkanghu.app.ui.appointment_nursing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.widget.calendarview.CalendarViewXz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectDateActivity extends Activity
{

	private CalendarViewXz   calendar;
	private ImageButton      calendarLeft;
	//    private Button buttonDate;
	private TextView         calendarCenter;
	private ImageButton      calendarRight;
	private SimpleDateFormat format;

	private ImageButton btn_back;
	private TextView    page_title;

	private Integer m_nowShowMonth;
	private Integer m_nowShowYour;

	private Date[] datedata;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_date);

		getNowMonth();
		getNowYear();

		format = new SimpleDateFormat("yyyy-MM-dd");
		//获取日历控件对象
		calendar = (CalendarViewXz)findViewById(R.id.calendar);
		calendar.setSelectMore(true); //false单选、true选择日期区间

		calendarLeft = (ImageButton)findViewById(R.id.calendarLeft);
		calendarCenter = (TextView)findViewById(R.id.calendarCenter);
		calendarRight = (ImageButton)findViewById(R.id.calendarRight);
		//        buttonDate = (Button) findViewById(R.id.buttonDate);

		btn_back = (ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener()
									{
										@Override
										public void onClick(View v)
										{
											finish();
										}
									}
								   );
		page_title = (TextView)findViewById(R.id.page_title);
		page_title.setText("陪护时间");

		try
		{
			//设置日历日期
			Date date = format.parse("2015-01-01");
			calendar.setCalendarData(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		//获取日历中年月 ya[0]为年，ya[1]为月（格式大家可以自行在日历控件中改）
		String[] ya = calendar.getYearAndmonth().split("-");
		m_nowShowMonth = Integer.valueOf(ya[1]);
		m_nowShowYour = Integer.valueOf(ya[0]);
		calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
		calendarLeft.setOnClickListener(new OnClickListener()
										{

											@Override
											public void onClick(View v)
											{
												//判断是否能够点击
												if (isClickLeftMonth())
												{
													//点击上一月 同样返回年月
													String leftYearAndmonth = calendar.clickLeftMonth();
													String[] ya = leftYearAndmonth.split("-");
													calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
													m_nowShowMonth = Integer.valueOf(ya[1]);
													m_nowShowYour = Integer.valueOf(ya[0]);
												}
												else
												{
													Toast.makeText(getApplicationContext(), "不能选择更早的日期", Toast.LENGTH_SHORT
																  ).show();
												}
											}
										}
									   );

		calendarRight.setOnClickListener(new OnClickListener()
										 {

											 @Override
											 public void onClick(View v)
											 {
												 //判断是否能够点击
												 if (isClickRightMonth())
												 {
													 //点击下一月
													 String rightYearAndmonth = calendar.clickRightMonth();
													 String[] ya = rightYearAndmonth.split("-");
													 calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
													 m_nowShowMonth = Integer.valueOf(ya[1]);
													 m_nowShowYour = Integer.valueOf(ya[0]);
												 }
												 else
												 {
													 Toast.makeText(getApplicationContext(), "不能选择更晚的日期", Toast.LENGTH_SHORT
																   ).show();
												 }
											 }
										 }
										);


		//设置控件监听，可以监听到点击的每一天（大家也可以在控件中根据需求设定）
		calendar.setOnItemClickListener(new CalendarViewXz.OnItemClickListener()
										{

											@Override
											public void OnItemClick(Date selectedStartDate, Date selectedEndDate, Date downDate)
											{
												if (calendar.isSelectMore())
												{
													Toast.makeText(getApplicationContext(),
																   format.format(selectedStartDate) + "到" + format.format(selectedEndDate),
																   Toast.LENGTH_SHORT
																  ).show();
												}
												else
												{
													Toast.makeText(getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT
																  ).show();
												}
											}
										}
									   );
	}

	public void setNowShowMonth(Integer nowShowMonth)
	{
		m_nowShowMonth = nowShowMonth;
	}

	public void setNowShowYour(Integer nowShowYour)
	{
		m_nowShowYour = nowShowYour;
	}

	private Integer getNowMonth()
	{
		Integer  month;
		Calendar calendar = Calendar.getInstance();
		month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	private Integer getNowYear()
	{
		Integer  year;
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		return year;
	}

	private boolean isClickLeftMonth()
	{
		if (m_nowShowYour > getNowYear())
		{
			return true;
		}
		else if (m_nowShowMonth <= getNowMonth())
		{
			return false;
		}
		return true;
	}

	private boolean isClickRightMonth()
	{
		if (m_nowShowYour == getNowYear() || m_nowShowMonth < (getNowMonth() + 2))
		{
			return true;
		}
		else if (m_nowShowYour > getNowYear() || m_nowShowMonth + 12 < (getNowMonth() + 2))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
}