package com.taixinkanghu.app.model.net.event;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

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
