package com.hridoykrisna.stdapi.filter;

import com.hridoykrisna.stdapi.model.User;
import com.hridoykrisna.stdapi.repository.UserRepo;
import com.hridoykrisna.stdapi.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepo userRepo;
    public JwtAuthenticationFilter(UserRepo userRepo, JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = getJwtFormRequest(request);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.isValidate(jwt)) {
                Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
                User user = userRepo.findByIdAndIsActiveTrue(userId);
                System.out.println(user.toString());
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFormRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
