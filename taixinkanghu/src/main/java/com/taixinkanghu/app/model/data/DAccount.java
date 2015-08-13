/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.data.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/1		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import android.text.TextUtils;
import android.util.Log;

import com.taixinkanghu.R;
import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.storage.OwnerPreferences;
import com.taixinkanghu.app.model.storage.StorageWrapper;
import com.taixinkanghu.util.android.AppUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAccount
{
	private static DAccount s_dAccount = new DAccount();

	//http into
	private int    m_Status  = DataConfig.S_HTTP_OK;
	private String m_errorMsg = null;

	//data
	private String m_From = null;
	private String m_id = null;
	private String m_code = null;
	private String m_mobile = null;
	private String m_nick = null;

	private DAccount()
	{

	}

	public static DAccount GetInstance()
	{
		return s_dAccount;
	}

	public boolean serialFromHttp(JSONObject response)
	{
		//测试代码
//		String testData = "{\"status\":200,\"user\":{\"id\":\"3\",\"mobile\":\"15010522656\",\"nick\":\"\",\"code\":\"8ea9fd32b4\"}}";
//		JSONObject testJsonObject = null;
//		try
//		{
//			testJsonObject = new JSONObject(testData);
//		}
//		catch (JSONException e)
//		{
//			e.printStackTrace();
//			return false;
//		}


		JSONArray jsonArray = null;
		try
		{
			m_From = DataConfig.REGISTER_FROM_HTTP;

			//http info
			m_Status = response.getInt(DataConfig.STATUS_KEY);
			//http error
			if (m_Status != DataConfig.S_HTTP_OK)
			{
				m_errorMsg = response.getString(DataConfig.ERROR_MSG);
				return false;
			}

			//正常
			m_errorMsg = AppUtil.GetResources().getString(R.string.info_register_success);

			JSONObject jsonObject = response.getJSONObject(DataConfig.USER_KEY);
			if (jsonObject == null)
				return false;

			if (serializationData(jsonObject) == false)
			{
				return false;
			}

			//序列化到本地存储
			OwnerPreferences setting = StorageWrapper.GetInstance().getOwnerPreferences();
			if (setting.serialization(jsonObject) == false)
			{
				return false;
			}


		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return false;
		}

		return true;

	}

	public boolean serialFromStorage(JSONObject response)
	{
		m_From = DataConfig.REGISTER_FROM_STORATE;
		return serializationData(response);
	}

	private boolean serializationData(JSONObject jsonObject)
	{
		try
		{
			m_id = jsonObject.getString(DataConfig.ID_KEY);
			m_code = jsonObject.getString(DataConfig.CODE_KEY);
			m_mobile = jsonObject.getString(DataConfig.MOBILE_KEY);
			m_nick = jsonObject.getString(DataConfig.NICK_KEY);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return false;
		}

		return true;
	}



	public int getStatus()
	{
		return m_Status;
	}

	public boolean isHttpSuccess()
	{
		return (m_Status == DataConfig.S_HTTP_OK);
	}

	public boolean isRegisterSuccess()
	{
		return (TextUtils.isEmpty(m_code) == false);
	}

	public String getErrorMsg()
	{
		return m_errorMsg;
	}

	public String getId()
	{
		return m_id;
	}

	public String getCode()
	{
		return m_code;
	}

	public String getMobile()
	{
		return m_mobile;
	}

	public String getNick()
	{
		return m_nick;
	}
}
