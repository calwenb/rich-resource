package com.wen.richresource.request;

import com.wen.richresource.entity.MovieEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author calwen
 * @since 2022/10/27
 */
@Data
public class MovieQueryRequest {
    private String region;
    private String type;
    private String language;
    private String releaseYear;
    private String score;
}
