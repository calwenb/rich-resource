package com.wen.richresource.config;

import com.wen.richresource.security.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Token验证拦截器规则配置类
 *
 * @author calwen
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {



    @Bean
    AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //验证拦截
        registry.addInterceptor(authenticationInterceptor());
    }


}