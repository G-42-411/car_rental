package com.koko.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @author 13629
 * @create 2020/12/17 9:21
 */

public class JwtHelper {

    @Value("${token.expireTime}")
    private static long expireTime;

    @Value("${token.secret}")
    private static String secret;

    public static String generate(Map<String, Object> obj) {
        long now = System.currentTimeMillis();
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generateKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(obj)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expireTime))
                .signWith(algorithm, secretKey);
        return builder.compact();
    }

    public static Claims parse(String token) {
        SecretKey secretKey = generateKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private static SecretKey generateKey() {
        byte[] bytes = Base64Util.encode(secret).getBytes();
        return new SecretKeySpec(bytes, "AES");
    }
}
