package com.wen.richresource.spider.movie;

import com.wen.releasedao.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Component
public class MovieSpider {
    @Resource
    MovieProcessor movieProcessor;
    @Resource
    MovieDBPipeline movieDBPipeline;
    @Resource
    BaseMapper baseMapper;
    public void run() {
//        baseMapper.exeSQL("TRUNCATE TABLE  movie",new Object[]{});
//        baseMapper.exeSQL("TRUNCATE TABLE  resource",new Object[]{});
        Spider.create(movieProcessor)
                .addUrl("https://www.ygdy8.net/html/gndy/dyzz/index.html")
                .addPipeline(movieDBPipeline)
                .thread(1)
                .run();
    }

}


