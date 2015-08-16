/**
 * Copyright (c) 213Team
 *
 * @className : app.model.Exception.RuntimeExceptions.${type_name}
 * @version : 1.0.0
 * @author : WangJY
 * @description : ${TODO}
 * <p/>
 * Modification History:
 * Date         	Author 		Version		Description
 * ----------------------------------------------------------------
 * 2015/7/9		WangJY		1.0.0		create
 */

package com.taixinkanghu.app.model.exception.RuntimeExceptions.net;

import com.taixinkanghu.app.model.config.TxkhExceptionCode;
import com.taixinkanghu.app.model.exception.RuntimeExceptions.BaseRunTimeException;

public class NetRTException extends BaseRunTimeException
{
	public NetRTException(Throwable cause)
	{
		super(TxkhExceptionCode.REX_NET, cause);
	}

	public NetRTException(String message)
	{
		super(TxkhExceptionCode.REX_NET, message);
	}

	public NetRTException(String message, Throwable cause)
	{
		super(TxkhExceptionCode.REX_NET, message, cause);
	}
}
