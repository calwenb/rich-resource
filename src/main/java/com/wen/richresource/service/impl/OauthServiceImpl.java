package com.wen.richresource.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wen.releasedao.core.mapper.BaseMapper;

import com.wen.richresource.entity.UserEntity;
import com.wen.richresource.enums.RedisEnum;
import com.wen.richresource.enums.TokenEnum;
import com.wen.richresource.exception.FailException;
import com.wen.richresource.exception.OauthException;
import com.wen.richresource.service.OauthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 验证token，token下发
 *
 * @author calwen
 * @since 2022/8/29
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Resource
    BaseMapper baseMapper;

    @Resource
    RedisTemplate<String, Integer> redisTemplate;
    private final static String JWT_SECRET = TokenEnum.JWT_SECRET.getProperty();
    private final static String TOKEN_PREFIX = RedisEnum.TOKEN_PREFIX.getProperty();

    @Override
    public String createToken(Integer uid) {
        Claims claims = Jwts.claims()
                .setId(String.valueOf(uid))
                .setIssuedAt(new Date())
                .setExpiration(DateUtil.offsetDay(new Date(), 7));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    @Override
    public Integer getUserId() {
        String token = this.getHeaderToken();
        Claims claims = this.getClaimsFromToken(token);
        return Integer.valueOf(claims.getId());
    }

    @Override
    public UserEntity getUser() {
        Integer userId = this.getUserId();
        UserEntity user = baseMapper.getById(UserEntity.class, userId);
        user.setPassword("");
        return user;
    }


    @Override
    public String saveToken(Integer uid, Integer userType, int hour) {
        String token = this.createToken(uid);
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, userType, hour, TimeUnit.HOURS);
        return token;
    }

    @Override
    public boolean removeToken() {
        String token = this.getHeaderToken();
        return Optional.ofNullable(redisTemplate.delete(TOKEN_PREFIX + token)).orElse(false);
    }

    @Override
    public Long getExpireTime() {
        String token = this.getHeaderToken();
        return redisTemplate.opsForValue().getOperations().getExpire(TOKEN_PREFIX + token);
    }

    @Override
    public void renew(Integer hour) {
        String token = this.getHeaderToken();
        redisTemplate.expire(TOKEN_PREFIX + token, hour, TimeUnit.HOURS);
    }

    @Override
    public boolean verifyToken() {
        String token = this.getHeaderToken();
        Object o = redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        return o != null;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private String getHeaderToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes == null ? null : requestAttributes.getRequest();
        if (request == null) {
            throw new OauthException("验证失败");
        }
        return Optional.ofNullable(request.getHeader(TokenEnum.HEADER.getProperty()))
                .orElseThrow(() -> new OauthException("未携带令牌"));
    }


}