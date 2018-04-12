/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.log.trace;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <pre>
 * 用于在Spring中初始化LogPersisterExecutor。
 * 存在两种加入LogPersister的方式：
 * 1.手动set
 * 2.应用BeanPostProcessor，检测Spring容器中的LogPersister实现类，自动加入。
 * </pre>
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-17 上午11:12:57
 */
public class LogPersisterExecutorFactoryBean implements FactoryBean<LogPersisterExecutor>,InitializingBean,DisposableBean,BeanPostProcessor{
	
	private List<LogPersister> persisters = new ArrayList<LogPersister>();
	
	private LogPersisterExecutor executor;

	@Override
	public LogPersisterExecutor getObject() throws Exception {
		return executor;
	}

	@Override
	public Class<?> getObjectType() {
		return LogPersisterExecutor.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public List<LogPersister> getPersisters() {
		return persisters;
	}

	public void setPersisters(List<LogPersister> persisters) {
		this.persisters = persisters;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		executor = new LogPersisterExecutor();
		executor.setPersisters(persisters);
		LogUtils.logPersisterExecutor = executor;
		
		executor.start();
	}

	@Override
	public void destroy() throws Exception {
		executor.stop();
	}
	
	public void setLogIdApplier(LogIdApplier applier){
		LogUtils.logIdApplier = applier;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof LogPersister && !persisters.contains(bean)){
			persisters.add((LogPersister)bean);
		}
		return bean;
	}

}
