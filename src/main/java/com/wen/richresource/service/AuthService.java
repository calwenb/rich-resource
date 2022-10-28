package com.wen.richresource.service;

import com.wen.richresource.entity.UserEntity;
import com.wen.richresource.request.UserRequest;

/**
 * @author calwen
 * @since 2022/10/28
 */
public interface AuthService {
    String login(UserRequest request);

    String register(UserRequest request);

    boolean removeToken();

    UserEntity getTokenUser();
}
