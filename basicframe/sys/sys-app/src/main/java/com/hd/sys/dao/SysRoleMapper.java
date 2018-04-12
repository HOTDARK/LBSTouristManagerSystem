package com.hd.sys.dao;

import java.util.List;

import com.hd.sfw.dao.mybatis.BaseMapper;
import com.hd.sys.entity.SysRole;

/**
 * <p>类名：ISysRoleDAO </p>
 * <p>描述：系统角色数据访问层接口类 </p>
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public interface SysRoleMapper extends BaseMapper<SysRole, Long> {

	/**
	 * <p>描述：根据机构ID查询角色列表 </p>
	 * <p>日期：2014-12-10 下午3:49:05 </p>
	 * @param orgId 机构ID
	 * @return 根据机构ID查询角色列表
	 */
	List<SysRole> findRoleListByOrgId(SysRole SysRole) throws Exception;

	/**
	 * <p>描述：根据机构ID更新角色状态 </p>
	 * <p>日期：2014-12-12 下午10:55:06 </p>
	 * @param role 角色对象
	 */
	void updateByOrgId(SysRole role) throws Exception;

	/**
	 * <p>描述：判断在同级机构下的角色名称是否存在 </p>
	 * <p>日期：2014-12-13 上午9:53:58 </p>
	 * @param role 角色对象
	 * @return 如果返回null代表不存在，不为空代表存在
	 */
	SysRole findExistsRoleNameByOrgId(SysRole role) throws Exception;
	
	/**
	 * <p>描述：根据角色ID更新角色状态 </p>
	 * <p>日期：2014-12-13 上午9:52:53 </p>
	 * @param role 要更新的角色对象
	 */
	void updateByRoleId(SysRole role) throws Exception;

	/**
	 * <p>描述：根据角色ID删除用户角色关联 </p>
	 * <p>日期：2014-12-15 下午1:01:52 </p>
	 * @param roleId 角色ID
	 */
	void deleteUserByRoleId(Long roleId) throws Exception;

	/**
	 * <p>描述：批量保存角色与用户关联 </p>
	 * <p>日期：2014-12-15 下午1:05:33 </p>
	 * @param list 角色
	 */
	void saveUserByRoleId(List<SysRole> list) throws Exception;

	/**
	 * <p>描述：根据用户ID查询角色列表 </p>
	 * <p>日期：2014-12-10 下午3:49:05 </p>
	 * @param userId userID
	 * @return 根据用户ID查询角色列表
	 */
	List<SysRole> findRoleListByUserId(Long userId) throws Exception;

}
