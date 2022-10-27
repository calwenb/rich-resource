package com.wen.richresource.spider.movie;

import cn.hutool.core.date.DateUtil;
import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.entity.ResourceEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Component
@Slf4j
public class MovieDBPipeline implements Pipeline {
    @Resource
    BaseMapper baseMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String content = resultItems.get("content");
        String resourceLink = resultItems.get("resourceLink");
        String title = (String) Optional.ofNullable(resultItems.get("title"))
                .orElse("未知");
        title = title.substring(0, title.indexOf("》") + 1);

        if (!StringUtils.isAnyBlank(content, resourceLink)) {
            MovieEntity movieEntity = parseMovieEntity(content);
            movieEntity.setTitle(title);
            movieEntity.setDeleted(false);
            movieEntity.setCreateTime(new Date());
            movieEntity.setUpdateTime(new Date());
            baseMapper.save(movieEntity);

            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setType("电影");//
            resourceEntity.setResourceLink(resourceLink);
            resourceEntity.setTargetId(movieEntity.getId());
            resourceEntity.setValid(true);
            resourceEntity.setDeleted(false);
            resourceEntity.setCreateTime(new Date());
            resourceEntity.setUpdateTime(new Date());
            baseMapper.save(resourceEntity);
        }

    }

    /**
     * 解析电影实体
     */
    private MovieEntity parseMovieEntity(String content) {
        MovieEntity movie = new MovieEntity();
        String[] arr = content.split("◎");
        for (String s : arr) {
            if (s.length() < 7) {
                continue;
            }
            String key = s.substring(0, 4);
            String value = s.substring(5);
//            System.out.println(key + "===:===" + value);
            switch (key) {
                case "译　　名":
                    movie.setOtherName(value);
                    break;
                case "片　　名":
                    movie.setName(value);
                    break;
                case "年　　代":
                    movie.setReleaseYear(value);
                    break;
                case "产　　地":
                    movie.setRegion(value);
                    break;
                case "类　　别":
                    movie.setType(value);
                    break;
                case "语　　言":
                    movie.setLanguage(value);
                    break;
                case "上映日期":
                    String dateStr = value.substring(0, 10);
                    try {
                        movie.setReleaseTime(DateUtil.parseDate(dateStr));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    break;
                case "豆瓣评分":
                    movie.setScore(value);
                    break;
                case "片　　长":
                    String minute = value.substring(0, value.indexOf("分钟"));
                    if (StringUtils.isNotBlank(minute)) {
                        movie.setTime(Integer.valueOf(minute));
                    }
                    break;
                case "演　　员":
                case "主　　演":
                    String[] actorArr = value.split(" ");
                    StringBuilder actorStr = new StringBuilder();
                    for (int i = 0; i < actorArr.length && i < 3; i++) {
                        actorStr.append(actorArr[i]).append("/r/n");
                    }
                    movie.setActor(String.valueOf(actorStr));
                    break;
                case "简　　介":
                    movie.setIntro(value);
                    break;
            }
        }
        if (StringUtils.isBlank(movie.getName())) {
            movie.setName(movie.getOtherName());
        }
        return movie;
    }
}
