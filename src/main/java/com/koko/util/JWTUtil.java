package com.koko.util;

import com.koko.common.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author 13629
 * @create 2020/12/17 9:21
 */

@Component
public class JWTUtil {

    @Value("${token.expireTime}")
    private long expireTime;

    @Value("${token.secret}")
    private String secret;

    /**
     * 1分钟
     */
    private static final int EXPIRATION_TIME_MINUTE = 1000 * 60;

    /**
     * 生成token
     */
    public String generate(String username) {
        long now = System.currentTimeMillis();
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generateKey();
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expireTime * EXPIRATION_TIME_MINUTE))
                .signWith(algorithm, secretKey);
        return builder.compact();
    }

    /**
     * 解析token
     */
    public Claims parseToken(String token) {
        SecretKey secretKey = generateKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     * 是否过期
     */
    public boolean isExpiration(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    /**
     * 生成密钥
     */
    private SecretKey generateKey() {
        byte[] bytes = Base64Util.encode(secret).getBytes();
        return new SecretKeySpec(bytes, "AES");
    }

    /**
     * 获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        return parseToken(token).getSubject();
    }
}
