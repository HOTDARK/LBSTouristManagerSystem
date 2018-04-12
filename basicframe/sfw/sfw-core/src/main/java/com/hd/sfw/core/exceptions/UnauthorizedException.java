/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.exceptions;

/**
 * 未授权异常
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-19 下午3:07:26
 */
public class UnauthorizedException extends BaseException{
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException() {
	}
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
	
	public UnauthorizedException(String msg,Throwable cause){
		super(msg, cause);
	}

}
