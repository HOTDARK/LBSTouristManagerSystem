package com.hd.sys.core.context;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.hd.sys.core.online.OnlineSession;
import com.hd.sys.core.online.OnlineSysUser;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.core.properties.ContextProperties;
import com.hd.sys.entity.SysUser;

/**
 * 
 * 系统上下文
 * @author sunjian  
 * @version 1.0 2013-2-20
 */
public class FrameworkContext {
	private final static Logger LOG = Logger.getLogger(FrameworkContext.class);
	
	public static String PROJECT_NAME;
	
	public final static String SYS_USER_FLAG = "IWD_SYS_USER_FLAG";
	public final static int SYS_SESSION_EXPIRED_TIME = 20;
	
	public final static String SYS_COOKIE_TOKEN = "IWD_LOGIN_TOKEN";
	
	private static FrameworkContext instance;
	
	//spring application context
	private ApplicationContext applicationContext;
	
	//在线用户管理器
	private OnlineUserManager onlineUserManager;
	
	//上下文属性变量
	private ContextProperties contextProperties;
	
	public OnlineUserManager getOnlineUserManager() {
		return onlineUserManager;
	}

	protected void setOnlineUserManager(OnlineUserManager onlineUserManager) {
		this.onlineUserManager = onlineUserManager;
	}
	
	public void setContextProperties(ContextProperties contextProperties) {
		this.contextProperties = contextProperties;
	}

	/**
	 * 获取Spring的ApplicationContext
	 * @return
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	protected void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 初始化相关框架参数，启动子系统
	 */
	public void startFramework(){
		LOG.info("启动框架.");
	}
	
	/**
	 * 关闭子系统做相关清理工作
	 */
	public void shutdownFramework(){
		LOG.info("关闭框架");
	}
	
	/**
	 * 本方法“一定”会返回一个用户<br>
	 * 在用户登陆的前提下大多数时候应该返回的是对应的登陆用户<br>
	 * 但是如果涉及到系统级逻辑调用，如系统任务调度时此时获取到的应该返回System用户<br>
	 * @return
	 */
	public SysUser getCurrentUser(){
		return onlineUserManager.getCurrentUser();
	}
	
	/**
	 * 在系统上下文中登陆用户
	 * @param username 用户名
	 * @param password 密码
	 * @param from 登陆来源
	 * @return
	 */
	public OnlineSession login(SysUser sysUser, String from){
		return onlineUserManager.login(sysUser, from);
	}
	
	/**
	 * 在系统上下文中登出用户
	 * @param sid
	 */
	public void logout(String sid){
		onlineUserManager.logout(sid);
	}
	
	/**
	 * 通过传入一个字符串判断当前用户是否具备相应权限
	 * @param privilege
	 * @return
	 */
//	public boolean isPermitted(String privilege){
//		return onlineUserManager.isPermitted(privilege);
//	}
	
	/**
	 * 判断是否具备相应权限
	 * @param privilege
	 * @return
	 */
//	public boolean isPermitted(SysPrivilege privilege){
//		return onlineUserManager.isPermitted(privilege);
//	}
	
	/**
	 * 获取在线用户列表
	 * @return
	 */
	public List<OnlineSysUser> getOnlineSysUser(){
		return onlineUserManager.getOnlineUser();
	}
	
	/**
	 * 获取context实例
	 * @return
	 */
	public static FrameworkContext getFrameworkContext(){
		return instance;
	}
	
	protected static void setFrameworkContext(FrameworkContext context){
		FrameworkContext.instance = context;
	}
	
	public int getInt(String key){
		return contextProperties.getInt(key);
	}
	
	public boolean getBoolean(String key){
		return contextProperties.getBoolean(key);
	}
	
	public String getString(String key){
		return contextProperties.getString(key);
	}
	
	public Object getObject(String key){
		return contextProperties.getObject(key);
	}
	
	public long getLong(String key){
		return contextProperties.getLong(key);
	}
}
