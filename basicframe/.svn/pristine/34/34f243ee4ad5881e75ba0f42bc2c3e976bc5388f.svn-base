/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log.script;

import javax.script.SimpleBindings;

import com.hd.workflow.engine.pvm.VariableScope;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-6-15 上午10:42:38
 */
public class VariableScopeBindings extends SimpleBindings{
	
	private VariableScope scope;
	
	public VariableScopeBindings(VariableScope scope) {
		this.scope = scope;
	}
	
	@Override
	public Object get(Object key) {
		Object obj =  super.get(key);
		return obj==null?scope.getVariable(key.toString()):obj;
	}
	
	@Override
	public boolean containsKey(Object key) {
		boolean f = super.containsKey(key);
		return f?true:scope.hasVariable(key.toString());
	}
	
}
