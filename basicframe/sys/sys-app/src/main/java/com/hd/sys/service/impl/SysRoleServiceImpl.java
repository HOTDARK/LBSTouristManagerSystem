package com.hd.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.dao.SysRoleMapper;
import com.hd.sys.entity.SysRole;
import com.hd.sys.service.SysRoleService;

/**
 * <p>类名：SysRoleService </p>
 * <p>描述：系统角色业务层实现类 </p>
 * <p>作者：cb </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> findRoleListByOrgId(SysRole SysRole) throws Exception {
		return sysRoleMapper.findRoleListByOrgId(SysRole);
	}
	
	@Override
	public List<SysRole> findRoleListByUserId(Long userId) throws Exception {
		return sysRoleMapper.findRoleListByUserId(userId);
	}

	@Override
	public void addOrUpdate(SysRole role) throws Exception {
		if(role != null){
			role.setCreateTime(new Date());			
			if(role.getRoleId() == null || role.getRoleId() == 0) {
				role.setState(1L);
				sysRoleMapper.insert(role);
			} else {
				sysRoleMapper.updateByPK(role);
			}
		}
	}

	@Override
	public SysRole findExistsRoleNameByOrgId(SysRole role) throws Exception {
		return sysRoleMapper.findExistsRoleNameByOrgId(role);
	}

	@Override
	public void updateByRoleId(SysRole role) throws Exception {
		sysRoleMapper.updateByRoleId(role);
	}
	
	@Override
	public void addUserByRoleId(SysRole role) throws Exception {
		List<SysRole> list = new ArrayList<SysRole>();
		//先删除原有的用户关联
		sysRoleMapper.deleteUserByRoleId(role.getRoleId());
		if(StringUtils.isNotBlank(role.getUserIds().toString())){
			String[] userIds = role.getUserIds().split(",");
			for(int i=0; i<userIds.length; i++) {
				SysRole r = new SysRole();
				r.setRoleId(role.getRoleId());
				r.setUserId(Long.valueOf(userIds[i]));
				r.setState(1L);
				r.setCreateTime(new Date());
				list.add(r);
			}
			sysRoleMapper.saveUserByRoleId(list);
		}
	}

	@Override
	public SysRole findById(Long id) throws Exception {
		return sysRoleMapper.findByPK(id);
	}

	@Override
	public List<SysRole> findByCondition(SysRole entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SysRole entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateById(SysRole entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination<SysRole> findPageByCondition(SysRole entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    
}
