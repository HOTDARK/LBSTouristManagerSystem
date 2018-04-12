package com.hd.api.validate;

/**
 * 字段验证公用类
 * @author somnuscy
 *
 */
public class ValidateObject {
	
	private Object param;// 验证参数
	private String msg;// 提示信息
	
	public ValidateObject(Object param, String msg) {
		this.param = param;
		this.msg = msg;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
