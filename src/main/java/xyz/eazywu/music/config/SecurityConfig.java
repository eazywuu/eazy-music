package xyz.eazywu.music.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import xyz.eazywu.music.exception.RestAuthenticationEntryPoint;
import xyz.eazywu.music.filter.JwtAuthenticationFilter;
import xyz.eazywu.music.filter.JwtAuthorizationFilter;
import xyz.eazywu.music.filter.JwtProvider;
import xyz.eazywu.music.service.UserService;

/**
 * Spring Security Config
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * token前缀标注
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * header属性名 授权
     */
    public static final String HEADER_STRING = "Authorization";
    /**
     * token请求uri
     */
    public static final String CREATE_TOKEN_URI = "/tokens";
    /**
     * 网站配置请求uri
     */
    public static final String SITE_SETTING_URI = "/settings/site";

    /**
     * 歌单请求uri
     */
    public static final String PLAYLIST_URI = "/playlists/**";

    private final UserService userService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域并取消csrf校验
        http.cors().and().csrf().disable()
                // 鉴定请求时，开放"/users/"的post请求，其他请求需要鉴定
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, CREATE_TOKEN_URI).permitAll()
                .antMatchers(HttpMethod.GET, SITE_SETTING_URI).permitAll()
                .antMatchers(HttpMethod.GET, PLAYLIST_URI).permitAll()
                .anyRequest().authenticated()
                .and()
                // 添加用户登录信息filter
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 添加token filter
                .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                // 开启异常处理器
                .exceptionHandling()
                // 注入认证入口点
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                // 设置session为无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    /**
     * 指定身份验证管理器，调用userService的loadUserByUsername
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定userService
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager(), jwtProvider);
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), userService, jwtProvider);
    }

    /**
     * 白名单
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger**/**")
                .antMatchers("/webjars/**")
                .antMatchers("/v3/**")
                .antMatchers("/doc.html")
                .antMatchers("/wechat/**");
    }
}
