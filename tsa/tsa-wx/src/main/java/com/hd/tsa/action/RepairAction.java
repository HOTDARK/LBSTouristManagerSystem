package com.hd.tsa.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.RepairInfo;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysDict;
import com.hd.sys.entity.SysOrg;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 维修控制类
 * 
 * @author LG
 *
 */
@Controller
@RequestMapping("/wxRepair")
public class RepairAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(RepairAction.class);
	// 实例化 统一业务API入口
	private IService iService = new WxService();
	/**
	 * 跳转我的报修列表页面
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取我的报修列表", parentDesc = "微信管理")
	@RequestMapping("/getMyRepairList")
	public ModelAndView getMyRepairList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,String idNum) {
		ModelAndView view = new ModelAndView("repairList.jsp");
		view.addObject("openid", openid);
		view.addObject("idNum", idNum);
		logger.info("跳转报修列表action的openid:"+openid);
		try {

			Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				view.addObject("userInfo", userInfo);
				String getMyRepairList = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/wxMyList.action");
				String result = HttpUtils.sendPost(getMyRepairList,
						"userPhone=" + userInfo.getSjhm() + "&xgh=" + userInfo.getXgh() + "&repairFlag=" + true, 0);
				Map<String, Object> resultData = JsonUtils.json2Object(result, Map.class, "");
				List<String> jsApiList = new ArrayList<>();
				// 需要用到哪些JS SDK API 就设置哪些
				jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
				jsApiList.add("uploadImage");// 上传图片接口
				String domain = PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.domain");
				WxJsapiConfig config = iService.createJsapiConfig(
						domain+"/drsp-wx/wxRepair/getMyRepairList.action?idNum="+idNum+"&openid=" + openid,
						jsApiList);
				config.setAppid(PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.appId"));
				
				view.addObject("config", config);
				view.addObject("resultData", resultData);
				String repairBaseInfo = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/wxRepairBaseInfo.action");
				String baseInfo = HttpUtils.sendPost(repairBaseInfo, "", 0);
				Map<String, Object> baseMap = JsonUtils.json2Object(baseInfo, Map.class, "");
				JSONArray repairAreas = JSONArray.fromObject(baseMap.get("repairAreas"));
				JSONArray repairProjects = JSONArray.fromObject(baseMap.get("repairProjects"));
				JSONArray serviceCompany = JSONArray.fromObject(baseMap.get("serviceCompany"));
				JSONArray sfxm = JSONArray.fromObject(baseMap.get("sfxm"));
				JSONArray xsss = JSONArray.fromObject(baseMap.get("xsss"));
				view.addObject("userInfo", userInfo);
				view.addObject("repairProjects", repairProjects);
				view.addObject("serviceCompany", serviceCompany);
				view.addObject("repairAreas", repairAreas);
				view.addObject("sfxm", sfxm);
				view.addObject("xsss", xsss);
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	/**
	 * 微信端获取我的报修列表
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param currentPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取我的报修列表", parentDesc = "微信管理")
	@RequestMapping("/getMyJsonRepairList")
	@ResponseBody
	public Map<String, Object> getMyJsonRepairList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid, int currentPage) {
		Map<String, Object> map = null;
		Map<String, Object> resultData = null;
		try {
			map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getMyRepairList = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "repair").concat("wxRepair/wxMyList.action");
				String result = HttpUtils.sendPost(getMyRepairList, "userPhone=" + userInfo.getSjhm() + "&xgh="
						+ userInfo.getXgh() + "&repairFlag=" + true + "&currentPage=" + currentPage, 0);
				resultData = JsonUtils.json2Object(result, Map.class, "");
			} else {
				response.sendRedirect("../wx/jumpPage.action?openid=" + openid + "&viewName=userBind.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("总页数：" + resultData.get("totalPages"));
		return resultData;
	}

	/**
	 * 跳转报修详情页面
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取报修详情", parentDesc = "微信管理")
	@RequestMapping("/getRepairDetail")
	public ModelAndView getMyRepairDetail(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid, Long id) {
		ModelAndView view = new ModelAndView("repairDetail.jsp");
		view.addObject("openid", openid);
		Map<String, Object> map = null;
		Map<String, Object> resultData = null;
		try {
			map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				// UserInfo userInfo = JsonUtils.json2Object(((JSONObject)
				// map.get(SESSION_MAP_USER)).toString(),
				// UserInfo.class, "");
				String getRepairInfo = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "repair").concat("wxRepair/wxGetRepairInfo.action");
				String result = HttpUtils.sendPost(getRepairInfo, "id=" + id, 0);
				WxUser user = iService.getUserInfoByOpenId(new WxUserGet(openid, WxConsts.LANG_CHINA));
				resultData = JsonUtils.json2Object(result, Map.class, "");
				view.addObject("headImg", user.getHeadimgurl());
				view.addObject("resultData", resultData);
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	/**
	 * 微信端确认维修
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param id
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端确认维修", parentDesc = "微信管理")
	@RequestMapping("/confirmRepair")
	@ResponseBody
	public ActionResult confirmRepair(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openid, Long id) {
		ActionResult ar = new ActionResult();
		Map<String, Object> map = null;
		try {
			map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String confirmRepair = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "repair").concat("wxRepair/wxRepairConfirm.action");
				String result = HttpUtils.sendPost(confirmRepair,
						"id=" + id + "&xgh=" + userInfo.getXgh() + "&xm=" + userInfo.getXm(), 0);
				if ("1".equals(result)) {
					ar.setSuccess(true);
				}
			} else {
				response.sendRedirect("../wx/jumpPage.action?openid=" + openid + "&viewName=userBind.jsp");
			}
		} catch (Exception e) {
			ar.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 微信端关闭维修
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param id
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端关闭维修", parentDesc = "微信管理")
	@RequestMapping("/closeRepair")
	@ResponseBody
	public ActionResult closeRepair(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openid, Long id) {
		ActionResult ar = new ActionResult();
		Map<String, Object> map = null;
		try {
			map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String closeRepair = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "repair").concat("wxRepair/wxRepairClose.action");
				String result = HttpUtils.sendPost(closeRepair,
						"id=" + id + "&xgh=" + userInfo.getXgh() + "&xm=" + userInfo.getXm(), 0);
				if ("1".equals(result)) {
					ar.setSuccess(true);
				}
			} else {
				response.sendRedirect("../wx/jumpPage.action?openid=" + openid + "&viewName=userBind.jsp");
			}
		} catch (Exception e) {
			ar.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 报修
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param repairInfo
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端报修", parentDesc = "微信管理")
	@RequestMapping("/repair")
	@ResponseBody
	public ActionResult repair(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openid, RepairInfo repairInfo,String filePaths,String oldFileNames,
			String fileNames,String fileSizes,String fileExtensions,String sfxm,String xsss,String xsssTwo) {
		ActionResult ar = new ActionResult();
		try {
			Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				repairInfo.setCreateUser(userInfo.getXgh());
				repairInfo.setRepairUser(userInfo.getXgh());
			} else {
				response.sendRedirect("userBind.jsp");
				return null;
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String orderDate=null;
			if(repairInfo.getOrderRepairDate()!=null){
				orderDate=sf.format(repairInfo.getOrderRepairDate());
			}
			
			repairInfo.setRepairChannel("004002");
			String wxRepair = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/wxRepair.action");
			String repairParams="repairChannel=" + repairInfo.getRepairChannel()+"&createUser="+repairInfo.getCreateUser()+"&repairUser="
					+repairInfo.getRepairUser()+"&repairUserName="+repairInfo.getRepairUserName()+"&userPhone="+repairInfo.getUserPhone()
					+"&repairArea="+repairInfo.getRepairArea()+"&repairProjectOne="+repairInfo.getRepairProjectOne()
					+"&orderRepairDate="+orderDate+"&repairCompany="+repairInfo.getRepairCompany()
					+"&detailLocation="+repairInfo.getDetailLocation()+"&repairContent="+repairInfo.getRepairContent()+"&filePaths="+filePaths
					+"&oldFileNames="+oldFileNames+"&fileNames="+fileNames+"&fileSizes="+fileSizes+"&fileExtensions="+fileExtensions
					+"&sfxm="+sfxm+"&xsss="+xsss+"&xsssTwo="+xsssTwo;
			HttpUtils.sendPost(wxRepair, repairParams, 0);
			ar.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ar;
	}


	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取三级单位", parentDesc = "微信管理")
	@RequestMapping("/secondeOrg")
	@ResponseBody
	public List<SysOrg> secondeOrg(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			Long parentOrgId) {
		List<SysOrg> orgs = new ArrayList<>();

		try {
			String thirdOrgLevel = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/getThirdLevelOrg.action");
			String result = HttpUtils.sendPost(thirdOrgLevel, "parentOrgId=" + parentOrgId, 0);
			JSONArray arr = JSONArray.fromObject(result);
			SysOrg org = null;
			for (int i = 0; i < arr.size(); i++) {
				JSONObject jo = JSONObject.fromObject(arr.get(i));
				org = new SysOrg();
				org.setOrgCode(jo.getString("orgCode"));
				org.setOrgName(jo.getString("orgName"));
				orgs.add(org);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgs;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "根据parentCode查询字典信息", parentDesc = "微信管理")
	@RequestMapping("/findSysDictByParent")
	@ResponseBody
	public List<SysDict> findSysDictByParent(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String parentCode) {
		List<SysDict> dicts = new ArrayList<>();

		try {
			String findSysDictByParent = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/findSysDictByParent.action");
			String result = HttpUtils.sendPost(findSysDictByParent, "parentCode=" + parentCode, 0);
			JSONArray arr = JSONArray.fromObject(result);
			SysDict dict = null;
			for (int i = 0; i < arr.size(); i++) {
				JSONObject jo = JSONObject.fromObject(arr.get(i));
				dict = new SysDict();
				dict.setTypeDictCode(jo.getString("typeDictCode"));
				dict.setTypeDictName(jo.getString("typeDictName"));
				dicts.add(dict);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dicts;
	}
	

}
