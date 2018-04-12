/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.exceptions;

/**
 * 业务异常类,一般用于在业务层抛出此异常
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-1 上午10:36:31
 */
public class IwdBusinessException extends BaseException{

	private static final long serialVersionUID = 1L;
	
	public IwdBusinessException(String msg) {
		super(msg);
	}
	
	public IwdBusinessException(String msg, Throwable cause){
		super(msg,cause);
	}
	
	public IwdBusinessException() {
	}
}
