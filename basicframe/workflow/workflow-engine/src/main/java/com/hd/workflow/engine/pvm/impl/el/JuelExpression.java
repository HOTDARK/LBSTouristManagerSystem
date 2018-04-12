/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl.el;

import javax.el.ELContext;
import javax.el.ValueExpression;

import com.hd.workflow.engine.pvm.VariableScope;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.delegate.Expression;

/**
 * juel实现的el表达式
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-9 下午4:52:50
 */
public class JuelExpression implements Expression{
	
	private String expressionText;
	
	private ValueExpression valueExpression;
	
	public JuelExpression(ValueExpression valueExpression,String expressionText) {
		this.valueExpression = valueExpression;
		this.expressionText = expressionText;
	}

	@Override
	public Object getValue(VariableScope variableScope) {
		ELContext context = Context.getConfiguration().getExpressionManager().getElContext(variableScope);
		return valueExpression.getValue(context);
	}

	@Override
	public String getExpressionText() {
		return expressionText;
	}

}
