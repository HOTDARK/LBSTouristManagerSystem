package com.xie.tsa.service.impl;

import com.hd.sys.dao.SysUserMapper;
import com.xie.tsa.dao.SysUserTokenMapper;
import com.xie.tsa.entity.SysUserToken;
import com.xie.tsa.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    SysUserTokenMapper tokenMapper;

    @Override
    public void insert(SysUserToken token) {
        tokenMapper.insert(token);
    }

    /**
     * 根据电话号码查询token
     * @param telphone
     * @return
     */
    @Override
    public SysUserToken findByTelphone(String telphone) {
        SysUserToken token = new SysUserToken(telphone, null);
        List<SysUserToken> byCondition = tokenMapper.findByCondition(token);
        if(byCondition!= null & byCondition.size() != 0){
            return byCondition.get(0);
        }
        return null;
    }
}
