package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.SafetyCheckMain;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;

/**
 * 安全检查控制层
 * @author 寂寞先生
 * 2018年1月25日
 */
@Controller
@RequestMapping("/safetyCheck")
public class SafetyCheckAction {
	
	private static Logger logger = Logger.getLogger(SafetyCheckAction.class);
	
	/**
	 * 跳转安全检查列表页面
	 * @param account
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goSafetyCheck")
	public ModelAndView goSafetyCheck(String account, String openid){
		ModelAndView modelAndView = new ModelAndView("safety_check_manage.jsp");
		modelAndView.addObject("account", account);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}
	
	/**
	 * 获取安全检查数据
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSafetyCheckList")
	@SuppressWarnings("unchecked")
	public ActionResult getSafetyCheckList(SafetyCheckMain entity, Integer pageNum){
		ActionResult result = new ActionResult();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageNum", pageNum);
			Object[] objs = {entity,paramMap};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(SafetyCheckAction.class, "propurl", "vehicle").concat("wxSafetyCheckMain/findByPage.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("list", map.get("list"));
				result.put("pageNums", map.get("pageNums"));
			}else{
				result.put("list", null);
				result.put("pageNums", 0);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMessage("获取安全检查数据失败");
			logger.error("微信端获取安全检查数据失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 跳转安全检查详情页面
	 * @param id
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goSafetyCheckDetailed")
	public ModelAndView goSafetyCheckDetailed(Long id, String openid){
		ModelAndView modelAndView = new ModelAndView("safety_check_detailed.jsp");
		try {
			String getData = PropertiesUtils.getProperty(SafetyCheckAction.class, "propurl", "vehicle").concat("wxSafetyCheckMain/findByMainId.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			SafetyCheckMain entity =JsonUtils.json2Object(resultStr, SafetyCheckMain.class, null);
			modelAndView.addObject("entity", entity);
			modelAndView.addObject("openid", openid);
		} catch (Exception e) {
			logger.error("微信端获取安全检查详情失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 根据id获取安全检查数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSafetyCheck")
	public ActionResult getSafetyCheck(Long id){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(SafetyCheckAction.class, "propurl", "vehicle").concat("wxSafetyCheckMain/findByMainId.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			SafetyCheckMain entity =JsonUtils.json2Object(resultStr, SafetyCheckMain.class, null);
			result.put("entity", entity);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("微信端根据id获取安全检查数据失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 确认安全检查结果
	 * @param id
	 * @param checkResult
	 * @param inconformtyReason
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/confirmCheckResult")
	public ActionResult confirmCheckResult(Long id, Integer checkResult, String inconformtyReason){
		ActionResult result = new ActionResult();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", id);
			paramMap.put("checkResult", checkResult);
			if (checkResult==2 && inconformtyReason!=null && inconformtyReason!="") {
				paramMap.put("inconformtyReason", inconformtyReason);
			}
			Object[] objs = {paramMap};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(SafetyCheckAction.class, "propurl", "vehicle").concat("wxSafetyCheckMain/updateByMainId.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rst =JsonUtils.json2Object(resultStr, boolean.class, null);
			result.setSuccess(rst);
		} catch (Exception e) {
			logger.error("确认安全检查结果失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

}
