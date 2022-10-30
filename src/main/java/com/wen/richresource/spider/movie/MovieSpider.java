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

    public void run() {
        Spider.create(movieProcessor)
                .addUrl("https://www.ygdy8.net/html/gndy/dyzz/index.html")
                .addPipeline(movieDBPipeline)
                .thread(1)
                .run();
    }

}


