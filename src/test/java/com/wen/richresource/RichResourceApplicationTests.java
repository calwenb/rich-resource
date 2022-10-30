package com.wen.richresource;

import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.enums.movie.MovieTypeEnum;
import com.wen.richresource.request.MovieQueryRequest;
import com.wen.richresource.service.MovieService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class RichResourceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    MovieService movieService;

    @Test
    void type() {
        List<MovieEntity> list = movieService.list(new MovieQueryRequest());
        Set<String> set = list.stream().map(MovieEntity::getType).collect(Collectors.toSet());
        HashSet<String> look = new HashSet<>();
        set.forEach(e -> {
            e = e.replace(" ", "");
            String[] split = e.split("/");
            look.addAll(Arrays.asList(split));
        });
        System.out.println(look);
        System.out.println(look.size());
    }

    @Test
    void type2() {
        for (MovieTypeEnum value : MovieTypeEnum.values()) {
            System.out.println(value);
        }
    }

    @Test
    void language() {
        List<MovieEntity> list = movieService.list(new MovieQueryRequest());
        Set<String> set = list.stream().map(MovieEntity::getLanguage).collect(Collectors.toSet());
        HashSet<String> look = new HashSet<>();
        set.forEach(e -> {
            if (StringUtils.isBlank(e)) {
                return;
            }
            e = e.replace(" ", "");
            String[] split = e.split("/|\\,");
            look.addAll(Arrays.asList(split));
        });
        System.out.println(look);
        System.out.println(look.size());
    }

    @Test
    void region() {
        List<MovieEntity> list = movieService.list(new MovieQueryRequest());
        Set<String> set = list.stream().map(MovieEntity::getRegion).collect(Collectors.toSet());
        HashSet<String> look = new HashSet<>();
        set.forEach(e -> {
            if (StringUtils.isBlank(e)) {
                return;
            }
            e = e.replace(" ", "");
            String[] split = e.split("/|\\,");
            look.addAll(Arrays.asList(split));
        });
        System.out.println(look);
        System.out.println(look.size());
    }



}
