/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm;

import java.util.Map;

/**
 * 共享变量区
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-2 下午4:37:02
 */
public interface VariableScope {
	
	/**
	 * 是否包含指定名称的变量
	 * @param variableName
	 * @return
	 */
	public boolean hasVariable(String variableName);
	
	/**
	 * 设置变量
	 * @param variableName
	 * @param value
	 */
	public void setVariable(String variableName, Object value);
	
	/**
	 * 获取变量
	 * @param variableName
	 * @return
	 */
	public Object getVariable(String variableName);
	/**
	 * 获取变量
	 * @param variableName
	 * @return
	 */
	public Object getObject(String variableName);
	
	public boolean hasLocalVariable(String variableName);
	
	public void setLocalVariable(String variableName, Object value);
	
	public Object getLocalVariable(String variableName);
	
	/**
	 * 获取所有变量
	 * @return
	 */
	public Map<String, Object> getVariables();
	
	public VariableScope getParent();
	
	public void setParent(VariableScope scope);
}
