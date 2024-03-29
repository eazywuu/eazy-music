package xyz.eazywu.music.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import xyz.eazywu.music.config.SecurityConfig;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求授权： 通过判断token确认是否授权
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    /**
     * 验证请求携带的token是否正确且未失效
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConfig.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken token = getAuthenticationToken(header);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()))
                .build()
                .verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
                .getSubject();
        if (username != null) {
            User user = userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }
        return null;
    }
}
