package com.hd.sys.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysRole;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：ISysOrgService </p>
 * <p>描述：组织机构业务层接口类 </p>
 * <p>作者：somnuscy </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
public interface SysOrgService extends BaseService<SysOrg, Long>  {
	/**
	 * <p>描述：查询组织机构数据 </p>
	 * <p>日期：2014-12-9 上午09:49:17 </p>
	 * @param org 组织机构实体类,如果查询某一个组织机构的下属机构则设置parentOrgId,如果查询所有部门,则传入参数null
	 * @param flag 表示是否需要添加一个跟节点,为true表示添加,false表示不添加
	 * @return 返回组织机构集合
	 * @throws Exception
	 */
	public List<SysOrg> getOrgTree(SysOrg org, boolean flag) throws Exception;
	/**
	 * <p>描述：获取用户当前所在机构 </p>
	 * <p>日期：2014-12-9 下午4:57:26 </p>
	 * @param ogrId 机构ID
	 * @return 获取用户当前所在机构
	 */
	public List<SysOrg> findOrgByUserId(Long ogrId) throws Exception;
	/**
	 * <p>描述：保存或修改组织机构 </p>
	 * <p>日期：2014-12-9 下午1:51:07 </p>
	 * @param org 组织机构对象
	 * @param roleList 角色
	 * @return 机构对象
	 */
	public Map<String, Object> addOrUpdateOrg(SysOrg org, List<SysRole> roleList) throws Exception;
	/**
	 * <p>描述：判断组织机构是否重复 </p>
	 * <p>日期：2014-12-9 下午4:58:44 </p>
	 * @param org 机构对象
	 * @return 机构对象
	 */
	public SysOrg doFindOrganizationName(SysOrg org) throws Exception;
	/**
	 * <p>描述：激活机构前判断 </p>
	 * <p>说明: 判断是否父节点为激活状态，如果不是，不能激活</p>
	 * @param orgId 机构ID
	 * @return true：是，false：否
	 */
	public boolean getParentOrgState(Long orgId) throws Exception;
	/**
	 * <p>描述：更新组织机构为激活或冻结 </p>
	 * <p>日期：2014-12-12 下午11:06:05 </p>
	 * @param org 要激活或冻结的机构
	 */
	public void optionStateByOrgId(SysOrg org) throws Exception;
	/**
	 * 查询组织机构信息,用作下拉选择
	 * @param sysOrg 组织机构
	 * @return 返回符合条件的组织机构集合
	 * @throws Exception
	 */
	public List<SysOrg> getOrgData(SysOrg sysOrg) throws Exception;
	/**
	 * 根据组织机构实体参数查询对应的组织机构ID
	 * @param sysOrg 组织机构实体
	 * @return 返回对应的机构ID,如果没有则返回null
	 * @throws Exception
	 */
	Long getOrgIdByCode(SysOrg sysOrg) throws Exception;
	/**
	 * 根据用户ID查出所有角色。再根据角色的机构ID查询机构信息
	 * @param userId
	 * @return
	 */
	public List<SysOrg> getOrgByUserrole(Long userId) throws Exception ;

	public List<SysOrg> getAllOrgTree(SysOrg sysOrganization, boolean b);
	
	public SysOrg getSysOrgByCode(String orgCode) throws Exception;
	
//	/**
//	 * 通过用户ID获取机构
//	 */
//	SysOrg getSysOrgByUserId(Long userId) throws Exception;

//	public SysOrg getSysOrgByCode(String findPropertiesKey);
//	/**
//	 * 通过组织机构名称查询组织机构信息
//	 * @param sysOrg 组织机构
//	 * @return 返回组织机构信息
//	 * @throws Exception
//	 */
//	SysOrg getOrgByName(SysOrg sysOrg) throws Exception;
//	/**
//	 * 根据机构类型查询有效的机构信息
//	 * @param orgType
//	 * @return
//	 * @throws Exception
//	 */
//	public List<SysOrg> getOrgDataByType(Long orgType);
    /**
     * 条件查询机构信息
     * @author wh
     * @date 2017年8月15日
     * @param entity
     * @return
     * @throws Exception
     */
	public List<SysOrg> findSysOrgByCondition(SysOrg entity) throws Exception;
}
