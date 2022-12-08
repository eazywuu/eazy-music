package com.wyz.music_springboot.config;

import com.wyz.music_springboot.exception.RestAuthenticationEntryPoint;
import com.wyz.music_springboot.filter.JwtAuthenticationFilter;
import com.wyz.music_springboot.filter.JwtAuthorizationFilter;
import com.wyz.music_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 生成jwt的密钥
    public static final String SECRET = "EazyMusic";
    // 到期时间10 days
    public static final long EXPIRATION_TIME = 864000000;
    // token前缀标注
    public static final String TOKEN_PREFIX = "Bearer ";
    // header属性名 授权
    public static final String HEADER_STRING = "Authorization";
    //
    public static final String SITE_SETTING_URL = "/users";

    UserService userService;

    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域并取消csrf校验
        http.cors().and().csrf().disable()
                // 鉴定请求时，开放"/users/"的post请求，其他请求需要鉴定
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SITE_SETTING_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                // 添加用户登录信息filter
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                // 添加token filter
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // 开启异常处理器
                .exceptionHandling()
                // 注入认证入口点
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                // 设置session为无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定userService
        auth.userDetailsService(userService);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }
}
