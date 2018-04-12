package com.xie.tsa.service;

import com.xie.tsa.entity.SysUserToken;

public interface TokenService {
    void insert(SysUserToken token);
    SysUserToken findByTelphone(String telphone);
}
