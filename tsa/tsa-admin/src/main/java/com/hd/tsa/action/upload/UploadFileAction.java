package com.hd.tsa.action.upload;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.service.common.UploadFileService;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

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
	private static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。  
	private static final int wdFormatPDF = 17;// PDF 格式  
	private static final int ppSaveAsPDF = 32;
	private static final int XLTYPE_PDF = 0;
	@Autowired
	private UploadFileService uploadFileService;

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
	
	@RequestMapping("/forwardLook")
	public ModelAndView forwardLook(String filepath){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("filepath", filepath);
		modelAndView.setViewName("contract/previewFile_content.jsp");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/previewFile")
	public void previewFile(String filepath,HttpServletRequest request, HttpServletResponse response){
		response.reset();
		boolean noDowload = true;
		String remotePath = ""; //ftp 文件目录路径  down/file
		String ftpfileName = "";//ftp 文件的名称 xxx.doc
		String downLoadPath = PropertiesUtils.getProperty(UploadFileAction.class, "common", "downLoadPath");//保存下载文件的路径  down/file
		String localPath = ""; //文件的路径 down/file/xxx.doc
		String previewPdfFile = "" ;//文件的路径 down/file/xxx.pdf
		if(filepath!=null&&filepath.length()>0){
		    ftpfileName= filepath.substring(filepath.lastIndexOf("/")+1);
		    remotePath = filepath.substring(0, filepath.lastIndexOf("/"));
		    localPath = downLoadPath+File.separator+ftpfileName;
		}
		try {
			if (remotePath.length() > 0 && ftpfileName.length() > 0) {//文件路径不为空
				File tempDirPath = new File(downLoadPath);
				if (!tempDirPath.exists()) {
					tempDirPath.mkdirs();
				}
				File localFile = new File(localPath);
				if (!localFile.exists()) {//没有下载则从ftp下载文件
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
							OutputStream os = new FileOutputStream(localFile);
							ftp.retrieveFile(ff.getName(), os);
							break;
						}
					}
					ftp.logout();
				}
			}
			File file = new File(localPath);
			if (!file.exists()) {
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("<div style='text-align:center;'><font style='font-weight:800;color:red;font-size:14px;text-align:center;'>文件不存在!</font></div>");
				return;
			}
			String sufix = getFileSufix(filepath);
			String name  = ftpfileName.substring(0, ftpfileName.lastIndexOf("."));
			OutputStream outp = response.getOutputStream();
			String contextType = getContextType(sufix, false);
			response.setContentType(contextType);
			response.setCharacterEncoding("utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + ftpfileName + "\"");
			response.addHeader("Content-Transfer-Encoding", "binary");
			// 直接查看TXT类型
			String viewTxtType[] = { "txt" };
			// 直接查看文件类型
			String viewType[] = { "jpg", "jpeg", "png", "gif", "pdf", "docx", "doc", "xls", "xlsx", "ppt", "pptx", "txt" };
			// 转换成pdf查看类型
			String viewForPDFType[] = { "docx", "doc", "xls", "xlsx", "ppt", "pptx" };
			// 读取文件并输出
			boolean isSucc = false; // 原始的realPath，防止后面word转为pdf报错，用回原word的地址
			// 图片和pdf文件直接打开不下载
			if (isHaving(viewType, sufix)) {
				InputStream in = null;
				ServletOutputStream out = null;
				// 判断是否要转换的类型
				// 如果word文档不能正常转换为pdf，则直接下载word文档
				if (isHaving(viewForPDFType, sufix)) {
					String pdfPath = PropertiesUtils.getProperty(this.getClass(), "common", "downLoadPath").concat(File.separator).concat("pdf").concat(File.separator).concat("view");
					File tempDirPath = new File(pdfPath);
					if (!tempDirPath.exists()) {
						tempDirPath.mkdirs();
					}
					previewPdfFile = pdfPath + File.separator + name + ".pdf";
					officeToPdf(localPath, previewPdfFile);
				}else{
					previewPdfFile=localPath;
				}

				if (previewPdfFile != null && !"".equals(previewPdfFile)) {
					File file2 = new File(previewPdfFile);
					if (!file2.exists()) {
						previewPdfFile = localPath;
					} else {
						try {
							isSucc = true;
							response.reset();
							if (isHaving(viewTxtType, sufix)) {// TXT预览特殊处理
								response.setCharacterEncoding("UTF-8");
								response.getWriter().print(readTxtFile(previewPdfFile));
								return;
							}

							out = response.getOutputStream();
							in = new BufferedInputStream(new FileInputStream(previewPdfFile));
							int ch;
							while ((ch = in.read()) != -1) {
								out.print((char) ch);
							}
						} catch (Exception e) {

						} finally {
							if (in != null) {
								in.close();
							}
							if (out != null) {
								out.flush();
								out.close();
							}
						}
					}
				} else {
					previewPdfFile = localPath;
				}
			}

			// 无法预览的进行下载
			if (!isSucc) {
				if (noDowload) {// 不进行下载
					response.reset();
					response.setCharacterEncoding("utf-8");
					response.getWriter().print("<div  style='text-align:center;'><font style='font-weight:800;color:red;font-size:14px;text-align:center;'>文件暂不支持预览,请下载到本地查看!</font></div>");
					return;
				}
				response.setContentLength((int) file.length());
				FileInputStream in = null;
				try {
					in = new FileInputStream(previewPdfFile);
					byte[] b = new byte[1024];
					int i = 0;
					while ((i = in.read(b)) > 0) {
						outp.write(b, 0, i);
					}
					outp.flush();

				} catch (IOException e) {
					// logger.info("用户取消操作");
				} catch (Exception e) {

				} finally {
					if (in != null) {
						in.close();
						in = null;
					}
					if (outp != null) {
						outp.close();
						outp = null;
						response.flushBuffer();
					}
				}

			}
		}catch(Exception e){
			
		}
	}
	
	public boolean isHaving(String[] fileType,String type){
		boolean flag = false;
		for(int i=0;i<fileType.length;i++){
			if(fileType[i].equalsIgnoreCase(type)){
				flag = true;
				return flag;
			}
		}
		return flag;
	}
	
	/**
	 * 获取内容类型。
	 * 
	 * @param extName
	 * @return
	 */
	public String getContextType(String extName, boolean isRead) {
		String contentType = "application/octet-stream";
		if ("jpg".equalsIgnoreCase(extName) || "jpeg".equalsIgnoreCase(extName)) {
			contentType = "image/jpeg";
		} else if ("png".equalsIgnoreCase(extName)) {
			contentType = "image/png";
		} else if ("gif".equalsIgnoreCase(extName)) {
			contentType = "image/gif";
		} else if ("doc".equalsIgnoreCase(extName) || "docx".equalsIgnoreCase(extName)) {
			contentType = "application/msword";
		} else if ("xls".equalsIgnoreCase(extName) || "xlsx".equalsIgnoreCase(extName)) {
			contentType = "application/vnd.ms-excel";
		} else if ("ppt".equalsIgnoreCase(extName) || "pptx".equalsIgnoreCase(extName)) {
			contentType = "application/ms-powerpoint";
		} else if ("rtf".equalsIgnoreCase(extName)) {
			contentType = "application/rtf";
		} else if ("htm".equalsIgnoreCase(extName) || "html".equalsIgnoreCase(extName)) {
			contentType = "text/html";
		} else if ("swf".equalsIgnoreCase(extName)) {
			contentType = "application/x-shockwave-flash";
		} else if ("bmp".equalsIgnoreCase(extName)) {
			contentType = "image/bmp";
		} else if ("mp4".equalsIgnoreCase(extName)) {
			contentType = "video/mp4";
		} else if ("wmv".equalsIgnoreCase(extName)) {
			contentType = "video/x-ms-wmv";
		} else if ("wm".equalsIgnoreCase(extName)) {
			contentType = "video/x-ms-wm";
		} else if ("rv".equalsIgnoreCase(extName)) {
			contentType = "video/vnd.rn-realvideo";
		} else if ("mp3".equalsIgnoreCase(extName)) {
			contentType = "audio/mp3";
		} else if ("wma".equalsIgnoreCase(extName)) {
			contentType = "audio/x-ms-wma";
		} else if ("wav".equalsIgnoreCase(extName)) {
			contentType = "audio/wav";
		}
		if ("pdf".equalsIgnoreCase(extName))// pdf不下载文件，读取文件内容
		{
			contentType = "application/pdf";
		}
		if (("sql".equalsIgnoreCase(extName) || "txt".equalsIgnoreCase(extName)) && isRead)// pdf不下载文件，读取文件内容
		{
			contentType = "text/plain";
		}
		return contentType;
	}
		
	public void officeToPdf(String officePath, String pdfPath){
		System.out.println("officePath："+officePath);
		System.out.println("pdfPath："+pdfPath);
		File file  = new File(pdfPath);
		if(file.exists()){//已存在转换好文件则用该文件,无需每次都转
			System.out.println("已经存在，无需转换");
			return;
		}
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		if (Pattern.matches("Linux.*", osName)) {
			System.out.println("openoffice");
			office2PDF(officePath, pdfPath);
		} else if (Pattern.matches("Windows.*", osName)) { 
			System.out.println("jacob");
			String suffix = getFileSufix(officePath);
			if (suffix.equals("doc") || suffix.equals("docx")) {
				wordToPdf(officePath, pdfPath);  
			} else if (suffix.equals("xls") || suffix.equals("xlsx")) {  
				excelToPdf(officePath, pdfPath);  
			} else if (suffix.equals("ppt") || suffix.equals("pptx")) {  
				pptToPdf(officePath, pdfPath);
			} else {
				System.out.println("无法转换");
			}
		}
	}
	
	 /**  
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为  
     * http://www.openoffice.org/  
     *   
     * <pre>  
     * 方法示例:  
     * String sourcePath = "F:\\office\\source.doc";  
     * String destFile = "F:\\pdf\\dest.pdf";  
     * Converter.office2PDF(sourcePath, destFile);  
     * </pre>  
     *   
     * @param sourceFile  
     *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc,  
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc  
     * @param destFile  
     *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf  
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,  
     *         则表示操作成功; 返回1, 则表示转换失败  
     */    
    public static void office2PDF(String sourceFile, String destFile) {
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                System.out.println("源文件不存在");
            }
            // 如果目标路径不存在, 则新建该路径    
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            // connect to an OpenOffice.org instance running on port 8100    
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();
            // convert    
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
            // close the connection    
            connection.disconnect();
        } catch (ConnectException e) {    
            e.printStackTrace();    
        }
    }
	
	/**
	 * 获取文件格式
	 * @param argFilePath
	 * @return
	 */
	private String getFileSufix(String argFilePath) {
		int splitIndex = argFilePath.lastIndexOf(".");
		return argFilePath.substring(splitIndex + 1);
	}
	
	/**
	 * WORD转PDF
	 * @param wordPath
	 * @param pdfPath
	 * @return
	 */
	private void wordToPdf(String wordPath, String pdfPath) {
		ActiveXComponent activeXComponent = null;
		try {
			System.out.println("开始转化WORD为PDF...");
			long start = System.currentTimeMillis();
			activeXComponent = new ActiveXComponent("Word.Application");
			activeXComponent.setProperty("Visible", false);
			Dispatch docs = activeXComponent.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.call(docs, //
					"Open", //
					wordPath, // FileName
					false, // ConfirmConversions
					true // ReadOnly
			).toDispatch();
			Dispatch.call(doc, //
					"SaveAs", //
					pdfPath, // FileName
					wdFormatPDF);
			Dispatch.call(doc, "Close", false);
			long end = System.currentTimeMillis();
			System.out.println("转换完成，用时：" + (end - start) / 1000 + "s");
		} catch (Exception e) {
			System.out.println("转换失败：" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (activeXComponent != null)
				activeXComponent.invoke("Quit", wdDoNotSaveChanges);
		}
	}
	
	/**
	 * EXCEL转化成PDF
	 * @param excelPath
	 * @param pdfPath
	 * @return
	 */
	private void excelToPdf(String excelPath, String pdfPath) {
		ActiveXComponent activeXComponent = null;
		try {
			System.out.println("开始转化EXCEL为PDF...");
			long start = System.currentTimeMillis();
			activeXComponent = new ActiveXComponent("Excel.Application");
			activeXComponent.setProperty("Visible", false);
			Dispatch excels = activeXComponent.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.call(excels, //
					"Open", //
					excelPath, //
					false, //
					true //
			).toDispatch();
			Dispatch.call(excel, //
					"ExportAsFixedFormat", //
					XLTYPE_PDF, //
					pdfPath);//
			Dispatch.call(excel, "Close", false);
			long end = System.currentTimeMillis();
			System.out.println("转换成功，用时：" + (end - start) / 1000 + "s"); 
		} catch (Exception e) {
			System.out.println("转换失败：" + e.getMessage());
		} finally {
			if (activeXComponent != null) {
				activeXComponent.invoke("Quit");
			}
		}
	}
	
	
	/**
	 * PPT转化成PDF
	 * @param pptPath
	 * @param pdfPath
	 */
	private void pptToPdf(String pptPath, String pdfPath) {
		ActiveXComponent activeXComponent = null;
		try {
			System.out.println("开始转化PPT为PDF...");
			long start = System.currentTimeMillis();
			activeXComponent = new ActiveXComponent("PowerPoint.Application");
//			activeXComponent.setProperty("Visible", false);
			Dispatch ppts = activeXComponent.getProperty("Presentations").toDispatch();
			Dispatch ppt = Dispatch.call(ppts, //
					"Open", //
					pptPath, //
					true, // ReadOnly
					// false, // Untitled指定文件是否有标题
					false// WithWindow指定文件是否可见
			).toDispatch();
			Dispatch.invoke(ppt, "SaveAs", Dispatch.Method, new Object[] { pdfPath, new Variant(ppSaveAsPDF) }, new int[1]);
			Dispatch.call(ppt, "Close");
			long end = System.currentTimeMillis();
			System.out.println("转换成功，用时：" + (end - start) / 1000 + "s");
		} catch (Exception e) {
			System.out.println("转换失败：" + e.getMessage());
		} finally {
			if (activeXComponent != null) {
				activeXComponent.invoke("Quit");
			}
		}
	}
	
	
	/**
	 * 
	 * 功能：Java读取txt文件的内容
	 * 
	 * 步骤：1：先获得文件句柄
	 * 
	 * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 
	 * 3：读取到输入流后，需要读取生成字节流
	 * 
	 * 4：一行一行的输出。readline()。
	 * 
	 * 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public String readTxtFile(String filePath) {
		
		StringBuffer str = new StringBuffer();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					str.append(lineTxt).append("\n");
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return str.toString();

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
	/*
   @ResponseBody
   @RequestMapping(value="/uploadImage",method={RequestMethod.POST})
	public JSONObject uploadImage(@RequestParam("file_data")CommonsMultipartFile[] files, HttpServletRequest request, String model , String uploadType ,String repairNum) {
		JSONObject objResult = new JSONObject();
		try {   
		     List<Map<String,String>> list = uploadFileService.uploadFiles(files,model);
		     for (Map<String, String> map : list) {
		    	 RepairAttachment entity = new RepairAttachment();
		    	 entity.setUploadType(uploadType);
		    	 entity.setOldFileName(map.get("oldFileName"));
		    	 entity.setFileName(map.get("fileName"));
		    	 entity.setSize(Long.parseLong(map.get("fileSize")));
		    	 entity.setFilePath(map.get("filePath"));
		    	 entity.setFileSuffix(map.get("fileExtension"));
		    	 entity.setCreateDate(new Date());
		    	 entity.setRepairNum(repairNum);
		    	 repairAttachmentService.save(entity);
			 }
			 objResult.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			objResult.put("result", false);
			logger.error("上传文件出错：" + e.getMessage(), e);
		}
		return objResult;
	}
	*/
}
