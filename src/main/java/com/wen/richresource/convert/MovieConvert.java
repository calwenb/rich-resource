package com.wen.richresource.convert;

import com.wen.releasedao.core.vo.PageVO;
import com.wen.richresource.entity.MovieEntity;
import com.wen.richresource.util.FileUtil;
import com.wen.richresource.vo.MovieVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author calwen
 * @since 2022/10/31
 */
@Component
@Slf4j
public class MovieConvert {
    String baseDir = "D:\\project-support\\rich-resource";

    public MovieVO convert(MovieEntity entity, String dataType) {
        MovieVO vo = new MovieVO();
        BeanUtils.copyProperties(entity, vo);
        if (Objects.equals(dataType, "t")) {
            String data = null;
            try {
                data = FileUtil.getImageBase64(baseDir + entity.getThumbnailUrl());
            } catch (IOException e) {
                log.error("ImageBase64 错误：\n" + e.getMessage());
            }
            vo.setData(data);
        } else if (Objects.equals(dataType, "o")) {
            String data = null;
            try {
                data = FileUtil.getImageBase64(baseDir + entity.getImageUrl());
            } catch (IOException e) {
                log.error("ImageBase64 错误：\n" + e.getMessage());
            }
            vo.setData(data);
            if (StringUtils.isBlank(entity.getScore())) {
                vo.setScore("1.0");
            }
        }
        return vo;
    }

    public List<MovieVO> list(List<MovieEntity> list, String dataType) {
        return list.stream().map(e -> this.convert(e, dataType)).collect(Collectors.toList());
    }

    public PageVO<MovieVO> page(PageVO<MovieEntity> page, String dataType) {
        List<MovieVO> list = list(page.getContent(), dataType);
        return PageVO.of(list, page.getPage(), page.getSize(), page.getTotal());
    }
}
