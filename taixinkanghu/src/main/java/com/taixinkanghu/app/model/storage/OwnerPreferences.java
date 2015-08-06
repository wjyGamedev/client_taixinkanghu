/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.storage.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/5		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.taixinkanghu.app.model.config.DataConfig;
import com.taixinkanghu.app.model.data.DAccount;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class OwnerPreferences extends BaseStorage
{
	private Context m_context = null;
	private SharedPreferences m_setting = null;
	private SharedPreferences.Editor m_editor = null;

	public OwnerPreferences()
	{
	}

	public void init(Context context)
	{
		m_context = context;
		m_setting = m_context.getSharedPreferences(StorageConfig.SETTTING_FILE_NAME, Context.MODE_PRIVATE);
		m_editor = m_setting.edit();

		tryLogin();

	}

	public boolean serialization(JSONObject jsonObject)
	{
		try
		{
			String id = jsonObject.getString(DataConfig.ID_KEY);
			String code = jsonObject.getString(DataConfig.CODE_KEY);
			String mobile = jsonObject.getString(DataConfig.MOBILE_KEY);
			String nick = jsonObject.getString(DataConfig.NICK_KEY);

			m_editor.putString(DataConfig.ID_KEY, id);
			m_editor.putString(DataConfig.CODE_KEY, code);
			m_editor.putString(DataConfig.MOBILE_KEY, mobile);
			m_editor.putString(DataConfig.NICK_KEY, nick);
			m_editor.commit();

		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Log.e("error", e.getMessage().toString());
			return false;
		}

		return true;
	}

	public boolean tryLogin()
	{
		String id = m_setting.getString(DataConfig.ID_KEY, "");
		String code = m_setting.getString(DataConfig.CODE_KEY, "");
		String mobile = m_setting.getString(DataConfig.MOBILE_KEY, "");
		String nick = m_setting.getString(DataConfig.NICK_KEY, "");

		HashMap<String, String> data = new HashMap<String, String>();
		data.put(DataConfig.ID_KEY, id);
		data.put(DataConfig.CODE_KEY, code);
		data.put(DataConfig.MOBILE_KEY, mobile);
		data.put(DataConfig.NICK_KEY, nick);

		JSONObject jsonObject = new JSONObject(data);
		boolean   bReturnFlag = DAccount.GetInstance().serialFromStorage(jsonObject);
		if (!bReturnFlag)
		{
			//TODO:ERROR
			return false;
		}
		return true;

	}
	public boolean logout()
	{
		//01. 有效性检测
		if (m_editor == null)
			return false;

		//02. prefer set null
		m_editor.putString(DataConfig.ID_KEY, "");
		m_editor.putString(DataConfig.CODE_KEY, "");
		m_editor.putString(DataConfig.MOBILE_KEY, "");
		m_editor.putString(DataConfig.NICK_KEY, "");
		m_editor.commit();

		//03. 更新DAccount
		HashMap<String, String> data = new HashMap<String, String>();
		data.put(DataConfig.ID_KEY, "");
		data.put(DataConfig.CODE_KEY, "");
		data.put(DataConfig.MOBILE_KEY, "");
		data.put(DataConfig.NICK_KEY, "");

		JSONObject jsonObject = new JSONObject(data);
		boolean   bReturnFlag = DAccount.GetInstance().serialFromStorage(jsonObject);
		if (!bReturnFlag)
		{
			//TODO:ERROR
		}
		return true;
	}

}
