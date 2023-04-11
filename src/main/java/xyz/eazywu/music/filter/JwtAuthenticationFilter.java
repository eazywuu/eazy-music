package xyz.eazywu.music.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.eazywu.music.config.SecurityConfig;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ResultType;
import xyz.eazywu.music.object.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录认证： 通过用户名和密码认证身份，并生成token
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 进行登录身份验证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // get到request中的json数据，映射到User.class生成user
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            log.info(user.toString());
            // set到authenticationManager进行鉴定
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            user.getAuthorities()
                    )
            );
        } catch (IOException e) {
            throw new BizException(ResultType.FORBIDDEN);
        }
    }

    /**
     * 登录身份验证成功生成token存入响应体header
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)  {
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
