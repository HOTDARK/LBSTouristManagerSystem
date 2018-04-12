package com.hd.sys.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.core.utils.Common;
import com.hd.sys.entity.ServerStatus;

/**
 * 可以收集的信息包括： 
 * 1：CPU信息，包括基本信息（vendor、model、mhz、cacheSize）和统计信息（user、sys、idle、nice、wait） 
 * 2：文件系统信息，包括Filesystem、Size、Used、Avail、Use%、Type 
 * 3：事件信息，类似Service Control Manager 
 * 4：内存信息，物理内存和交换内存的总数、使用数、剩余数；RAM的大小 
 * 5：网络信息，包括网络接口信息和网络路由信息 
 * 6：进程信息，包括每个进程的内存、CPU占用数、状态、参数、句柄
 * 7：IO信息，包括IO的状态，读写大小等
 * 8：服务状态信息
 * 9：系统信息，包括操作系统版本，系统资源限制情况，系统运行时间以及负载，JAVA的版本信息等.
 */
@Controller
@RequestMapping("/serverMonitor")
public class ServerMonitorAction{

	@LogOpt(level=FunLogConst.LEVEL_2, desc=FunLogConst.DESC_SYSTEM_MONITOR, parentDesc=FunLogConst.DESC_SYSTEM)
	@RequestMapping("/forwardMonitor")
	public String forwardMonitor(HttpServletRequest request) {
		return "system/server_monitor.jsp";
	}

	/**
	 * 获取服务器基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/info")
	public Map<String, Object> serverBaseInfo() throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ServerStatus status = ServerStatus.findServerStatus();
		String cpuUsage = status.getCpuUsage();
		long FreeMem = status.getFreeMem();
		long useMem = status.getUsedMem();
		long TotalMem = status.getTotalMem();
		String serverUsage = Common.fromUsage(useMem, TotalMem);
		dataMap.put("cpuUsage", cpuUsage);//cpu
		dataMap.put("FreeMem", FreeMem);
		dataMap.put("TotalMem", TotalMem);
		dataMap.put("serverUsage", serverUsage);//ram
		long JvmFreeMem = status.getJvmFreeMem();
		long JvmTotalMem = status.getJvmTotalMem();
		String JvmUsage = Common.fromUsage(JvmTotalMem - JvmFreeMem, JvmTotalMem);
		dataMap.put("JvmFreeMem", JvmFreeMem);
		dataMap.put("JvmTotalMem", JvmTotalMem);
		dataMap.put("JvmUsage", JvmUsage);//jvm
		dataMap.put("cpu", PropertiesUtils.getProperty(this.getClass(),"common","cpu"));
		dataMap.put("jvm", PropertiesUtils.getProperty(this.getClass(),"common","jvm"));
		dataMap.put("ram", PropertiesUtils.getProperty(this.getClass(),"common","ram"));
		dataMap.put("data", status);
		return dataMap;
	}

	
	/**
	 * 修改配置　
	 * @param request
	 * @param nodeId
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
	@RequestMapping("/modifySer")
    public Map<String, Object> modifySer(HttpServletRequest request,String arr) throws Exception{
    	Map<String, Object> dataMap = new HashMap<String,Object>();
    	Map<String, Object> map = new HashMap<String,Object>();
    	try {
    	if (StringUtils.isNotBlank(arr)) {
			String[] str=arr.split(",");
			map.put("cpu", str[0]);
			map.put("ram", str[1]);
			map.put("jvm", str[2]);
		}
    	Set<String> keySet = map.keySet();
    	for (String str : keySet) {
    	  PropertiesUtils.modifyProperties(this.getClass(),"common",str, map.get(str).toString());
		}
		// 从输入流中读取属性列表（键和元素对）
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("flag", false);
		}
    	dataMap.put("flag", true);
		return dataMap;
    }
    
 
}
