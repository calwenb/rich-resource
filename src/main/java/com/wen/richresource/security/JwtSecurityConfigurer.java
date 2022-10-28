/*
package com.wen.richresource.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtUtil jwtUtil;

    public JwtSecurityConfigurer(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenAuthenticationFilter customFilter = new JwtTokenAuthenticationFilter(jwtUtil);
        http.exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
*/
