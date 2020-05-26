package com.cn.aop;


import com.cn.entity.ShortUrl;
import com.cn.util.Constants;
import com.cn.util.JedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ShortUrlAop {

    @Autowired
    private JedisUtil jedisUtil;

    @Pointcut("execution(* com.cn.service.*.*(..))")
    public void  shortUrlServiceExecution(){

    }

    @AfterReturning(value = "com.cn.aop.ShortUrlAop.shortUrlServiceExecution()",returning = "result")
    public void addRedis(JoinPoint joinPoint, Object result){
        ShortUrl shortUrl =  (ShortUrl)result;
        jedisUtil.set(Constants.REDIS_PREFIX.concat(shortUrl.getShortUrl()),shortUrl.getUrl(),60);
    }
}
