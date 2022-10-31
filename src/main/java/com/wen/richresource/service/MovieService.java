package com.wen.richresource.service;

import com.wen.releasedao.core.vo.PageVO;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.request.MovieQueryRequest;

import java.util.List;
import java.util.Map;

/**
 * @author calwen
 * @since 2022/10/27
 */
public interface MovieService {

    MovieEntity get(Integer id);

    List<MovieEntity> list(MovieQueryRequest request);

    PageVO<MovieEntity> page(MovieQueryRequest request);

    Map<String, Object[]> type();

}
