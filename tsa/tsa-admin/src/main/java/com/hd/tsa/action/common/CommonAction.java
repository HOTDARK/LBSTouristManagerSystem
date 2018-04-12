package com.hd.tsa.action.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/common")
public class CommonAction {
    @Autowired
	private SysUserService sysUserService;
    
    /** log4j */
	private static Logger logger = Logger.getLogger(CommonAction.class);
    
    private static	String url = PropertiesUtils.getProperty(CommonAction.class, "propftp", "ftpUrl");
	private static	int port= Integer.valueOf(PropertiesUtils.getProperty(CommonAction.class, "propftp", "ftpPort"));
	private static String username = PropertiesUtils.getProperty(CommonAction.class, "propftp", "ftpUserName");
	private static String password = PropertiesUtils.getProperty(CommonAction.class, "propftp", "ftpPassword");
    
	/**
	 * 跳转选择人员页面
	 * @return
	 */
	@LogOpt(level=FunLogConst.LEVEL_3, desc="跳转选择人员页面", parentDesc="跳转选择人员页面")
	@RequestMapping("/forwardSelectPerson")
	public String forwardSelectStaff(){
		return "common/select_person.jsp";
	}
	
	/**
	 * 跳转选择单个用户页面
	 * @author wh
	 * @date 2017年7月6日
	 * @return
	 */
	@RequestMapping("forwordSelectOneUser")
	public String forwordSelectPage(){
		return "common/select_oneUser.jsp";
	}
	
	/**
	 * 跳转单位选择页面
	 * @return
	 */
    @RequestMapping("/forwardSelectOrg")
   public String forwardSelectOrg(){
	   return "common/selectOrg.jsp";
   }
    
	
	/**
	 * 通过账号查询用户
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findPersonsByAccount")
	public List<SysUser> findPersonsByAccount(String account){
		List<SysUser> list = null;
		try {
			String[] accounts = account.split(",");
			StringBuffer personAccount = new StringBuffer();
			boolean flag = false;
			for (int i = 0; i < accounts.length; i++) {
				if(StringUtils.isNotEmpty(accounts[i])){
					if(flag){
						personAccount.append(",");
					}
					personAccount.append("'").append(accounts[i]).append("'");
					flag = true;
				}
			}
			list = sysUserService.findByUserAccounts(personAccount.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 多文件上传 ftp
	 * @author wh
	 * @date 2017年8月22日
	 * @param files
	 * @param request
	 * @param model 文件夹名字
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadImage", method = { RequestMethod.POST })
	public JSONObject uploadImage(@RequestParam("files")CommonsMultipartFile[] files, HttpServletRequest request, String model) {
		String path = PropertiesUtils.getProperty(CommonAction.class, "propftp", "ftpRoot")+model;
		String filepath = "";
		JSONObject obj = new JSONObject();
		try {
			// 上传 连接ftp服务器 
			FTPClient ftp = new FTPClient();  
			int reply;  
			ftp.setDefaultTimeout(150000);
			ftp.connect(url, port);// 连接FTP服务器  
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
			ftp.login(username, password);// 登录  
			reply = ftp.getReplyCode();  
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();  
				throw new RuntimeException("ftp连接失败！"); 
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);    			
			ftp.makeDirectory(path); //创建目录
			ftp.changeWorkingDirectory(path);//切换目录
			ftp.setBufferSize(1024);//设置缓冲区 如果未设置该参数，将会一个字节一个字节的读取数据
			ftp.setControlEncoding("UTF-8");
			ftp.enterLocalPassiveMode(); //遍历目录
			List<Map<String, String>> list = new ArrayList<>();
			for(int i=0;i<files.length;i++){
				Map<String,String> map = new HashMap<>();
				CommonsMultipartFile file = files[i];
                String originalFileName = file.getOriginalFilename();
				int index = file.getOriginalFilename().lastIndexOf('.');
				String fileExtension = originalFileName.substring(index+1, originalFileName.length());
				String fileName = new Date().getTime() +"."+ fileExtension;
		        ftp.storeFile(fileName,file.getInputStream());  

				filepath = path+"/"+fileName;
				map = new HashMap<>();
				map.put("filePath", filepath);
				list.add(map);
			}
			obj.put("result", true);
			obj.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("result", false);
			logger.error("上传文件出错：" + e.getMessage(), e);
		}
		return obj;
	}
}
