package com.hd.sys.test;


import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring集成测试 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-12 下午2:14:21
 */
public abstract class BaseUnit {
	protected ApplicationContext context;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext( new String[] {"applicationContext-system-test.xml"});
	}
}
