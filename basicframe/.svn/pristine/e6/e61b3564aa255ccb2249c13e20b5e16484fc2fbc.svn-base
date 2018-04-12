package com.hd.sys.entity;

import java.util.ArrayList;
import java.util.List;

import com.hd.sfw.core.model.Pagination;

/**
 * <p>类名：SysFunction </p>
 * <p>描述：系统功能 </p>
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public class SysFunc extends Pagination<SysFunc> implements java.io.Serializable {
	
	private static final long serialVersionUID = 3970148183896527111L;

	/** 功能ID */
    private Long functionId;
    
    /** 功能Code **/
    private String functionCode;
	
    /** 功能名称 */
    private String functionName;
	
    /** 父功能ID */
    private Long parentFunctionId;
	
    /** 功能标识【1-公共功能，0-私有功能】 */
    private Long functionFlag;
	
    /** 状态【1-有效，0-无效】 */
    private Long state;
    
    /** 是否是叶子节点【0-非叶子节点，1-叶子节点】 **/
    private Long functionLeafNode;
	
    /** 功能类型【0-功能及菜单，1-功能，2-菜单】 */
    private Long functionType;
	
    /** 功能URL */
    private String functionUrl;
	
    /** 排序号 */
    private Long seqNum;
	
    /** 备注 */
    private String functionDesc;
    
    /** 父级功能名称 **/
    private String parentFunctionName;
    
    private List<SysFunc> sysFunctionList = new ArrayList<SysFunc>();
    private String functionIds;
    private String icoName;
	
	public String getIcoName() {
		return icoName;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}

	public SysFunc() {}
	
	/**
	 * @return 获取 functionId
	 */
	public Long getFunctionId() {
		return this.functionId;
	}
	
	/**
	 * @param taskId 要设置的 functionId
	 */
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}
	
	/**
	 * @return 获取 functionCode
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * @param functionCode 要设置的 functionCode
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * @return 获取 functionName
	 */
	public String getFunctionName() {
		return this.functionName;
	}
	
	/**
	 * @param taskId 要设置的 functionName
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	/**
	 * @return 获取 parentFunctionId
	 */
	public Long getParentFunctionId() {
		return this.parentFunctionId;
	}
	
	/**
	 * @param taskId 要设置的 parentFunctionId
	 */
	public void setParentFunctionId(Long parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}
	/**
	 * @return 获取 functionFlag
	 */
	public Long getFunctionFlag() {
		return this.functionFlag;
	}
	
	/**
	 * @param taskId 要设置的 functionFlag
	 */
	public void setFunctionFlag(Long functionFlag) {
		this.functionFlag = functionFlag;
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
	 * @return 获取 functionType
	 */
	public Long getFunctionType() {
		return this.functionType;
	}
	
	/**
	 * @param taskId 要设置的 functionType
	 */
	public void setFunctionType(Long functionType) {
		this.functionType = functionType;
	}
	/**
	 * @return 获取 functionUrl
	 */
	public String getFunctionUrl() {
		return this.functionUrl;
	}
	
	/**
	 * @param taskId 要设置的 functionUrl
	 */
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
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
	 * @return 获取 functionDesc
	 */
	public String getFunctionDesc() {
		return this.functionDesc;
	}
	
	/**
	 * @param taskId 要设置的 functionDesc
	 */
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public Long getFunctionLeafNode() {
		return functionLeafNode;
	}

	public void setFunctionLeafNode(Long functionLeafNode) {
		this.functionLeafNode = functionLeafNode;
	}

	public List<SysFunc> getSysFunctionList() {
		return sysFunctionList;
	}

	public void setSysFunctionList(List<SysFunc> sysFunctionList) {
		this.sysFunctionList = sysFunctionList;
	}

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	/**
	 * @return the parentFunctionName
	 */
	public String getParentFunctionName() {
		return parentFunctionName;
	}

	/**
	 * @param parentFunctionName the parentFunctionName to set
	 */
	public void setParentFunctionName(String parentFunctionName) {
		this.parentFunctionName = parentFunctionName;
	}

	@Override
	public String toString() {
		return "SysFunction [functionId=" + functionId + ", functionCode="
				+ functionCode + ", functionName=" + functionName
				+ ", parentFunctionId=" + parentFunctionId + ", functionFlag="
				+ functionFlag + ", state=" + state + ", functionLeafNode="
				+ functionLeafNode + ", functionType=" + functionType
				+ ", functionUrl=" + functionUrl + ", seqNum=" + seqNum
				+ ", functionDesc=" + functionDesc + ", parentFunctionName="
				+ parentFunctionName + ", sysFunctionList=" + sysFunctionList
				+ ", functionIds=" + functionIds + ", icoName=" + icoName + "]";
	}
	
	
	
}
