package com.wen.richresource;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * @author calwen
 * @since 2022/10/31
 */

public class Tests {
    @Test
    void t2() {
        String s = "/10 from 2194 users";
        if (StringUtils.isNotBlank(s)) {
            int i = s.indexOf("/10 from");
            if (i > 0) {
                String substring = s.substring(0, i);
                System.out.println(substring);
                System.out.println("aaa");
            }
        }

    }
}
