package com.hd.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.DateUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.core.online.OnlineSession;
import com.hd.sys.core.online.OnlineSysUser;
import com.hd.sys.core.online.OnlineUserInfo;
import com.hd.sys.core.online.OnlineUserManager;
import com.hd.sys.dao.SysOrgMapper;
import com.hd.sys.dao.SysUserMapper;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysUser;
import com.hd.sys.entity.SysUserRole;
import com.hd.sys.service.SysUserService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>类名：SysUserService </p>
 * <p>描述：系统角色业务层实现类 </p>
 * <p>作者：somnuscy </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
@SuppressWarnings("unchecked")
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Autowired
	private OnlineUserManager onlineUserManager;

	@Override
	public Pagination<SysUser> getSysUserListByOrgId(SysUser sysUser) throws Exception {
		Integer rowCount = sysUserMapper.findNumByCondition(sysUser);
		List<SysUser> rowList = new ArrayList<SysUser>();
		if(rowCount>0){
			rowList = sysUserMapper.findByPage(sysUser, sysUser.getiDisplayStart(), sysUser.getiDisplayLength());
		}
		Pagination<SysUser> page = new Pagination<SysUser>(rowCount, rowList);
		return page;
	}
	
	@Override
	public Pagination<OnlineUserInfo> getOnlineUsers(OnlineUserInfo userInfo) {
		Integer count=0;
		List<OnlineUserInfo> all=new ArrayList<OnlineUserInfo>();
		List<OnlineSysUser> users = onlineUserManager.getOnlineUser();
		if (users!=null&&users.size()>0) {
			for (OnlineSysUser user : users) {
				for(Entry<String, OnlineSession> entry : user.getSessions().entrySet()) {
					OnlineUserInfo item=new OnlineUserInfo();
					item.setIp(entry.getValue().getFrom());
					item.setId(entry.getKey());
					item.setUserName(user.getUser().getUserName());
					item.setUserId(user.getUser().getUserId().toString());
					item.setLoginTime(DateUtils.formatStr(entry.getValue().getLoginDate(), DateUtils.SDF_YYYY_MM_DD_HH_MM_SS));
					all.add(item);
				}
			}
		}
		List<OnlineUserInfo> query=new ArrayList<OnlineUserInfo>();
		if (all!=null&&all.size()>0) {
			if (StringUtils.isNotBlank(userInfo.getUserName())) {
				for (OnlineUserInfo onlineUserInfo : all) {
				 	if (userInfo.getUserName().trim().equals(onlineUserInfo.getUserName())) {
				 		query.add(onlineUserInfo);
					}
				}
			  if (query!=null&&query.size()>0) {
				  count=query.size();
			 }else{
				 count=0;
			 }
			  all=query;
			}else{
				count=all.size();
			}
		}
		count=all.size();
		int start=userInfo.getiDisplayStart();
		int limit=userInfo.getiDisplayLength();
		Pagination<OnlineUserInfo> page =null;
		if (count<=limit) {
			  page =  new Pagination<OnlineUserInfo>(count, all);
		}else{
			 int temp=0;
			 List<OnlineUserInfo> allUserInfos=new ArrayList<OnlineUserInfo>();
			 for (int i = start; i <count; i++) {
				 if (limit==temp) {
					break;
				 }
				 temp++;
				 allUserInfos.add(all.get(i));
			 }
			 page =  new Pagination<OnlineUserInfo>(count, allUserInfos);
		}
		return page;
	}

	@Override
	public List<SysUser> getRolesByUserId(Long userId) throws Exception {
		return sysUserMapper.getRolesByUserId(userId);
	}

	@Override
	public List<SysUser> getRolesByOrgId(Long orgId) throws Exception {
		return sysUserMapper.getRolesByOrgId(orgId);
	}
    
	public void batchInsertRoles (Long userId, List<String> roleIds) throws Exception{
		List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
		for (String roleId : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setState(1L);
			sysUserRole.setRoleId(Long.parseLong(roleId));
			sysUserRole.setCreateTime(new Date());
			userRoleList.add(sysUserRole);
		}
		sysUserMapper.batchInsertRoles(userRoleList);
	}

	@Override
	public int updateStateById(SysUser user) throws Exception {
		return sysUserMapper.updateStateById(user);
	}

	@Override
	public void saveOrUpdateUser(HttpServletRequest request,SysUser user) throws Exception {
//		MultipartFile file = FileUtil.getUploadFile(request);
//		String oldImgUrl = request.getParameter("oldImageUri");
//		if (file != null) {
//			String fileUploadDir = PropertiesUtils.getProperty(this.getClass(),"common" ,"fileUploadDir");
//			File dest = new File(FileUtil.getUploadFileDir(file,fileUploadDir,"photos"));
//			if (!dest.exists()) {
//				dest.mkdirs();
//			}
//			file.transferTo(dest);
//			String dbUrl = "photos/" + dest.getName();
//			dbUrl.replace("\\", "/");
//			user.setUserPhoto(dbUrl);
//			if(null!=oldImgUrl && !oldImgUrl.equals("")){
//				FileUtil.deleteFile(fileUploadDir+"/"+oldImgUrl);
//			}
//		}
		if (user != null && user.getUserId() != null) {
			sysUserMapper.updateByPK(user);
			HashMap<String, Object> hm = (HashMap<String, Object>) request.getSession().getAttribute("sessionMap");
			if(hm != null) {
				SysUser currentUser = ((SysUser)hm.get("userInfo"));
				//如果改的是当前用户的资料，就更新session里面的信息
				if (user.getUserId().equals(currentUser.getUserId())) {
					currentUser.setUserPhoto(user.getUserPhoto());
				}
			}
		} else {
			user.setCreateTime(new Date());
			if(StringUtils.isBlank(user.getUserPwd())) {
				user.setUserPwd(PropertiesUtils.getProperty(this.getClass(), "common", "defaultPwd"));
				user.setPwdState(SysUser.PWD_STATE_INIT);
			} else {
				user.setPwdState(SysUser.PWD_STATE_CUSTOM);
			}
			sysUserMapper.insert(user);
		}
	}

	@Override
	public SysUser getUserInfoById(Long userId) throws Exception {
		SysUser user = sysUserMapper.findByPK(userId);
		if(null!=user){
			List<String> orgNameList = new ArrayList<String>();
			SysOrg org = sysOrgMapper.getParentOrgByOrgId(user.getOrgId());//查询用户直属部门
			while (org!=null) {//循环查询用户上级部门,直到没有上级部门时结束循环
				orgNameList.add(org.getOrgName());
				org = sysOrgMapper.getParentOrgByOrgId(org.getParentOrgId());
			}
			StringBuilder sb = new StringBuilder();
			for (int i = orgNameList.size()-1; i >=0 ; i--) {
				if(i<orgNameList.size()-1){
					sb.append("->");
				}
				sb.append(orgNameList.get(i));
			}
			user.setOrgNames(sb.toString());
		}	
		return user;
	}
	
	@Override
	public void deleteRolesByUserId(Long userId) throws Exception {
		sysUserMapper.deleteRolesByUserId(userId);
	}

	@Override
	public void deleteRolesByUserIdAndOrgId(SysUser user) throws Exception {
		sysUserMapper.deleteRolesByUserIdAndOrgId(user);
	}

	@Override
	public void updateUserOrg(SysUser user)
			throws Exception {
		if(StringUtils.isNotBlank(user.getRoleIds())){			
			sysUserMapper.deleteRolesByUserIdAndRoles(user);//删除用户部分角色信息
		}
		if(user.getOrgId()!=null){	//如果传入了部门id,则修改用户部门信息		
			sysUserMapper.updateByPK(user);
		}
	}
	
	@Override
	public void updateByRoleIds(SysUser user) throws Exception {
		sysUserMapper.updateByRoleIds(user);
	}
	
	@Override
	public void updateUserRoles(SysUser user, String roleIdStr) throws Exception {
		if (user.getOrgId() != null) {
			deleteRolesByUserIdAndOrgId(user); //删除用户所有在该部门下的角色
		} else {
			deleteRolesByUserId(user.getUserId()); //删除用户所有角色
		}
		if (!StringUtils.isBlank(roleIdStr)) {
			String[] roleIdArr = roleIdStr.split(",");
			List<String> roleIds = CollectionUtils.array2list(roleIdArr);
			batchInsertRoles(user.getUserId(),roleIds);
		}
	}
	
	@Override
	public List<SysUser> findUserListByOrgIdOrRoleId(SysUser user) throws Exception {
		return sysUserMapper.findUserListByOrgIdOrRoleId(user);
	}
	
//	@Override
//	public Map<String, Object> userLogin(SysUser user) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		user.setUserAccount(user.getUserAccount().trim());
//		SysUser sysUser = sysUserMapper.findUserByNameAndPwd(user);
//		if(sysUser != null) {
//			map.put("loginState", "success");
//			map.put("orgInfo", sysOrgMapper.findByPK(sysUser.getOrgId()));
//			map.put("userInfo", sysUser);
//		}else{
//			map.put("loginState", "fail");
//		}
//		return map;
//	}

//	@Override
//	public Pagination<InterfaceAccount> getUserAccountByParm(
//			InterfaceAccount account) {
//		return sysUserMapper.getUserAccountByParm(account, account.getiDisplayStart(), 5);
//	}

	@Override
	public SysUser findById(Long id) throws Exception {
		return sysUserMapper.findByPK(id);
	}

	@Override
	public List<SysUser> findByCondition(SysUser entity) throws Exception {
		List<SysUser> list=sysUserMapper.findByCondition(entity);
//		String ss;
		return list;
	}

	@Override
	public void save(SysUser entity) throws Exception {
		sysUserMapper.insert(entity);
	}

	@Override
	public void updateById(SysUser entity) throws Exception {
		sysUserMapper.updateByPK(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		sysUserMapper.deleteByPK(id);
	}

	@Override
	public SysUser getSysUser(String username) {
		return sysUserMapper.getSysUser(username);
	}

	@Override
	public Pagination<SysUser> findPageByCondition(SysUser entity) throws Exception {
		Integer rowCount = sysUserMapper.findNumByCondition(entity);
		List<SysUser> rowList = new ArrayList<SysUser>();
		if(rowCount>0){			
			rowList = sysUserMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
		}
		return new Pagination<SysUser>(rowCount, rowList);
	}
	
	@Override
	public List<SysUser> findByUserAccounts(String userAccounts) throws Exception {
		List<SysUser> list = sysUserMapper.findByUserAccounts(userAccounts);
		return list;
	}

	@Override
	public List<SysUser> queryOutByCondition(String userAccounts, Integer orgId) throws Exception {
		return sysUserMapper.queryOutByCondition(userAccounts, orgId);
	}
	@Override
	public List<SysUser> findByOrgCode(String userAccounts) throws Exception {
		List<SysUser> list = sysUserMapper.findByUserAccounts(userAccounts);
		return list;
	}

	@Override
	public String findSysUserName(String userAccount) throws Exception {
		return sysUserMapper.findSysUserName(userAccount);
	}

	@Override
	public List<SysUser> findByCompanyCode(String companyCode) throws Exception {
		return sysUserMapper.findByCompanyCode(companyCode);
	}
	
}
