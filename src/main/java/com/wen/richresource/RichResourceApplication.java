package com.wen.richresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author calwen
 * @since 2022/10/27
 */
@EnableScheduling
@SpringBootApplication
public class RichResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RichResourceApplication.class, args);
    }

}
