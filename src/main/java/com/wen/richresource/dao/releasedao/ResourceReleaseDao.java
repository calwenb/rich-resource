package com.wen.richresource.dao.releasedao;

import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.releasedao.core.wrapper.QueryWrapper;
import com.wen.richresource.entity.ResourceEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Component
public class ResourceReleaseDao {
    @Resource
    BaseMapper baseMapper;

    public List<ResourceEntity> getByTargetId(Integer targetId) {
        QueryWrapper wrapper = new QueryWrapper()
                .eq("target_id", targetId);
        return baseMapper.getList(ResourceEntity.class, wrapper);
    }
}
