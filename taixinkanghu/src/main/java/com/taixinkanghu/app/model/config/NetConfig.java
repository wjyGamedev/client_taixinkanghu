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

public class NetConfig
{
	private final static String IP_ADDREDD_INNER = "54.223.220.229:8888";		//内部测试服务器
	private final static String IP_ADDREDD_OFFICAL = "54.223.209.101:8888";	//泰心康护正式服务器
	private final static String IP_ADDREDD_TEST = "54.223.217.123:8888";		//协同开发服务器

	public final static String s_hospitalListAddress = "http://"+IP_ADDREDD_INNER+"/downloads/hospital_list/";
	public final static String s_nurseBasicsListAddress = "http://"+IP_ADDREDD_INNER+"/downloads/nurse_basics_list/";
	public final static String s_nurseSeniorListAddress = "http://"+IP_ADDREDD_INNER+"/downloads/nurse_senior_list/";


	public final static String s_ShoppingBasicsListAddress = "http://"+IP_ADDREDD_INNER+"/downloads/goods_basics_list/";
}
