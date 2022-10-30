package com.wen.richresource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenEnum {
    HEADER("Authorization"),
    TOKEN_PREFIX("Bearer "),
    JWT_SECRET("rich-resource-calwen");

    private final String value;
}
