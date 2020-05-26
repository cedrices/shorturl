package com.cn.filter;

import com.cn.entity.ShortUrl;
import com.cn.service.ShortUrlService;
import com.cn.util.Constants;
import com.cn.util.JedisUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UrlFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            log.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String url = request.getRequestURI();
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if(url.startsWith(Constants.URL_PREFIX)){
            WebApplicationContext webApplicationContext = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(request.getServletContext());
            JedisUtil  jedisUtil = (JedisUtil)webApplicationContext.getBean(JedisUtil.class);
            url  = url.replaceAll(Constants.URL_PREFIX,"");
               String value = jedisUtil.get(Constants.REDIS_PREFIX.concat(url));
               if(StringUtils.isEmpty(value)){
                   ShortUrlService shortUrlService = (ShortUrlService)webApplicationContext.getBean(ShortUrlService.class);
                   ShortUrl shortUrl = shortUrlService.findByShortUrl(url);
                   value = shortUrl.getUrl();
               }
            log.info("过滤器拦截的path：{}",url);
               if(!StringUtils.isEmpty(value)){
                   response.sendRedirect(value);
               }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
           log.info("filter destroy");
    }
}
