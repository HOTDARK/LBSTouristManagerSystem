package com.xie.tsa.utils;

import org.junit.Test;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    @Test
    public void test(){
        System.out.println(getUUID());
    }
}
