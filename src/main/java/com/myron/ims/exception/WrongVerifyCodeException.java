package com.myron.ims.exception;

import java.io.Serializable;

/**
 * 错误验证码
 * @author lin.r.x
 *
 */
public class WrongVerifyCodeException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;

	public WrongVerifyCodeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WrongVerifyCodeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public WrongVerifyCodeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WrongVerifyCodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WrongVerifyCodeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
