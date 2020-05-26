package com.cn.service.impl;

import com.cn.entity.ShortUrl;
import com.cn.repository.ShortUrlRepository;
import com.cn.service.ShortUrlService;
import com.cn.util.Constants;
import com.cn.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;



    @Override
    public ShortUrl saveShortUrl(ShortUrl shortUrl) {
        ShortUrl shortUrl1  = shortUrlRepository.findByShortUrl(shortUrl.getShortUrl());
        if(shortUrl1 == null){
            shortUrlRepository.saveShortUrl(shortUrl);
        }

        return shortUrl;
    }

    @Override
    public ShortUrl findByShortUrl(String shortUrl) {
        ShortUrl shortUrl1 = shortUrlRepository.findByShortUrl(shortUrl);
        return shortUrl1;
    }
}
