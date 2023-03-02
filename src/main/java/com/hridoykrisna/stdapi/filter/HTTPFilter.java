package com.hridoykrisna.stdapi.filter;

import com.hridoykrisna.stdapi.model.User;
import com.hridoykrisna.stdapi.service.CustomUserDetailsService;
import com.hridoykrisna.stdapi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Configuration
public class HTTPFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public HTTPFilter(UserService userService, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    private User getUser(HttpServletRequest request) {
        String authString = null;
        Enumeration<String> headersNameEnumeration = request.getHeaderNames();
        while (headersNameEnumeration.hasMoreElements()) {
            String headerKey = headersNameEnumeration.nextElement();
            if (headerKey.equalsIgnoreCase("Authorization")) {
                authString = request.getHeader(headerKey);
                break;
            }
        }
        if (authString != null && !authString.equals("")) {
            String[] authParts = authString.split("\\s");
            String authInfo = authParts[1];
            byte[] bytes = DatatypeConverter.parseBase64Binary(authInfo);
            String decodeAuth = new String(bytes);
            String userNameAndPassword[] = decodeAuth.split(":");
            if (userNameAndPassword[0] != null && userNameAndPassword[1] != null) {
                User user = userService.getByUsername(userNameAndPassword[0]);
                if (passwordEncoder.matches(userNameAndPassword[1], user.getPassword())){
                    return user;
                }
                return null;
            }
        }
        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            User user = getUser(request);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception e){
            throw new RuntimeException();
        }
        filterChain.doFilter(request, response);
    }
}
