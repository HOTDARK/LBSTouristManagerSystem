package com.hd.sys.action.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sys.core.consts.SessionConst;
import com.hd.sys.core.tags.modal.Select;
import com.hd.sys.core.utils.DontLog;
import com.hd.sys.entity.SysDict;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysDictService;
import com.hd.sys.service.SysOrgService;

/**
 * 
 * <p>类名：BaseAction </p>
 * <p>描述：基本页面控制类 </p>
 * <p>作者：cb </p>
 * <p>时间：2014-12-9 下午03:54:44 </p>
 *
 */
@Controller
@RequestMapping("/baseAction")
public class BaseAction {
	
	@Autowired
	private SysDictService sysTypeDictService;
	@Autowired
	private SysOrgService sysOrgService;
	
	/**
	 * <p>描述：页面跳转方法 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param request
	 * @param path 路径
	 */
	@RequestMapping("/browse")
	@DontLog
	public ModelAndView browse(HttpServletRequest request,String path){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(path);
        return modelAndView;
	}
	

	
	/**
	 * <p>描述：获取登录用户对象 </p>
	 * <p>日期：2014-12-18 下午3:52:17 </p>
	 * @param request HttpServletRequest对象
	 * @return 登录用户对象
	 */
	@SuppressWarnings("unchecked")
	public SysUser getLoginUser(HttpServletRequest request) {
		Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute("sessionMap");
		if(session == null){
			return null;
		}
		SysUser user = (SysUser) session.get("userInfo");
		if(user == null){
			return null;
		} else {
			return user;
		}
	}
	
	/**
	 * <p>描述：登录用户机构对象 </p>
	 * @param request HttpServletRequest对象
	 * @return 登录用户机构对象
	 */
	@SuppressWarnings("unchecked")
	public SysOrg getLoginOrg(HttpServletRequest request) {
		Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute(SessionConst.SESSION_MAP);
		if(session == null){
			return null;
		}
		return (SysOrg) session.get(SessionConst.SESSION_MAP_ORG);
	}

	/**
	 * <p>描述：获取session中用户能操作的组织机构 </p>
	 * <p>日期：2014-12-10 上午10:11:29 </p>
	 * @param request HttpServletRequest对象
	 * @return 返回能组织机构ID,多个用逗号隔开
	 */
	@SuppressWarnings("unchecked")
	public String getOrgsPermission(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}
		Object obj = session.getAttribute("roleInfo");
		if(obj == null){
			return null;
		}
		Map<String, String[]> permissionMap = (Map<String, String[]>)obj;
		List<String> orgs = new ArrayList<String>();
		for (String s : permissionMap.keySet()) {//遍历登录用户所有角色
			String[] permission = permissionMap.get(s);//某一个角色的功能权限和部门数据权限
			if(StringUtils.isBlank(permission[1])){
				continue;
			}
			String[] orgIds = permission[1].split(",");//获取部门数据权限
			for (int i = 0; i < orgIds.length; i++) {
				if(!orgs.contains(orgIds[i])){
					orgs.add(orgIds[i]);//如果不存在在结果集,则添加
				}
			}
		}
		// 将所有组织机构ID拼接成字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < orgs.size(); i++) {
			if (i == orgs.size() -1) {
				sb.append(orgs.get(i));
			} else {
				sb.append(orgs.get(i));
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * <p>描述：获取session中用户能操作的功能 </p>
	 * <p>日期：2014-12-10 上午10:11:29 </p>
	 * @param request HttpServletRequest对象
	 * @return 返回能组织机构ID,多个用逗号隔开
	 */
	@SuppressWarnings("unchecked")
	public String getFuncsPermission(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}
		Object obj = session.getAttribute("roleInfo");
		if(obj == null){
			return null;
		}
		Map<String, String[]> permissionMap = (Map<String, String[]>)obj;
		List<String> funcs = new ArrayList<String>();
		for (String s : permissionMap.keySet()) {//遍历登录用户所有角色
			String[] permission = permissionMap.get(s);//某一个角色的功能权限和部门数据权限
			if(StringUtils.isBlank(permission[0])){
				continue;
			}
			String[] funcIds = permission[0].split(",");//获取功能权限
			for (int i = 0; i < funcIds.length; i++) {
				if(!funcs.contains(funcIds[i])){
					funcs.add(funcIds[i]);//如果不存在在结果集,则添加
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("-1");
		for (int i = 0; i < funcs.size(); i++) {
			sb.append(",");
			sb.append(funcs.get(i));//将所有功能ID拼接成字符串
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * <p>描述：获取session中用户在某一个功能中能够操作的部门数据 </p>
	 * <p>日期：2014-12-10 上午10:11:29 </p>
	 * @param request HttpServletRequest对象
	 * @param functionId 功能Id
	 * @return 返回能组织机构ID,多个用逗号隔开
	 */
	@SuppressWarnings("unchecked")
	public String getOrgsPermissionByFunctionId(HttpServletRequest request,String functionId){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}
		Object obj = session.getAttribute("roleInfo");
		if(obj == null){
			return null;
		}
		Map<String, String[]> permissionMap = (Map<String, String[]>)obj;
		List<String> orgs = new ArrayList<String>();
		boolean isExists = false;//标记当前角色是否有该功能权限
		for (String s : permissionMap.keySet()) {
			String[] permission = permissionMap.get(s);
			if(StringUtils.isBlank(permission[0])){
				continue;
			}
			isExists = false;
			String[] funcIds = permission[0].split(",");
			for (int i = 0; i < funcIds.length; i++) {
				if(funcIds[i].equals(functionId)){
					isExists = true;//如果有该功能权限,则结束当前循环
					break;
				}
			}
			if(isExists){
				permission = permissionMap.get(s);
				if(StringUtils.isBlank(permission[1])){
					continue;
				}
				String[] orgIds = permission[1].split(",");//获取当前角色所有组织机构ID
				for (int i = 0; i < orgIds.length; i++) {
					if(!orgs.contains(orgIds[i])){
						orgs.add(orgIds[i]);//如果不存在在结果集,则添加
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < orgs.size(); i++) {
			if(i > 0){
				sb.append(",");
			}
			sb.append(orgs.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * <p>描述：获取session中用户在某一个组织机构中的操作权限 </p>
	 * <p>日期：2014-12-10 上午10:11:29 </p>
	 * @param request HttpServletRequest对象
	 * @param orgId 组织机构Id
	 * @return 返回多个功能ID,多个用逗号隔开
	 */
	@SuppressWarnings("unchecked")
	public String getFuncsPermissionByOrgId(HttpServletRequest request,String orgId){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}
		Object obj = session.getAttribute("roleInfo");
		if(obj == null){
			return null;
		}
		Map<String, String[]> permissionMap = (Map<String, String[]>)obj;
		List<String> funcs = new ArrayList<String>();
		boolean isExists = false;//标记当前角色是否有该部门权限
		for (String s : permissionMap.keySet()) {
			String[] permission = permissionMap.get(s);
			if(StringUtils.isBlank(permission[1])){
				continue;
			}
			isExists = false;
			String[] orgIds = permission[1].split(",");
			for (int i = 0; i < orgIds.length; i++) {
				if(orgIds[i].equals(orgId)){
					isExists = true;//如果有该部门权限,则结束当前循环
					break;
				}
			}
			if(isExists){
				permission = permissionMap.get(s);
				if(StringUtils.isBlank(permission[0])){
					continue;
				}
				String[] funcIds = permission[0].split(",");//获取当前角色所有功能ID
				for (int i = 0; i < funcIds.length; i++) {
					if(!funcs.contains(funcIds[i])){
						funcs.add(funcIds[i]);//如果不存在在结果集,则添加
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < funcs.size(); i++) {
			if(i > 0){
				sb.append(",");
			}
			sb.append(funcs.get(i));
		}
		return sb.toString();
	}
	
	/** 获取省市
	 * <p>描述：获取缓存</p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param request code
	 * @param List<SysTypeDict> 类型集合
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/obtainCache")
	public List<Select> obtainCache(HttpServletRequest request, String code) throws Exception{
		List<SysDict> list = sysTypeDictService.findSysDictByParent(code);
		List<Select> selectsList = new ArrayList<Select>();
		if(list == null){
			Select tempSelect = new Select();
			selectsList.add(tempSelect);
			return selectsList;
		}
		for (SysDict type:list) {
			Select select = new Select();
			select.setId(type.getApplicationFlag());
			select.setText(type.getTypeDictName());
			select.setData(type);
			selectsList.add(select);
		}
		Select select = new Select();
		select.setId("");
		select.setText("--请选择--");
		selectsList.add(0, select);
        return selectsList;
	}
	
	/**
	 * 获取登录用户的数据权限 
	 * @param request
	 * @return  返回 逗号分隔 orgCode字符串  
	 */
	@SuppressWarnings("unchecked")
	public String getUserOrgPermit(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}
		Object obj = session.getAttribute("roleOrgInfo");
		if(obj == null){
			return null;
		}
		String orgcodes = "";
		List<SysOrg> roleOrgs = (List<SysOrg>)obj;
		for(SysOrg org : roleOrgs){
			orgcodes += org.getOrgCode()+",";
		}
		orgcodes = orgcodes.substring(0, orgcodes.lastIndexOf(","));
		return orgcodes;
	}
	
	/**
	 * <p>描述：获取登录用户对象的orgCode </p>
	 * @param request HttpServletRequest对象
	 * @return 登录用户对象的orgCode
	 */
	@SuppressWarnings("unchecked")
	public String getLoginUserOrgCode(HttpServletRequest request) {
		Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute("sessionMap");
		if(session == null){
			return null;
		}
		SysUser user = (SysUser) session.get("userInfo");
		if(user == null){
			return null;
		} else {
			try {
				SysOrg org = sysOrgService.findById(user.getOrgId());
				return org.getOrgCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	
	/**
	 * <p>描述：获取登录用户对象的公司编码 </p>
	 * @param request HttpServletRequest对象
	 * @return 登录用户对象的公司编码
	 */
	@SuppressWarnings("unchecked")
	public String getLoginUserCompanyCode(HttpServletRequest request) {
		Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute("sessionMap");
		if(session == null){
			return null;
		}
		SysUser user = (SysUser) session.get("userInfo");
		if(user == null){
			return null;
		} else {
			try {
				SysOrg org = sysOrgService.findById(user.getOrgId());
				String  orgCode = org.getOrgCode();
				if(orgCode.length()>4){//如果机构编码长度大于4  公司编码为前4位 ，否则公司编码和机构编码一直
					orgCode = orgCode.substring(0, 4);
				}
				return orgCode;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/**
	 * 获取登录用户的数据权限 
	 * @param request
	 * @return  返回用户拥有数据权限的公司  
	 */
	@SuppressWarnings("unchecked")
	public List<SysOrg> getUserPermitCompany(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}
		Object obj = session.getAttribute("roleOrgInfo");
		if(obj == null){
			return null;
		}
		List<SysOrg> roleOrgs = (List<SysOrg>)obj;
		List<SysOrg> companyOrgs = new ArrayList<>();
		for(SysOrg org : roleOrgs){
			if(org.getOrgType()==2||org.getOrgType()==3){
				companyOrgs.add(org);
			}
		}
		return companyOrgs;
	}
	
	/**
	 * <p>描述：获取登录用户对象的公司编码 </p>
	 * @param request HttpServletRequest对象
	 * @param length 编码长度
	 * @return 登录用户对象的公司编码
	 */
	@SuppressWarnings("unchecked")
	public String getCompanyCodeByLength(HttpServletRequest request , Integer length) {
		Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute("sessionMap");
		if(session == null){
			return null;
		}
		SysUser user = (SysUser) session.get("userInfo");
		if(user == null){
			return null;
		} else {
			try {
				SysOrg org = sysOrgService.findById(user.getOrgId());
				String  orgCode = org.getOrgCode();
				if(orgCode.length()>length){//如果机构编码长度大于4  公司编码为前4位 ，否则公司编码和机构编码一直
					orgCode = orgCode.substring(0, length);
				}
				return orgCode;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/**
	 * <p>描述：获取登录用户对象的公司 </p>
	 * @param request HttpServletRequest对象
	 * @return 登录用户对象的公司
	 */
	@SuppressWarnings("unchecked")
	public SysOrg getLoginUserCompany(HttpServletRequest request) {
		Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute("sessionMap");
		if(session == null){
			return null;
		}
		SysOrg dept = (SysOrg) session.get(SessionConst.SESSION_MAP_ORG);
		if(dept == null){
			return null;
		} else {
			try {
				SysOrg org = null;
				if(dept.getOrgCode().length() > 4){
					org = sysOrgService.getSysOrgByCode(dept.getOrgCode().substring(0, 4));
				}else{
					org = sysOrgService.getSysOrgByCode(dept.getOrgCode());
				}
				return org;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new SysOrg();
		}
	}
}
