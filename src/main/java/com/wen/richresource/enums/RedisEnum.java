package com.wen.richresource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisEnum {
    TOKEN_PREFIX("rich-resource:token:"),
    LIKE_PREFIX("rich-resource:like:");

    private final String value;
}
