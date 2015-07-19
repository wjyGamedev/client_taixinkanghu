/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.handler.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/19		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.handler;

import android.util.Log;

import com.taixinkanghu.app.model.data.DNurseContainer;
import com.taixinkanghu.app.model.net.IResponseListener;

import org.json.JSONObject;

public class ResNurseBasicListHandler extends IResponseListener
{
	@Override
	public void onResponse(JSONObject response)
	{
		boolean bReturnFlag = DNurseContainer.getInstance().serialBasiclist(response);
		if (bReturnFlag == false)
		{
			Log.w("error", "bReturnFlag == false");
		}
	}
}
