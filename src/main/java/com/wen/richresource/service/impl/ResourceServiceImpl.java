package com.wen.richresource.service.impl;

import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.releasedao.core.wrapper.QueryWrapper;
import com.wen.richresource.entity.ResourceEntity;
import com.wen.richresource.service.ResourceService;
import com.wen.richresource.spider.movie.MovieSpider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Resource
    MovieSpider movieSpider;

    @Resource
    BaseMapper baseMapper;

    @Override
    public ResourceEntity get(Integer id) {
        return baseMapper.getById(ResourceEntity.class, id);
    }

    @Override
    public List<ResourceEntity> getByTargetId(Integer targetId) {
        QueryWrapper wrapper = new QueryWrapper()
                .eq("target_id", targetId);
        return baseMapper.getList(ResourceEntity.class, wrapper);
    }

    @Override
    public void crawlMovieResource() {
        movieSpider.run();
    }
}
