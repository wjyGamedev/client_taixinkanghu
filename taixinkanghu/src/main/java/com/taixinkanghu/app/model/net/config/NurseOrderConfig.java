/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.net.config.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/18		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.net.config;

public class NurseOrderConfig
{
	//01. common
	public final static String HOSPITAL_ID = "hospital_id";
	public final static String USER_ID            = "uid";                        //用户id
	public final static String NURSE_ID         = "cid";                        //护理员ID
	public final static String ORDER_ID         = "oid";                        //订单ID
	public final static String USER_PHONE_NUM     = "order_mobile";            //用户输入的手机号
	public final static String PATIENT_NAME       = "patient_name";            //患者名称
	public final static String PATIENT_GENDER     = "patient_gender";            //患者性别
	public final static String PATIENT_AGE        = "patient_age";                //患者年龄
	public final static String PATIENT_WEIGHT     = "patient_weight";            //患者体重
	public final static String PATIENT_STATUS     = "patient_status";            //患者自理状态
	public final static String PATIENT_REMARK     = "patient_remark";            //患者备注
	public final static String ORDER_TOTAL_CHARGE = "order_total_charge";        //订单的总价格。
	public final static String SCHEDULE_ALL   = NurseBasicListConfig.SCHEDULE_ALL;
	public final static String SCHEDULE_DAY   = NurseBasicListConfig.SCHEDULE_DAY;
	public final static String SCHEDULE_NIGHT = NurseBasicListConfig.SCHEDULE_NIGHT;

	//02. confirm
	//0201. recv(无)
	//0201. send
	//03. nurse order list
	public final static String NURSE_ORDER_LIST   = "order_list";                    //用户订单列表
	public final static String ORDER_TIME         = "order_time";                //订单时间
	public final static String ORDER_STATUS       = "order_status";            //订单的状态。
	public final static String ORDER_SERIAL_NUM   = "show_id";    //订单的交易流水号。
	public final static String SCHEDULE_DATE_LIST = "schedule";
	public final static String DATE_ALL_LIST      = "all";
	public final static String DATE_DAY_LIST      = "day";
	public final static String DATE_NIGHT_LIST    = "night";

	//04. nurse order check
	//05. nurse order alipay
	public final static String RESULT_STATUS   = "result_status";


	//logical
	public final static int NURSE_IN_SERVICE = -2;

}
