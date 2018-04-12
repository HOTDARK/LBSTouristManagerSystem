package com.hd.sfw.webservice.model;

import java.util.concurrent.atomic.AtomicInteger;

import com.hd.sfw.webservice.model.enums.AuthType;
import com.hd.sfw.webservice.model.enums.WebServiceType;

/**
 * 
 * webservice配置信息
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-9-10 上午11:19:04
 */
public class WebServiceConfigure{
	public static final String socketChanne="1";
	public static final String socketOrdinary="2";
	public static final String SOCKETTYPEKEY="socketTypekey";
	
	/**
	 * socket类型  1：socke通道  2：普通socket
	 */
	private String socketType;
	
	/**
	 * 接口标识(每个接口唯一)
	 */
	private String id;
	
	/**
	 * 接口中文名称
	 */
	private String name;
	
	/**
	 * url地址，wsdl地址等
	 */
	private String url;
	
	/**
	 * 超时时间
	 */
	private Long timeout;
	
	/**
	 * webservice的targetNamespace
	 */
	private String targetNamespace;
	
	/**
	 * 调用的webservice的方法名字
	 */
	private String methodName;
	
	/**
	 * 设置方法的soapaction
	 */
	private String soapAction;
	
	//授权类型
	private AuthType auth;
	
	private String username;
	
	private String password;
	
	/**
	 * 调用类型(使用的那个方式来调用webservice)
	 */
	private WebServiceType webServiceType;
	
	
	//调用次数
	private AtomicInteger count = new AtomicInteger();
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the timeout
	 */
	public Long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the targetNamespace
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * @param targetNamespace the targetNamespace to set
	 */
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the soapAction
	 */
	public String getSoapAction() {
		return soapAction;
	}

	/**
	 * @param soapAction the soapAction to set
	 */
	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	/**
	 * @return the webServiceType
	 */
	public WebServiceType getWebServiceType() {
		return webServiceType;
	}

	/**
	 * @param webServiceType the webServiceType to set
	 */
	public void setWebServiceType(WebServiceType webServiceType) {
		this.webServiceType = webServiceType;
	}

	/**
	 * @return the auth
	 */
	public AuthType getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(AuthType auth) {
		this.auth = auth;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取接口调用次数
	 * @return the count
	 */
	public int getCount() {
		return count.get();
	}
	
	/**
	 * 重置计数器
	 */
	public void reset(){
		count.set(0);
	}
	
	/**
	 * 计数器累加1
	 */
	public void increment(){
		count.incrementAndGet();
	}

	public String getSocketType() {
		return socketType;
	}

	public void setSocketType(String socketType) {
		this.socketType = socketType;
	}
	
}