/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.entity;
import com.hd.sfw.core.model.Pagination;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-11-13
 */
public class WxActivityRel extends Pagination<WxActivityRel>{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	private java.lang.Long id;
	private java.lang.Long layoutId;
	private java.lang.Long detailId;
	private java.lang.Integer seqNum;
	//columns END
	
	private String modulName;
	
	private String filePath;
	
	//用于查询的时间列

	public WxActivityRel(){
	}

	public WxActivityRel(
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
	public void setLayoutId(java.lang.Long value) {
		this.layoutId = value;
	}
	
	public java.lang.Long getLayoutId() {
		return this.layoutId;
	}
	public void setDetailId(java.lang.Long value) {
		this.detailId = value;
	}
	
	public java.lang.Long getDetailId() {
		return this.detailId;
	}
	public void setSeqNum(java.lang.Integer value) {
		this.seqNum = value;
	}
	
	public java.lang.Integer getSeqNum() {
		return this.seqNum;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}