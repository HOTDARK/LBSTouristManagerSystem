/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.utils.ResponseUtils;
import com.hd.workflow.app.commons.ActionResult;
import com.hd.workflow.app.commons.AppCommon;
import com.hd.workflow.app.model.WorkflowStencil;
import com.hd.workflow.app.service.WorkflowStencilService;

/**
 * 流程元素定义，模型定义 action
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-28 上午9:30:15
 */
@Controller
@RequestMapping(AppCommon.BASE_PATH+"/stencil")
public class StencilAction {
	
	@Autowired
	private WorkflowStencilService stencilService;
	
	@RequestMapping("index")
	public String index(){
		return "workflow/stencil_manage";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public  Map<String, Object> list(
			@RequestParam(value="start",defaultValue="0",required=false)int start,
			@RequestParam(value="length",defaultValue="10",required=false)int length,
			@RequestParam(value="group",required=false)String group,
			@RequestParam(value="stenId",required=false)String stenId,
			@RequestParam(value="stenName",required=false)String stenName){
		WorkflowStencil workflowStencil=new WorkflowStencil();
		if (StringUtils.isNotBlank(group)) {
			workflowStencil.setGroups(group);
		}
		if (StringUtils.isNotBlank(stenId)) {
			workflowStencil.setStencilId(stenId);
		}
		if (StringUtils.isNotBlank(stenName)) {
			workflowStencil.setTitle(stenName);
		}
		List<WorkflowStencil> data = stencilService.findByPage(workflowStencil, start, length);
		int total = stencilService.countByCondition(workflowStencil);
	    Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("draw", System.currentTimeMillis());
		rs.put("recordsTotal", total);
		rs.put("recordsFiltered",total);
		rs.put("data",data);
		return rs;
	}
	
	@RequestMapping("stegrouplist")
	@ResponseBody
	public List<WorkflowStencil> steGroupList(){
		return stencilService.findSteGroupList();
	}
	
	@RequestMapping("del")
	@ResponseBody
	public ActionResult del(@RequestParam("id")String id){
		String[] arr = id.split(",");
		for(String s : arr){
			stencilService.delete(Integer.valueOf(s));
		}
		
		return new ActionResult(true);
	}
	
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public ActionResult saveOrUpdate(WorkflowStencil stencil){
		ActionResult result = new ActionResult();
		
		if(StringUtils.isEmpty(stencil.getStencilId())){
			result.setMessage("ID不能为空");
		}
		
		if(StringUtils.isEmpty(stencil.getTitle())){
			result.setMessage("名称不能为空");
		}
		
		if(StringUtils.isEmpty(stencil.getClazz())){
			result.setMessage("类名不能为空");
		}
		
		if(!stencilService.checkStencilId(stencil.getId(), stencil.getStencilId())){
			result.setMessage("ID重复");
		}
		
		if(result.containsKey("msg")){
			return result;
		}
		
		stencil.setType("node");
		stencil.setPropertyPackages("overrideidpackage,namepackage,multiinstance_typepackage");
		stencil.setRoles("Activity,sequence_start,sequence_end,all");
		stencil.setState(1);
		
		stencilService.saveOrUpdate(stencil);
		result.setSuccess(true);
		
		return result;
	}
	
	@RequestMapping("/stencilset")
	public void getStencilSet(HttpServletResponse response){
		ResponseUtils.responseJson(response,stencilService.stencilset());
	}
}
