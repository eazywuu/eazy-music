package xyz.eazywu.music.filter;

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

    private final JwtProvider jwtProvider;

    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    /**
     * 验证请求携带的token是否正确且未失效
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头
        String header = request.getHeader(SecurityConfig.HEADER_STRING);

        // 验证请求头，空或token prefix不匹配，继续执行filter chain
        if (header == null || !header.startsWith(SecurityConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken token = this.getAuthenticationToken(header);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        if (header == null) {
            return null;
        }
        // 反向获取username
        // 指定生成jwt的加密算法
        String username = jwtProvider.getUsernameFromToken(header.replace(SecurityConfig.HEADER_STRING, ""));

        if (username == null) {
            return null;
        }

        User user = userService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
    }
}
