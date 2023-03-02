package com.hridoykrisna.stdapi.config;


import com.hridoykrisna.stdapi.filter.HTTPFilter;
import com.hridoykrisna.stdapi.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final HTTPFilter httpFilter;

    public SecurityConfig(HTTPFilter httpFilter, CustomUserDetailsService customUserDetailsService, MyAuthenticationEntryPoint myAuthenticationEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.httpFilter = httpFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors().
                and()
                .csrf(csrf -> csrf.disable())
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .requestMatchers("/", "/login").permitAll()
                .anyRequest().authenticated().and()
                .httpBasic(withDefaults())
                .addFilterBefore(httpFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
