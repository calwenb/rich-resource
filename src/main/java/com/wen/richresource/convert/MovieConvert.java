package com.wen.richresource.convert;

import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.util.FileUtil;
import com.wen.richresource.vo.MovieVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author calwen
 * @since 2022/10/31
 */
@Component
public class MovieConvert {
    String baseDir = "D:\\project-support\\rich-resource";

    public MovieVO convert(MovieEntity entity, String dataType) {
        MovieVO vo = new MovieVO();
        BeanUtils.copyProperties(entity, vo);
        if (Objects.equals(dataType, "t")) {
            String data = FileUtil.getImageBase64(baseDir + entity.getThumbnailUrl());
            vo.setData(data);
        } else if (Objects.equals(dataType, "o")) {
            String data = FileUtil.getImageBase64(baseDir + entity.getImageUrl());
            vo.setData(data);
        }
        return vo;
    }

    public List<MovieVO> list(List<MovieEntity> list, String dataType) {
        return list.stream().map(e -> this.convert(e, dataType)).collect(Collectors.toList());
    }
}
