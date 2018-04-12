/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.utils.ResponseUtils;
import com.hd.workflow.app.UserRelationshipMapping;
import com.hd.workflow.app.commons.ActionResult;
import com.hd.workflow.app.commons.AppCommon;
import com.hd.workflow.app.convert.JsonConvertXml;
import com.hd.workflow.app.convert.element.ElementBuilder;
import com.hd.workflow.app.model.WorkflowProcess;
import com.hd.workflow.app.service.WorkflowProcessService;
import com.hd.workflow.engine.WorkflowEngine;
import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.impl.ExecutionImpl;
import com.hd.workflow.log.ExecutionInfoListener;

/**
 * 流程action
 * @version	0.0.1
 */
@Controller
@RequestMapping(AppCommon.BASE_PATH+"/process")
public class ProcessAction {
	
	private final static Logger log = LoggerFactory.getLogger(ProcessAction.class);
	
	@Autowired
	@Qualifier("dynamicElementBuilder")
	private ElementBuilder dynamicElementBuilder;
	
	@Autowired
	private WorkflowProcessService workflowProcessService;
	
	@Autowired
	private WorkflowEngine engine;
	
	@Autowired
	private UserRelationshipMapping userRelationshipMapping;
	
	@RequestMapping("index")
	public String index(){
		return "workflow/process_manage";
	}
	
	@RequestMapping("all")
	@ResponseBody
	public List<WorkflowProcess> all(){
		return workflowProcessService.getAllProcessWithoutDetail();
	}
	
	@RequestMapping("process_list")
	@ResponseBody
	public Object processList(HttpSession session,
			@RequestParam(value="start",defaultValue="0",required=false)int start,
			@RequestParam(value="length",defaultValue="10",required=false)int length){
		WorkflowProcess condition = new WorkflowProcess();
		condition.setState(1);
		condition.setResourceId(0);
		condition.setSortColumns("id desc");
		
		String uid = userRelationshipMapping.getUid(session);
		if(!AppCommon.isShowAll(uid)){
			condition.setUid(uid);
		}
		
		List<WorkflowProcess> data = workflowProcessService.findByCondition(condition, start, length);
		int total = workflowProcessService.countByCondition(condition);
		
		userRelationshipMapping.fill(data);
		
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("draw", System.currentTimeMillis());
		rs.put("recordsTotal", total);
		rs.put("recordsFiltered",total);
		rs.put("data",data);
		
		return rs;
	}
	
	@RequestMapping("preview")
	public void preview(HttpServletResponse response,@RequestParam("id")int id){
		WorkflowProcess process = workflowProcessService.findById(id);
		if(process!=null){
			ResponseUtils.responseHtml(response, process.getDesignerXml());
		}
	}
	
	
	@RequestMapping("previewPid")
	public void previewPid(HttpServletResponse response,@RequestParam("processId")String processId){
		WorkflowProcess process = workflowProcessService.findByProcessId(processId);
		if(process!=null){
			ResponseUtils.responseHtml(response, process.getDesignerXml());
		}
	}
	
	@RequestMapping("history")
	@ResponseBody
	public List<WorkflowProcess> history(@RequestParam("id")int id){
		return workflowProcessService.getAllVersion(id);
	}
	
	@RequestMapping("del")
	@ResponseBody
	public ActionResult del(@RequestParam("id")String id){
		String[] arr = id.split(",");
		for(String s : arr){
			workflowProcessService.delete(Integer.valueOf(s));
		}
		return new ActionResult(true);
	}
	
	@RequestMapping("get")
	public void get(HttpServletResponse response,@RequestParam(value="id",defaultValue="0",required=false)int id){
		if(id>0){
			WorkflowProcess process = workflowProcessService.findById(id);
			if(process!=null){
				ObjectNode json = JsonNodeFactory.instance.objectNode();
				
				json.put("name", process.getName());
				json.put("description",process.getDescription());
				json.put("modelId",process.getId());
				json.put("processId", process.getProcessId());
				
				ObjectMapper mapper = new ObjectMapper();
				try{
					json.put("model", mapper.readTree(process.getDesignerJson()));
				}catch (Exception e) {
					log.error(e.getMessage(),e);
				}
				
				ResponseUtils.responseJson(response, json.toString());
			}else{
				//流程未找到
			}
		}else{
			String model = "newmodel.json";
			
			InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream(model);
		    try {
				String str =  IOUtils.toString(stencilsetStream,"utf-8");
				ResponseUtils.responseJson(response, str);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	@RequestMapping("save")
	@ResponseBody
	public ActionResult save(
			HttpSession session,
			@RequestParam("json_xml")String jsonXml,
			@RequestParam("svg_xml")String svgXml,
			@RequestParam("name")String name,
			@RequestParam("process_id")String processId,
			@RequestParam("description")String description,
			@RequestParam(value="id",required=false,defaultValue="0")int id){
		ActionResult result = new ActionResult();
		
		if(StringUtils.isEmpty(processId)){
			result.setMessage("流程ID不能为空");
			return result;
		}
		
		if(!workflowProcessService.checkProcessId(id, processId)){
			result.setMessage("流程ID已存在");
			return result;
		}
		
		JsonConvertXml jsonConvertXml = new JsonConvertXml(jsonXml,dynamicElementBuilder);
		String xml = jsonConvertXml.toXml();
		
		WorkflowProcess process = new WorkflowProcess();
		process.setProcessId(jsonConvertXml.getProcessId());
		process.setDesignerJson(jsonXml);
		process.setDesignerXml(svgXml);
		process.setProcessXml(xml);
		process.setName(name);
		process.setDescription(description);
		process.setId(id);
		process.setUid(userRelationshipMapping.getUid(session));
		
		workflowProcessService.saveOrUpdate(process);
		
		result.setSuccess(true);
		result.put("id", process.getId());
		result.put("lastUpdated", System.currentTimeMillis());
		
		return result;
		
	}
	
	@RequestMapping("process_name")
	public void processName(HttpServletResponse response,@RequestParam("process_id")String processId){
		WorkflowProcess info = workflowProcessService.findByProcessId(processId);
		if(info!=null){
			ResponseUtils.responseJson(response, "{\"name\":\""+info.getName()+"\"}");
		}else{
			ResponseUtils.responseJson(response, "{\"name\":null}");
		}
	}
	
	@RequestMapping("run")
	@ResponseBody
	public void run(HttpServletResponse response,@RequestParam("id")String id){
		Future<ProcessInstance> future =  engine.startProcessInstance(id, null);
		
		try {
			ProcessInstance instance = future.get();
			Exception ex = ((ExecutionImpl)instance).getException();
			if(ex!=null){
				ex.printStackTrace();
			}
			
			instance.getVariable(ExecutionInfoListener.EXEC_SEQUENCE_KEY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		ResponseUtils.responseJson(response, "ok");
		
	}
}
