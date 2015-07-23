package com.taixinkanghu.app.ui.shopping_page;

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/22.
 */
public class DShoppingBasics
{

	public boolean serialization(JSONObject response)
	{
		try
		{
			m_id = response.getInt(DataConfig.GOODS_ID);
			m_name = response.getString(DataConfig.GOODS_NAME);
			m_price = response.getInt(DataConfig.GOODS_UNIT_PRICE);
			m_praiseRate = response.getInt(DataConfig.PRAISE_RATE);
			m_evaluationTimes = response.getInt(DataConfig.EVALUATION_TIMES);

		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return false;
		}
		return true;
	}

	public Integer getId()
	{
		return m_id;
	}

	public String getName()
	{
		return m_name;
	}

	public Integer getPrice()
	{
		return m_price;
	}

	public Integer getPraiseRate()
	{
		return m_praiseRate;
	}

	public Integer getEvaluationTimes()
	{
		return m_evaluationTimes;
	}

	/**
	 * 数据区
	 */

	private Integer m_id              = 0;           //ID
	private String  m_name            = null;        //商品名字
	private Integer m_price           = 0;           //单价
	private Integer m_praiseRate      = 0;           //好评率
	private Integer m_evaluationTimes = 0;           //好评率
}
