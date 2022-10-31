package com.wen.richresource.spider.movie;

import cn.hutool.core.date.DateUtil;
import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.releasedao.core.wrapper.QueryWrapper;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.entity.ResourceEntity;
import com.wen.richresource.enums.ResourceTypeEnum;
import com.wen.richresource.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.SimpleFormatter;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Component
@Slf4j
public class MovieDBPipeline implements Pipeline {
    @Resource
    BaseMapper baseMapper;
    String baseDir = "D:\\project-support\\rich-resource";

    @Override
    public void process(ResultItems resultItems, Task task) {
        String content = resultItems.get("content");
        String resourceLink = resultItems.get("resourceLink");
        String resourceName = resultItems.get("resourceName");
        String imageUrl = resultItems.get("imageUrl");
        String title = (String) Optional.ofNullable(resultItems.get("title"))
                .orElse("未知");
        title = title.substring(0, title.indexOf("》") + 1);

        if (!StringUtils.isAnyBlank(content, resourceLink)) {
            MovieEntity movieEntity = parseMovieEntity(content, title);
            QueryWrapper wrapper = new QueryWrapper()
                    .eq("name", movieEntity.getName());
            MovieEntity model = baseMapper.get(MovieEntity.class, wrapper);
            if (model != null) {
                return;
            }
            String path = "\\image\\" + System.currentTimeMillis() + "-"
                    + movieEntity.getName() + ".jpg";
            String thumbnailPath = "\\image\\" + (System.currentTimeMillis() + 1) + "-"
                    + movieEntity.getName() + ".jpg";
            try {
                FileUtil.saveFile(imageUrl, baseDir + path);
                FileUtil.thumbnail(baseDir + path, baseDir + thumbnailPath);
            } catch (Exception e) {
                log.error("图片爬取失败");
            }
            movieEntity.setImageUrl(path);
            movieEntity.setThumbnailUrl(thumbnailPath);
            movieEntity.setDeleted(false);
            baseMapper.add(movieEntity);

            System.out.println(" ==> add");
            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setType(ResourceTypeEnum.MOVIE.getType());
            resourceEntity.setName(resourceName);
            resourceEntity.setResourceLink(resourceLink);
            resourceEntity.setTargetId(movieEntity.getId());
            resourceEntity.setValid(true);
            resourceEntity.setDeleted(false);
            baseMapper.add(resourceEntity);
        }


    }

/*
    private boolean equalMovie(MovieEntity model, MovieEntity movie) {

        return false;
    }
*/

    /**
     * 解析电影实体
     */
    private MovieEntity parseMovieEntity(String content, String title) {
        MovieEntity movie = new MovieEntity();
        String[] arr = content.split("◎");
        for (String s : arr) {
            if (s.length() < 7) {
                continue;
            }
            String key = s.substring(0, 4);
            String value = s.substring(5);
            switch (key) {
                case "译　　名":
                    movie.setOtherName(value);
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
                    if (StringUtils.isNotBlank(value)) {
                        int i = value.indexOf("/10 from");
                        if (i > 0) {
                            value = value.substring(0, i);
                            movie.setScore(value);
                            break;
                        }
                    }
                    movie.setScore("0");
                    break;
                case "片　　长":
                    if (StringUtils.isNotBlank(value)) {
                        int i = value.indexOf("分钟");
                        if (i > 0) {
                            value = value.substring(0, i);
                            movie.setTime(Integer.valueOf(value));
                            break;
                        }
                    }
                    movie.setTime(0);
                    break;
                case "演　　员":
                case "主　　演":
                    String[] actorArr = value.split("　　");
                    StringBuilder actorStr = new StringBuilder();
                    int len = 0;
                    for (int i = 0; i < actorArr.length && len < 3; i++) {
                        String str = actorArr[i];
                        if (StringUtils.isBlank(str)) {
                            continue;
                        }
                        actorStr.append(str.replace((char) 12288, ' ').trim()).append("、");
                        len++;
                    }
                    actorStr.deleteCharAt(actorStr.length() - 1);
                    movie.setActor(String.valueOf(actorStr));
                    break;
                case "简　　介":
                    movie.setIntro(value);
                    break;
            }
        }
        movie.setTitle(title);
        String name = title.substring(title.indexOf("《") + 1, title.indexOf("》"));
        if (StringUtils.isBlank(name)) {
            movie.setName("未知");
        }
        name = name.replace("\\", "-");
        name = name.replace("/", "-");
        movie.setName(name);
        return movie;
    }
}
