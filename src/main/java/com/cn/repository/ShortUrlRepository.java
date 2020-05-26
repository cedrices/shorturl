package com.cn.repository;

import com.cn.entity.ShortUrl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShortUrlRepository {


    //添加短链接与长链接数据
    @Insert(" insert into t_short_url(short_url,url)  values(#{entity.shortUrl},#{entity.url}) ")
    int saveShortUrl(@Param("entity") ShortUrl entity);

    //通过短链接获取长链接
    @Select(" select a.id,a.short_url,a.url  from t_short_url a where a.short_url= #{shortUrl} ")
    ShortUrl findByShortUrl(@Param("shortUrl") String shortUrl);
}
