package com.hd.api.server.entity;

import java.util.Date;

/**
 * 北向接口日志对象
 */
public class WebServiceNorth {

	private int id;
	
	/**
	 * 接口名字
	 */
	private String wname;

	/**
	 * 开始调用时间
	 */
	private Date startTime;

	
	/**
	 * 结束调用时间
	 */
	private Date endTime;
	
	/**
	 * 传入参数
	 */
	private String input;

	/**
	 * 返回参数
	 */
	private String output;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
