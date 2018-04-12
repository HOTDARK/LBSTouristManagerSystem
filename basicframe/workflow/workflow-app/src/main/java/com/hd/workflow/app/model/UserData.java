/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.model;

/**
 * 用户信息接口
 * @version	0.0.1
 */
public interface UserData {
	
	/**
	 * 用户账户ID
	 * @return
	 */
	public String getUid();
	
	/**
	 * 用户名
	 * @param username
	 */
	public void setUsername(String username);
}
