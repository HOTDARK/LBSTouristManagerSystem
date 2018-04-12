package com.hd.sys.entity;

import java.util.Date;

/**
 * <p>类名：sysUserRole </p>
 * <p>描述：用户角色 </p>
 * <p>作者：ljb </p>
 * <p>时间：2014年12月9日 星期二 </p>
 */
public class SysUserRole implements java.io.Serializable {
	
	private static final long serialVersionUID = -1539850286570771267L;

	/** USER_ROLE_ID */
    private Long userRoleId;
	
    /** 用户ID */
    private Long userId;
	
    /** 角色ID */
    private Long roleId;
	
    /** 状态【1-有效，0-无效】 */
    private Long state;
	
    /** 创建时间 */
    private Date createTime;
	
	public SysUserRole() {}
	
	/**
	 * @return 获取 userRoleId
	 */
	public Long getUserRoleId() {
		return this.userRoleId;
	}
	
	/**
	 * @param taskId 要设置的 userRoleId
	 */
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	/**
	 * @return 获取 userId
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	/**
	 * @param taskId 要设置的 userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return 获取 roleId
	 */
	public Long getRoleId() {
		return this.roleId;
	}
	
	/**
	 * @param taskId 要设置的 roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	 * @return 获取 createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	
	/**
	 * @param taskId 要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
