package com.hd.sys.core.online;

import java.util.List;

import com.hd.sys.entity.SysUser;

/**
 * 在线用户管理器
 * @author sunjian
 * @version V1.0, 2013-5-10 上午10:58:18
 */
public interface OnlineUserManager {
	
	/**
	 * 在系统上下文中登陆用户
	 * @param user 包含权限信息的用户对象
	 * @param from 登陆来源一般情况为ip
	 * @return 返回OnlineSession
	 */
	public OnlineSession login(SysUser user,String from);
	
	/**
	 * 在系统上下文中登出该账号
	 * @param username
	 */
	public void logoutByUsername(String username);
	
	/**
	 * 在系统上下文中注销sid对应的会话
	 * @param sid
	 */
	public void logout(String sid);
	
	/**
	 * 在系统上下文中注销sid对应的会话
	 * @param sid
	 * @param invalidate 是否调用invalidate()方法
	 */
	public void logout(String sid,boolean invalidate);
	
	/**
	 * 在用户登陆的前提下大多数时候应该返回的是对应的登陆用户<br>
	 * 但是如果涉及到系统级逻辑调用，如系统任务调度时此时获取到的应该返回System用户<br>
	 * @return
	 */
	public SysUser getCurrentUser();
	
	/**
	 * 根据用户名获取在线用户
	 * @param username
	 * @return
	 */
	public OnlineSysUser getCurrentOnlineUser();
	
	/**
	 * 获取当前操作用户的OnlineSession
	 * @return
	 */
	public OnlineSession getCurrentOnlineSession();
	
	/**
	 * 通过传入一个字符串判断当前用户是否具备相应权限
	 * @param privilege
	 * @return
	 */
/*	public boolean isPermitted(String privilege);*/
	
	/**
	 * 判断是否具备相应权限
	 * @param privilege
	 * @return
	 */
/*	public boolean isPermitted(SysPrivilege privilege);*/
	
	/**
	 * 获取在线用户列表
	 * @return
	 */
	public List<OnlineSysUser> getOnlineUser();

}
