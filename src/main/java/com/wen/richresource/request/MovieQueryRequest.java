package com.wen.richresource.request;

import lombok.Data;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Data
public class MovieQueryRequest {
    private String keyword;
    private List<String> regionList;
    private List<String> typeList;
    private List<String> languageList;
    private String releaseYear;
    private String score;
    private String dataType;
    /**
     * 简单查询
     */
    private boolean simple;
    /**
     * 分页页码
     */
//    @NotNull(message = " 分页页码参数 page必传 ")
    private Integer pageNum;

    /**
     * 分页大小
     */
//    @NotNull(message = " 分页大小参数 size必传 ")
    @Max(value = 200, message = " 分页大小不可超过200 ")
    private Integer PageSize;
}
