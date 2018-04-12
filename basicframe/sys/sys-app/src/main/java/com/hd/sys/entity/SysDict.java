package com.hd.sys.entity;

import com.hd.sfw.core.model.Pagination;

/**
 * <p>类名：SysTypeDict </p>
 * <p>描述：业务类型 </p>
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public class SysDict extends Pagination<SysDict> implements java.io.Serializable {
	
	private static final long serialVersionUID = -4127535397646914227L;

	/** 类型字典CODE */
    private String typeDictCode;
	
    /** 类型字典名称 */
    private String typeDictName;
	
    /** 父级类型字典CODE */
    private String parentTypeDictCode;
    
	/** 是否是叶子节点 */
    private Long typeLeafNode;
    
    /** 有效状态【1-有效，0-无效】 */
    private Long state;
	
    /** 排序号 */
    private Long seqNum;
	
    /** 应用标识 */
    private String applicationFlag;
    
    /** 父级类型名称 **/
    private String parentTypeDictName;
    
    private String oldTypeDictCode;
    
    private String mappingName;//映射名称
    private String mappingCode;//映射编码
    private String remark;//备注
	
	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getMappingCode() {
		return mappingCode;
	}

	public void setMappingCode(String mappingCode) {
		this.mappingCode = mappingCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOldTypeDictCode() {
		return oldTypeDictCode;
	}

	public void setOldTypeDictCode(String oldTypeDictCode) {
		this.oldTypeDictCode = oldTypeDictCode;
	}

	public SysDict() {}
	
	/**
	 * @return 获取 typeDictCode
	 */
	public String getTypeDictCode() {
		return this.typeDictCode;
	}
	
	/**
	 * @param taskId 要设置的 typeDictCode
	 */
	public void setTypeDictCode(String typeDictCode) {
		this.typeDictCode = typeDictCode;
	}
	/**
	 * @return 获取 typeDictName
	 */
	public String getTypeDictName() {
		return this.typeDictName;
	}
	
	/**
	 * @param taskId 要设置的 typeDictName
	 */
	public void setTypeDictName(String typeDictName) {
		this.typeDictName = typeDictName;
	}
	/**
	 * @return 获取 parentTypeDictCode
	 */
	public String getParentTypeDictCode() {
		return this.parentTypeDictCode;
	}
	
	/**
	 * @param taskId 要设置的 parentTypeDictCode
	 */
	public void setParentTypeDictCode(String parentTypeDictCode) {
		this.parentTypeDictCode = parentTypeDictCode;
	}
	/**
	 * @return 获取 state
	 */
	public Long getState() {
		return this.state;
	}
	
	/**
	 * @param taskId 要设置的 state
	 */
	public void setState(Long state) {
		this.state = state;
	}
	/**
	 * @return 获取 seqNum
	 */
	public Long getSeqNum() {
		return this.seqNum;
	}
	
	/**
	 * @param taskId 要设置的 seqNum
	 */
	public void setSeqNum(Long seqNum) {
		this.seqNum = seqNum;
	}
	/**
	 * @return 获取 applicationFlag
	 */
	public String getApplicationFlag() {
		return this.applicationFlag;
	}
	
	/**
	 * @param taskId 要设置的 applicationFlag
	 */
	public void setApplicationFlag(String applicationFlag) {
		this.applicationFlag = applicationFlag;
	}

	public Long getTypeLeafNode() {
		return typeLeafNode;
	}

	public void setTypeLeafNode(Long typeLeafNode) {
		this.typeLeafNode = typeLeafNode;
	}

	/**
	 * @return the parentTypeDictName
	 */
	public String getParentTypeDictName() {
		return parentTypeDictName;
	}

	/**
	 * @param parentTypeDictName the parentTypeDictName to set
	 */
	public void setParentTypeDictName(String parentTypeDictName) {
		this.parentTypeDictName = parentTypeDictName;
	}
	
}
