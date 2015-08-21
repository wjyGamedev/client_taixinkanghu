/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.event.recv.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/21		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.event.recv;

import com.taixinkanghu.app.model.config.EventID;
import com.taixinkanghu.app.model.net.event.BaseNetEvent;

public class FinishNurseOrderAlipayEvent extends BaseNetEvent
{
	private String m_result = null;

	public FinishNurseOrderAlipayEvent()
	{
		super(EventID.FINISHED_NURSE_ORDER_ALIPAY);
	}

	public String getResult()
	{
		return m_result;
	}

	public void setResult(String result)
	{
		m_result = result;
	}
}
