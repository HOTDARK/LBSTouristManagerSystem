package com.hd.tsa.action.upload;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hd.tsa.service.common.UploadFileService;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/fileoper")
public class UploadFileAction {
	
	/** log4j */
	private static Logger logger = Logger.getLogger(UploadFileAction.class);
	private static	String url = PropertiesUtils.getProperty(UploadFileAction.class, "propftp", "ftpUrl");
	private static	int port= Integer.valueOf(PropertiesUtils.getProperty(UploadFileAction.class, "propftp", "ftpPort"));
	private static String username = PropertiesUtils.getProperty(UploadFileAction.class, "propftp", "ftpUserName");
	private static String password = PropertiesUtils.getProperty(UploadFileAction.class, "propftp", "ftpPassword");
	@Autowired
	private UploadFileService uploadFileService;
	
	// 实例化 统一业务API入口
	private IService iService = new WxService();
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JSONObject uploadFiles(String model, HttpServletRequest request){
	    String path = PropertiesUtils.getProperty(UploadFileAction.class, "propftp", "ftpRoot")+model;
		String filepath = "";
		JSONObject obj = new JSONObject();  
		try {
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
                        String originalFileName = file.getOriginalFilename();
						int index = file.getOriginalFilename().lastIndexOf('.');
						String fileExtension = originalFileName.substring(index+1, originalFileName.length());
						String fileName = new Date().getTime() +"."+ fileExtension;
						Long fileSize = file.getSize();
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
				        ftp.storeFile(fileName,file.getInputStream());  

						filepath = path+"/"+fileName;
						obj.put("filePath", filepath);
						obj.put("oldFileName", originalFileName);
						obj.put("fileName",fileName);
						obj.put("fileSize",fileSize);
						obj.put("fileExtension",fileExtension);
						obj.put("result", true);
					}
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("result", false);
			logger.error("上传文件出错：" + e.getMessage(), e);
		}
		return obj;
	}
	
	@ResponseBody
	@RequestMapping("/downFile")
	public void downExcel(String filepath,String downfileName, HttpServletResponse response,HttpServletRequest request){		
		String fileName="";
		if(filepath!=null&&filepath.length()>0){
			String fileEnd = filepath.substring(filepath.lastIndexOf("."));
			if(downfileName!=null&&downfileName.length()>0){
				fileName = downfileName+fileEnd;
			}else{			
				fileName = new Date().getTime()+fileEnd;
			}
		}		
		try {
			String userAgent = request.getHeader("User-Agent");
			//针对IE或者以IE为内核的浏览器：
			if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
			fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} else {
			//非IE浏览器的处理：
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
			/**
			if(remotePath.length()>0&&ftpfileName.length()>0){
			  FTPClient ftp = new FTPClient();
			  int reply;
			  ftp.setControlEncoding("UTF-8");
			  ftp.connect(url, port);
			  // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			  ftp.login(username, password);// 登录			  
			  reply = ftp.getReplyCode();
			  if (!FTPReply.isPositiveCompletion(reply)) {
				 ftp.disconnect();
			  }
			  ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
			  ftp.enterLocalPassiveMode();
			  ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			  FTPFile[] fs = ftp.listFiles();
			  for (FTPFile ff : fs) {
				if (ff.getName().equals(ftpfileName)) {			
					OutputStream os = response.getOutputStream();
					ftp.retrieveFile(ff.getName(), os);
				}
			  }
			  ftp.logout();		
			}
			  */
			OutputStream os = response.getOutputStream();
			uploadFileService.downFile(filepath, os);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("下载文件出错：" + e.getMessage(), e);
			response.setHeader("Content-Disposition", "attachment;fileName=file does not exist");
		}
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
   @RequestMapping(value="/uploadImage",method={RequestMethod.POST})
	public Map<String,String> uploadImage(@RequestParam("repairImg")CommonsMultipartFile[] files, HttpServletRequest request) {

		List<Map<String,String>> list=null;
		Map<String,String> pic=null;
		try {   
		     list = uploadFileService.uploadFiles(files,"repair");
		     pic=list.get(0);
		     pic.put("code", "1");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传文件出错：" + e.getMessage(), e);
			return null;
		}
		return pic;
	}
	
    /**
     * 从微信服务器下载图片，并上传到本地服务器
     * @param request
     * @param serverIds
     * @param ftpDir
     * @return
     */
    @ResponseBody
    @RequestMapping("/downloadImgFromWx")
	public ActionResult downloadImgFromWx(HttpServletRequest request, String serverIds, String ftpDir){
    	ActionResult result = new ActionResult();
		try {
			String[] serverIdArr = serverIds.split(",");
			Map<String, File> fileMap = new HashMap<String, File>();
			String path=request.getSession().getServletContext().getRealPath("/")+"tempImg/";
			File fileDir = new File(path);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			for(int i=0;i<serverIdArr.length;i++){
				File file = iService.downloadTempMedia(serverIdArr[i], fileDir);
				fileMap.put(serverIdArr[i], file);
			}
			Map<String, String> map = uploadFileService.uploadWxImgs(fileMap, ftpDir);
			System.gc();
			result.put("paths", map);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("微信上传图片失败："+e.getMessage(), e);
			e.printStackTrace();;
		}
	   return result;
	}
    
    /**
     * 从微信服务器下载图片并上传本地服务器，图片数量不定
     * @param request
     * @param response
     * @param httpSession
     * @param openid
     * @param serverIds
     * @param ftpDir
     * @return
     */
    @LogOpt(level = FunLogConst.LEVEL_3, desc = "从微信服务器下载图片并上传至ftp", parentDesc = "微信管理")
	@RequestMapping("/downloadImgsFromWx")
	@ResponseBody
	public Map<String, Object> downloadImgsFromWx(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid, String serverIds,String ftpDir) {
		String[] serverIdArr = serverIds.split(",");
		Map<String, File> files = new HashMap<>();
		Map<String, Object> resMap=new HashMap<>();
		try {
			String path=request.getSession().getServletContext().getRealPath("/")+"tempImg/";
			File fileDir = new File(path);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			for (String serverId : serverIdArr) {
				File file = iService.downloadTempMedia(serverId, fileDir);
				files.put(serverId, file);
			}
			resMap = uploadFileService.uploadWxFiles(files, ftpDir);
			System.gc();
			if (CollectionUtils.isNotEmpty(files)) {
				files.forEach((k, v)->{
					files.get(k).delete();
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
}
