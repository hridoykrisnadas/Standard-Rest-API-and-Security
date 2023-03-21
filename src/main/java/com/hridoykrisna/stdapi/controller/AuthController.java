package com.hridoykrisna.stdapi.controller;

import com.hridoykrisna.stdapi.dto.LoginDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.service.AuthService;
import com.hridoykrisna.stdapi.utli.URLConstraint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstraint.AuthManagement.ROOT)
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(PasswordEncoder passwordEncoder, AuthService authService){
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseDto login(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest, HttpServletResponse response){
        return authService.Login(loginDto);
    }
}
