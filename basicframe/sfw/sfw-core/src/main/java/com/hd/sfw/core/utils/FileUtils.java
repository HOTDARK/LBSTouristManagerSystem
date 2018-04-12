package com.hd.sfw.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * <p>类名：FileUtil </p>
 * <p>描述：文件工具类,主要包含文件保存,文件下载,大小验证等方法 </p>
 * <p>作者：cb </p>
 * <p>时间：2014-12-4 下午02:55:33 </p>
 *
 */
public class FileUtils {

	private static Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * 
	 * <p>描述：删除文件 </p>
	 * <p>日期：2014-12-4 下午03:14:00 </p>
	 * @param filePath 要删除的文件路径
	 */
	public static void deleteFile(String filePath) {   
        File file = new File(filePath);  
        if (file.exists()) {  
            file.delete();  
        }
    }
	/**
	 * 
	 * <p>描述：删除文件 </p>
	 * <p>日期：2014-12-4 下午03:14:00 </p>
	 * @param file 要删除的文件
	 */
	public static void deleteFile(File file) {   
        if (file.exists()) {  
            file.delete();  
        }
    }
	
	/**
	 * 
	 * <p>描述：验证文件大小是否超过指定大小 </p>
	 * <p>日期：2014-12-4 下午02:56:19 </p>
	 * @param file 要验证的文件
	 * @param maxLength 指定大小,单位是字节
	 * @return 如果超过指定大小则返回true,否则返回false
	 */
	public static boolean validateFileLength(File file, long maxLength){
		return file.length()>maxLength?true:false;
	}
	
	/**
	 * 
	 * <p>描述：根据路径创建一级或多级目录 </p>
	 * <p>日期：2014-12-4 下午02:57:23 </p>
	 * @param folderPath 目录路径
	 */
    public static void createFolder(String folderPath) {
        File dir = new File(folderPath);
        if (!dir.exists()) {
        	dir.mkdir();
        }
    }
    
    /**
     * 
     * <p>描述：删除文件夹及文件夹下的所有文件 </p>
     * <p>日期：2014-12-4 下午03:03:33 </p>
     * @param folderPath 要删除的文件夹
     */
    public static void deleteFolder(String folderPath){
    	File file = new File(folderPath);
		if(file.isDirectory()){
			//删除文件夹下的所有文件
			File[] f = file.listFiles();
			for (int i = 0; i < f.length; i++) {
				deleteFolder(f[i].getAbsolutePath());
			}
			//删除文件夹
			file.delete();
		}
		else{
			if(file.exists()){
				file.delete();
			}
		}
    }
    
    /**
     * 
     * <p>描述：将文件保存到目标地址 </p>
     * <p>日期：2014-12-4 下午02:58:12 </p>
     * @param file 要保存的文件
     * @param path 目标文件夹
     */
    public static void saveFile(File file,String path){
		if(file != null && path != null){
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				//如果目标文件夹不存在,则创建
				File dirs = new File(path);
				if(!dirs.exists()){
					dirs.mkdirs();
				}
				
				fos = new FileOutputStream(path+File.separatorChar+file.getName());   	         
				fis = new FileInputStream(file);   
				byte[] buffer = new byte[1024];   	
				int len;   
				while ((len = fis.read(buffer))!=-1)    
				{   
					fos.write(buffer, 0, len);   
				}   
 
			}  catch (Exception e) {
				logger.error("保存文件到目标地址出错："+e.getMessage(), e);
			} finally{
				if(fis!=null){
					try {
						fis.close();
						fis = null;
					} catch (IOException e) {
						logger.error("关闭输入流出错："+e.getMessage(), e);
					}
				}
				if(fos!=null){
					try {
						fos.close();
						fos = null;
					} catch (IOException e) {
						logger.error("关闭输出流出错："+e.getMessage(), e);
					}
				}
			}
		}
	}
    
    /**
     * 
     * <p>描述：文件下载 </p>
     * <p>日期：2014-12-4 下午05:31:30 </p>
     * @param response HttpServletResponse对象
     * @param filePath 文件路径
     * @param fileName 文件显示名字
     */
    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) {
        File file=new File(filePath);
        if(file.exists()){
        	InputStream is = null;
			BufferedInputStream bis = null;
			OutputStream os = null;
			BufferedOutputStream bos = null;
			try {
				is = new FileInputStream(file);
				bis = new BufferedInputStream(is);
				os = response.getOutputStream();
				bos = new BufferedOutputStream(os);
				response.setContentType("application/x-download");//设置response内容的类型
				response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));//设置头部信息
				int len = 0;
				byte[] buffer = new byte[2048];
				//开始向网络传输文件流
				while ((len = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				bos.flush();//这里一定要调用flush()方法
			} catch (Exception e) {
				logger.error("文件下载出错："+e.getMessage(), e);
			} finally {
				if(is!=null){
					try {
						is.close();
						is = null;
					} catch (IOException e) {
						logger.error("关闭输入流出错："+e.getMessage(), e);
					}
				}
				if(bis!=null){
					try {
						bis.close();
						bis = null;
					} catch (IOException e) {
						logger.error("关闭输入流出错："+e.getMessage(), e);
					}
				}
				if(os!=null){
					try {
						os.close();
						os = null;
					} catch (IOException e) {
						logger.error("关闭输出流出错："+e.getMessage(), e);
					}
				}
				if(bos!=null){
					try {
						bos.close();
						bos = null;
					} catch (IOException e) {
						logger.error("关闭输出流出错："+e.getMessage(), e);
					}
				}
			}
        }else{
            logger.error("文件不存在");
        }
    }
    /**
     * 
     * <p>描述：文件复制 </p>
     * <p>日期：2014-12-4 下午05:31:30 </p>
     * @param sourceFilePath 源文件地址
     * @param targetFilePath 目标文件地址
     * @param isDeleteSource 是否删除源文件
     * @throws IOException
     */
    public static void copyFile(String sourceFilePath, String targetFilePath,
			boolean isDeleteSource) throws IOException {
		copyFile(new File(sourceFilePath), new File(targetFilePath),
				isDeleteSource);
	}
    /**
     * 
     * <p>描述：文件复制 </p>
     * <p>日期：2014-12-4 下午05:31:30 </p>
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @param isDeleteSource 是否删除源文件
     * @throws IOException
     */
	public static void copyFile(File sourceFile, File targetFile,
			boolean isDeleteSource) throws IOException {
		int len;
		if (sourceFile == null) {
			logger.error("源文件不能为空");
		}
		if (!(sourceFile.exists())) {
			logger.error("找不到源文件: “"
					+ sourceFile.getAbsolutePath() + "”");
		}
		if (targetFile == null) {
			logger.error("目标文件不能为空");
		}
		if (!(targetFile.getParentFile().exists())) {
			targetFile.getParentFile().mkdirs();
		}

		FileInputStream fis = new FileInputStream(sourceFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		FileOutputStream fos = new FileOutputStream(targetFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		byte[] b = new byte[5120];

		while ((len = bis.read(b)) != -1) {
			bos.write(b, 0, len);
		}
		bos.flush();
		bis.close();
		bos.close();
		fos.close();
		fis.close();
		if ((targetFile.exists()) && (sourceFile.isFile()) && (isDeleteSource))
			sourceFile.delete();
	}
	
	/**
	 * <p>描述:获取上传文件 </p>
	 * <p>日期：2014-12-15 下午10:28:25 </p>
	 * @param request HttpServletRequest对象
	 * @return 返回上传的多媒体文件对象
	 */
	public static MultipartFile getUploadFile (HttpServletRequest request) {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		return file;
	}
	
	
	
	
	/**
	 * <p>描述:获取文件要存放的真实路径并且创建文件夹 </p>
	 * <p>日期：2014-12-26 上午9:01:49 </p>
	 * @param file 上传文件
	 * @param fileUploadDirs 本地磁盘路径
	 * @param dirType 需要创建的文件夹
	 * @return 文件所在磁盘存放路径
	 * @throws Exception
	 */
	public static String getUploadFileDir (MultipartFile file,String fileUploadDirs, String dirType) throws Exception{
		String fileUploadDir = fileUploadDirs + "/" + dirType;
		String fileName = file.getOriginalFilename();
		String[] fileNameArr = fileName.split("\\.");
		File fileDir = new File(fileUploadDir);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileUploadDir + "/" +  createFileName(fileNameArr[fileNameArr.length - 1]);
	}
	
	/**
	 * <p>描述:生成UUID文件名 </p>
	 * <p>日期：2014-12-22 下午4:42:48 </p>
	 * @param fileExt 文件后缀
	 * @return 返回处理后的文件名
	 */
	public static String createFileName(String fileExt) {
		String newFileName = UUID.randomUUID().toString().replace("-","") + "." + fileExt;
		return newFileName;
	}
}
