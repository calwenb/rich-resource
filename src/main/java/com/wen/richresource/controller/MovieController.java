package com.wen.richresource.controller;

import com.wen.releasedao.core.vo.PageVO;
import com.wen.richresource.convert.MovieConvert;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.request.MovieQueryRequest;
import com.wen.richresource.service.MovieService;
import com.wen.richresource.util.ResultUtil;
import com.wen.richresource.vo.MovieVO;
import com.wen.richresource.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author calwen
 * @since 2022/10/27
 */
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Resource
    MovieService movieService;
    @Resource
    MovieConvert convert;

    @GetMapping("/{id}/{dataType}")
    public ResultVO<MovieVO> get(@PathVariable Integer id, @PathVariable String dataType) {
        MovieVO data = convert.convert(movieService.get(id), dataType);
        return ResultUtil.success(data);
    }

    @GetMapping("/list")
    public ResultVO<List<MovieVO>> list(MovieQueryRequest request) {
        List<MovieVO> data = convert.list(movieService.list(request), request.getDataType());
        return ResultUtil.success(data);
    }

    @GetMapping("/recommend/{id}")
    public ResultVO<List<MovieVO>> recommend(@PathVariable Integer id) {
        List<MovieVO> data = convert.list(movieService.recommend(id), "t");
        return ResultUtil.success(data);
    }

    @GetMapping("/page")
    public ResultVO<PageVO<MovieVO>> page(MovieQueryRequest request) {
        PageVO<MovieEntity> page = movieService.page(request);
        PageVO<MovieVO> data = convert.page(page, request.getDataType());
        return ResultUtil.success(data);
    }

    @GetMapping("/type")
    public ResultVO<Map<String, Object[]>> type() {
        Map<String, Object[]> data = movieService.type();
        return ResultUtil.success(data);
    }

}

