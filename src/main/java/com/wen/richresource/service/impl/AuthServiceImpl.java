package com.wen.richresource.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wen.releasedao.core.mapper.BaseMapper;
import com.wen.releasedao.core.wrapper.QueryWrapper;
import com.wen.richresource.entity.UserEntity;
import com.wen.richresource.enums.RedisEnum;
import com.wen.richresource.enums.TokenEnum;
import com.wen.richresource.enums.UserTypeEnum;
import com.wen.richresource.exception.FailException;
import com.wen.richresource.exception.OauthException;
import com.wen.richresource.request.UserRequest;
import com.wen.richresource.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author calwen
 * @since 2022/10/28
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    BaseMapper baseMapper;

    @Resource
    RedisTemplate<String, String> redisTemplate;
    private final String JWT_SECRET = TokenEnum.JWT_SECRET.getValue();
    private final String TOKEN_PREFIX = RedisEnum.TOKEN_PREFIX.getValue();

    @Override
    public String login(UserRequest request) {
        QueryWrapper wrapper = new QueryWrapper()
                .eq("account", request.getAccount())
                .eq("password", request.getPassword());
        UserEntity user = Optional.ofNullable(baseMapper.get(UserEntity.class, wrapper))
                .orElseThrow(() -> new FailException("账号密码错误或未注册"));

        //记住密码给30天，否则12小时
        if (Objects.equals(request.getRemember(), true)) {
            return TokenEnum.TOKEN_PREFIX.getValue() +
                    saveToken(user.getId(), user.getType(), 30 * 24);
        } else {
            return TokenEnum.TOKEN_PREFIX.getValue() +
                    saveToken(user.getId(), user.getType(), 12);
        }
    }

    @Override
    public String register(UserRequest request) {
        QueryWrapper wrapper = new QueryWrapper()
                .eq("account", request.getAccount());
        if (baseMapper.get(UserEntity.class, wrapper) != null) {
            throw new FailException("注册失败，账号已存在");
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(request, user);
        user.setType(UserTypeEnum.ADMIN.name());
        user.setDeleted(false);
        if (baseMapper.add(user)) {
            return saveToken(user.getId(), user.getType(), 12);
        }
        throw new FailException("注册失败");
    }


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
        user.setPassword(null);
        return user;
    }

    @Override
    public String saveToken(Integer uid, String userType, int hour) {
        String token = this.createToken(uid);
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, userType, hour, TimeUnit.HOURS);
        return token;
    }

    @Override
    public boolean removeToken() {
        String token = this.getHeaderToken();
        return Optional.ofNullable(redisTemplate.delete(TOKEN_PREFIX + token))
                .orElse(false);
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
        return Optional.ofNullable(request.getHeader(TokenEnum.HEADER.getValue()))
                .orElseThrow(() -> new OauthException("未携带令牌"))
                .substring(TokenEnum.TOKEN_PREFIX.getValue().length());
    }


}
