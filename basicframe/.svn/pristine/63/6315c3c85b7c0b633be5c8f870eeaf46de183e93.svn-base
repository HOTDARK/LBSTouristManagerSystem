package com.hd.sys.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 重写Spring注入类
 *
 */
public class SpringUtil implements ApplicationContextAware {
	public static ApplicationContext ctx;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public synchronized void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ctx = arg0;
	}

	/**
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
}
