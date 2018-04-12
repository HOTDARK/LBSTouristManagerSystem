package com.hd.tsa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.hd.tsa.entity.ComplaintProposal;
import com.hd.tsa.entity.ComplaintProposalAttachment;
import com.hd.tsa.entity.UserBackRelation;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.DateUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.entity.SysOrg;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.exception.WxErrorException;

import net.sf.json.JSONObject;

/**
* @author 持一盏月色对空樽
* @version 创建时间：2017年11月30日 下午2:00:34
*/
@Controller
@RequestMapping("/complaintProposal")
public class ComplaintProposalAction extends UserBaseAction{
	/** log4j */
	private static Logger logger = Logger.getLogger(ComplaintProposalAction.class);
	// 实例化 统一业务API入口
	private IService iService = new WxService();
	
	/**
	 * 初始化微信上传图片
	 * @author 持一盏月色对空樽
	 * @date 2018年1月3日
	 * @param openid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("forwardComplaintProposal")
	public Map<String, Object> forwardComplaintProposal(String openid,Integer idNum,Integer type,String viewName){
		Map<String, Object> map = new HashMap<>();
		List<String> jsApiList = new ArrayList<>();
		// 需要用到哪些JS SDK API 就设置哪些
		jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
		jsApiList.add("uploadImage");// 上传图片接口
		String domain = PropertiesUtils.getProperty(ComplaintProposalAction.class, "wx", "wx.domain");
		WxJsapiConfig config=null;
		try {
			config = iService.createJsapiConfig(
					domain+"/drsp-wx/wx/jumpPage.action?viewName="+viewName+"&idNum="+idNum+"&type="+type+"&openid=" + openid,
					jsApiList);
			config.setAppid(PropertiesUtils.getProperty(ComplaintProposalAction.class, "wx", "wx.appId"));
			map.put("config", config);
		} catch (WxErrorException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 初始化上传图片
	 * @param openid
	 * @param type
	 * @param viewName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/initializeUploadImg")
	public Map<String, Object> initializeUploadImg(Long id, String openid,String viewName){
		Map<String, Object> map = new HashMap<>();
		List<String> jsApiList = new ArrayList<>();
		// 需要用到哪些JS SDK API 就设置哪些
		jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
		jsApiList.add("uploadImage");// 上传图片接口
		String domain = PropertiesUtils.getProperty(ComplaintProposalAction.class, "wx", "wx.domain");
		WxJsapiConfig config=null;
		try {
			config = iService.createJsapiConfig(
					domain+"/drsp-wx/complaintProposal/findSuperviseDetail.action?id="+id+"&viewName="+viewName+"&openid=" + openid,
					jsApiList);
			config.setAppid(PropertiesUtils.getProperty(ComplaintProposalAction.class, "wx", "wx.appId"));
			map.put("config", config);
		} catch (WxErrorException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 新增投诉建议
	 * @author 持一盏月色对空樽
	 * @date 2018年1月3日
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertComplaintProposal")
	public ActionResult insertComplaintProposal(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			ComplaintProposal entity,String filePaths,String oldFileNames,
			String fileNames,String fileSizes,String fileExtensions,String type,String openid){
		ActionResult result = new ActionResult();
		UserInfo userInfo = null;
		try {
			Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
			if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
				userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
			}
			entity.setCreateUser(userInfo.getXgh());
			entity.setDeleted(0);
			entity.setState(1);
			entity.setCreateTime(new Date());
			entity.setTrack(2);
			entity.setType(Integer.parseInt(type));
			if("1".equals(type)){
				entity.setMenuCode("003002");
			}else{
				entity.setMenuCode("003001");
			}
			entity.setJurisdictionCode("01");
			
			List<ComplaintProposalAttachment> list = new ArrayList<ComplaintProposalAttachment>();
			ComplaintProposalAttachment attEntity  = null;
			String[] filePath=null;
			String[] oldFileName=null;
			String[] fileName=null;
			String[] fileSize=null;
			String[] fileExtension=null;
			if(filePaths!=null && !"".equals(filePaths)){
				filePath=filePaths.split(",");
			}
			if(oldFileNames!=null && !"".equals(oldFileNames)){
				oldFileName=oldFileNames.split(",");
			}
			if(fileNames!=null && !"".equals(fileNames)){
				fileName=fileNames.split(",");
			}
			if(fileSizes!=null && !"".equals(fileSizes)){
				fileSize=fileSizes.split(",");
			}
			if(fileExtensions!=null && !"".equals(fileExtensions)){
				fileExtension=fileExtensions.split(",");
			}
			if(oldFileName!=null){
				for(int i=0;i<oldFileName.length;i++){
					attEntity = new ComplaintProposalAttachment();
					attEntity.setFileName(fileName[i]);
					attEntity.setOldFileName(oldFileName[i]);
					attEntity.setSize(Long.parseLong(fileSize[i]));
					attEntity.setFileSuffix(fileExtension[i]);
					attEntity.setFilePath(filePath[i]);
					attEntity.setCreateTime(new Date());
					attEntity.setFileType("1");
					attEntity.setCreateUser(userInfo.getXgh());
					list.add(attEntity);
				}
			}
			String strList = JsonUtils.toJson(list, null, null, null);
			String wxComplaintProposal = PropertiesUtils.getProperty(RepairAction.class, "propurl", "supervise").concat("wxSupervise/saveSupervise.action");
			String cpParams = "title="+entity.getTitle()+"&content="+entity.getContent()+"&listStr="+strList
					+"&state="+entity.getState()+"&deleted="+entity.getDeleted()+"&createTime="+DateUtils.formatStr(entity.getCreateTime(), DateUtils.SDF_YYYY_MM_DD_HH_MM_SS)
					+"&track="+entity.getTrack()+"&type="+type+"&createUser="+entity.getCreateUser()+"&jurisdictionCode="+entity.getJurisdictionCode()+"&menuCode="+entity.getMenuCode()
					+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm();
			HttpUtils.sendPost(wxComplaintProposal, cpParams, 0);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("新增投诉建议失败:"+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查看我的投诉建议
	 * @author 持一盏月色对空樽
	 * @date 2018年1月4日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findMyComplaintProposalList")
	@ResponseBody
	public Map<String,Object> findMyComplaintProposalList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,int pageNum,Integer type) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getMyList = PropertiesUtils.getProperty(RepairAction.class, "propurl", "supervise").concat("wxSupervise/findByPage.action");
				String params="createUser="+userInfo.getXgh()+"&type="+type+"&pageNum="+pageNum+"&iDisplayLength=10"+"&sortColumns=create_time DESC";
				String serviceResult = HttpUtils.sendPost(getMyList, params, 0);
				map=JsonUtils.json2Object(serviceResult, Map.class, "");
				if(CollectionUtils.isEmpty(map)){
					map.put("list", new ArrayList<Object>());
					map.put("pageNums",0);
				}
				map.put("userInfo", userInfo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查看我的投诉建议详情
	 * @author 持一盏月色对空樽
	 * @date 2018年1月5日
	 * @param id
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findSuperviseDetail")
	public ModelAndView findSuperviseDetail(String id,String openid,String viewName,Integer idNum,Integer type){
		ModelAndView view=new ModelAndView(viewName);
		if(idNum!=null){
			view.addObject("idNum", idNum);
		}
		view.addObject("type", type);
		view.addObject("openid", openid);
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				view.addObject("userInfo", userInfo);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "supervise").concat("wxSupervise/findById.action");
				String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
				Map<String,Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
				view.addObject("entity", map.get("entity"));
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 工单查询投诉建议
	 * @author 持一盏月色对空樽
	 * @date 2018年1月8日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findComplaintProposalList")
	@ResponseBody
	public Map<String,Object> findComplaintProposalList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,int pageNum,String jurisdictionCode,Integer type) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String obUser = PropertiesUtils.getProperty(ComplaintProposalAction.class, "propurl", "user").concat("userPermit/obtainBackPermit.action");
				String userParams = "openId="+openid+"&backType=supervise";
				String userResult = HttpUtils.sendPost(obUser, userParams, 0);
				UserBackRelation userEntity = JsonUtils.json2Object(userResult, UserBackRelation.class, "");
				String listJson = JsonUtils.toJson(userEntity.getPermits(), null, null, null);
				
				String getMyList = PropertiesUtils.getProperty(ComplaintProposalAction.class, "propurl", "supervise").concat("manageSupervise/findByPage.action");
				String params="jurisdictionCode="+jurisdictionCode+"&menuCode="+listJson+"&pageNum="+pageNum+"&type="+type+"&iDisplayLength=10"+"&sortColumns=create_time DESC";
				String serviceResult = HttpUtils.sendPost(getMyList, params, 0);
				map=JsonUtils.json2Object(serviceResult, Map.class, "");
				if(CollectionUtils.isEmpty(map)){
					map.put("list", new ArrayList<Object>());
					map.put("pageNums",0);
				}
				map.put("userInfo", userInfo);
			}
		} catch (Exception e) {
			logger.error("工单查询投诉建议"+e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 修改投诉建议信息
	 * @author 持一盏月色对空樽
	 * @date 2018年1月10日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateComplaintProposal")
	public ActionResult updateComplaintProposal(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession,String openid,ComplaintProposal entity,String is,String filePaths,String oldFileNames,
			String fileNames,String fileSizes,String fileExtensions){
		ActionResult result = new ActionResult();
		try {
			Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getMyList = PropertiesUtils.getProperty(ComplaintProposalAction.class, "propurl", "supervise").concat("manageSupervise/updateSupervise.action");
				String params="";
				List<ComplaintProposalAttachment> list = new ArrayList<ComplaintProposalAttachment>();
				ComplaintProposalAttachment attEntity  = null;
				String[] filePath=null;
				String[] oldFileName=null;
				String[] fileName=null;
				String[] fileSize=null;
				String[] fileExtension=null;
				if(filePaths!=null && !"".equals(filePaths)){
					filePath=filePaths.split(",");
				}
				if(oldFileNames!=null && !"".equals(oldFileNames)){
					oldFileName=oldFileNames.split(",");
				}
				if(fileNames!=null && !"".equals(fileNames)){
					fileName=fileNames.split(",");
				}
				if(fileSizes!=null && !"".equals(fileSizes)){
					fileSize=fileSizes.split(",");
				}
				if(fileExtensions!=null && !"".equals(fileExtensions)){
					fileExtension=fileExtensions.split(",");
				}
				if(oldFileName!=null){
					for(int i=0;i<oldFileName.length;i++){
						attEntity = new ComplaintProposalAttachment();
						attEntity.setFileName(fileName[i]);
						attEntity.setOldFileName(oldFileName[i]);
						attEntity.setSize(Long.parseLong(fileSize[i]));
						attEntity.setFileSuffix(fileExtension[i]);
						attEntity.setFilePath(filePath[i]);
						attEntity.setCreateTime(new Date());
						attEntity.setCreateUser(userInfo.getXgh());
						attEntity.setType("2");
						list.add(attEntity);
					}
				}
				String strList = JsonUtils.toJson(list, null, null, null);
				//发布，撤回
				if("3".equals(is)){
					params="id="+entity.getId()+"&state="+entity.getState();
				}else
				//反馈
				if("2".equals(is)){
					//建议
					if("1".equals(entity.getType())){
						params="id="+entity.getId()+"&feedback="+entity.getFeedback()+"&track=1"+"&jurisdictionCode="+entity.getJurisdictionCode()+"&menuCode=003002"+"&feedbackTime="+DateUtils.formatStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm()+"&reason1="+entity.getFeedback();
					}else{
						params="id="+entity.getId()+"&feedback="+entity.getFeedback()+"&track=1"+"&jurisdictionCode="+entity.getJurisdictionCode()+"&menuCode=003001"+"&feedbackTime="+DateUtils.formatStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm()+"&reason1="+entity.getFeedback();
					}
				}else
				//指派
				if("4".equals(is)){
					params="id="+entity.getId()+"&jurisdictionCode="+entity.getJurisdictionCode()+"&menuCode="+entity.getMenuCode()+"&track="+entity.getTrack()+"&reason="+entity.getReason()+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm()+"&reason1="+entity.getReason();
				}else
				//考评
				if("1".equals(is)){
					//建议
					if("1".equals(entity.getType())){
						params="id="+entity.getId()+"&adoptEvaluate="+entity.getAdoptEvaluate()+"&adopt="+entity.getAdopt()+"&track=1"+"&jurisdictionCode=01"+"&menuCode=003002"+"&feedbackTime="+DateUtils.formatStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm()+"&reason1="+entity.getAdoptEvaluate();
					}else{
						params="id="+entity.getId()+"&adoptEvaluate="+entity.getAdoptEvaluate()+"&adopt="+entity.getAdopt()+"&track=1"+"&jurisdictionCode=01"+"&menuCode=003001"+"&feedbackTime="+DateUtils.formatStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm()+"&reason1="+entity.getAdoptEvaluate();
					}
				}else
				//确认
				if("5".equals(is)){
					params="id="+entity.getId()+"&track="+entity.getTrack()+"&suerTime="+DateUtils.formatStr(new Date(),"yyyy-MM-dd HH:mm:ss")+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm()+"&reason1="+entity.getReason();
				}else
				//驳回
				if("6".equals(is)){
					if("1".equals(entity.getType())){
						params="id="+entity.getId()+"&track=2"+"&reason1="+entity.getReason()+"&menuCode=003002"+"&jurisdictionCode=01"+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm();
					}else{
						params="id="+entity.getId()+"&track=2"+"&reason1="+entity.getReason()+"&menuCode=003001"+"&jurisdictionCode=01"+"&is="+is+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm();
					}
				}
				params=params+"&listStr="+strList;
				HttpUtils.sendPost(getMyList, params, 0);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("修改投诉建议信息"+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查询组织机构
	 * @author 持一盏月色对空樽
	 * @date 2018年1月10日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findOrg")
	public List<SysOrg> findOrg(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid){
		Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
		List<SysOrg> orgList = null;
		if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
			String getMyList = PropertiesUtils.getProperty(ComplaintProposalAction.class, "propurl", "supervise").concat("manageSupervise/findByParentId.action");
			String params="parentId=23";
			String list = HttpUtils.sendPost(getMyList, params, 0);
			orgList=JsonUtils.json2Object(list, List.class, "");
		}
		return orgList;
	}
}
