package com.wen.richresource.controller;

import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.request.MovieQueryRequest;
import com.wen.richresource.service.MovieService;
import com.wen.richresource.util.ResultUtil;
import com.wen.richresource.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Resource
    MovieService movieService;

    @GetMapping("/{id}")
    public ResultVO<MovieEntity> get(@PathVariable Integer id) {
        MovieEntity data = movieService.get(id);
        return ResultUtil.success(data);
    }

    @GetMapping("/list")
    public ResultVO<List<MovieEntity>> list(MovieQueryRequest request) {
        List<MovieEntity> data = movieService.list(request);
        return ResultUtil.success(data);
    }

}
