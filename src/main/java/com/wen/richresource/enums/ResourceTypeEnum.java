package com.wen.richresource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author calwen
 * @since 2022/10/28
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {
    MOVIE("电影"),
    MUSIC("音乐"),
    TEXT("文本");
    private final String type;
}
