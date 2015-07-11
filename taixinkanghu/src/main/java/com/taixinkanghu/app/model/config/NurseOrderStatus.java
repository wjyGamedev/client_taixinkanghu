package com.taixinkanghu.app.model.config;

/**
 * Copyright (c) 213Team
 *
 * @author : WangJY
 * @version : 1.0.0
 * @className : app.model.config.${type_name}
 * @description : ${TODO：陪护订单的状态}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */
public enum NurseOrderStatus
{
	/**
	 * 待支付/待评价/待服务/服务中/已完成/已取消
	 */
	ORDER_FROM_CHECK_STATUS_WAIT_PAYMENT,
	ORDER_FROM_CHECK_STATUS_WAIT_EVALUATION,
	ORDER_FROM_CHECK_STATUS_WAIT_SERVER,
	ORDER_FROM_CHECK_STATUS_IN_SERVICE,
	ORDER_FROM_CHECK_STATUS_FINISHAED,
	ORDER_FROM_CHECK_STATUS_CANCELED,

}
