/**
 * Copyright (c) 213Team
 *
 * @className : app.model.Exception.RuntimeExceptions.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/6		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.exception.RuntimeExceptions;

import com.taixinkanghu.app.model.config.TxkhExceptionCode;

public class ManagerRTException extends BaseRunTimeException
{
	public ManagerRTException(Throwable cause)
	{
		super(TxkhExceptionCode.REX_MANAGER_UNIMPLEMENT, cause);
	}

	public ManagerRTException(String message)
	{
		super(TxkhExceptionCode.REX_MANAGER_UNIMPLEMENT, message);
	}

	public ManagerRTException(String message, Throwable cause)
	{
		super(TxkhExceptionCode.REX_MANAGER_UNIMPLEMENT, message, cause);
	}

}
