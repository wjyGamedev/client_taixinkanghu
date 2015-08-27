package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;

/**
 * Created by Administrator on 2015/7/16.
 */
public class WorkerLevelActivity extends Activity implements GestureDetector.OnGestureListener
{

	private GestureDetector m_GestureDetector;


	private ImageButton btn_back;
	private TextView    page_title;
	private Button      btn_goto_main;
	private TextView    m_workerLevelExplainTv;
	private RadioGroup  m_workerLevelRg;
	private RadioButton m_threeStarBtn;
	private RadioButton m_fourStarBtn;
	private RadioButton m_fiveStarBtn;
	private RadioButton m_sixStarBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_worker_level);

		btn_back = (ImageButton)findViewById(R.id.btn_back);
		btn_goto_main = (Button)findViewById(R.id.btn_goto_main);
		m_workerLevelExplainTv = (TextView)findViewById(R.id.worker_level_explain_tv);
		m_workerLevelRg = (RadioGroup)findViewById(R.id.rg_worker_level);
		m_threeStarBtn = (RadioButton)findViewById(R.id.three_star_btn);
		m_fourStarBtn = (RadioButton)findViewById(R.id.four_star_btn);
		m_fiveStarBtn = (RadioButton)findViewById(R.id.five_star_btn);
		m_sixStarBtn = (RadioButton)findViewById(R.id.six_star_btn);
		page_title = (TextView)findViewById(R.id.page_title);

		m_GestureDetector = new GestureDetector(this, this);

		m_threeStarBtn.setChecked(true);

		page_title.setText(R.string.worker_level_title);

		btn_back.setOnClickListener(new View.OnClickListener()
									{
										@Override
										public void onClick(View v)
										{
											finish();
										}
									}
								   );
		btn_goto_main.setOnClickListener(new View.OnClickListener()
										 {
											 @Override
											 public void onClick(View v)
											 {
												 finish();
											 }
										 }
										);

		m_workerLevelRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
												   {
													   @Override
													   public void onCheckedChanged(RadioGroup group, int checkedId)
													   {
														   if (checkedId == m_threeStarBtn.getId())
														   {
															   m_workerLevelExplainTv.setText(getString(R.string
																												.three_star_worker_explain));
														   }
														   else if (checkedId == m_fourStarBtn.getId())
														   {
															   m_workerLevelExplainTv.setText(getString(R.string
																												.four_star_worker_explain));
														   }
														   else if (checkedId == m_fiveStarBtn.getId())
														   {
															   m_workerLevelExplainTv.setText(getString(R.string
																												.five_star_worker_explain));
														   }
														   else if (checkedId == m_sixStarBtn.getId())
														   {
															   m_workerLevelExplainTv.setText(getString(R.string.six_star_worker_explain));
														   }
													   }
												   }
												  );
	}

	public boolean dispatchTouchEvent(MotionEvent event)
	{
		if (m_GestureDetector.onTouchEvent(event))
		{
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		if (e2.getX() - e1.getX() > MainActivityConfig.DELTA_MOTION_EVENT)
		{             // 从左向右滑动（左进右出）
			if (m_workerLevelRg.getCheckedRadioButtonId() == m_threeStarBtn.getId())
			{
				m_sixStarBtn.setChecked(true);
			}
			else if (m_workerLevelRg.getCheckedRadioButtonId() == m_fourStarBtn.getId())
			{
				m_threeStarBtn.setChecked(true);
			}
			else if (m_workerLevelRg.getCheckedRadioButtonId() == m_fiveStarBtn.getId())
			{
				m_fourStarBtn.setChecked(true);
			}
			else if (m_workerLevelRg.getCheckedRadioButtonId() == m_sixStarBtn.getId())
			{
				m_fiveStarBtn.setChecked(true);
			}
			return true;
		}
		else if (e2.getX() - e1.getX() < -MainActivityConfig.DELTA_MOTION_EVENT)
		{         // 从右向左滑动（右进左出）
			if (m_workerLevelRg.getCheckedRadioButtonId() == m_threeStarBtn.getId())
			{
				m_fourStarBtn.setChecked(true);
			}
			else if (m_workerLevelRg.getCheckedRadioButtonId() == m_fourStarBtn.getId())
			{
				m_fiveStarBtn.setChecked(true);
			}
			else if (m_workerLevelRg.getCheckedRadioButtonId() == m_fiveStarBtn.getId())
			{
				m_sixStarBtn.setChecked(true);
			}
			else if (m_workerLevelRg.getCheckedRadioButtonId() == m_sixStarBtn.getId())
			{
				m_threeStarBtn.setChecked(true);
			}
			return true;
		}

		return true;
	}

}
