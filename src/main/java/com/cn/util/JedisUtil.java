package com.cn.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

@Slf4j
@Component
public class JedisUtil {


    @Autowired
    private JedisPool jedisPool;

    //获取key对应value
    public  String get(String key){

        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            Boolean isExists = jedis.exists(key);
            log.info("{}:key不存在",key);
            if(isExists){
                value = jedis.get(key);
            }
            log.info(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }

    //添加redis的key-value的字符串类型
    public String set(String key,String value,int expire){

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Random random  = new Random();
            value = jedis.setex(key,random.nextInt(expire)+Constants.REDIS_KEY_TIME,value);
            log.info("设置key成功{}",value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }

}
