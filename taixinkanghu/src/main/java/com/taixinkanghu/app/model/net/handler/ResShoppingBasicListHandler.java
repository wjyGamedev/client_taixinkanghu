package com.taixinkanghu.app.model.net.handler;

import android.util.Log;

import com.taixinkanghu.app.model.net.IResponseListener;
import com.taixinkanghu.app.ui.shopping_page.DShoppingContainer;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/22.
 */
public class ResShoppingBasicListHandler extends IResponseListener
{
	@Override
	public void onResponse(JSONObject response)
	{
		boolean bReturnFlag = DShoppingContainer.GetInstance().serialBasiclist(response);
		if (bReturnFlag == false)
		{
			Log.w("error", "bReturnFlag == false");
		}
	}

}
