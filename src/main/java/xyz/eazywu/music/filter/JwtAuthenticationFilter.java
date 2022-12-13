package xyz.eazywu.music.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.eazywu.music.config.SecurityConfig;
import xyz.eazywu.music.entity.User;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Date;

/**
 * 认证： 通过用户名和密码认证身份，并生成jwt
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * TODO 进行登录身份验证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // get到request中的json数据，映射到User.class生成user
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            // set到authenticationManager进行鉴定
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayDeque<>()
                    )
            );
        } catch (IOException e) {
            throw new BizException(ExceptionType.FORBIDDEN);
        }
    }

    /**
     * TODO 登录身份验证成功生成jwt
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                // 主题：用户名
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                // 过期时间：当前时间 + 有效时间
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                // 签名：使用签名算法对密钥加密
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
        //设置token名称，拼接token，加入header
        response.addHeader(SecurityConfig.HEADER_STRING, SecurityConfig.TOKEN_PREFIX + token);
    }
}
