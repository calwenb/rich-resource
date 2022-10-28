package com.wen.richresource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenEnum {
    HEADER("token"),
    JWT_SECRET("calwendbshagicba");

    private String property;
}
