package com.hd.tsa.test.base;


import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring集成测试 
 */
public abstract class BaseUnit {
	protected ApplicationContext context;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext( new String[] {"applicationContext-test.xml"});
	}
}
