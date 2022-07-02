package com.walle.jwtserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import java.io.IOException;

public class MyFilter2 implements Filter {

    private final Logger log = LoggerFactory.getLogger(MyFilter2.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("필터2");
        chain.doFilter(request, response);
    }
}
