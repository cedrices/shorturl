package com.cn.web;

import com.cn.entity.ShortUrl;
import com.cn.service.ShortUrlService;
import com.cn.util.JedisUtil;
import com.cn.util.UrlToShortUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PushUrlController {


    @Autowired
    private ShortUrlService shortUrlService;


    @GetMapping(value = "/pushUrl")
    public String savePushUrl(@RequestParam String url){
        String shortUrl = UrlToShortUrlUtil.shortUrl(url);
        ShortUrl shortUrl1 = shortUrlService.saveShortUrl(new ShortUrl(shortUrl,url));
        return shortUrl1.getShortUrl();
    }


}
