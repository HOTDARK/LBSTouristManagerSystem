package com.xie.tsa.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TokenManager {
    private static TokenManager tokenManager;
    private Map<String, String> map = new HashMap<String, String>();

    private TokenManager() {
    }

    public static synchronized TokenManager getInstance() {
        if (tokenManager == null) {
            tokenManager = new TokenManager();
        }
        return tokenManager;
    }

    public void addToken(String telphone, String token) {
        map.put(telphone, token);
    }

    public String exitToken(String token) {
        String telphone = TokenUtils.getTelphone(token);
        String s = map.get(telphone);
        if (s == null) {
            return null;
        } else {
            if (s.equals(token)) {
                return telphone;
            }
            return null;
        }
    }

    public void removeOutOfTimeToken(){
        Set<String> strings = map.keySet();
        for (String key:
                strings) {
            String token = map.get(key);

            //Base64Utils.
        }

    }

}
