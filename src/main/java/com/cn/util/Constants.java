package com.cn.util;

public class Constants {

    //redis固定过期时间
    public static  final int REDIS_KEY_TIME = 60;

    //redis中前缀 用于区别其他key
    public static  final String REDIS_PREFIX = "URL_";

    //用于short前缀判断
    public static  final String URL_PREFIX = "/short/";
}
