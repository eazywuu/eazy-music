package xyz.eazywu.music.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt生成器
 *
 * @author Patrik Bateman
 * @version 1.0.0
 * @since 2024/4/3 14:25
 */
@Component
public class JwtProvider {

    /**
     * 生成jwt的密钥
     */
    private final String secret;
    /**
     * 到期时间10 days
     */
    private final long expirationTime;

    public JwtProvider(@Value("jwt.secret") String secret, @Value("jwt.expiration-time") long expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    public String generateToken(String username) {
        return JWT.create()
                // 主题：用户名
                .withSubject(username)
                // 过期时间：当前时间 + 有效时间
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                // 签名：使用签名算法对密钥加密
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                // 验证header
                .verify(token)
                .getSubject();
    }
}
