package com.wen.richresource.request;

import lombok.Data;


/**
 * @author calwen
 * @since 2022/10/28
 */
@Data
public class ResourceQueryRequest {
    private Integer targetId;
    private String type;
    private Boolean valid;
    private String resourceLink;
}
