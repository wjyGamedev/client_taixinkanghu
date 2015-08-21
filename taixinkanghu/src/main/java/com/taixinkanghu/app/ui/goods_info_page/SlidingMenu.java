package com.taixinkanghu.app.ui.goods_info_page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.taixinkanghu.app.model.controller.CMainPage;
import com.taixinkanghu.app.model.data.page.DMainPage;

/**
 * Created by ysnow on 2015/3/3.
 */
public class SlidingMenu extends ScrollView
{
	private int mScreenHeight;
	//      private int mOnePage;
	//      private int mMenuPadding=220;


	private GoodsInfoScrollViewPageOne wrapperMenu;
	private GoodsInfoScrollViewPageTwo wrapperContent;
	private boolean isSetted  = false;
	private boolean ispageOne = true;

	public SlidingMenu(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

		//计算显示区域高度(像素)
		float     scale       = context.getResources().getDisplayMetrics().density;
		int       titleHeight = (int)(96 * scale + 0.5f);
		DMainPage dMainPage   = CMainPage.getInstance().getMainPage();
		mScreenHeight = dMainPage.getAppRegionHeight() - titleHeight;

	}

	public void setmScreenHeight(int mScreenHeight)
	{
		this.mScreenHeight = mScreenHeight;
	}

	public SlidingMenu(Context context)
	{
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		if (!isSetted)
		{
			//得到里面的控件
			final LinearLayout wrapper = (LinearLayout)getChildAt(0);
			wrapperMenu = (GoodsInfoScrollViewPageOne)wrapper.getChildAt(0);
			wrapperContent = (GoodsInfoScrollViewPageTwo)wrapper.getChildAt(1);
			//			WebSettings settings = wrapperContent.getSettings();
			//			settings.setJavaScriptEnabled(true);
			//			wrapperContent.setWebViewClient(new WebViewClient()
			//											{
			//												@Override
			//												public boolean shouldOverrideUrlLoading(WebView view, String url)
			//												{
			//													wrapperContent.loadUrl(url);
			//													return true;
			//												}
			//											}
			//										   );
			//设置两个子View的高度为手机的高度
			wrapperMenu.getLayoutParams().height = mScreenHeight;
			wrapperContent.getLayoutParams().height = mScreenHeight;
			isSetted = true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			this.scrollTo(0, 0);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_UP:
				//隐藏在左边的距离
				int scrollY = getScrollY();
				int creteria = mScreenHeight / 5;//滑动多少距离
				if (ispageOne)
				{
					if (scrollY <= creteria)
					{
						//显示菜单
						this.smoothScrollTo(0, 0);
					}
					else
					{
						//隐藏菜单
						this.smoothScrollTo(0, mScreenHeight);
						//						wrapperContent.loadUrl("https://github.com/ysnows");
						this.setFocusable(false);
						ispageOne = false;
					}
				}
				else
				{
					int scrollpadding = mScreenHeight - scrollY;
					if (scrollpadding >= creteria)
					{
						this.smoothScrollTo(0, 0);
						ispageOne = true;
					}
					else
					{
						this.smoothScrollTo(0, mScreenHeight);
						//						wrapperContent.loadUrl("https://github.com/ysnows");
					}
				}

				return true;
		}
		return super.onTouchEvent(ev);
	}


	public void closeMenu()
	{
		if (ispageOne)
			return;
		this.smoothScrollTo(0, 0);
		ispageOne = true;
	}

	public void openMenu()
	{
		if (!ispageOne)
			return;
		this.smoothScrollTo(0, mScreenHeight);
		ispageOne = false;
	}

	/**
	 * 打开和关闭菜单
	 */
	public void toggleMenu()
	{
		if (ispageOne)
		{
			openMenu();
		}
		else
		{
			closeMenu();
		}
	}


}
