/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import com.hd.workflow.engine.pvm.impl.el.ELFunctions;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-4-20 下午3:20:32
 */
public class JuelTest {
	public static void main(String[] args) throws Exception{
		ExpressionFactory factory = new de.odysseus.el.ExpressionFactoryImpl();

		// package de.odysseus.el.util provides a ready-to-use subclass of ELContext
		de.odysseus.el.util.SimpleContext context = new de.odysseus.el.util.SimpleContext();
		context.setFunction("iwd", "mapping", ELFunctions.class.getMethod("mapping", Object.class, String.class));
		
		ValueExpression e = factory.createValueExpression(context, "${aa==1}", Object.class);
		
		System.out.println(e.getValue(context)); 
		
	}
}
