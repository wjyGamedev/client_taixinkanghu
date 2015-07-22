package com.taixinkanghu.app.ui.shopping_page;

import android.util.Log;

import com.taixinkanghu.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/22.
 */
public class DShoppingGoodsImges
{
	private DShoppingGoodsImges()
	{
		initTestData();
	}

	private void initTestData()
	{
		m_ImageArrayList.add(R.drawable.face_1);
		m_ImageArrayList.add(R.drawable.face_2);
		m_ImageArrayList.add(R.drawable.face_3);
		m_ImageArrayList.add(R.drawable.face_4);
		m_ImageArrayList.add(R.drawable.face_5);
		m_ImageArrayList.add(R.drawable.face_6);
		m_ImageArrayList.add(R.drawable.face_7);
		m_ImageArrayList.add(R.drawable.face_8);
		m_ImageArrayList.add(R.drawable.face_9);
		m_ImageArrayList.add(R.drawable.face_10);
		m_ImageArrayList.add(R.drawable.face_11);
		m_ImageArrayList.add(R.drawable.face_12);
		m_ImageArrayList.add(R.drawable.face_13);
		m_ImageArrayList.add(R.drawable.face_14);
		m_ImageArrayList.add(R.drawable.face_15);
		m_ImageArrayList.add(R.drawable.face_16);
		m_ImageArrayList.add(R.drawable.face_17);
		m_ImageArrayList.add(R.drawable.face_18);
		m_ImageArrayList.add(R.drawable.face_19);
		m_ImageArrayList.add(R.drawable.face_20);
		m_ImageArrayList.add(R.drawable.face_21);
		m_ImageArrayList.add(R.drawable.face_22);
		m_ImageArrayList.add(R.drawable.face_23);
		m_ImageArrayList.add(R.drawable.face_24);
		m_ImageArrayList.add(R.drawable.face_25);
		m_ImageArrayList.add(R.drawable.face_26);
		m_ImageArrayList.add(R.drawable.face_27);
		m_ImageArrayList.add(R.drawable.face_28);
		m_ImageArrayList.add(R.drawable.face_29);
		m_ImageArrayList.add(R.drawable.face_30);
	}


	public static DShoppingGoodsImges getInstance()
	{
		return s_dGoodsImages;
	}

	public boolean serialization(JSONObject response)
	{
		return true;
	}

	public ArrayList<Integer> getImageIDList()
	{
		return m_ImageArrayList;
	}

	public Integer getImageIDbyIndex(int index)
	{
		if (index >= m_ImageArrayList.size())
		{
			Log.e("error", "index >= m_ImageArrayList.size()");
			return 0;
		}

		return m_ImageArrayList.get(index);

	}

	private static DShoppingGoodsImges s_dGoodsImages    = new DShoppingGoodsImges();
	private        ArrayList<Integer>  m_ImageArrayList = new ArrayList<Integer>();
}
