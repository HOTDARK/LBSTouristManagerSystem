/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.model;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-24 下午4:41:17
 */
public class ConditionParam {
	private String key;
	
	private String value;
	
	private String script;
	
	private String color;
	
	public ConditionParam() {
		
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public ConditionParam(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @param key
	 * @param value
	 */
	public ConditionParam(String key, String value,String script) {
		this.key = key;
		this.value = value;
		this.script = script;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}

	/**
	 * @param script the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
