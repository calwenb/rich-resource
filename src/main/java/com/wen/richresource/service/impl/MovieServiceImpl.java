package com.wen.richresource.service.impl;

import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.releasedao.core.wrapper.QueryWrapper;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.request.MovieQueryRequest;
import com.wen.richresource.service.MovieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestScope;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    BaseMapper baseMapper;

    @Override
    public MovieEntity get(Integer id) {
        return baseMapper.getById(MovieEntity.class, id);
    }

    @Override
    public List<MovieEntity> list(MovieQueryRequest request) {

        String type = request.getType();
        String score = request.getScore();
        String region = request.getRegion();
        String language = request.getLanguage();
        String releaseYear = request.getReleaseYear();
        QueryWrapper wrapper = new QueryWrapper();

        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotBlank(score)) {
            wrapper.eq("score", score);
        }
        if (StringUtils.isNotBlank(region)) {
            wrapper.eq("region", region);
        }
        if (StringUtils.isNotBlank(language)) {
            wrapper.eq("language", language);
        }
        if (StringUtils.isNotBlank(releaseYear)) {
            wrapper.eq("release_year", releaseYear);
        }
        return baseMapper.getList(MovieEntity.class, wrapper);
    }
}
