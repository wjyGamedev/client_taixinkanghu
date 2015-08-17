/**
 * Copyright (c) 213Team
 *
 * @className : com.taixinkanghu.app.model.exception.RuntimeExceptions.logical.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/8/17		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.exception.RuntimeExceptions.logical;

import com.taixinkanghu.app.model.config.TxkhExceptionCode;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.BaseRunTimeException;

public class LogicalRTException extends BaseRunTimeException
{
	public LogicalRTException(Throwable cause)
	{
		super(TxkhExceptionCode.REX_LOGICAL, cause);
	}

	public LogicalRTException(String message)
	{
		super(TxkhExceptionCode.REX_LOGICAL, message);
	}

	public LogicalRTException(String message, Throwable cause)
	{
		super(TxkhExceptionCode.REX_LOGICAL, message, cause);
	}
}
