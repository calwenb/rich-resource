package com.wen.richresource.request;

import lombok.Data;

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
}
