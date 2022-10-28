package com.wen.richresource.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wen.releasedao.core.annotation.CreateTime;
import com.wen.releasedao.core.annotation.IdField;
import com.wen.releasedao.core.annotation.UpdateTime;
import com.wen.richresource.entity.MovieEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author calwen
 * @since 2022/10/28
 */
@Data
public class MovieVO {
    private Integer id;
    private String title;
    private String name;
    private String otherName;
    /**
     * 地区
     */
    private String region;
    private String type;
    private Integer time;
    private String language;
    /**
     * 发行年
     */
    private String releaseYear;
    /**
     * 发行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;
    /**
     * 演员
     */
    private String actor;
    private String score;
    /**
     * 简介
     */
    private String intro;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
