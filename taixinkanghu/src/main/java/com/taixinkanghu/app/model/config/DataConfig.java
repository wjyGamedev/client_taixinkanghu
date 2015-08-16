/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.config.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/11		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.config;

public class DataConfig
{
	public final static String DHOSPITAL_LIST = "hospital_list";
	public final static String DHOSPITAL_ID   = "id";
	public final static String DHOSPITAL_NAME = "name";


	public final static String NURSE_BASICS_LIST            = "nurse_basics_list";
	public final static String NURSE_ID                     = "id";
	public final static String NURSE_HOSPITAL_ID            = "hospital_id";
	public final static String NURSE_NAME                   = "name";
	public final static String NURSE_STAR_LEVEL             = "star_level";
	public final static String NURSE_AGE                    = "age";
	public final static String NURSE_HOMETOWN               = "hometown";
	public final static String NURSE_NURING_EXP             = "nursing_exp";
	public final static String NURSE_NURING_LEVEL           = "nursing_level";

	public final static String SERVICE_CHARGE_PER_ALL_CARE = "service_charge_per_all_care";				//24小时，可自理
	public final static String SERVICE_CHARGE_PER_ALL_HALF_CARE = "service_charge_per_all_half_care";		//24小时，半自理
	public final static String SERVICE_CHARGE_PER_ALL_CANNT_CARE = "service_charge_per_all_cannt_care";	//24小时，不可自理

	public final static String SERVICE_CHARGE_PER_DAY_CARE = "service_charge_per_day_care";				//12白，可自理
	public final static String SERVICE_CHARGE_PER_DAY_HALF_CARE = "service_charge_per_day_half_care";		//12白，半自理
	public final static String SERVICE_CHARGE_PER_DAY_CANNT_CARE = "service_charge_per_day_cannt_care";	//12白，不可自理

	public final static String SERVICE_CHARGE_PER_NIGHT_CARE = "service_charge_per_night_care";			//12黑，可自理
	public final static String SERVICE_CHARGE_PER_NIGHTL_HALF_CARE = "service_charge_per_night_care";		//12黑，半自理
	public final static String SERVICE_CHARGE_PER_NIGHT_CANNT_CARE = "service_charge_per_night_care";		//12黑，不可自理



	public final static String NURSE_SERVICE_STATUS         = "nurse_service_status";

	public final static String NURSE_SENIOR_LIST     = "nurse_senior_list";
	public final static String NURSE_JOB_NUM         = "job_num";
	public final static String NURSE_LANGUAGE_LEVEL  = "language_level";
	public final static String NURSE_EDUCATION       = "education";
	public final static String NURSE_NATION          = "nation";
	public final static String NURSE_INTRO           = "intro";
	public final static String NURSE_DEPARTMENTS     = "departments";
	public final static String NURSE_CERTIFICATE     = "certificate";
	public final static String NURSE_SERVICE_CONTENT = "service_content";
	public final static String NURSE_GOOD_RATE       = "good_rate";
	public final static String NURSE_COMMENT_NUM     = "comment_num";

	public final static String NURSE_SHEDULE_LIST        = "nurse_shedule_list";
	public final static String NURSE_SHEDULE_MONTH       = "month";
	public final static String NURSE_SHEDULE_DAY_SERVICE = "days";

	public final static String NURSE_COMMENT_LIST = "nurse_comment_list";


	//康复用品
	public final static String GOODS_BASICS_LIST = "goods_basics_list";
	public final static String GOODS_ID          = "id";
	public final static String GOODS_NAME        = "name";
	public final static String GOODS_UNIT_PRICE  = "price";
	public final static String PRAISE_RATE       = "praise_rate";
	public final static String EVALUATION_TIMES  = "evaluation_times";

	//注册
	public final static String HTTP_STATUS_CODE = "register_token";
	public final static String JSON_DATA_KEY    = "json_data";
	public final static String STATUS_KEY       = "status";
	public final static String ERROR_MSG        = "msg";


	public final static int S_HTTP_OK                      = 200;
	public final static int S_SMS_OK                       = 200;    //注册，服务器返回成功标志
	public final static int S_SMS_ACCESS_PERMISSION_REFUSE = 512;
	public final static int S_SMS_APPKEY_INVALID           = 513;
	public final static int S_SMS_PERMISSION_INVALID       = 514;
	public final static int S_SMS_SERVER_ERROR             = 515;
	public final static int S_SMS_PARAM_INVALID            = 517;
	public final static int S_SMS_PHONE_FORMAT_INVALID     = 518;
	public final static int S_SMS_AUTH_CODE_NUM_MAX        = 519;
	public final static int S_SMS_AUTH_CODE_INVALID        = 520;
	public final static int S_SMS_CREDIT_LOW               = 526;

	public final static String USER_KEY   = "user";
	public final static String ID_KEY     = "id";
	public final static String CODE_KEY   = "code";
	public final static String MOBILE_KEY = "mobile";
	public final static String NICK_KEY   = "nick";

	public final static String REGISTER_FROM_HTTP    = "http";
	public final static String REGISTER_FROM_STORATE = "storage";

	//预约陪护
	public final static String NAME = "name";
	public final static String PHONE_NUM = "phone_num";
	public final static String SEX_ID = "sex_id";
	public final static String AGE = "age";
	public final static String WEIGHT = "weight";
	public final static String HOSPITAL_ID = "hospital_id";
	public final static String DEPARTMENT_NAME = "department_name";
	public final static String PATIENT_STATE_ID = "patient_state_id";
	public final static String STRICT = "strict";



	public final static String SCHEDULE_ALL   = "schedule_all";
	public final static String SCHEDULE_DAY   = "schedule_day";
	public final static String SCHEDULE_NIGHT = "schedule_night";
	public final static String SCHEDULE_SPLIT = ",";

	//日期样式
	public final static String PATTERN_DATE_MONTH_DAY_WEEK = "MM月dd日(E)";    //MM月dd日(星期E)
	public final static String PATTERN_DATE_MONTH_DAY = "MM月dd日";    //MM月dd日
	public final static String PATTERN_DATE_YEAR_MONTH_DAY = "yyyy-MM-dd";    //YYYY年MM月dd日
	public final static int    MAX_SELECT_MONTH            = 3;

	public final static int MAX_SELECT_DAY_TYEP = 3;
	public final static int SELECT_DAY_TYEP_ALL = 0;
	public final static int SELECT_DAY_TYEP_DAY = 1;
	public final static int SELECT_DAY_TYEP_NIGHT = 2;

}

