package com.hd.sfw.log.trace.model;

import java.util.Date;

import com.hd.sfw.log.trace.LogId;

/**
 * 
 * 操作日志实体类
 * @author somnuscy 
 * 
 */
public class SysLogOper {
	
	/**
	 * info级别
	 */
	public final static int INFO = 1;
	
	/**
	 * warning级别
	 */
	public final static int WARN = 2;
	
	/**
	 * error级别
	 */
	public final static int ERROR = 3;
	
	private int id;
	
	//日志id
	private String logId;
	
	//父日志id
	private String logPid;
	
	//日志源
	private String source;
	
	//日志类型
	private String type;
	
	//排序
	private int ord;
	
	//记录时间
	private Date logDate;
	
	//日志信息
	private String message;
	
	//日志级别
	private int level;
	
	//用户名
	private String username;
	
	//用户登陆ip
	private String from;
	
	//是否自动为子类生成message字段
	private boolean autoGenMsg = true;
	
	public SysLogOper() {
	}
	
	public SysLogOper(String msg,String source,String type){
		this.message = msg;
		this.source = source;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogPid() {
		return logPid;
	}

	public void setLogPid(String logPid) {
		this.logPid = logPid;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * 是否自动为子类生成message字段
	 * @return
	 */
	public boolean isAutoGenMsg() {
		return autoGenMsg;
	}

	/**
	 * 设置自动为子类生成message字段
	 * @param autoGenMsg
	 */
	public void setAutoGenMsg(boolean autoGenMsg) {
		this.autoGenMsg = autoGenMsg;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 设置日志logid
	 * @param logId
	 */
	public void setLogID(LogId logId){
		this.logId = logId.getId();
		logPid = logId.getPid();
		ord = logId.getOrd();
		username = logId.getUsername();
		from = logId.getFrom();
	}
}
