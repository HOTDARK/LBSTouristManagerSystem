package com.hd.sys.core.context;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.core.properties.ContextProperties;

/**
 * 框架初始化类，用于在Spring中初始化系统相关配置<br>
 * 使用setter设置需要的组件 最后调用init方法完成初始化
 * @author sunjian
 * @version V1.0, 2013-4-23 上午09:59:51
 */
public class FrameworkInit implements FactoryBean<FrameworkContext>,ApplicationContextAware {
	private final static Logger log = Logger.getLogger(FrameworkInit.class);
	
	private FrameworkContext frameworkContext = null;
	
	public FrameworkInit() {
		frameworkContext = new FrameworkContext();
		FrameworkContext.setFrameworkContext(frameworkContext);
	}
	
	/**
	 * 项目名 
	 * @param name
	 */
	public void setProjectName(String name){
		FrameworkContext.PROJECT_NAME = name;
	}
	
	public void setOnlineUserManager(OnlineUserManager onlineUserManager){
		frameworkContext.setOnlineUserManager(onlineUserManager);
	}
	
	public void setContextProperties(ContextProperties contextProperties) {
		frameworkContext.setContextProperties(contextProperties);
	}
	
	/**
	 * 启动框架
	 */
	public void init(){
		frameworkContext.startFramework();
	}
	
	public void destory(){
		log.info("Framework going down");
		frameworkContext.shutdownFramework();
		
		//关闭所有jdbc
	    try {  
	        Enumeration<Driver> drivers = DriverManager.getDrivers();  
	        while(drivers.hasMoreElements()) {  
	            DriverManager.deregisterDriver(drivers.nextElement());  
	        }  
	    } catch(Exception e) {
	    	//ignore
	    }
	}

	@Override
	public FrameworkContext getObject() throws Exception {
		return frameworkContext;
	}

	@Override
	public Class<FrameworkContext> getObjectType() {
		return FrameworkContext.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		frameworkContext.setApplicationContext(context);
	}
	
}
