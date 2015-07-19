/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DCommentList
{
	public boolean serialization(JSONObject response)
	{
		try
		{
			m_iGoodRate = response.getInt(DataConfig.NURSE_GOOD_RATE);
			m_iCommentNum = response.getInt(DataConfig.NURSE_COMMENT_NUM);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return  false;
		}
		return true;
	}

	public void initID(int iID)
	{
		m_iID = iID;
	}
	public int getiGoodRate()
	{
		return m_iGoodRate;
	}

	public int getiCommentNum()
	{
		return m_iCommentNum;
	}

	private int m_iID = 0;                   //ID
	private int m_iGoodRate   = 0;    //好评率
	private int m_iCommentNum = 0;//多少人进行了评论


}
