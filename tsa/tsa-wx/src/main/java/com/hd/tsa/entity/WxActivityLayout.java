/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.BaseModel;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-11-28
 */
public class WxActivityLayout extends BaseModel{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long infoId;
	private java.lang.Integer lineNum;
	private java.lang.Integer columnsNum;
	//columns END
	
	//用于查询的时间列

	public WxActivityLayout(){
	}

	public WxActivityLayout(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setInfoId(java.lang.Long value) {
		this.infoId = value;
	}
	
	public java.lang.Long getInfoId() {
		return this.infoId;
	}
	public void setLineNum(java.lang.Integer value) {
		this.lineNum = value;
	}
	
	public java.lang.Integer getLineNum() {
		return this.lineNum;
	}
	public void setColumnsNum(java.lang.Integer value) {
		this.columnsNum = value;
	}
	
	public java.lang.Integer getColumnsNum() {
		return this.columnsNum;
	}

}