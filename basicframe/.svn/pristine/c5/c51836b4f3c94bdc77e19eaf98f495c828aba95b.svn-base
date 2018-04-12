/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.el.ELContext;

import com.hd.workflow.engine.pvm.VariableScope;

/**
 * 变量区
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-10 上午11:30:07
 */
public class VariableScopeImpl implements VariableScope{
	
	protected Map<String, Object> variables = new ConcurrentHashMap<String, Object>();
	
	private VariableScope parent;
	
	//缓存的ElContext，提高性能，避免多次初始化
	private ELContext cachedElContext;
	
	@Override
	public boolean hasVariable(String variableName) {
		if(parent!=null){
			return parent.hasVariable(variableName);
		}else{
			return variables.containsKey(variableName);
		}
	}

	@Override
	public void setVariable(String variableName, Object value) {
		if(value==null){
			return;
		}
		
		if(parent!=null){
			parent.setVariable(variableName, value);
		}else{
			variables.put(variableName, value);
		}
		
	}

	@Override
	public Object getVariable(String variableName) {
		if(parent!=null){
			return parent.getVariable(variableName);
		}else{
			return variables.get(variableName);
		}
		
	}

	@Override
	public Map<String, Object> getVariables() {
		return variables;
	}

	/**
	 * @return the cachedElContext
	 */
	public ELContext getCachedElContext() {
		return cachedElContext;
	}

	/**
	 * @param cachedElContext the cachedElContext to set
	 */
	public void setCachedElContext(ELContext cachedElContext) {
		this.cachedElContext = cachedElContext;
	}

	@Override
	public VariableScope getParent() {
		return parent;
	}

	@Override
	public void setParent(VariableScope scope) {
		this.parent = scope;
	}

	@Override
	public boolean hasLocalVariable(String variableName) {
		return variables.containsKey(variableName);
	}

	@Override
	public void setLocalVariable(String variableName, Object value) {
		if(value==null){
			return;
		}
		
		variables.put(variableName, value);
	}

	@Override
	public Object getLocalVariable(String variableName) {
		return variables.get(variableName);
	}

	@Override
	public Object getObject(String variableName) {
		if(parent!=null){
			return parent.getVariable(variableName);
		}else{
			return variables.get(variableName);
		}
	}
	
}
