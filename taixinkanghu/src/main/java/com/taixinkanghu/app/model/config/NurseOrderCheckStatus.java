/**
 * Copyright (c) 213Team
 *
 * @className : app.model.config.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO:陪护订单的查询状态}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.config;

public enum NurseOrderCheckStatus
{
	/**
	 * 全部/OrderFormStatus待支付/待评价/待服务/服务中/已完成/已取消
	 */
	ORDER_FROM_CHECK_STATUS_ALL,
	ORDER_FROM_CHECK_STATUS_WAIT_PAYMENT,
	ORDER_FROM_CHECK_STATUS_WAIT_EVALUATION,
	ORDER_FROM_CHECK_STATUS_WAIT_SERVER,
	ORDER_FROM_CHECK_STATUS_IN_SERVICE,
	ORDER_FROM_CHECK_STATUS_FINISHAED,
	ORDER_FROM_CHECK_STATUS_CANCELED,

};

