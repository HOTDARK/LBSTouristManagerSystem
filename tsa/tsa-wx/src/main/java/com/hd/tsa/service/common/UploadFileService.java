package com.hd.tsa.service.common;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UploadFileService {
   public OutputStream downFile(String filepath,OutputStream os) throws Exception;

   public List<Map<String, String>> uploadFiles(CommonsMultipartFile[] files, String model) throws Exception;
   
   public Map<String, Object> uploadWxFiles(Map<String, File> files, String model) throws Exception; 
   
   public Map<String, String> uploadWxImgs(Map<String, File> fileMap, String model) throws Exception;
}
