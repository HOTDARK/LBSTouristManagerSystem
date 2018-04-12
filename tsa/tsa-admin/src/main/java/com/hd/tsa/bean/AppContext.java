package com.hd.tsa.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	
	private static ApplicationContext appContext = null;
	
	/**
	 * 获取ApplicationContext
	 * @return
	 */
	public static ApplicationContext getAppContext() {
		if (appContext == null) {
			appContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		}
		return appContext;
	}
}
