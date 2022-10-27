package com.wen.richresource.service;

import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.request.MovieQueryRequest;

import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
public interface MovieService {

    MovieEntity get(Integer id);

    List<MovieEntity> list(MovieQueryRequest request);

}
