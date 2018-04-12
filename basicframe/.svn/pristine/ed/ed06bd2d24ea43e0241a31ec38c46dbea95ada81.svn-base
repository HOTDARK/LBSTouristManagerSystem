package com.hd.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.core.online.OnlineUserInfo;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：SysUserService </p>
 * <p>描述：系统角色业务层接口类 </p>
 * <p>作者：somnuscy </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public interface SysUserService extends BaseService<SysUser,Long> {

	/**
	 * 
	 * <p>描述：根据部门ID查询部门下的所有人员集合(分页) </p>
	 * <p>日期：2014-12-8 下午03:30:50 </p>
	 * @param sysUser 系统人员对象,包含部门ID及分页参数
	 * @return 返回分页对象,包含数据集合和总记录条数
	 * @throws Exception
	 */
	public Pagination<SysUser> getSysUserListByOrgId(SysUser sysUser) throws Exception;

	/**
	 * <p>描述:根据用户id查询该用户的角色和角色所属的机构 </p>
	 * <p>日期：2014-12-8 下午5:26:49 </p>
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getRolesByUserId(Long userId) throws Exception;
	
	/**
	 * <p>描述:根据机构id查询角色 </p>
	 * <p>日期：2014-12-8 下午5:26:49 </p>
	 * @param orgId 机构ID
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getRolesByOrgId(Long orgId) throws Exception;
	
	/**
	 * <p>描述:批量插入角色 </p>
	 * <p>日期：2014-12-8 下午5:26:49 </p>
	 * @param userId
	 * @param 角色id
	 * @return
	 * @throws Exception
	 */
	public void batchInsertRoles(Long userId, List<String> roleIds) throws Exception;
	
	/**
	 * 
	 * <p>描述：修改用户状态 </p>
	 * <p>日期：2014-12-11 上午10:24:14 </p>
	 * @param user 用户实体类
	 * @return 返回受影响行数
	 * @throws Exception
	 */
	public int updateStateById(SysUser user) throws Exception;
	
	/**
	 * <p>描述:修改或者新增用户 </p>
	 * <p>日期：2014-12-11 下午1:56:03 </p>
	 * @param user
	 * @throws Exception
	 */
	public void saveOrUpdateUser(HttpServletRequest request,SysUser user) throws Exception;
	
	/**
	 * 
	 * <p>描述：根据用户id查询用户信息(包含级联的部门信息组成的字符串) </p>
	 * <p>日期：2014-12-11 下午03:49:00 </p>
	 * @param userId 用户id
	 * @return 返回用户详细信息
	 * @throws Exception
	 */
	public SysUser getUserInfoById(Long userId) throws Exception;
	
	/**
	 * <p>描述:根据用户id删除所有角色 </p>
	 * <p>日期：2014-12-12 上午9:35:02 </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void deleteRolesByUserId(Long userId) throws Exception;
	
	/**
	 * <p>描述:删除该用户在某个机构下的所有角色 </p>
	 * <p>日期：2014-12-12 上午9:53:21 </p>
	 * @param map
	 * @throws Exception
	 */
	public void deleteRolesByUserIdAndOrgId(SysUser user) throws Exception;
	
	/**
	 * 
	 * <p>描述：用户组织机构变更,同时对角色信息进行变更 </p>
	 * <p>日期：2014-12-13 下午03:44:36 </p>
	 * @param user 用户实体类
	 * @throws Exception
	 */
	public void updateUserOrg(SysUser user) throws Exception;
	
	/**
	 * <p>描述：根据角色ID更新用户状态 </p>
	 * <p>日期：2014-12-13 上午10:05:24 </p>
	 * @param user 用户对象
	 */
	public void updateByRoleIds(SysUser user) throws Exception;

	/**
	 * <p>描述：根据机构ID或角色ID查询用户集合 </p>
	 * <p>日期：2014-12-15 下午1:49:30 </p>
	 * @param user 用户对象
	 * @return 根据机构ID或角色ID查询用户集合
	 */
	public List<SysUser> findUserListByOrgIdOrRoleId(SysUser user) throws Exception;

//	/**
//	 * <p>描述：用户登录 </p>
//	 * <p>日期：2014-12-15 下午2:55:37 </p>
//	 * @param user 用户对象
//	 * @return 用户，机构信息
//	 */
//	public Map<String, Object> userLogin(SysUser user) throws Exception;
	
	/**
	 * <p>描述：根据角色ID更新用户状态 </p>
	 * <p>日期：2014-12-13 上午10:05:24 </p>
	 * @param user 用户对象
	 * @param roleIdStr 角色id字符串
	 */
	public void updateUserRoles(SysUser user, String roleIdStr) throws Exception;

//	public Pagination<InterfaceAccount> getUserAccountByParm(InterfaceAccount account);

	public SysUser getSysUser(String username);

	public Pagination<OnlineUserInfo> getOnlineUsers(OnlineUserInfo onlineUserInfo);
	
	public List<SysUser> findByUserAccounts(String userAccounts) throws Exception;
	//根据已选用户名和机构ID 查出为选用户
	public List<SysUser> queryOutByCondition(String userAccounts,Integer orgId) throws Exception;
	
	public List<SysUser> findByOrgCode(String userAccounts) throws Exception;
	
	/**
	 * 根据用户登录名查询用户姓名
	 * @param userAccount 用户登录名
	 * @return 用户姓名
	 */
	public String findSysUserName(String userAccount) throws Exception;
	/**
	 * 根据公司编码查询该公司用户
	 * @author wh
	 * @date 2017年10月31日
	 * @param companyCode
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> findByCompanyCode(String companyCode) throws Exception;
}
