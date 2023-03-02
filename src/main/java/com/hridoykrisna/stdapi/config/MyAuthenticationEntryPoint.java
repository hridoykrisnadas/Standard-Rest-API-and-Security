package com.hridoykrisna.stdapi.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Configuration
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private static final Logger logger = LogManager.getLogManager().getLogger(MyAuthenticationEntryPoint.class.getName());
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic Realm= "+getRealmName());
        response.sendError(response.SC_UNAUTHORIZED, "Unauthorized Request");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Standard Rest API");
        super.afterPropertiesSet();
    }
}
