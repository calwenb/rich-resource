package com.wen.richresource.entity;

import com.wen.releasedao.core.annotation.CreateTime;
import com.wen.releasedao.core.annotation.IdField;
import com.wen.releasedao.core.annotation.TableName;
import com.wen.releasedao.core.annotation.UpdateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("movie")
public class MovieEntity {
    @IdField
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
    private Boolean deleted;
    @CreateTime
    private Date createTime;
    @UpdateTime
    private Date updateTime;
}
