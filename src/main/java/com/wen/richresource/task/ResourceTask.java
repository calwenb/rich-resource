package com.wen.richresource.task;

import com.wen.richresource.service.ResourceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ResourceTask {
    @Resource
    ResourceService resourceService;

    //    @Scheduled(initialDelay = 2000, fixedDelay = 3000000)
    @Scheduled(cron = "0 0 0 */3 * ?")
    public void crawlMovieResource() {
        resourceService.crawlMovieResource();
    }
}
