package com.hd.sys.service;

import java.util.List;
import java.util.Map;

import com.hd.sys.entity.SysPermit;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：ISysPermissionService </p>
 * <p>描述：系统权限管理业务层接口类 </p>
 * <p>作者：xmh </p>
 * <p>时间：2014年12月15日 星期一 </p>
 */
public interface SysPermitService extends BaseService<SysPermit, Long>  {

	/**
	 * <p>描述：根据用户ID查询机构权限 </p>
	 * <p>日期：2014-12-15 下午3:29:35 </p>
	 * @param userId 用户ID
	 * @return 机构权限
	 */
	List<SysPermit> findOrgPermissionByUserId(Long userId) throws Exception;
	
	/**
	 * <p>描述：根据用户ID查询功能权限 </p>
	 * <p>日期：2014-12-15 下午3:29:35 </p>
	 * @param userId 用户ID
	 * @return 功能权限
	 */
	List<SysPermit> findFuncPermissionByUserId(Long userId) throws Exception;
	
	/**
	 * <p>描述：根据角色ID查询机构权限 </p>
	 * <p>日期：2014-12-15 下午3:29:35 </p>
	 * @param roleId 角色ID
	 * @return 机构权限
	 */
	List<SysPermit> findOrgPermissionByRoleId(Long roleId) throws Exception;
	
	/**
	 * <p>描述：根据角色ID查询功能权限 </p>
	 * <p>日期：2014-12-15 下午3:29:35 </p>
	 * @param roleId 角色ID
	 * @return 功能权限
	 */
	List<SysPermit> findFuncPermissionByRoleId(Long roleId) throws Exception;
	
	/**
	 * <p>描述：根据用户id查询用户权限数据 </p>
	 * <p>日期：2014-12-10 上午08:34:50 </p>
	 * @param userId 用户id
	 * @return 返回Map,key为角色ID,value为String[],[0]-functionIds,[1]-orgIds
	 * @throws Exception
	 */
	public Map<String, String[]> getPermissionByUserId(Long userId) throws Exception;
	
	/**
	 * <p>描述：保存机构权限 </p>
	 * <p>日期：2014-12-15 下午3:33:24 </p>
	 * @param permission 权限
	 */
	void saveOrgPermission(SysPermit permission) throws Exception;
	
	/**
	 * <p>描述：保存功能权限 </p>
	 * <p>日期：2014-12-15 下午3:33:24 </p>
	 * @param permission 权限
	 */
	void saveFuncPermission(SysPermit permission) throws Exception;
	
	/**
	 * <p>描述：批量保存机构权限 </p>
	 * <p>日期：2014-12-15 下午3:33:24 </p>
	 * @param list 权限
	 */
	void saveOrgPermissions(List<SysPermit> list) throws Exception;
	
	/**
	 * <p>描述：批量保存功能权限 </p>
	 * <p>日期：2014-12-15 下午3:33:24 </p>
	 * @param list 权限
	 */
	void saveFuncPermissions(List<SysPermit> list) throws Exception;
	
	/**
	 * <p>描述：根据角色ID删除机构权限 </p>
	 * <p>日期：2014-12-15 下午3:38:33 </p>
	 * @param permission 权限
	 */
	void deleteOrgByIdAndRoleId(SysPermit permission) throws Exception;

	/**
	 * <p>描述：根据角色ID删除功能权限 </p>
	 * <p>日期：2014-12-15 下午3:38:36 </p>
	 * @param permission 权限
	 */
	void deleteFuncByIdAndRoleId(SysPermit permission) throws Exception;

	/**
	 * <p>描述：查询功能树-配置权限 </p>
	 * <p>日期：2014-12-15 下午7:46:40 </p>
	 * @param permission 权限	
	 * @return 查询功能树-配置权限
	 */
	List<SysPermit> getFunctionTreeAll(SysPermit permission) throws Exception;

	/**
	 * <p>描述：查询机构树-配置权限 </p>
	 * <p>日期：2014-12-17 下午3:14:49 </p>
	 * @param permission
	 * @return 查询机构树-配置权限
	 */
	List<SysPermit> getOrgTreeAll(SysPermit permission) throws Exception;

}
