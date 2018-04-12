package com.xie.tsa.action.common;

import com.alibaba.fastjson.JSON;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.xie.tsa.entity.Talk;
import com.xie.tsa.service.TsaUserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file/")
public class FileAction {
    private static String strPath = PropertiesUtils.getProperty(FileAction.class, "common", "uploadRoot");

    @Autowired
    TsaUserService tsaUserService;

    @RequestMapping(value = "/headUpload",method = RequestMethod.POST)
    public String fileUpload(MultipartFile[] headImage){
        boolean b = tsaUserService.uploadFile(headImage, strPath + "headImage\\");

        if(b){
            Talk<Integer> talk = new Talk("200","上传成功");
            return JSON.toJSONString(talk);
        }

        Talk<Integer> talk = new Talk("200","上传失败");
        return JSON.toJSONString(talk);

    }

    @RequestMapping(value = "/headDown",method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDown(String fileNmae){
        File file = tsaUserService.downLoadFile(strPath + "headImage\\" + fileNmae);
        Talk<Integer> talk;

        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", fileNmae);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }

    }
}
