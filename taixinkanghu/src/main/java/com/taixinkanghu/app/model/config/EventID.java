/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.config.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/9		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.config;

public class EventID
{
	/**
	 * network 100~999
	 */
	public final static int NETWORK_DEFAULT     = 100;
	public final static int QUEST_HOSPITAL_LIST = 101;
	public final static int QUEST_NURSE_BASIC_LIST = 102;
	public final static int QUEST_NURSE_SENIOR_LIST = 103;

	//康复用品
	public final static int QUEST_SHOPPING_BASIC_LIST = 104;

	/**
	 * UI event
	 */
	public final static int UI_DEFAULT = 1000;
	public final static int UI_SWITCH_IMAGE= 1001;
}
