package com.hd.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sys.dao.SysFuncMapper;
import com.hd.sys.dao.SysOrgMapper;
import com.hd.sys.dao.SysPermitMapper;
import com.hd.sys.dao.SysRoleMapper;
import com.hd.sys.dao.SysUserMapper;
import com.hd.sys.entity.SysFunc;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysPermit;
import com.hd.sys.entity.SysRole;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysOrgService;

/**
 * <p>类名：SysOrgService </p>
 * <p>描述：组织机构业务层实现类 </p>
 * <p>作者：somnuscy </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
@Service
public class SysOrgServiceImpl implements SysOrgService {

	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysPermitMapper sysPermitMapper;
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	//新增机构按钮FUNCTION_ID
	private final String ADD_ORG_FUNCTION_CODE = "001001001";
	
	@Override
	public List<SysOrg> getOrgTree(SysOrg org, boolean flag) throws Exception {
		List<SysOrg> orgList = findByCondition(org);
		if(flag){
			SysOrg sysOrg = new SysOrg();
			sysOrg.setCreateTime(new Date());
			sysOrg.setOrgId(0L);
			sysOrg.setParentOrgId(0L);
			sysOrg.setOrgName("组织机构");
			sysOrg.setOrgType(-1L);
			sysOrg.setTypeName("根机构");
			orgList.add(0, sysOrg);
		}
		return orgList;
	}
	
	@Override
	public List<SysOrg> getAllOrgTree(SysOrg org, boolean flag) {
		List<SysOrg> orgList = sysOrgMapper.getAllOrgTree(org);
		if(flag){
			SysOrg sysOrg = new SysOrg();
			sysOrg.setCreateTime(new Date());
			sysOrg.setOrgId(0L);
			sysOrg.setParentOrgId(0L);
			sysOrg.setOrgName("组织机构");
			sysOrg.setOrgType(-1L);
			sysOrg.setTypeName("根机构");
			orgList.add(0, sysOrg);
		}
		return orgList;
	}

	@Override
	public Map<String, Object> addOrUpdateOrg(SysOrg org, List<SysRole> roleList) throws Exception {
		Map<String, Object> rel = new HashMap<String, Object>();
		if(org.getOrgId()==null || org.getOrgId()==0 ) {
			// 上级机构对象信息
			SysOrg superOrg = null;
			// 新增
			if(org.getParentOrgId()==null || org.getParentOrgId()==0L) {
				superOrg = sysOrgMapper.findTopOrg();
				org.setParentOrgId(0L);
				org.setDeep(1L);
			} else {
				SysOrg relOrg =  findById(org.getParentOrgId());
				if("sub".equals(org.getAddMethod())) {
					// 下级
					superOrg = relOrg;
					org.setDeep(relOrg.getDeep()+1);
				} else {
					// 平级
					superOrg = findById(relOrg.getParentOrgId());
					org.setParentOrgId(relOrg.getParentOrgId());
					org.setDeep(relOrg.getDeep());
				}
			}
			// 按规则重新生成orgCode
			if (superOrg != null) {
				// 根据上级机构ID获取其下最大机构编码
				String maxOrgCode = sysOrgMapper.findMaxOrgCodeByPoi(superOrg.getOrgId());
				if (StringUtils.isBlank(maxOrgCode)) {
					org.setOrgCode(superOrg.getOrgCode().concat("01"));
				} else {
					String maxCode = maxOrgCode.substring(superOrg.getOrgCode().length());
					int code = Integer.parseInt(maxCode) + 1;
					// 0 代表前面补充0  
					// 2 代表长度为2
					// d 代表参数为正数型
					maxCode = String.format("%02d", code);
					org.setOrgCode(superOrg.getOrgCode().concat(maxCode));
				}
			}
			org.setCreateTime(new Date());
			org.setState(1L);
			org.setOrgLeafNode(1L);
			save(org);
			Long orgId = org.getOrgId();
			
			// 保存组织机构信息成功后设置超级管理员的权限
			if(!roleList.isEmpty() && StringUtils.isNotBlank(orgId.toString())) {
				List<SysPermit> permissionList = new ArrayList<SysPermit>();
				SysFunc sysFunction = new SysFunc();
				sysFunction.setFunctionCode(ADD_ORG_FUNCTION_CODE);
				Long functionId = sysFuncMapper.getFuncIdByCode(sysFunction);//查询添加部门功能对应的功能ID
				for (SysRole sysRole : roleList) {
					boolean isExists = false;
					List<SysPermit> roleP = sysPermitMapper.findFuncPermissionByRoleId(sysRole.getRoleId());
					for (SysPermit sysPermission : roleP) {
						if(sysPermission.getFunctionId().longValue()==functionId.longValue()) {//判断当前角色是否有新增机构权限
							isExists = true;
							break;
						}
					}
					if(isExists) {
						SysPermit rolePerm = new SysPermit();
						rolePerm.setRoleId(sysRole.getRoleId()); // 当前角色ID
						rolePerm.setOrgId(org.getOrgId());
						rolePerm.setCreateTime(new Date());
						rolePerm.setState(1L);
						permissionList.add(rolePerm);
						if (sysRole.getRoleId() != 1L) {
							SysPermit adminRole = new SysPermit();
							adminRole.setRoleId(1L); // 超级管理员
							adminRole.setOrgId(org.getOrgId());
							adminRole.setCreateTime(new Date());
							adminRole.setState(1L);
							permissionList.add(adminRole);
						}
					}
				}
				//不为空则保存权限
				if(permissionList != null && permissionList.size() > 0) {
					sysPermitMapper.saveOrgPermissions(permissionList);
					rel.put("isPerm", true);
				} else {
					rel.put("isPerm", false);
				}
			}
		} else {
			//修改
			updateById(org);
			rel.put("isPerm", false);
		}
		rel.put("sysOrg", org);
		return rel;
	}

	@Override
	public List<SysOrg> findOrgByUserId(Long ogrId) throws Exception {
		return sysOrgMapper.getOrgbyUserId(String.valueOf(ogrId));
	}

	@Override
	public SysOrg doFindOrganizationName(SysOrg org) throws Exception {
		SysOrg orgsub = null;
		SysOrg orgbrother = null;
		SysOrg orgedit = null;
		//新增下级
		if("sub".equals(org.getAddMethod())) {
			org.setParentOrgId(org.getParentOrgId());
			orgsub = sysOrgMapper.doFindOrganizationName(org);
			return orgsub;
		
		}//新增平级
		else if("brother".equals(org.getAddMethod())){
			SysOrg relOrg =  findById(org.getParentOrgId());
			if(relOrg != null) {
				org.setParentOrgId(relOrg.getParentOrgId());
			}
			orgbrother = sysOrgMapper.doFindOrganizationName(org);
			return orgbrother;
		}//当为修改的时候addmehod传过来的为空
		else {
			org.setOrgId(org.getOrgId());
			org.setParentOrgId(org.getParentOrgId());
			orgedit = sysOrgMapper.doFindOrganizationName(org);
			return orgedit;
		}
	}

	@Override
	public void optionStateByOrgId(SysOrg org) throws Exception {
		// 修改机构下的人员
		SysUser user = new SysUser();
		user.setOrgId(org.getOrgId());
		user.setState(org.getState());
		sysUserMapper.updateByOrgId(user);
		// 修改机构下的角色
		SysRole role = new SysRole();
		role.setOrgId(org.getOrgId());
		role.setState(org.getState());
		sysRoleMapper.updateByOrgId(role);
		// 修改机构
		sysOrgMapper.updateByOrgId(org);
		// 获取机构下的子机构
		List<SysOrg> sysOrgs = sysOrgMapper.getChildOrgIds(org.getOrgId());
		if (CollectionUtils.isNotEmpty(sysOrgs)) {
			for (SysOrg sysOrg : sysOrgs) {
				sysOrg.setState(org.getState());
				optionStateByOrgId(sysOrg);
			}
		}
	}

	@Override
	public boolean getParentOrgState(Long orgId) throws Exception {
		List<SysOrg> lst = sysOrgMapper.getParentOrgState(orgId);
		if(lst.get(0) == null) {
			return true;
		}
		Long parentState = lst.get(0).getState();
		if(parentState == null || parentState == 1) {
			return true;
		}  else {
			return false;
		}
	}

	@Override
	public List<SysOrg> getOrgData(SysOrg sysOrg) throws Exception {
		return sysOrgMapper.getOrgData(sysOrg);
	}

//	@Override
//	public SysOrg getOrgByName(SysOrg sysOrg) throws Exception {
//		return sysOrgMapper.getOrgByName(sysOrg);
//	}

	@Override
	public Long getOrgIdByCode(SysOrg sysOrg) throws Exception {
		return sysOrgMapper.getOrgIdByCode(sysOrg);
	}

//	@Override
//	public SysOrg getSysOrgByUserId(Long userId) throws Exception {
//		return sysOrgMapper.getSysOrgByUserId(userId);
//	}

//	@Override
//	public SysOrg getSysOrgByCode(String findPropertiesKey) {
//		return sysOrgMapper.getSysOrgByCode(findPropertiesKey);
//	}

	@Override
	public SysOrg findById(Long id) throws Exception {
		return sysOrgMapper.findByPK(id);
	}

	@Override
	public List<SysOrg> findByCondition(SysOrg entity) throws Exception {
		return sysOrgMapper.findByCondition(entity);
	}

	@Override
	public void save(SysOrg entity) throws Exception {
		sysOrgMapper.insert(entity);
	}

	@Override
	public void updateById(SysOrg entity) throws Exception {
		sysOrgMapper.updateByPK(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		sysOrgMapper.deleteByPK(id);
	}

	@Override
	public List<SysOrg> getOrgByUserrole(Long userId) throws Exception {
		List<SysOrg> orgs = new ArrayList<SysOrg>();
		List<SysRole> roleList = sysRoleMapper.findRoleListByUserId(userId);
		if(CollectionUtils.isNotEmpty(roleList)){
			for (SysRole sysRole : roleList) {
				List<SysPermit> permissions = sysPermitMapper.findOrgPermissionByRoleId(sysRole.getRoleId());
				if(CollectionUtils.isNotEmpty(permissions)){
					for (SysPermit sysPermission : permissions) {
						SysOrg sorg = findById(sysPermission.getOrgId());
						if(null != sorg){
							orgs.add(sorg);
						}
					}
				}
			}
		}
		return orgs;
	}

//	@Override
//	public List<SysOrg> getOrgDataByType(Long orgType) {
//		SysOrg sysOrg = new SysOrg();
//		sysOrg.setState(1L);
//		sysOrg.setOrgType(orgType);
//		try {
//			return getOrgData(sysOrg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public Pagination<SysOrg> findPageByCondition(SysOrg entity) throws Exception {
		int num = sysOrgMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<SysOrg>();
		} else {
			List<SysOrg> orgs = sysOrgMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<>(num, orgs);
		}
	}

	@Override
	public SysOrg getSysOrgByCode(String orgCode) throws Exception {
		return sysOrgMapper.getSysOrgByCode(orgCode);
	}

	@Override
	public List<SysOrg> findSysOrgByCondition(SysOrg entity) throws Exception {
		return sysOrgMapper.findSysOrgByCondition(entity);
	}
}
