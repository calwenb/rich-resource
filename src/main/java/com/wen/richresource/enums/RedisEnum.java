package com.wen.richresource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisEnum {
    TOKEN_PREFIX("x-webalbum:token:"),

    TRASH_PREFIX("x-webalbum:trash:"),

    SMS_Mail_CODE_PREFIX("x-webalbum:smsmail:code:"),
    LIKE_PREFIX("x-webalbum:like:"),
    FILE_SHARE_CODE_PREFIX("x-webalbum:share:code:"),
    FILE_SHARE_FID_PREFIX("x-webalbum:share:code:");

    private final String property;
}
