package com.taixinkanghu.app.model.event.net.send;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.event.net.BaseNetEvent;

/**
 * Created by Administrator on 2015/7/22.
 */
public class ReqShoppingBasicListEvent extends BaseNetEvent
{
	public ReqShoppingBasicListEvent()
	{
		super(EventID.QUEST_SHOPPING_BASIC_LIST);
	}

}
