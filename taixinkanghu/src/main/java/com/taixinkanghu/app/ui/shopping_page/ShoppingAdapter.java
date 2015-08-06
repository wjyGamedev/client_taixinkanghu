package com.taixinkanghu.app.ui.shopping_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.data.DFaceImages;
import com.taixinkanghu.app.ui.adapter.IBaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/7/22.
 */
public class ShoppingAdapter extends IBaseAdapter
{
	public ShoppingAdapter(Context context)
	{
		super(context);
		init();
	}


	private DShoppingBasicsList m_dShoppingBasicsList = null;
	private LayoutInflater      m_layoutInflater      = null;

	private void init()
	{
		m_dShoppingBasicsList = DShoppingContainer.GetInstance().GetShoppingBaisicsList();
		m_layoutInflater = LayoutInflater.from(m_context);
	}

	@Override
	public int getCount()
	{
		return m_dShoppingBasicsList.GetNurseBasicsHashMap().size();
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;

		if (convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = m_layoutInflater.inflate(R.layout.item_shopping_list, null);
			convertView.setTag(viewHolder);

			viewHolder.initListViewItem(convertView);
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}

		viewHolder.initContent(m_dShoppingBasicsList, position);
		return convertView;
	}

}

final class ViewHolder
{
	private ImageView m_goodsImage;				//商品图片
	private TextView  m_nameText;				//名字
	private TextView  m_priceText;				//价格
	private TextView  m_praiseRateText;			//好评率
	private TextView  m_evaluationTimesText;	//评论次数

	private HashMap<Integer, DShoppingBasics> m_dShoppingBasicsHashMap   = null;

	private ArrayList<DShoppingBasics> m_dShoppingBasicsArrayList = new ArrayList<DShoppingBasics>();

	public void initListViewItem(View view)
	{
		m_goodsImage = (ImageView)view.findViewById(R.id.goods_img);
		m_nameText = (TextView)view.findViewById(R.id.goods_name_title);
		m_priceText = (TextView)view.findViewById(R.id.goods_price);
		m_praiseRateText = (TextView)view.findViewById(R.id.goods_praise_rate);
		m_evaluationTimesText = (TextView)view.findViewById(R.id.goods_evaluation_times);
	}

	public void initContent(DShoppingBasicsList shoppingBasicsList, int position)
	{

		if (m_dShoppingBasicsHashMap == null || m_dShoppingBasicsHashMap.size() == 0)
		{
			initHashMap(shoppingBasicsList);
		}

		if (m_dShoppingBasicsArrayList == null || m_dShoppingBasicsArrayList.size() == 0)
		{
			initArrayList(shoppingBasicsList);
		}

		if (m_dShoppingBasicsArrayList == null)
		{
			//TODO:error
			return;
		}

		if (position >= m_dShoppingBasicsArrayList.size())
		{
			//TODO:error
			return;
		}

		DShoppingBasics tmpShoppingBasics = m_dShoppingBasicsArrayList.get(position);
		int             iID               = tmpShoppingBasics.getId();
		int             iImageIndex       = (iID - 1);
		int             iImageID          = DFaceImages.getInstance().getImageIDbyIndex(iImageIndex);

		m_goodsImage.setImageResource(iImageID);
		m_nameText.setText(tmpShoppingBasics.getName());
		m_priceText.setText(tmpShoppingBasics.getPrice().toString());
		m_praiseRateText.setText(tmpShoppingBasics.getPraiseRate().toString());
		m_evaluationTimesText.setText(tmpShoppingBasics.getEvaluationTimes().toString());

	}

	private void initHashMap(DShoppingBasicsList shoppingBasicsList)
	{
		m_dShoppingBasicsHashMap = shoppingBasicsList.GetNurseBasicsHashMap();
	}

	private void initArrayList(DShoppingBasicsList shoppingBasicsList)
	{
		if (m_dShoppingBasicsArrayList != null || m_dShoppingBasicsArrayList.size() != 0)
		{
			m_dShoppingBasicsArrayList.clear();
		}

		Iterator<HashMap.Entry<Integer, DShoppingBasics>> iterator = null;
		if (m_dShoppingBasicsHashMap != null)
		{
			iterator = m_dShoppingBasicsHashMap.entrySet().iterator();
		}
		else
		{
			iterator = shoppingBasicsList.GetNurseBasicsHashMap().entrySet().iterator();
		}

		while (iterator.hasNext())
		{
			HashMap.Entry<Integer, DShoppingBasics> entry = iterator.next();
			m_dShoppingBasicsArrayList.add(entry.getValue());
		}

	}

}