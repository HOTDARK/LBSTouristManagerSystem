package com.hd.sys.service;

import java.util.List;

import com.hd.sys.entity.SysRole;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：ISysRoleService </p>
 * <p>描述：系统角色业务层接口类 </p>
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public interface SysRoleService   extends BaseService<SysRole, Long>{

	/**
	 * <p>描述：根据机构ID查询角色列表 </p>
	 * <p>日期：2014-12-10 下午3:49:05 </p>
	 * @param orgId 机构ID
	 * @return 根据机构ID查询角色列表
	 */
	List<SysRole> findRoleListByOrgId(SysRole SysRole) throws Exception;
	
	/**
	 * <p>描述：根据用户ID查询角色列表 </p>
	 * <p>日期：2014-12-10 下午3:49:05 </p>
	 * @param userId userID
	 * @return 根据用户ID查询角色列表
	 */
	List<SysRole> findRoleListByUserId(Long userId) throws Exception;
	
	/**
	 * <p>描述：判断在同级机构下的角色名称是否存在 </p>
	 * <p>日期：2014-12-13 上午9:53:58 </p>
	 * @param role 角色对象
	 * @return 如果返回null代表不存在，不为空代表存在
	 */
	SysRole findExistsRoleNameByOrgId(SysRole role) throws Exception;

	/**
	 * <p>描述：保存或修改角色 </p>
	 * <p>日期：2014-12-13 上午9:52:15 </p>
	 * @param role 保存或修改角色对象
	 */
	void addOrUpdate(SysRole role) throws Exception;

	/**
	 * <p>描述：根据角色ID更新角色状态 </p>
	 * <p>日期：2014-12-13 上午9:52:53 </p>
	 * @param role 要更新的角色对象
	 */
	void updateByRoleId(SysRole role) throws Exception;

	/**
	 * <p>描述：保存角色与用户关联 </p>
	 * <p>日期：2014-12-15 下午12:57:05 </p>
	 * @param role 角色对象
	 */
	void addUserByRoleId(SysRole role) throws Exception;

}
