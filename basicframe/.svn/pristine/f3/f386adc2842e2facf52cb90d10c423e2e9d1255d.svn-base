package com.hd.sys.entity;

import com.hd.sfw.core.model.BaseModel;

/**
 * @author sunjian
 * @version V1.0, 2013-5-15 上午09:39:23
 */
public class SysProp extends BaseModel{
	private static final long serialVersionUID = 1L;

	/**
	 * 读写不可删除
	 */
	public final static int STATE_WRITE = 2;

	/**
	 * 读写 可删除
	 */
	public final static int STATE_READWRITE = 1;

	/**
	 * 只读
	 */
	public final static int STATE_READONLY = 0;
	
	//属性名称
	private String key;
	
	//数据库中保存的字符串类型值
	private String value;
	
	//把value转换成对应object的hanlder
	private String type;
	
	//属性状态
	private Integer state;
	
	//描述
	private String description;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
