/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hd.workflow.app.model.UserData;

/**
 * 用户关联映射接口，用于动态关联数据
 * @version	0.0.1
 */
public interface UserRelationshipMapping {
	
	/**
	 * 执行流程的用户账户id的key
	 */
	public String EXEC_USER_ID = "process_exec_user_id";

	/**
	 * 获取用户账户id
	 * @param session
	 * @return
	 */
	public String getUid(HttpSession session);
	
	/**
	 * 填充用户信息 
	 * 即在管理界面上显示的用户名
	 * @param list
	 */
	public void fill(List<? extends UserData> list);
}
