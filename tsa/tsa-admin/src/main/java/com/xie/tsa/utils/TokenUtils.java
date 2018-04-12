package com.xie.tsa.utils;

import com.alibaba.fastjson.JSON;
import com.hd.sys.entity.SysUser;
import com.xie.tsa.entity.SysUserToken;
import com.xie.tsa.entity.UserLocationRecord;
import flex.messaging.util.UUIDUtils;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    /**
     * 根据传入的字符串编码后返回
     *
     * @param user
     * @return
     */
    public static String getToken(SysUser user) {
        SysUser sysUser = new SysUser();
        sysUser.setTelephone(user.getTelephone());
        // String str = "123qwertyuioplkjhgfdsazxcvbnm,{}:''\"\"";
        String str = JSON.toJSONString(sysUser);
        byte[] bytes1 = str.getBytes();
        for (int i = 0; i < bytes1.length - 1; i++) {
            bytes1[i] = (byte) (bytes1[i] + 1);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(bytes1);
        return encode.replace("\r\n","");
    }

    /**
     * 根据编码的字符串获得原来的字符串
     *
     * @param encode
     * @return
     */
    public static String getTelphone(String encode) {
        BASE64Decoder decoder = new BASE64Decoder();
        int i = 0;
        try {
            byte[] bytes = decoder.decodeBuffer(encode);
            for (i = 0; i < bytes.length - 1; i++) {
                bytes[i] = (byte) (bytes[i] - 1);
            }
            String s = new String(bytes);

            SysUser token = JSON.parseObject(s, SysUser.class);
            return token.getTelephone();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test
    public void test() {
        SysUser sysUser = new SysUser();
        sysUser.setTelephone("15340526151");
        String token = getToken(sysUser).replace("\r\n","");
        System.out.println(token);
        String telphone = getTelphone(token);
        System.out.println(telphone);

    }
}
