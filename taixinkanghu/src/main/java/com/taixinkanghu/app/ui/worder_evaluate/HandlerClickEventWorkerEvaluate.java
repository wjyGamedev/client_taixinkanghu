package com.taixinkanghu.app.ui.worder_evaluate;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.listener.view.BaseHandleOnClickEvent;

/**
 * Created by Administrator on 2015/8/3.
 */
public class HandlerClickEventWorkerEvaluate extends BaseHandleOnClickEvent
{
	public HandlerClickEventWorkerEvaluate(Activity activity)
	{
		super(activity);
	}

	@Override
	public void onClick(View v)
	{
		Activity activity = (Activity)m_context;
		switch (v.getId())
		{
			case R.id.btn_back:
			{
				activity.finish();
				break;
			}
			case R.id.btn_goto_main:
			{
				activity.finish();
				break;
			}
			case R.id.btn_comment:
			{
				activity.finish();
				Toast.makeText(activity, "评论已发表", Toast.LENGTH_SHORT).show();
				break;
			}
			default:
			{
				System.out.println("1111111111111111111");
				break;
			}
		}
	}
}