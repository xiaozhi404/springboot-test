package com.example.springboottest.common.utils;

import com.example.springboottest.common.exception.ExceptionMsgEnum;
import com.example.springboottest.common.exception.dto.BusinessException;
import com.example.springboottest.common.security.dto.AccessToken;
import com.example.springboottest.common.security.dto.AdminTokenDTO;
import com.example.springboottest.common.security.dto.HomeTokenDTO;
import com.example.springboottest.common.security.dto.TokenOutbound;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;

public class JWTUtils {

    private static Integer EXPIRATION_TIME = 11; // 过期时间一天 864_000_00 ms
    private static Integer REFRESH_EXPIRATION_TIME = 1000*60; // 过期时间三十天 864_000_00 ms * 30
    private static String ADMIN_SECRET = "admin_secret";
    private static String ADMIN_REFRESH_SECRET = "admin_refresh_secret";
    private static String HOME_SECRET = "home_secret";
    private static String HOME_REFRESHE_SECRET = "home_refresh_secret";
    private static String tokenPrefix = "Bearer ";


    /**
     * 构建后台token
     */
    public static TokenOutbound buildAdminToken(AdminTokenDTO adminTokenDTO) {
        String accessToken = tokenPrefix + Jwts.builder()
                            .setSubject(adminTokenDTO.getSysUserId().toString())
                            .claim("authorities", String.join(",", adminTokenDTO.getAuthorities()))
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                            .signWith(SignatureAlgorithm.HS512, ADMIN_SECRET)
                            .compact();
        String refreshToken = tokenPrefix + Jwts.builder()
                            .setSubject(adminTokenDTO.getSysUserId().toString())
                            .claim("authorities", String.join(",", adminTokenDTO.getAuthorities()))
                            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                            .signWith(SignatureAlgorithm.HS512, ADMIN_SECRET)
                            .compact();
        return new TokenOutbound(accessToken, refreshToken);
    }

    /**
     * 从token取后台用户信息和权限信息
     * 若该jwt过期了 则会抛出ExpiredJwtException
     * @return
     */
    public static AdminTokenDTO extractAdminToken(String jwtToken) {
        Claims claims = Jwts.parser()
                            .setSigningKey(ADMIN_SECRET)
                            .parseClaimsJws(jwtToken.substring(tokenPrefix.length()))
                            .getBody();
        String authorities = (String) claims.get("authorities");
        String subject = claims.getSubject();
        return new AdminTokenDTO(
                Integer.parseInt(subject),
                Arrays.asList(authorities.split(","))
        );
    }

    /**
     * 构建前台token
     * @param homeTokenDTO
     * @return
     */
    public static TokenOutbound buildHomeToken(HomeTokenDTO homeTokenDTO) {
        String accessToken =  tokenPrefix + Jwts.builder()
                .setSubject(homeTokenDTO.getHomeUserId().toString())
                .claim("authorities", String.join(",", homeTokenDTO.getAuthorities()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, HOME_SECRET)
                .compact();
        String refreshToken =  tokenPrefix + Jwts.builder()
                .setSubject(homeTokenDTO.getHomeUserId().toString())
                .claim("authorities", String.join(",", homeTokenDTO.getAuthorities()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, HOME_SECRET)
                .compact();
        return new TokenOutbound(accessToken, refreshToken);
    }

    /**
     * 从token取前台用户信息和权限信息
     * @param jwtToken
     * @return
     */
    public static HomeTokenDTO extractHomeToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(HOME_SECRET)
                .parseClaimsJws(jwtToken.substring(tokenPrefix.length()))
                .getBody();
        String authorities = (String) claims.get("authorities");
        String subject = claims.getSubject();
        return new HomeTokenDTO(
                Integer.parseInt(subject),
                Arrays.asList(authorities.split(","))
        );
    }



    /**
     * 作用：刷新后台令牌（这里需要改造）
     * 一般的业务逻辑是前台用户的角色权限不会变化，后台用户的角色权限会变化，
     * 这里可以根据refreshToken要不要存权限信息进行改造。
     * 前台的实现：refreshToken存权限信息，不需要查用户权限进行返回accessToken.
     * 后台的实现：
     * 1、refreshToken不存权限信息，需要根据用户id获取权限信息，构建accessToken返回
     * 2、refreshToken存权限信息，将用户id和权限信息作为secret，
     * 这样每个用户的secret不一样，只要用户的权限信息一改变，则需要重新登录。
     * 否则的话refreshToken存权限信息，不需要查用户权限进行返回accessToken.
     *
     */
    public static AccessToken refreshHomeToken(String refreshToken) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(HOME_REFRESHE_SECRET)
                    .parseClaimsJws(refreshToken.substring(tokenPrefix.length()))
                    .getBody();
        } catch (Exception e) {
            throw new BusinessException(ExceptionMsgEnum.TOKEN_EXPIRED);
        }

        String authorities = (String) claims.get("authorities");
        String userId = claims.getSubject();
        String accessToken = tokenPrefix + Jwts.builder()
                .setSubject(userId)
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, HOME_SECRET)
                .compact();
        return new AccessToken(accessToken);
    }

}
