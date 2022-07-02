package com.walle.jwtserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .addFilter(corsConfig.corsFilter()) // @CrossOrigin(인증이 없을때), 인증이 필요할 때는 시큐리티 필터에 등록
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않겠다라는 의미

                .and()
                .formLogin().disable()
                .httpBasic().disable() // httpBasic : HTTP Header Authorization에 인증정보(ID, PASSWORD)를 넣고 요청하는 방법 (암호화가 안되는 http 사용X)
                                       // Bearer    : HTTP Header Authorization에 JWT 토큰을 넣고 요청하는 방법 (노출이 되어도 httpBasic보다 상대적으로 안전)

                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")

                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")

                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")

                .anyRequest().permitAll();

    }
}
