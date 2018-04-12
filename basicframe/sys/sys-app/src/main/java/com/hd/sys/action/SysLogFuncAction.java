package com.hd.sys.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.utils.DateUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysOrg;
import com.hd.sys.entity.vo.SysLogFuncCountVo;
import com.hd.sys.service.SysLogFuncService;
import com.hd.sys.service.SysOrgService;

/**
 * <p>类名：SysLogAction </p>
 * <p>描述：系统日志页面控制类 </p>
 * <p>作者：jl </p>
 * <p>时间：2015年1月7日 星期三 </p>      
 */
@Controller
@RequestMapping("/sysLogFunc")
public class SysLogFuncAction {
	
	@Autowired
	private SysLogFuncService sysLogFuncService;
	
	@Autowired
	private SysOrgService sysOrgService;
	
	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_STATISTICS_FUN, parentDesc=FunLogConst.DESC_STATISTICS)
	@RequestMapping("/forwardLogFunc")
	public String forwardLogFunc(HttpServletRequest request){
		return "statis/func_use_chart.jsp";
	}
	
	@RequestMapping("/toFunctionCountListPage")
	public String toFunctionCountListPage(HttpServletRequest request,Model model) throws Exception{
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String area = request.getParameter("area");
		if (StringUtils.isNotBlank(startTime)) {
			model.addAttribute("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			model.addAttribute("endTime", endTime);
		}
		if (StringUtils.isNotBlank(area)) {
			try {
				area=new String(area.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			model.addAttribute("area", area);
		}
		List<SysOrg> lists = sysOrgService.getOrgData(new SysOrg());
		if (lists!=null&&lists.size()>0) {
			model.addAttribute("lists", lists);
		} 
		return "statis/func_use_list.jsp";
	}
	
	
	@RequestMapping("/syslogCount")
	@ResponseBody
	public List<SysLogFuncCountVo> syslogCount(HttpServletRequest request,SysLogFuncCountVo sysLog){
		List<SysLogFuncCountVo> clist = new ArrayList<SysLogFuncCountVo>();
		clist = this.sysLogFuncService.countSysLogByArea(sysLog);
        return clist;
	}
	
	@RequestMapping("/syslogCountByArea")
	@ResponseBody
	public ActionResult syslogCountByArea(HttpServletRequest request,SysLogFuncCountVo sysLog){
		ActionResult res = new ActionResult();
		try {
			List<SysLogFuncCountVo> clist = this.sysLogFuncService.countSysLogByArea(sysLog);
			Map<String,Integer> counts = new HashMap<String,Integer>();
			if(com.hd.sfw.core.utils.CollectionUtils.isNotEmpty(clist)){
				for (SysLogFuncCountVo slc : clist) {
					Integer count = counts.get(slc.getOrgName());
					if(null == count){
						counts.put(slc.getOrgName(), Integer.parseInt(slc.getCount()));
					}else{
						counts.put(slc.getOrgName(), Integer.parseInt(slc.getCount())+count);
					}
				}
			}
			res.put("xAxis", counts.keySet().toArray(new String[counts.keySet().size()]));
			res.put("nums", counts.values().toArray(new Integer[counts.values().size()]));
			res.setSuccess(true);
		} catch (Exception e) {
			res.setMessage("加载功能使用统计数据出错："+e.getMessage());
		}
		return res;
	}
	
	@RequestMapping("/chatExportCount")
	@ResponseBody
	public boolean chatExportCount(HttpServletRequest request,HttpServletResponse response,SysLogFuncCountVo sysLog){
		try {
			List<SysLogFuncCountVo> voList = this.sysLogFuncService.countDescSysLogByArea(sysLog);
			if(com.hd.sfw.core.utils.CollectionUtils.isEmpty(voList)){
				return false;
			}
			XSSFWorkbook xw = new XSSFWorkbook();
			XSSFSheet xs = xw.createSheet("总功能统计");
			XSSFRow rowHead = xs.createRow(0);
			int index = 0;
			int i=1;
			rowHead.createCell(index++).setCellValue("地市");	
			rowHead.createCell(index++).setCellValue("访问次数");	
			for (SysLogFuncCountVo sysLogCountVo : voList) {
				index = 0;
				XSSFRow rowi = xs.createRow(i++);
				rowi.createCell(index++).setCellValue(sysLogCountVo.getOrgName());	
				rowi.createCell(index++).setCellValue(sysLogCountVo.getCount());	
			}
			String fileName = "FunctionCount"+DateUtils.formatStr(new Date(), DateUtils.SDF_YYYYMMDDHHMMSS)+".xlsx";
			response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes()));  
			OutputStream os= new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/vnd.ms-excel;charset=gbk");  
            xw.write(os);
            os.flush();  
            os.close();  
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping("/exportCount")
	@ResponseBody
	public boolean exportCount(HttpServletRequest request,HttpServletResponse response,SysLogFuncCountVo sysLog){
		List<SysLogFuncCountVo> voList = null;
		try {
			voList = this.sysLogFuncService.countSysLogByArea(sysLog);
			if(CollectionUtils.isEmpty(voList)){
				return false;
			}
			XSSFWorkbook xw = new XSSFWorkbook();
			XSSFSheet xs = xw.createSheet("功能统计");
			XSSFRow rowHead = xs.createRow(0);
			int index = 0;
			rowHead.createCell(index++).setCellValue("地市");	
			rowHead.createCell(index++).setCellValue("功能名称");	
			rowHead.createCell(index++).setCellValue("访问次数");	
			
			for (int i = 0; i < voList.size(); i++) {
				index = 0;
				SysLogFuncCountVo model = voList.get(i);
				XSSFRow rowi = xs.createRow(i+1);
				rowi.createCell(index++).setCellValue(model.getOrgName());	
				rowi.createCell(index++).setCellValue(model.getFunctionName());	
				rowi.createCell(index++).setCellValue(model.getCount());	
			}
			String fileName = "FunctionCount"+DateUtils.formatStr(new Date(), DateUtils.SDF_YYYYMMDDHHMMSS)+".xlsx";
			response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes()));  
			OutputStream os= new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/vnd.ms-excel;charset=gbk");  
            xw.write(os);
            os.flush();  
            os.close();  
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

