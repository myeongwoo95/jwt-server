package com.walle.jwtserver.config;

import com.walle.jwtserver.filter.MyFilter1;
import com.walle.jwtserver.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // SecurityConfig에서 http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter)로 등록한 필터가 일반 필터보다 우선순위가 높다.
    // 시큐리티 필터 이전에 필터를 동작시키고 싶다면 시큐티리피 필터 체인중에 가장 앞에있는 SecurityContextPersistenceFilter 보다 앞에 필터를 등록한다.(http.addFilterBefore)

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1(){
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*"); // 모든 요청에 다 필터를 걸겠다는 의미
        bean.setOrder(0); //낮은 번호가 필터중에 가장 먼저 실행됨
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2(){
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
}
