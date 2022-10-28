package com.wen.richresource.controller;

import com.wen.richresource.entity.ResourceEntity;
import com.wen.richresource.request.ResourceQueryRequest;
import com.wen.richresource.service.ResourceService;
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
 * @since 2022/10/28
 */
@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Resource
    ResourceService resourceService;

    @GetMapping("/{id}")
    public ResultVO<ResourceEntity> get(@PathVariable Integer id) {
        ResourceEntity data = resourceService.get(id);
        return ResultUtil.success(data);
    }
    @GetMapping("/list")
    public ResultVO<List<ResourceEntity>> list(ResourceQueryRequest request) {
        List<ResourceEntity> data = resourceService.list(request);
        return ResultUtil.success(data);
    }

}
