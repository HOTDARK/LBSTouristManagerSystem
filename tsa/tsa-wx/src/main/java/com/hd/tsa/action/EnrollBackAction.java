package com.hd.tsa.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

@Controller
@RequestMapping("/enrollBack")
public class EnrollBackAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(EnrollBackAction.class);
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取报名后台操作列表", parentDesc = "微信管理")
	@RequestMapping("/getEnrollBackList")
	@ResponseBody
	public Map<String,Object> getEnrollBackList(String permitCode , String backAccount,String openid,int pageNum,int displayLength) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			permitCode=permitCode.replace("|", "'");
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("studentAuditBack/getBackList.action");
			String permitResult = HttpUtils.sendPost(queryReparInfoByUser,
					"permitCode="+permitCode+"&pageNum="+pageNum+"&displayLength="+displayLength+"&userAccount="+backAccount, 0);
			map=JsonUtils.json2Object(permitResult, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询审核用车预约单列表：" + e.getMessage(), e);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDetail")
	public ModelAndView getDetail(Long id,String openid,String backAccount,String orgCode){
		ModelAndView view=new ModelAndView("enrollBack.jsp");
		view.addObject("openid", openid);
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				view.addObject("userInfo", userInfo);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxEnroll/getDetail.action");
				String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
				Map<String,Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
				view.addObject("sa", map.get("sa"));
				view.addObject("backAccount", backAccount);
				view.addObject("orgCode", orgCode);
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/audit")
	public BaseModel audit(Long id,String userAccount,String invoiceNum,String invoiceDate){
		BaseModel result = new BaseModel();
		try {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sif=new SimpleDateFormat("yyyy年MM月dd日");
			Date date=sif.parse(invoiceDate);
			invoiceDate=sf.format(date);
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("studentAuditBack/studentAuditAdopt.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id+"&userAccount="+userAccount+"&invoiceNum="+invoiceNum+"&invoiceDate="+invoiceDate, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/reject")
	public BaseModel reject(Long id,String userAccount,String reason){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("studentAuditBack/saveStudentAuditReject.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id+"&userAccount="+userAccount+"&reason="+reason, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
}
