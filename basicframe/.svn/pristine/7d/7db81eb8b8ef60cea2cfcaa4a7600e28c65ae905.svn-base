/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.impl.el;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ValueExpression;

import org.apache.commons.lang.StringUtils;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.pvm.VariableScope;
import com.hd.workflow.engine.pvm.delegate.Expression;
import com.hd.workflow.engine.pvm.impl.VariableScopeImpl;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

/**
 * 表达式管理器<br>
 * 用于初始化、获取表达式上下文
 * 
 * @version 0.0.1
 * @author <a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date 2015-2-10 上午10:52:06
 */
public class ExpressionManager {
	
	private static List<Method> cachedMethods = new ArrayList<Method>();
	
	static{
		for(Method m : ELFunctions.class.getMethods()){
			if(Modifier.isStatic(m.getModifiers())&&Modifier.isPublic(m.getModifiers())){
				cachedMethods.add(m);
			}
		}
	}
	
	protected ExpressionFactory expressionFactory;
	
	//默认实现
	protected SimpleContext parsingElContext = new SimpleContext();

	public ExpressionManager() {
		expressionFactory = new ExpressionFactoryImpl();
		installFunction(parsingElContext);
	}

	public Expression createExpression(String expression) {
		if(expression==null){
			expression="";
		}
		ValueExpression valueExpression = expressionFactory.createValueExpression(parsingElContext, StringUtils.trim(expression),	Object.class);
		return new JuelExpression(valueExpression, expression);
	}

	public void setExpressionFactory(ExpressionFactory expressionFactory) {
		this.expressionFactory = expressionFactory;
	}

	public ELContext getElContext(VariableScope variableScope) {
		ELContext elContext = null;
		if (variableScope instanceof VariableScopeImpl) {
			VariableScopeImpl variableScopeImpl = (VariableScopeImpl) variableScope;
			elContext = variableScopeImpl.getCachedElContext();
		}

		if (elContext == null) {
			elContext = createElContext(variableScope);
			if (variableScope instanceof VariableScopeImpl) {
				((VariableScopeImpl) variableScope).setCachedElContext(elContext);
			}
		}
		
		return createElContext(variableScope);
	}

	protected ELContext createElContext(VariableScope variableScope) {
		ELResolver elResolver = createElResolver(variableScope);
		SimpleContext context = new SimpleContext(elResolver);
		
		installFunction(context);
		
		return context;
	}
	
	/**
	 * 为context设置自定义el函数 
	 * @param context
	 */
	protected void installFunction(SimpleContext context){
		if(CollectionUtils.isNotEmpty(cachedMethods)){
			for(Method method : cachedMethods){
				context.setFunction("iwd", method.getName(), method);
			}
		}
	}

	protected ELResolver createElResolver(VariableScope variableScope) {
		CompositeELResolver elResolver = new CompositeELResolver();
		elResolver.add(new VariableScopeElResolver(variableScope));
		elResolver.add(new ArrayELResolver());
		elResolver.add(new ListELResolver());
		elResolver.add(new MapELResolver());
		elResolver.add(new BeanELResolver());
		elResolver.add(new NullResolver());
		return elResolver;
	}
	
}
