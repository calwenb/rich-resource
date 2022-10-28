package com.wen.richresource.service.impl;

import com.wen.richresource.entity.UserEntity;
import com.wen.richresource.request.UserRequest;
import com.wen.richresource.service.AuthService;
import com.wen.richresource.service.OauthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author calwen
 * @since 2022/10/28
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    OauthService oauthService;

    @Override
    public String login(UserRequest request) {
        return null;
    }

    @Override
    public String register(UserRequest request) {
        return null;
    }

    @Override
    public boolean removeToken() {
        return oauthService;
    }

    @Override
    public UserEntity getTokenUser() {
        return oauthService.getUser();
    }
}
