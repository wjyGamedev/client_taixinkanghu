/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.ui.main_page.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/13		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.ui.main_page;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.MainActivityConfig;
import com.taixinkanghu.app.model.data.DMainPageImages;

import java.util.ArrayList;

public class HomeTabFragment extends Fragment implements GestureDetector.OnGestureListener
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initData();
		initModule();
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		m_viewFlipper.stopFlipping();
		m_viewFlipper.setAutoStart(false);


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
			Animation rInAnim = AnimationUtils.loadAnimation(this.getActivity(),
															 R.anim.push_right_in
															);    // 向右滑动左侧进入的渐变效果（alpha  0.1 -> 1.0）
			Animation rOutAnim = AnimationUtils.loadAnimation(this.getActivity(),
															  R.anim.push_right_out
															 ); // 向右滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）

			m_viewFlipper.setInAnimation(rInAnim);
			m_viewFlipper.setOutAnimation(rOutAnim);
			m_viewFlipper.showPrevious();
			return true;
		}
		else if (e2.getX() - e1.getX() < -MainActivityConfig.DELTA_MOTION_EVENT)
		{         // 从右向左滑动（右进左出）
			Animation lInAnim = AnimationUtils.loadAnimation(this.getActivity(),
															 R.anim.push_left_in
															);        // 向左滑动左侧进入的渐变效果（alpha 0.1  -> 1.0）
			Animation lOutAnim = AnimationUtils.loadAnimation(this.getActivity(),
															  R.anim.push_left_out
															 );    // 向左滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）

			m_viewFlipper.setInAnimation(lInAnim);
			m_viewFlipper.setOutAnimation(lOutAnim);
			m_viewFlipper.showNext();
			return true;
		}
		return true;
	}


	@Override
	public void onStart()
	{
		super.onStart();

		//调节图片展示区的大小
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		int     iWidth  = display.getWidth();

		ArrayList<Integer> imageIDList = DMainPageImages.getInstance().getImageIDList();
		if (imageIDList.isEmpty())
			return;

		Drawable drawable   = getResources().getDrawable(imageIDList.get(0));
		int      iImgWidth  = drawable.getIntrinsicWidth();
		int      iImgHeight = drawable.getIntrinsicHeight();

		final LinearLayout        linearLayout = (LinearLayout)this.getView().findViewById(R.id.display_imgs_linearlayout);
		LinearLayout.LayoutParams params       = (LinearLayout.LayoutParams)linearLayout.getLayoutParams();
		params.width = iWidth;
		params.height = iWidth * iImgHeight / iImgWidth;
		linearLayout.requestLayout();

		//调节功能按钮区的大小
		RelativeLayout          relativeLayout = (RelativeLayout)m_gridLayout.getChildAt(0);
		GridLayout.LayoutParams rlParams       = (GridLayout.LayoutParams)relativeLayout.getLayoutParams();
		rlParams.width = (int)(iWidth * 0.5);
		rlParams.height = (int)(iWidth * 0.5 * MainActivityConfig.COFFE_DELTA);
		relativeLayout.requestLayout();

		relativeLayout = (RelativeLayout)m_gridLayout.getChildAt(1);
		rlParams = (GridLayout.LayoutParams)relativeLayout.getLayoutParams();
		rlParams.width = (int)(iWidth * 0.5);
		rlParams.height = (int)(iWidth * 0.5 * MainActivityConfig.COFFE_DELTA);
		relativeLayout.requestLayout();

	}

	public void initData()
	{
		m_viewFlipper = (ViewFlipper)this.getActivity().findViewById(R.id.view_flipper);
		m_gestureDetectorCompat = new GestureDetectorCompat(this.getActivity(), this);
		m_gridLayout = (GridLayout)this.getActivity().findViewById(R.id.function_gridlayout);
	}


	public void initModule()
	{
		//隐藏返回按钮
		this.getActivity().findViewById(R.id.btn_back).setVisibility(View.GONE);

		//展示图片：公司/优惠信息
		ArrayList<Integer> imageIDList = DMainPageImages.getInstance().getImageIDList();
		ImageView          imageView   = null;
		for (int index = 0; index < imageIDList.size(); index++)
		{
			imageView = new ImageView(this.getActivity());
			imageView.setImageResource(imageIDList.get(index));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			m_viewFlipper.addView(imageView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																		   LinearLayout.LayoutParams.MATCH_PARENT
			)
								 );
		}

		m_viewFlipper.setAutoStart(true);
		m_viewFlipper.setFlipInterval(MainActivityConfig.SWITCH_TIME_MILLISECS);
		if (m_viewFlipper.isAutoStart() && !m_viewFlipper.isFlipping())
		{
			m_viewFlipper.startFlipping();
		}

		//功能区初始化
		m_gridLayout.setColumnCount(MainActivityConfig.MAIN_FUNCTION_NUM);
		View view = null;
		view = setFunctionWidget(this.getActivity(), R.drawable.main_shopping, MainActivityConfig.FUNC_SHOPPING_TEXT);
		m_gridLayout.addView(view, MainActivityConfig.MAIN_FUNCTION_GROUP_FLAG);

		view = setFunctionWidget(this.getActivity(), R.drawable.main_appointment_nursing, MainActivityConfig
										 .FUNC_APPOINTMENT_NURSING_TEXT);
		m_gridLayout.addView(view, MainActivityConfig.MAIN_FUNCTION_GROUP_FLAG);

		//下面文字区
		m_editText = (EditText)getActivity().findViewById(R.id.func_text);
		m_editText.setBackground(null);
		m_editText.setEnabled(false);
		m_editText.setFocusable(false);
		m_editText.setHorizontallyScrolling(false);
		m_editText.setText(R.string.main_func_text);

	}

	private View setFunctionWidget(Context ctx, int iIcon, String inString)
	{
		View      view      = LayoutInflater.from(ctx).inflate(R.layout.main_tab_item, null);
		TextView  textView  = (TextView)view.findViewById(R.id.main_tab_textview);
		ImageView imageView = (ImageView)view.findViewById(R.id.main_tab_imgview);
		textView.setText(inString);
		imageView.setBackgroundResource(iIcon);
		imageView.getLayoutParams().width = (int)getResources().getDimension(R.dimen.func_imageview_width);
		imageView.getLayoutParams().height = (int)getResources().getDimension(R.dimen.func_imageview_height);
		return view;
	}

	/**
	 * 数据区
	 */
	private GestureDetectorCompat m_gestureDetectorCompat = null;
	private ViewFlipper           m_viewFlipper           = null;
	private GridLayout            m_gridLayout            = null;
	private EditText			   m_editText				= null;

}
