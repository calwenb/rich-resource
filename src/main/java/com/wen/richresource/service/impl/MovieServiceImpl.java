package com.wen.richresource.service.impl;

import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.releasedao.core.vo.PageRequest;
import com.wen.releasedao.core.vo.PageVO;
import com.wen.releasedao.core.wrapper.QueryWrapper;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.enums.movie.MovieLanguageTypeEnum;
import com.wen.richresource.enums.movie.MovieRegionTypeEnum;
import com.wen.richresource.enums.movie.MovieTypeEnum;
import com.wen.richresource.request.MovieQueryRequest;
import com.wen.richresource.service.MovieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        QueryWrapper wrapper = this.multiCondition(request);
        return baseMapper.getList(MovieEntity.class, wrapper);
    }

    @Override
    public PageVO<MovieEntity> page(MovieQueryRequest request) {
        PageRequest pageRequest = new PageRequest(request.getPageNum(), request.getPageSize());
        QueryWrapper wrapper = this.multiCondition(request);
        return baseMapper.page(MovieEntity.class, wrapper, pageRequest);
    }

    @Override
    public Map<String, Object[]> type() {
        Map<String, Object[]> map = new HashMap<>();
        MovieTypeEnum[] typeList = MovieTypeEnum.values();
        MovieLanguageTypeEnum[] languageList = MovieLanguageTypeEnum.values();
        MovieRegionTypeEnum[] region = MovieRegionTypeEnum.values();
        map.put("type", typeList);
        map.put("language", languageList);
        map.put("region", region);
        return map;
    }

    @Override
    public List<MovieEntity> recommend(Integer id) {
        MovieEntity movie = baseMapper.getById(MovieEntity.class, id);
        String type = movie.getType();
        if (StringUtils.isNotBlank(type)) {
            if (type.contains(" / ")) {
                type = type.split(" / ")[0];
            }
            QueryWrapper wrapper = new QueryWrapper()
                    .like("type", type)
                    .notEq("id", movie.getId())
                    .orderDesc("release_time")
                    .limit(10);
            return baseMapper.getList(MovieEntity.class, wrapper);
        }
        QueryWrapper wrapper = new QueryWrapper()
                .notEq("id", movie.getId())
                .orderDesc("release_time")
                .limit(10);
        return baseMapper.getList(MovieEntity.class, wrapper);
    }


    private QueryWrapper multiCondition(MovieQueryRequest request) {
        String keyword = request.getKeyword();
        String releaseYear = request.getReleaseYear();
        String score = request.getScore();
        Boolean simple = request.isSimple();
        List<String> regionList = request.getRegionList();
        List<String> typeList = request.getTypeList();
        List<String> languageList = request.getLanguageList();


        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like("name,other_name", keyword);
        }
        if (StringUtils.isNotBlank(score)) {
            wrapper.eq("score", score);
        }
        if (StringUtils.isNotBlank(releaseYear) && !Objects.equals(releaseYear, "??????")) {
            wrapper.eq("release_year", releaseYear);
        }
        if (Objects.equals(simple, true)) {
            wrapper.select("id,title,release_time");
        }
        if (regionList != null && !regionList.isEmpty()) {
//            regionList.remove("??????");
            for (String region : regionList) {
                wrapper.like("region", region);
            }
        }
        if (typeList != null && !typeList.isEmpty()) {
//            typeList.remove("??????");
            for (String type : typeList) {
                wrapper.like("type", type);
            }
        }

        if (languageList != null && !languageList.isEmpty()) {
//            languageList.remove("??????");
            for (String language : languageList) {
                if (MovieLanguageTypeEnum.??????.name().equals(language)) {
                    wrapper.like("language", language)
                            .or().like("language", "?????????")
                            .or().like("language", "??????");
                    continue;
                }
                wrapper.like("language", language);
            }
        }

        wrapper.orderDesc("release_time");
        return wrapper;
    }
}
