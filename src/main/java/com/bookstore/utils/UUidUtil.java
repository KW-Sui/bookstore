package com.bookstore.utils;

import java.util.UUID;

public class UUidUtil {
    public static String  getUUID(){
        return UUID.randomUUID().toString();
    }
}
