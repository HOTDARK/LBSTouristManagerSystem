package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.TrainingLog;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;

@Controller
@RequestMapping("/trainningLog")
public class TrainningLogAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(TrainningLogAction.class);
	
	/**
	 * 根据日期和帐号查询是否有日志
	 * @param orgCode
	 * @param userAccount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findByDateAndOrg")
	public Map<String, Object> findByDateAndOrg(String orgCode,String userAccount,String queryDate){
		Map<String, Object> map=null;
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("trainingLog/findByDateAndOrg.action");
			String result = HttpUtils.sendPost(getDataUrl, "orgCode="+orgCode+"&userAccount="+userAccount+"&queryDate="+queryDate, 0);
			map=JsonUtils.json2Object(result, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 新增或修改
	 * @param entity
	 * @param orgCode
	 * @param userAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTrainingLog")
	public ActionResult updateTrainingLog(TrainingLog entity,String orgCode,String userAccount){
		ActionResult ar = new ActionResult();
		try {
			entity.setCreateUser(userAccount);
			Object[] objs={entity};
			String params=HttpUtils.packParam(objs).concat("&orgCode="+orgCode+"&userAccount="+userAccount);
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("trainingLog/updateTrainingLog.action");
			String result = HttpUtils.sendPost(getDataUrl, params, 0);
			ar=JsonUtils.json2Object(result, ActionResult.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return ar;
	}
	
	/**
	 * 获取列表
	 * @param userAccount
	 * @param orgCode
	 * @param currentPage
	 * @param displayLength
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findTrainingLog")
	public Map<String, Object> findTrainingLog(String userAccount,String orgCode,int currentPage, int displayLength){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("trainingLog/findTrainingLog.action");
			String result = HttpUtils.sendPost(getDataUrl, "orgCode="+orgCode+"&userAccount="+userAccount+"&currentPage="+currentPage+"&displayLength="+displayLength, 0);
			map=JsonUtils.json2Object(result, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
	@RequestMapping("/goDetail")
	public ModelAndView goDetail(Long id,String openid){
		ModelAndView view = new ModelAndView("trainningLogDetail.jsp");
		view.addObject("openid", openid);
		try {
			String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("trainingLog/findById.action");
			String result = HttpUtils.sendPost(getDataUrl, "id="+id, 0);
			TrainingLog log=JsonUtils.json2Object(result, TrainingLog.class, null);
			view.addObject("log", log);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
}
