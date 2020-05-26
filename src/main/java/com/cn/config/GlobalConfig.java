package com.cn.config;

import com.cn.filter.UrlFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
public class GlobalConfig {

    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean(new UrlFilter());
        registration.addUrlPatterns("/*");
        return  registration;
    }


    @Autowired
    private  RedisProperties redisProperties;

    @Bean
    public JedisPool registerJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxActive());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisProperties.getHost(),redisProperties.getPort(),redisProperties.getTimeout(),null);
        log.info("redis配置启动成功！");
        return jedisPool;
    }
}
