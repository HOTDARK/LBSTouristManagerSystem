package com.hd.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sys.dao.SysPermitMapper;
import com.hd.sys.dao.SysUserMapper;
import com.hd.sys.entity.SysPermit;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysPermitService;

/**
 * <p>类名：SysPermissionService </p>
 * <p>描述：系统权限管理业务层实现类 </p>
 * <p>作者：xmh </p>
 * <p>时间：2014年12月15日 星期一 </p>
 */
@Service
public class SysPermitServiceImpl implements SysPermitService {

	@Autowired
	private SysPermitMapper sysPermitMapper;
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public List<SysPermit> findOrgPermissionByUserId(Long userId) throws Exception {
		return sysPermitMapper.findOrgPermissionByUserId(userId);
	}

	@Override
	public List<SysPermit> findFuncPermissionByUserId(Long userId) throws Exception {
		return sysPermitMapper.findFuncPermissionByUserId(userId);
	}
	
	@Override
	public List<SysPermit> findOrgPermissionByRoleId(Long roleId) throws Exception {
		return sysPermitMapper.findOrgPermissionByRoleId(roleId);
	}
	
	@Override
	public List<SysPermit> findFuncPermissionByRoleId(Long roleId) throws Exception {
		return sysPermitMapper.findFuncPermissionByRoleId(roleId);
	}

	@Override
	public Map<String, String[]> getPermissionByUserId(Long userId)
			throws Exception {
		List<SysUser> roleList = sysUserMapper.getRolesByUserId(userId);
		if(roleList == null){
			return null;//如果没查询到角色集合,则直接返回null
		}
		List<SysPermit> permissionList = null;//接收角色的功能权限/组织机构权限
		StringBuilder sb = null;//拼接功能ID/组织机构ID
		String[] permissions = null;//保存功能ID拼接字符串/组织机构ID拼接字符串
		Map<String, String[]> permissionMap = new HashMap<String, String[]>();//保存权限结果
		for (SysUser u : roleList) {
			permissions = new String[2];
			if(permissionMap.containsKey(u.getRoleId()+"")){
				continue;//表示已经处理过该角色,不在处理
			}
			permissionList = sysPermitMapper.getFuncsByRoleId(u.getRoleId());
			sb = new StringBuilder();
			for (int i = 0; i < permissionList.size(); i++) {
				if(i > 0){
					sb.append(",");
				}
				sb.append(permissionList.get(i).getFunctionId());
			}
			permissions[0] = sb.toString();//设置功能权限ID拼接字符串
			permissionList = sysPermitMapper.getOrgsByRoleId(u.getRoleId());
			sb = new StringBuilder();
			for (int i = 0; i < permissionList.size(); i++) {
				if(i > 0){
					sb.append(",");
				}
				sb.append(permissionList.get(i).getOrgId());
			}
			permissions[1] = sb.toString();//设置组织机构权限ID拼接字符串
			permissionMap.put(u.getRoleId()+"", permissions);
		}
		return permissionMap;
	}
	
	@Override
	public void saveOrgPermission(SysPermit permission) throws Exception {
		sysPermitMapper.insert(permission);
	}

	@Override
	public void saveFuncPermission(SysPermit permission) throws Exception {
		sysPermitMapper.insert(permission);
	}
	
	@Override
	public void saveOrgPermissions(List<SysPermit> list) throws Exception {
		sysPermitMapper.saveOrgPermissions(list);
	}
	
	@Override
	public void saveFuncPermissions(List<SysPermit> list) throws Exception {
		sysPermitMapper.saveFuncPermissions(list);
	}
	
	@Override
	public void deleteFuncByIdAndRoleId(SysPermit permission) throws Exception {
		sysPermitMapper.deleteFuncByIdAndRoleId(permission);
	}
	
	@Override
	public void deleteOrgByIdAndRoleId(SysPermit permission) throws Exception {
		sysPermitMapper.deleteOrgByIdAndRoleId(permission);
	}
	
	@Override
	public List<SysPermit> getFunctionTreeAll(SysPermit permission) throws Exception {
		List<SysPermit> funList = sysPermitMapper.getFunctionTreeAll(permission);
		SysPermit nosp = new SysPermit();
		nosp.setChecked(false);
		if (CollectionUtils.isNotEmpty(funList)) {
			for (SysPermit sp : funList) {
				if (sp.isChecked()) {
					nosp.setChecked(true);
					break;
				}
			}
		}
		nosp.setFunctionId(0L);
		nosp.setParentId(0L);
		nosp.setFunctionName("全部功能");
		funList.add(0, nosp);
		return funList;
	}
	
	@Override
	public List<SysPermit> getOrgTreeAll(SysPermit permission) throws Exception {
		return sysPermitMapper.getOrgTreeAll(permission);
	}

	@Override
	public SysPermit findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysPermit> findByCondition(SysPermit entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SysPermit entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateById(SysPermit entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination<SysPermit> findPageByCondition(SysPermit entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    
}
