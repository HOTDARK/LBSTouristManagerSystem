/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.exception;

/**
 * 针对执行节点在需要触发边界异常事件时的默认异常类
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-5 下午4:01:18
 */
public class BizError extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String errCode;
	
	public BizError(String errCode) {
		this.errCode = errCode;
	}
	
	public BizError(String errCode,String msg){
		super(msg);
		this.errCode = errCode;
	}
	
	public BizError(String errCode,Throwable throwable){
		super(throwable);
		this.errCode = errCode;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
