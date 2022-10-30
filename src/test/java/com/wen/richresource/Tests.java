package com.wen.richresource;

import org.junit.jupiter.api.Test;

/**
 * @author calwen
 * @since 2022/10/31
 */

public class Tests {
    @Test
    void t2() {
        String s = "////\\";
        System.out.println(s);
        String s1 = s.replace("\\", "-");
        s1 = s1.replace("/", "-");
        System.out.println(s1);
    }
}
