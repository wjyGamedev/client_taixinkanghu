/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.widget.calendarview.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/7		WangJY		1.0.0		create
 */

package com.taixinkanghu.widget.calendarview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;

import com.taixinkanghu.R;

public class BaseSurface
{
	/**
	 * 1. 布局尺寸 2. 文字颜色，大小 3. 当前日期的颜色，选择的日期颜色
	 */

	private Context m_context = null;

	//01. 布局尺寸
	private int   m_width   = 0;            // 整个控件的宽度
	private int   m_height  = 0;           // 整个控件的高度
	private float m_density = 0.0f;            //密度

	private float m_monthHeight = 0.0f; // 显示月的高度
	private float m_weekHeight  = 0.0f; // 显示星期的高度
	private float m_cellWidth   = 0.0f; // 日期方框宽度
	private float m_cellHeight  = 0.0f; // 日期方框高度
	private float m_borderWidth = 0.0f;

	//02. 文字颜色，大小
	private int m_backGroundColor   = Color.parseColor("#FFFFFF");    //背景色
	private int m_btnColor          = Color.parseColor("#666666");
	private int m_borderColor       = Color.parseColor("#CCCCCC");
	private int m_cellSelectedColor = Color.parseColor("#99CCFF");
	private int m_textColor         = Color.BLACK;
	private int m_todayNumberColor = Color.RED;

	//03. 当前日期的颜色，选择的日期颜色，边框颜色，状态颜色
	public Path m_borderPath = null;            // 边框路径

	public Paint m_borderPaint         = null;
	public Paint m_monthPaint          = null;
	public Paint m_weekPaint           = null;
	public Paint m_datePaint           = null;
	public Paint m_monthChangeBtnPaint = null;
	public Paint m_cellBgPaint         = null;

	//04. 基本数据
	private String[] m_weekContent = null;

	public BaseSurface(Context context)
	{
		m_context = context;
	}

	public void init()
	{
		initLayout();
		initPath();
		initPaint();
		initContent();
	}

	private void initLayout()
	{
		float temp = m_height / 7f;
		m_monthHeight = 0;
		m_weekHeight = (float)((temp + temp * 0.3f) * 0.7);

		m_cellHeight = (m_height - m_monthHeight - m_weekHeight) / 6f;
		m_cellWidth = m_width / 7f;

		m_borderWidth = (float)(0.5 * m_density);
		m_borderWidth = m_borderWidth < 1 ? 1 : m_borderWidth;

	}

	private void initPath()
	{
		m_borderPath = new Path();
		m_borderPath.rLineTo(m_width, 0);
		m_borderPath.moveTo(0, m_monthHeight + m_weekHeight);
		m_borderPath.rLineTo(m_width, 0);
		for (int i = 1; i < 6; i++) {
			m_borderPath.moveTo(0, m_monthHeight + m_weekHeight + i * m_cellHeight);
			m_borderPath.rLineTo(m_width, 0);
			m_borderPath.moveTo(i * m_cellWidth, m_monthHeight);
			m_borderPath.rLineTo(0, m_height - m_monthHeight);
		}
		m_borderPath.moveTo(6 * m_cellWidth, m_monthHeight);
		m_borderPath.rLineTo(0, m_height - m_monthHeight);
	}

	private void initPaint()
	{
		m_borderPaint = new Paint();
		m_borderPaint.setColor(m_borderColor);
		m_borderPaint.setStyle(Paint.Style.STROKE);
		m_borderPaint.setStrokeWidth(m_borderWidth);

		float textSize = m_cellHeight * 0.4f;
		m_monthPaint = new Paint();
		m_monthPaint.setColor(m_textColor);
		m_monthPaint.setAntiAlias(true);
		m_monthPaint.setTextSize(textSize);
		m_monthPaint.setTypeface(Typeface.DEFAULT_BOLD);

		float weekTextSize = m_weekHeight * 0.6f;
		m_weekPaint = new Paint();
		m_weekPaint.setColor(m_textColor);
		m_weekPaint.setAntiAlias(true);
		m_weekPaint.setTextSize(weekTextSize);
		m_weekPaint.setTypeface(Typeface.DEFAULT_BOLD);

		float cellTextSize = m_cellHeight * 0.5f;
		m_datePaint = new Paint();
		m_datePaint.setColor(m_textColor);
		m_datePaint.setAntiAlias(true);
		m_datePaint.setTextSize(cellTextSize);
		m_datePaint.setTypeface(Typeface.DEFAULT_BOLD);

		m_monthChangeBtnPaint = new Paint();
		m_monthChangeBtnPaint.setAntiAlias(true);
		m_monthChangeBtnPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		m_monthChangeBtnPaint.setColor(m_btnColor);

		m_cellBgPaint = new Paint();
		m_cellBgPaint.setAntiAlias(true);
		m_cellBgPaint.setStyle(Paint.Style.FILL);
		m_cellBgPaint.setColor(m_cellSelectedColor);
	}

	private void initContent()
	{
		try
		{
			m_weekContent = m_context.getResources().getStringArray(R.array.week_array);
		}
		catch (Resources.NotFoundException e)
		{

		}
	}

	public int getWidth()
	{
		return m_width;
	}

	public void setWidth(int width)
	{
		m_width = width;
	}

	public int getHeight()
	{
		return m_height;
	}

	public void setHeight(int height)
	{
		m_height = height;
	}

	public float getDensity()
	{
		return m_density;
	}

	public void setDensity(float density)
	{
		m_density = density;
	}

	public float getMonthHeight()
	{
		return m_monthHeight;
	}

	public void setMonthHeight(float monthHeight)
	{
		m_monthHeight = monthHeight;
	}

	public float getWeekHeight()
	{
		return m_weekHeight;
	}

	public void setWeekHeight(float weekHeight)
	{
		m_weekHeight = weekHeight;
	}

	public float getCellWidth()
	{
		return m_cellWidth;
	}

	public void setCellWidth(float cellWidth)
	{
		m_cellWidth = cellWidth;
	}

	public float getCellHeight()
	{
		return m_cellHeight;
	}

	public void setCellHeight(float cellHeight)
	{
		m_cellHeight = cellHeight;
	}

	public float getBorderWidth()
	{
		return m_borderWidth;
	}

	public void setBorderWidth(float borderWidth)
	{
		m_borderWidth = borderWidth;
	}

	public int getBackGroundColor()
	{
		return m_backGroundColor;
	}

	public void setBackGroundColor(int backGroundColor)
	{
		m_backGroundColor = backGroundColor;
	}

	public int getBtnColor()
	{
		return m_btnColor;
	}

	public void setBtnColor(int btnColor)
	{
		m_btnColor = btnColor;
	}

	public int getBorderColor()
	{
		return m_borderColor;
	}

	public void setBorderColor(int borderColor)
	{
		m_borderColor = borderColor;
	}

	public int getCellSelectedColor()
	{
		return m_cellSelectedColor;
	}

	public void setCellSelectedColor(int cellSelectedColor)
	{
		m_cellSelectedColor = cellSelectedColor;
	}

	public int getTextColor()
	{
		return m_textColor;
	}

	public void setTextColor(int textColor)
	{
		m_textColor = textColor;
	}

	public int getTodayNumberColor()
	{
		return m_todayNumberColor;
	}

	public void setTodayNumberColor(int todayNumberColor)
	{
		m_todayNumberColor = todayNumberColor;
	}

	public Path getBorderPath()
	{
		return m_borderPath;
	}

	public void setBorderPath(Path borderPath)
	{
		m_borderPath = borderPath;
	}

	public Paint getBorderPaint()
	{
		return m_borderPaint;
	}

	public void setBorderPaint(Paint borderPaint)
	{
		m_borderPaint = borderPaint;
	}

	public Paint getMonthPaint()
	{
		return m_monthPaint;
	}

	public void setMonthPaint(Paint monthPaint)
	{
		m_monthPaint = monthPaint;
	}

	public Paint getWeekPaint()
	{
		return m_weekPaint;
	}

	public void setWeekPaint(Paint weekPaint)
	{
		m_weekPaint = weekPaint;
	}

	public Paint getDatePaint()
	{
		return m_datePaint;
	}

	public void setDatePaint(Paint datePaint)
	{
		m_datePaint = datePaint;
	}

	public Paint getMonthChangeBtnPaint()
	{
		return m_monthChangeBtnPaint;
	}

	public void setMonthChangeBtnPaint(Paint monthChangeBtnPaint)
	{
		m_monthChangeBtnPaint = monthChangeBtnPaint;
	}

	public Paint getCellBgPaint()
	{
		return m_cellBgPaint;
	}

	public void setCellBgPaint(Paint cellBgPaint)
	{
		m_cellBgPaint = cellBgPaint;
	}

	public int getWeekLength()
	{
		return m_weekContent.length;
	}

	public String getWeekContent(int index)
	{
		if (index >= m_weekContent.length)
			return null;

		return m_weekContent[index];
	}


}
