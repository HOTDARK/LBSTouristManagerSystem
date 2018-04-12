package com.hd.sys.core.online;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.hd.sys.entity.SysUser;

/**
 * 本类的设计目的是为了一个用户账号可以在多个客户端登陆<br>
 * 可以方便的从本来的实例中找到登陆客户端
 * @author sunjian
 * @version V1.0, 2013-5-10 上午10:58:49
 */
public class OnlineSysUser {
	private SysUser user;
	
	//已经授权的权限,目的是为了更快的判断是否被授权
	private Set<String> rightPrivileges = Collections.synchronizedSet(new HashSet<String>());
	
	private Map<String, OnlineSession> sessions = new ConcurrentHashMap<String, OnlineSession>();
	
	public boolean isRight(String uri){
		return rightPrivileges.contains(uri);
	}
	
	public void addRight(String uri){
		rightPrivileges.add(uri);
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public Map<String, OnlineSession> getSessions() {
		return sessions;
	}
	
	/**
	 * 清空已授权的权限
	 */
	public void clearRights(){
		rightPrivileges.clear();
	}
	
}
