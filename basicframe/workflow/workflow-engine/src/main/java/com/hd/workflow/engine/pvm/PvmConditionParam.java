/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm;

import com.hd.workflow.engine.pvm.delegate.Expression;

/**
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-24 下午4:44:27
 */
public class PvmConditionParam {
	
	private String key;
	
	private Expression value;

	private String script;
	
	public PvmConditionParam() {
	}
	
	public PvmConditionParam(String key, Expression value) {
		this.key = key;
		this.value = value;
	}
	
	public PvmConditionParam(String key, Expression value,String script) {
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
	public Expression getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Expression value) {
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
	
	
	
}
