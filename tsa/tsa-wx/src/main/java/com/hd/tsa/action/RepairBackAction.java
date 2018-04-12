package com.hd.tsa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.RepairInfo;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysDict;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.SysUser;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/back")
public class RepairBackAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(RepairAction.class);
	// 实例化 统一业务API入口
	private IService iService = new WxService();
	
	/**
	 * 跳转后台操作页面
	 * @param entity
	 * @param backAccount
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转后台操作页面", parentDesc = "微信管理")
	@RequestMapping("/operPage")
	public ModelAndView operPage(RepairInfo entity , String backAccount,String openid,String orgCode) {
		
		ModelAndView view=new ModelAndView("backOperation.jsp");
		Map<String, Object> results = new HashMap<>();
		int operType=-1;
		try {
			if("003001".equals(entity.getRepairState())){
				operType=1;
			} else if("003002".equals(entity.getRepairState())){
				operType=2;
			} else if("003005".equals(entity.getRepairState()) || "003011".equals(entity.getRepairState())){
				operType=3;
			} else if("003004".equals(entity.getRepairState())){
				operType=4;
			} else if("003006".equals(entity.getRepairState())){
				operType=5;
			}
			String getRepairInfo = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/wxGetRepairInfo.action");
			String serviceResult = HttpUtils.sendPost(getRepairInfo, "id="+entity.getId(), 0);
			results=JsonUtils.json2Object(serviceResult, Map.class, null);
			
			List<String> jsApiList = new ArrayList<>();
			// 需要用到哪些JS SDK API 就设置哪些
			jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
			jsApiList.add("uploadImage");// 上传图片接口
			String domain = PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.domain");
			String url=domain+"/drsp-wx/back/operPage.action?id="+entity.getId()+"&repairNum="+entity.getRepairNum()+"&repairState="+entity.getRepairState()+"&backAccount="+backAccount+"&orgCode="+orgCode+"&openid=" + openid;
			WxJsapiConfig config = iService.createJsapiConfig(url,jsApiList);
			config.setAppid(PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.appId"));
			view.addObject("config", config);
			
			view.addObject("repairInfo", results.get("repairInfo"));
			view.addObject("attas", results.get("attas"));
			view.addObject("backAccount", backAccount);
			view.addObject("operType", operType);
			view.addObject("openid", openid);
			view.addObject("orgCode", orgCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	/**
	 * 驳回	关闭
	 * @param entity
	 * @param operReason
	 * @param backAccount
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "关闭和驳回", parentDesc = "微信管理")
	@RequestMapping("/closeOrReject")
	@ResponseBody
	public BaseModel closeOrReject(RepairInfo entity ,  String operReason  , String backAccount,int operType) {
		BaseModel result = new BaseModel();

		try {
			String closeOrReject = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/saveRepairInfoOper.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&repairState="+entity.getRepairState()+"&operReason="
					+operReason+"&userAccount="+backAccount+"&operType="+operType;
			String serviceResult = HttpUtils.sendPost(closeOrReject, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	
	
	
	/**
	 * 一级审核
	 * @param entity
	 * @param backAccount
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "一级审核", parentDesc = "微信管理")
	@RequestMapping("/declareAuditing")
	@ResponseBody
	public BaseModel declareAuditing(RepairInfo entity , String backAccount) {
		BaseModel result = new BaseModel();

		try {
			String declareAuditing = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/declareAuditing.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&repairState="+entity.getRepairState()+"&userAccount="+backAccount+"&serviceCompany="+entity.getServiceCompany();
			String serviceResult = HttpUtils.sendPost(declareAuditing, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 获取承接单位
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取承接单位", parentDesc = "微信管理")
	@RequestMapping("/getCjdw")
	@ResponseBody
	public List<SysOrg> getCjdw() {
		List<SysOrg> orgs = new ArrayList<>();

		try {
			String thirdOrgLevel = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/getCjdw.action");
			String result = HttpUtils.sendPost(thirdOrgLevel, "", 0);
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
	
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取二级审核基础数据", parentDesc = "微信管理")
	@RequestMapping("/getSecondAuditBaseData")
	@ResponseBody
	public Map<String,List<SysDict>> getSecondAuditBaseData() {
		Map<String,List<SysDict>> map=new HashMap<>();
		List<SysDict> dicts = new ArrayList<>();

		try {
			String findSysDictByParent = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/findSysDictByParent.action");
			String result = HttpUtils.sendPost(findSysDictByParent, "parentCode=005", 0);
			JSONArray arr = JSONArray.fromObject(result);
			SysDict dict = null;
			for (int i = 0; i < arr.size(); i++) {
				JSONObject jo = JSONObject.fromObject(arr.get(i));
				dict = new SysDict();
				dict.setTypeDictCode(jo.getString("typeDictCode"));
				dict.setTypeDictName(jo.getString("typeDictName"));
				dicts.add(dict);
			}
			map.put("fflx", dicts);
			dicts = new ArrayList<>();
			String wxxmUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("wxRepair/findSysDictByParent.action");
			String wxxmResult = HttpUtils.sendPost(wxxmUrl, "parentCode=007", 0);
			JSONArray wxxmArr = JSONArray.fromObject(wxxmResult);
			for (int i = 0; i < wxxmArr.size(); i++) {
				JSONObject jo = JSONObject.fromObject(wxxmArr.get(i));
				dict = new SysDict();
				dict.setTypeDictCode(jo.getString("typeDictCode"));
				dict.setTypeDictName(jo.getString("typeDictName"));
				dicts.add(dict);
			}
			map.put("wxxm", dicts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "二级审核", parentDesc = "微信管理")
	@RequestMapping("/secondAuditing")
	@ResponseBody
	public BaseModel secondAuditing(RepairInfo entity , String backAccount) {
		BaseModel result = new BaseModel();
		try {
			String declareAuditing = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/serviceAuditing.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&repairState="+entity.getRepairState()
							+"&userAccount="+backAccount+"&paymentType="+entity.getPaymentType()+"&repairType="+entity.getRepairType()
							+"&repairProjectOne="+entity.getRepairProjectOne()+"&repairProjectTwo="+entity.getRepairProjectTwo()
							+"&serviceCompany="+entity.getServiceCompany()+"&userPhone="+entity.getUserPhone();
			String serviceResult = HttpUtils.sendPost(declareAuditing, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	//saveServiceOper
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "用户确认，方案审核", parentDesc = "微信管理")
	@RequestMapping("/saveServiceOper")
	@ResponseBody
	public BaseModel saveServiceOper(RepairInfo entity , String backAccount) {
		BaseModel result = new BaseModel();
		try {
			String declareAuditing = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/saveServiceOper.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&repairState="+entity.getRepairState()
							+"&userAccount="+backAccount;
			String serviceResult = HttpUtils.sendPost(declareAuditing, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取维修人员", parentDesc = "微信管理")
	@RequestMapping("/getWxry")
	@ResponseBody
	public List<SysUser> getWxry(String orgCode) {
		List<SysUser> users = new ArrayList<>();

		try {
			String getWxry = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/findCompanyUser.action");
			String result = HttpUtils.sendPost(getWxry, "orgCode="+orgCode, 0);
			JSONArray arr = JSONArray.fromObject(result);
			SysUser user = null;
			for (int i = 0; i < arr.size(); i++) {
				JSONObject jo = JSONObject.fromObject(arr.get(i));
				user = new SysUser();
				user.setUserAccount(jo.getString("userAccount"));
				user.setUserName(jo.getString("userName"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "派工", parentDesc = "微信管理")
	@RequestMapping("/paigong")
	@ResponseBody
	public BaseModel paigong(RepairInfo entity , String backAccount) {
		BaseModel result = new BaseModel();

		try {
			String closeOrReject = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/saveSendPerson.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&userAccount="+backAccount+"&repairPerson="+entity.getRepairPerson();
			String serviceResult = HttpUtils.sendPost(closeOrReject, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "完工", parentDesc = "微信管理")
	@RequestMapping("/wangong")
	@ResponseBody
	public BaseModel wangong(RepairInfo entity , String backAccount,String filePaths,String oldFileNames,String fileNames,String fileSizes,String fileExtensions) {
		BaseModel result = new BaseModel();

		try {
			String closeOrReject = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/saveRepairFinish.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&userAccount="+backAccount+
					"&isReceipt="+entity.getIsReceipt()+"&serviceCompany="+entity.getServiceCompany()+
					"&filePaths="+filePaths+"&oldFileNames="+oldFileNames+"&fileNames="+fileNames+"&fileSizes="+fileSizes+
					"&fileExtensions="+fileExtensions;
			String serviceResult = HttpUtils.sendPost(closeOrReject, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "转工", parentDesc = "微信管理")
	@RequestMapping("/zhuangong")
	@ResponseBody
	public BaseModel zhuangong(RepairInfo entity , String backAccount) {
		BaseModel result = new BaseModel();

		try {
			String closeOrReject = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/saveChangeService.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&userAccount="+backAccount
					+"&serviceCompany="+entity.getServiceCompany();
			String serviceResult = HttpUtils.sendPost(closeOrReject, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	//jiaofei
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "缴费确认", parentDesc = "微信管理")
	@RequestMapping("/jiaofei")
	@ResponseBody
	public BaseModel jiaofei(RepairInfo entity , String backAccount) {
		BaseModel result = new BaseModel();

		try {
			String closeOrReject = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/saveChangeService.action");
			String params="id=" + entity.getId()+"&repairNum="+entity.getRepairNum()+"&userAccount="+backAccount;
			String serviceResult = HttpUtils.sendPost(closeOrReject, params, 0);
			result=JsonUtils.json2Object(serviceResult, BaseModel.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
