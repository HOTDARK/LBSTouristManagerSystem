package com.hd.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.dao.SysOrgMapper;
import com.hd.sys.entity.SysFunc;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysRole;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysFuncService;
import com.hd.sys.service.SysOrgService;
import com.hd.sys.service.SysPermitService;
import com.hd.sys.service.SysRoleService;

/**
 * <p>类名：SysOrgAction </p>
 * <p>描述：组织机构页面控制类 </p>  
 * <p>作者：somnuscy </p>
 */
@Controller
@RequestMapping("/sysOrg")
public class SysOrgAction extends BaseAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(SysOrgAction.class);
	
	@Autowired
	private SysOrgService sysOrgService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysPermitService sysPermitService;
	@Autowired
	private SysFuncService sysFuncService;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_ORG, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("/forwardOrg")
	public String forwardOrg(HttpServletRequest request){
		return "system/org_list.jsp";
	}
	
	@LogOpt(level=FunLogConst.LEVEL_3, desc="机构变更", parentDesc=FunLogConst.DESC_SYSTEM_ORG)
	@RequestMapping("/forwardOrgEdit")
	public String forwardOrgEdit(HttpServletRequest request){
		return "system/org_edit.jsp";
	}
	
    /**
	 * <p>描述：获取按钮权限 </p>
	 * <p>日期：2014年12月8日 星期一 </p>
	 * @param request
	 * @return flag=true：有权限，false无权限
	 */
	@ResponseBody
	@RequestMapping("/getBtnPermission")
	public Map<String, Object> getBtnPermission(HttpServletRequest request, String btnVal){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysFunc sysFunction = new SysFunc();
			sysFunction.setFunctionCode(btnVal);
			Long functionId = sysFuncService.getFuncIdByCode(sysFunction);
			if(functionId!=null){
				result.put("flag", this.getOrgsPermissionByFunctionId(request, functionId.toString()));
			}
			else{				
				result.put("flag", "");
			}
		} catch (Exception e) {
			logger.error("获取按钮权限：" + e.getMessage(), e);
		}
        return result;
	}
	
	/**
	 * <p>描述：获取用户当前所在机构 </p>
	 * <p>日期：2014-12-9 上午10:16:20 </p>
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/findOrgByUserId")
	public List<SysOrg> findOrgByUserId(HttpServletRequest request,Long ogrId){
		List<SysOrg> orgList = new ArrayList<SysOrg>(1);
		try {
			if(ogrId > 0){				
				orgList = sysOrgService.findOrgByUserId(ogrId);
				return orgList;
			}
		} catch (Exception e) {
			logger.error("获取用户当前所在机构出错： " + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * <p>描述：查询组织机构树 </p>
	 * <p>日期：2014-12-9 上午11:14:52 </p>
	 * @param sysOrganization 机构对象
	 * @return 组织机构树
	 */
	@ResponseBody
	@RequestMapping("/getOrgTree")
	public List<SysOrg> getOrgTree(HttpServletRequest request,SysOrg sysOrganization){
		try {
			return sysOrgService.getOrgTree(sysOrganization, false);
		} catch (Exception e) {
			logger.error("查询组织机构树出错：" + e.getMessage(), e);
		}
		return new ArrayList<SysOrg>();
	}
	@ResponseBody
	@RequestMapping("/getAllOrgTree")
	public List<SysOrg> getAllOrgTree(HttpServletRequest request,SysOrg sysOrganization){
		try {
			return sysOrgService.getAllOrgTree(sysOrganization, false);
		} catch (Exception e) {
			logger.error("查询组织机构树出错：" + e.getMessage(), e);
		}
		return new ArrayList<SysOrg>();
	}
	
	/**
	 * <p>描述：通过机构ID加载组织机构 </p>
	 * <p>日期：2014-12-9 上午10:48:48 </p>
	 * @param ogrId 机构ID
	 * @return 通过机构ID加载组织机构
	 */
	@ResponseBody
	@RequestMapping("/findById")
	public SysOrg findById(HttpServletRequest request, Long orgId) {
		SysOrg sysOrg = null;
		try {
			sysOrg = (SysOrg) sysOrgService.findById(orgId);
			return sysOrg;
		} catch (Exception e) {
			logger.error("通过id加载组织机构出错：" + e.getMessage(), e);
		}
		return new SysOrg();
	}
	
	/**
	 * <p>描述：保存组织机构 </p>
	 * <p>日期：2014-12-9 上午11:05:39 </p>
	 * @param org 要保存的机构对象
	 * @return true：保存成功，false：保存失败
	 */
	@ResponseBody
	@RequestMapping("/doSave")
	public Map<String, Object> doSave(HttpServletRequest request, SysOrg org) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysUser user = getLoginUser(request);
			List<SysRole> roleList = new ArrayList<SysRole>();
			if(user != null){
				//获取用户角色
				roleList = sysRoleService.findRoleListByUserId(user.getUserId());
			}
			Map<String, Object> rel =  sysOrgService.addOrUpdateOrg(org, roleList);
			if((Boolean) rel.get("isPerm")){ //不为空代表机构权限已保存，需更新权限session
				//更新权session权限数据
				if(user != null) {
					// 获取顶级机构信息
					SysOrg topOrg = sysOrgMapper.findTopOrg();
					Map<String, String[]> newPermission = sysPermitService.getPermissionByUserId(user.getUserId());
					if (!user.getOrgId().equals(topOrg.getOrgId())) {
						for (String s : newPermission.keySet()) {
							String[] tmp = newPermission.get(s);
							if(StringUtils.isBlank(tmp[1])){
								continue;
							}
							if (tmp[1].contains(topOrg.getOrgId().toString()+",")) {
								tmp[1] = tmp[1].replaceAll(topOrg.getOrgId().toString()+",", "");
							}
							if (tmp[1].contains(topOrg.getOrgId().toString())) {
								tmp[1] = tmp[1].replaceAll(topOrg.getOrgId().toString(), "");
							}
						}
					}
					request.getSession().setAttribute("roleInfo", newPermission);
				}
			}
			result.put("status", true);
			result.put("sysOrg", rel.get("sysOrg"));
		} catch (Exception e) {
			logger.error("保存组织机构出错" + e.getMessage(), e);
			result.put("status", false);
		}
		return result;
	}
	
	/**
	 * <p>描述：判断组织机构是否重复 </p>
	 * <p>日期：2014-12-9 上午11:29:53 </p>
	 * @param org 机构对象
	 * @return true：不重复，false：重复
	 */
	@ResponseBody
	@RequestMapping("/findSameOrganizationName")
	public boolean findSameOrganizationName(HttpServletRequest request,SysOrg org) {
		SysOrg sysOrganization = null;
		try{
			sysOrganization = sysOrgService.doFindOrganizationName(org);
			if(sysOrganization != null){
				return false;
			}
		}catch(Exception e){
			logger.error("判断组织机构重复出错" + e.getMessage(), e);
		}
		return true;
	}
	
	/**
	 * <p>描述：冻结组织机构 </p>
	 * <p>日期：2014-12-9 上午11:36:49 </p>
	 * @param org 要冻结的机构对象
	 * @return true：冻结成功，false：冻结失败
	 */
	@ResponseBody
	@RequestMapping("/doUnabled")
	public boolean doUnabled(HttpServletRequest request,SysOrg org) {
		try {
			org.setState(0L);
			sysOrgService.optionStateByOrgId(org);
		} catch (Exception ex) {
			logger.error("冻结组织机构出错：" + ex.getMessage(), ex);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>描述：激活组织结构 </p>
	 * <p>日期：2014-12-9 上午11:40:31 </p>
	 * @return true：激活成功，false：激活失败
	 */
	@ResponseBody
	@RequestMapping("/doEnabled")
	public boolean doEnabled(HttpServletRequest request,SysOrg org) {
		try {
			org.setState(1L);
			sysOrgService.optionStateByOrgId(org);
		} catch (Exception e) {
			logger.error("激活组织结构出错：" + e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	/**
	 * <p>时间：2012-6-1 上午10:20:42</p>
	 * <p>说明: 判断是否父节点为激活状态，如果不是，不能激活</p>
	 * @return true：是，false：否
	 */
	@ResponseBody
	@RequestMapping("/getParentOrgState")
	public boolean getParentOrgState(HttpServletRequest request,Long orgId) {
		boolean status = false;
		try {
			status = sysOrgService.getParentOrgState(orgId);
		} catch (Exception e) {
			logger.error("激活父组织结构出错：" + e.getMessage(), e);
			return false;
		}
		return status;
	}
	
	/**
	 * 查询组织机构数据,用作下拉框显示
	 * @author	<a href="mailto:chenbin@iwangding.com">陈彬</a>
	 * @param sysOrg 组织机构
	 * @param request HttpServletRequest对象
	 * @return 返回组织机构集合
	 */
	@ResponseBody
	@RequestMapping("/getOrgData")
	public List<SysOrg> getOrgData(HttpServletRequest request, SysOrg sysOrg) {
		try {
			sysOrg.setState(1L);
			sysOrg.setPermissionOrgIds(this.getOrgsPermissionByFunctionId(request, sysOrg.getFuncId()));
			return sysOrgService.getOrgData(sysOrg);
		} catch (Exception e) {
			logger.error("查询组织机构数据出错：" + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 验证组织机构标识唯一性
	 * @param sysOrg 组织机构实体
	 * @return 返回true表示存在机构标识,返回false表示不存在机构标识
	 */
	@ResponseBody
	@RequestMapping("/validateHasOrgCode")
	public boolean validateHasOrgCode(SysOrg sysOrg){
		Long orgId = null;
		try{
			orgId = sysOrgService.getOrgIdByCode(sysOrg);
		} catch (Exception e) {
			logger.error("验证机构标识唯一出错：" + e.getMessage(), e);
		}
		return orgId!=null?true:false;
	}
	
	/**
	 * <p>描述：获取用户当前所在机构 </p>
	 * <p>日期：2014-12-9 上午10:16:20 </p>
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/findOrgByCurrentUser")
	public List<SysOrg> findOrgByCurrentUser(HttpServletRequest request){
		List<SysOrg> orgList = new ArrayList<SysOrg>(1);
		@SuppressWarnings("unchecked")
		HashMap<String, Object> hm = (HashMap<String, Object>) request.getSession().getAttribute("sessionMap");
		try {
			if (hm != null) {
				SysUser user = (SysUser) hm.get("userInfo");
				if (user != null) {
					orgList = sysOrgService.findOrgByUserId(user.getUserId());
					return orgList;
				}
			}
		} catch (Exception e) {
			logger.error("获取用户当前所在机构出错： " + e.getMessage(), e);
		}
		return null;
	}
}

