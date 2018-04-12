/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;

import com.hd.workflow.engine.pvm.VariableScope;

/**
 * 用于在表达式执行时直接从变量区读取变量
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-10 上午10:40:57
 */
public class VariableScopeElResolver extends ELResolver{
	
	private VariableScope variableScope;
	
	public VariableScopeElResolver(VariableScope variableScope) {
		this.variableScope = variableScope;
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		return Object.class;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context,
			Object base) {
		return null;
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		return Object.class;
	}

	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		if(base==null){
			String variable = (String) property;
			if(variableScope.hasVariable(variable)){
				context.setPropertyResolved(true);
				return variableScope.getVariable(variable);
			}
		}
		return null;
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return false;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property,Object value) {
		if (base == null) {
			String variable = (String) property;
			if (variableScope.hasVariable(variable)) {
				variableScope.setVariable(variable, value);
			}
		}
	}

}
