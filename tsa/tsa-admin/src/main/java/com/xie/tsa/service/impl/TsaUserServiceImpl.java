package com.xie.tsa.service.impl;

import com.hd.sys.entity.SysUser;
import com.hd.sys.service.SysUserService;
import com.xie.tsa.entity.SysUserToken;
import com.xie.tsa.service.TokenService;
import com.xie.tsa.service.TsaUserService;
import com.xie.tsa.utils.TokenUtils;
import com.xie.tsa.utils.FileUtils;
import com.xie.tsa.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class TsaUserServiceImpl implements TsaUserService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    TokenService tokenService;
    @Override
    public boolean uploadFile(MultipartFile[] files, String path) {
        try {
            for (MultipartFile mulFile :
                    files) {
                String originalFilename = mulFile.getOriginalFilename();
                path = path + originalFilename;
                FileUtils.transformToLocal(mulFile, path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public File downLoadFile(String path) {
        File file = FileUtils.downFile(path);
        return file;
    }


    @Override
    public Boolean deleteUserByIds(String[] split) {

        for (String id :
                split) {
            try {
                sysUserService.deleteById(Long.parseLong(id));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public String  login(SysUser sysUser) throws Exception {
        String sysUserName = sysUserService.findSysUserName(sysUser.getUserAccount());
        if(sysUserName == null){
            sysUserService.save(sysUser);
            SysUserToken token = new SysUserToken(sysUser.getTelephone(), TokenUtils.getToken(sysUser));
            tokenService.insert(token);
            TokenManager.getInstance().addToken(sysUser.getTelephone(),token.getToken());
            return token.getToken();
        }else {
            SysUserToken byTelphone = tokenService.findByTelphone(sysUser.getTelephone());
            return byTelphone.getToken();
        }
    }
}
