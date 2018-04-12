/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.factory;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hd.workflow.engine.pvm.delegate.TaskDelegate;

/**
 * 可应用spring注解的实例工厂，此类需在spring容器中实例化后引用
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-27 下午4:18:20
 */
public class SpringTaskDelegateFactory implements TaskDelegateFactory,ApplicationContextAware{
	private ApplicationContext applicationContext;

	@Override
	public TaskDelegate newInstance(Class<TaskDelegate> clazz) throws NewTaskDelegateException {
		
		try{
			TaskDelegate delegate = clazz.newInstance();
			AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
			bpp.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
			bpp.processInjection(delegate);
			
			return delegate;
		}catch (Exception e) {
			throw new NewTaskDelegateException();
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
