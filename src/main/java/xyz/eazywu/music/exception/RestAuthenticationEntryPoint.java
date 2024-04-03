package xyz.eazywu.music.exception;

import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理器
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 设置验证后返回的response相关信息
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 设置响应体状态 code
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResponse = new ErrorResponse();
        // 设置响应体内容 json内部code字段
        errorResponse.setCode(ExceptionType.UNAUTHORIZED.getCode());
        errorResponse.setMessage(ExceptionType.UNAUTHORIZED.getMessage());
        response.getWriter().println(JSONUtil.parse(errorResponse));
        response.getWriter().flush();
    }
}
