package com.wen.richresource.spider;

import com.wen.richresource.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MovieSpiderTest {
    @Resource
    ResourceService resourceService;

    @Test
    void run() {
        resourceService.crawlMovieResource();
    }

}
