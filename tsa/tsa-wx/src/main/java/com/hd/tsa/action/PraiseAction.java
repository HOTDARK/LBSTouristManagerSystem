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

import com.hd.tsa.entity.Praise;
import com.hd.tsa.entity.PraiseAttachment;
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
* @version 创建时间：2018年1月29日 下午1:50:39
*/
@Controller
@RequestMapping("/praise")
public class PraiseAction extends UserBaseAction{
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
	@RequestMapping("forwardPraise")
	public Map<String, Object> forwardPraise(String openid,Integer idNum,String viewName){
		Map<String, Object> map = new HashMap<>();
		List<String> jsApiList = new ArrayList<>();
		// 需要用到哪些JS SDK API 就设置哪些
		jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
		jsApiList.add("uploadImage");// 上传图片接口
		String domain = PropertiesUtils.getProperty(PraiseAction.class, "wx", "wx.domain");
		WxJsapiConfig config=null;
		try {
			config = iService.createJsapiConfig(
					domain+"/drsp-wx/wx/jumpPage.action?viewName="+viewName+"&idNum="+idNum+"&openid=" + openid,
					jsApiList);
			config.setAppid(PropertiesUtils.getProperty(PraiseAction.class, "wx", "wx.appId"));
			map.put("config", config);
		} catch (WxErrorException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增表扬
	 * @author 持一盏月色对空樽
	 * @date 2018年1月29日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param entity
	 * @param filePaths
	 * @param oldFileNames
	 * @param fileNames
	 * @param fileSizes
	 * @param fileExtensions
	 * @param openid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertPraise")
	public ActionResult insertPraise(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			Praise entity,String filePaths,String oldFileNames,
			String fileNames,String fileSizes,String fileExtensions,String openid){
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
			
			List<PraiseAttachment> list = new ArrayList<PraiseAttachment>();
			PraiseAttachment attEntity  = null;
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
					attEntity = new PraiseAttachment();
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
			String wxComplaintProposal = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("wxPraise/savePraise.action");
			String cpParams = "title="+entity.getTitle()+"&content="+entity.getContent()+"&listStr="+strList
					+"&state="+entity.getState()+"&deleted="+entity.getDeleted()+"&createTime="+DateUtils.formatStr(entity.getCreateTime(), DateUtils.SDF_YYYY_MM_DD_HH_MM_SS)
					+"&createUser="+entity.getCreateUser();
			HttpUtils.sendPost(wxComplaintProposal, cpParams, 0);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("新增投诉建议失败:"+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查看我的表扬
	 * @author 持一盏月色对空樽
	 * @date 2018年1月29日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findMyPraiseList")
	@ResponseBody
	public Map<String,Object> findMyPraiseList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,int pageNum) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getMyList = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("wxPraise/findByPage.action");
				String params="createUser="+userInfo.getXgh()+"&pageNum="+pageNum+"&iDisplayLength=10"+"&sortColumns=create_time DESC";
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
	 * 查看表扬详情
	 * @author 持一盏月色对空樽
	 * @date 2018年1月29日
	 * @param id
	 * @param openid
	 * @param viewName
	 * @param idNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findPraiseDetail")
	public ModelAndView findPraiseDetail(String id,String openid,String viewName,Integer idNum){
		ModelAndView view=new ModelAndView(viewName);
		if(idNum!=null){
			view.addObject("idNum", idNum);
		}
		view.addObject("openid", openid);
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				view.addObject("userInfo", userInfo);
				String getData = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("wxPraise/findById.action");
				String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
				Map<String,Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
				
				List<SysOrg> orgList = null;
				String getMyList = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("manageSupervise/findByParentId.action");
				String params="";
				String list = HttpUtils.sendPost(getMyList, params, 0);
				orgList=JsonUtils.json2Object(list, List.class, "");
				for(Object org:orgList){
					Map<String, String> orgMap=(Map<String, String>)org;
					if(orgMap.get("orgCode").equals(map.get("organization"))){
						map.put("orgName", orgMap.get("orgName"));
						map.put("orgCode", orgMap.get("orgCode"));
					}
				}
				view.addObject("entity",map);
				view.addObject("orgList", orgList);
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
	 * 工单查询表扬列表
	 * @author 持一盏月色对空樽
	 * @date 2018年1月29日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findPraiseList")
	@ResponseBody
	public Map<String,Object> findPraiseList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,int pageNum) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getMyList = PropertiesUtils.getProperty(ComplaintProposalAction.class, "propurl", "supervise").concat("wxPraise/findByPage.action");
				String params="pageNum="+pageNum+"&iDisplayLength=10"+"&sortColumns=create_time DESC&deleted=0";
				String serviceResult = HttpUtils.sendPost(getMyList, params, 0);
				map=JsonUtils.json2Object(serviceResult, Map.class, "");
				if(CollectionUtils.isEmpty(map)){
					map.put("list", new ArrayList<Object>());
					map.put("pageNums",0);
				}
				map.put("userInfo", userInfo);
			}
		} catch (Exception e) {
			logger.error("工单查询表扬"+e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 发布、撤回、标注表扬
	 * @author 持一盏月色对空樽
	 * @date 2018年1月29日
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param entity
	 * @param is
	 * @param orgCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePraise")
	public ActionResult updatePraise(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession,String openid,Praise entity,String is,String orgCode){
		ActionResult result = new ActionResult();
		try {
			String getMyList = "";
			String params="";
			//发布，撤回
			if("3".equals(is)){
				getMyList = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("wxPraise/freezeAndActivate.action");
				params="id="+entity.getId()+"&state="+entity.getState();
			}else
			//标注
			if("4".equals(is)){
				getMyList = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("wxPraise/signDept.action");
				params="id="+entity.getId()+"&orgCode="+orgCode;
			}
			HttpUtils.sendPost(getMyList, params, 0);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("发布、撤回、标注表扬"+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询组织机构
	 * @author 持一盏月色对空樽
	 * @date 2018年1月29日
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
			String getMyList = PropertiesUtils.getProperty(PraiseAction.class, "propurl", "supervise").concat("manageSupervise/findByParentId.action");
			String params="";
			String list = HttpUtils.sendPost(getMyList, params, 0);
			orgList=JsonUtils.json2Object(list, List.class, "");
		}
		return orgList;
	}
}
