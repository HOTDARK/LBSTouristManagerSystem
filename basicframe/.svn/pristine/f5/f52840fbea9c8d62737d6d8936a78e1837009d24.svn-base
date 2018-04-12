/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.core.exceptions.IwdBusinessException;
import com.hd.sfw.core.utils.DateUtils;
import com.hd.workflow.app.UserRelationshipMapping;
import com.hd.workflow.app.commons.AppCommon;
import com.hd.workflow.app.model.WorkflowLog;
import com.hd.workflow.app.model.WorkflowProcess;
import com.hd.workflow.app.service.WorkflowLogService;
import com.hd.workflow.app.service.WorkflowProcessService;

/**
 * 
 * @version	0.0.1
 */
@Controller
@RequestMapping(AppCommon.BASE_PATH+"/log")
public class LogAction {
	
	@Autowired
	private WorkflowProcessService workflowProcessService;
	
	@Autowired
	private WorkflowLogService workflowLogService;
	
	@Autowired
	private UserRelationshipMapping userRelationshipMapping;
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("workflow/journal");
		mav.addObject("process", workflowProcessService.getAllProcessWithoutDetail());
		return mav;
	}
	
	@RequestMapping("/process")
	@ResponseBody
	public Object getProcess(){
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("process", workflowProcessService.getAllProcessWithoutDetail());
		return rs;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Object list(
			HttpSession session,
			@RequestParam("start")int start,
			@RequestParam("length")int length,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="result",required=false)String result,
			@RequestParam(value="processId",required=false)String processId){
		
		WorkflowLog condition = new WorkflowLog();
		condition.setSortColumns("id desc");
		condition.setStartTimeBegin(DateUtils.parseDate(startDate));
		
		Date end = DateUtils.parseDate(endDate);
		if(end!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(end);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			condition.setEndTimeEnd(calendar.getTime());
		}
		
		condition.setConclusionResult(result);
		condition.setProcessId(processId);
		
		String uid = userRelationshipMapping.getUid(session);
		if(!AppCommon.isShowAll(uid)){
			condition.setUid(uid);
		}
		
		List<WorkflowLog> data = workflowLogService.findByPage(condition, start, length);
		int total = workflowLogService.countByCondition(condition);
		
		userRelationshipMapping.fill(data);
		
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("draw", System.currentTimeMillis());
		rs.put("recordsTotal", total);
		rs.put("recordsFiltered",total);
		rs.put("data",data);
		
		return rs;
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(@RequestParam("id")int id){
		ModelAndView mav = new ModelAndView("workflow/journal_detail");
		mav.addObject("info",workflowLogService.findById(id));
		mav.addObject("logs", workflowLogService.findItemByLogId(id));
		return mav;
	}
	
	@RequestMapping("trace")
	public ModelAndView trace(@RequestParam(value="id",required=false,defaultValue="0")int id,
			@RequestParam(value="processId",required=false)String processId,
			@RequestParam(value="version",required=false,defaultValue="0")int version,
			@RequestParam(value="trace",required=false)String trace){
		
		if(id>0){
			WorkflowLog info = workflowLogService.findById(id);
			if(info==null){
				throw new IwdBusinessException("id不存在");
			}
			
			trace = info.getTrace();
			processId = info.getProcessId();
			version = info.getVersion();
		}
		
		WorkflowProcess workflowProcess = workflowProcessService.findByVersion(processId, version);
		if(workflowProcess==null){
			throw new IwdBusinessException("流程未找到");
		}
		
		ModelAndView mav = new ModelAndView("workflow/trace");
		mav.addObject("svg", workflowProcess.getDesignerXml());
		mav.addObject("trace",trace);
		
		return mav;
	}
	
}
