package com.wen.richresource.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author calwen
 * @since 2022/10/28
 */
@Data
public class ResourceVO {
    private Integer id;
    private Integer targetId;
    private String type;
    private Boolean valid;
    private String resourceLink;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
