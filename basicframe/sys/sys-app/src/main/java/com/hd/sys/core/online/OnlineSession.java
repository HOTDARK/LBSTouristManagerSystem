package com.hd.sys.core.online;

import java.util.Date;

import javax.servlet.http.HttpSession;

/**
 * 在线用户session
 * @author sunjian
 * @version V1.0, 2013-5-31 下午02:21:06
 */
public class OnlineSession {
	//框架产生的session id
	private String id;
	
	//用户名
	private OnlineSysUser onlineSysUser;
	
	//登陆时间
	private Date loginDate = new Date();
	
	//登陆来源
	private String from;
	
	private HttpSession session;
	
	//最后一次访问时间
	private Date lastDate = new Date();
	
	private boolean invalidate = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public OnlineSysUser getOnlineSysUser() {
		return onlineSysUser;
	}

	public void setOnlineSysUser(OnlineSysUser onlineSysUser) {
		this.onlineSysUser = onlineSysUser;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
	/**
	 * 更新最后一次访问
	 */
	public void update(){
		lastDate = new Date();
	}
	
	/**
	 * 是否已经被注销
	 */
	public boolean isInvalidate() {
		return invalidate;
	}

	/**
	 * 使session失效
	 */
	public void invalidate(){
		if(!invalidate&&session!=null){
			try{
				session.invalidate();
			}catch (Exception e) {
			}
		}
		invalidate = true;
	}
	
}
