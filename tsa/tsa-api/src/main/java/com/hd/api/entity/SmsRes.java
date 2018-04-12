package com.hd.api.entity;

/**
 * 短信接口结果实体类
 * @author somnuscy
 *
 */
public class SmsRes {
	
	private String res_code;// 结果代码
	private String res_message;// 结果信息
	private String identifier;// 短信唯一标识
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	public String getRes_message() {
		return res_message;
	}
	public void setRes_message(String res_message) {
		this.res_message = res_message;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
