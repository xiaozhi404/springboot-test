package com.example.springboottest.common.utils;

import com.example.springboottest.common.security.dto.AdminTokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;

public class JWTUtils {

    private static Integer EXPIRATION_TIME = 864_000_00; // 过期时间一天
    private static String SECRET = "xiaozhi";
    private static String tokenPrefix = "Bearer ";

    public static String buildAdminToken(AdminTokenDTO adminTokenDTO) {
        return tokenPrefix + Jwts.builder()
                .setSubject(adminTokenDTO.getSysUserId().toString())
                .claim("authorities", String.join(",", adminTokenDTO.getAuthorities()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static AdminTokenDTO extractAdminToken(String jwtToken) {
        Claims claims = Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(jwtToken.substring(tokenPrefix.length()))
                            .getBody();
        String authorities = (String) claims.get("authorities");
        String subject = claims.getSubject();
        return new AdminTokenDTO(
                Integer.parseInt(subject),
                Arrays.asList(authorities.split(","))
        );
    }


}
