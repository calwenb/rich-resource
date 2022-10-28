package com.wen.richresource.service;

import com.wen.richresource.entity.ResourceEntity;
import com.wen.richresource.request.ResourceQueryRequest;

import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
public interface ResourceService {

    ResourceEntity get(Integer id);

    List<ResourceEntity> getByTargetId(Integer targetId);


    void crawlMovieResource();

    List<ResourceEntity> list(ResourceQueryRequest request);
}
