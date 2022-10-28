package com.wen.richresource.service;


import com.wen.richresource.entity.UserEntity;

/**
 * OauthService  业务类
 * 1.生成token令牌
 * 2.从请求头获取token令牌
 * 3.解析token令牌成user类
 *
 * @author calwen
 */
public interface OauthService {

    String saveToken(Integer uid, Integer userType, int hour);

    String createToken(Integer uid);

    Integer getUserId();

    UserEntity getUser();

    boolean verifyToken();

    boolean removeToken();

    Long getExpireTime();


    /**
     * 令牌续签
     *
     * @param hour - 续签时长
     */
    void renew(Integer hour);


}
