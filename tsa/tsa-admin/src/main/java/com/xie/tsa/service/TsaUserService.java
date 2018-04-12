package com.xie.tsa.service;

import com.hd.sys.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface TsaUserService {
    boolean uploadFile(MultipartFile[] files,String path);
    File downLoadFile(String path);

    Boolean deleteUserByIds(String[] split);

    String  login(SysUser sysUser) throws Exception;
}
