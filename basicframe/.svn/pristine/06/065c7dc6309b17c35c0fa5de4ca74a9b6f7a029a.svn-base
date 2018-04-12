/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sys.action.base;

import java.util.HashMap;

/**
 * 做为action的返回类型使用，包含一些通用字段
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-4 上午10:42:15
 */
public class ActionResult extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;
	
	public ActionResult() {
		//默认为false
		this.put("success", false);
	}
	
	/**
	 * 定义默认的success状态
	 * @param successfule
	 */
	public ActionResult(boolean successfule){
		this.put("success", successfule);
	}
	
	public void setSuccess(boolean success){
		this.put("success", success);
	}
	
	public void setMessage(String msg){
		this.put("msg", msg);
	}
}
