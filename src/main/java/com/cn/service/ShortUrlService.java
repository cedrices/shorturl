package com.cn.service;

import com.cn.entity.ShortUrl;
import org.springframework.stereotype.Service;


public  interface ShortUrlService {

    //保存短url与原长链接
    ShortUrl saveShortUrl(ShortUrl shortUrl);

    //获取短链接对应的长链接
    ShortUrl findByShortUrl(String shortUrl);
}
