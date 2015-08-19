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
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.data;

import com.taixinkanghu.app.model.net.config.ProtocalConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DDepartmentList
{
	private static DDepartmentList s_departmentList = new DDepartmentList();

	private int                  m_Status    = ProtocalConfig.HTTP_OK;
	private ArrayList<DDepartment> m_departments = new ArrayList<>();

	private DDepartmentList()
	{
	}

	public static DDepartmentList GetInstance()
	{
		return s_departmentList;
	}

	public synchronized boolean serialization(JSONObject response) throws JSONException
	{
		//01. 清空原来容器
//		if (m_departments != null && m_departments.size() != 0)
//		{
//			m_departments.clear();
//		}
//
//		//02. http is ok
//		m_Status = response.getInt(ProtocalConfig.HTTP_STATUS);
//
//		if (!LogicalUtil.IsHttpSuccess(m_Status))
//		{
//			String errorMsg = response.getString(ProtocalConfig.HTTP_ERROR_MSG);
//			throw new JsonSerializationException(errorMsg);
//		}
//
//		//03. 序列化json
//		JSONArray jsonArray = response.getJSONArray(DepartmentListConfig.LIST);
//		if (jsonArray == null)
//		{
//			String errMsg = AppUtil.GetResources().getString(R.string.err_info_json_serilization);
//			throw new JsonSerializationException(errMsg + ":" + DepartmentListConfig.LIST);
//		}
//
//		JSONObject jsonObject = null;
//		DDepartment department = null;
//		for (int index = 0; index < jsonArray.length(); index++)
//		{
//			jsonObject=(JSONObject)jsonArray.get(index);
//			department = new DDepartment();
//			department.serialization(jsonObject);
//
//			m_departments.add(department);
//		}

		//test func
		m_departments.add(new DDepartment(1, "心血管内科"));
		m_departments.add(new DDepartment(2, "呼吸内科"));
		return  true;

	}


	public synchronized int getStatus()
	{
		return m_Status;
	}

	public synchronized ArrayList<DDepartment> getDepartments()
	{
		return m_departments;
	}

	public synchronized DDepartment getDepartmentByID(int id)
	{
		for (DDepartment department : m_departments)
		{
			if (department.getID() == id)
				return department;
		}
		return null;
	}

}


