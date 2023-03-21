package com.hridoykrisna.stdapi.service.IMPL;

import com.hridoykrisna.stdapi.dto.LoginDto;
import com.hridoykrisna.stdapi.dto.LoginResponseDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.filter.JwtTokenProvider;
import com.hridoykrisna.stdapi.model.User;
import com.hridoykrisna.stdapi.repository.UserRepo;
import com.hridoykrisna.stdapi.service.AuthService;
import com.hridoykrisna.stdapi.utli.ResponseBuilder;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceIMPL implements AuthService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    public AuthServiceIMPL(JwtTokenProvider jwtTokenProvider, UserRepo userRepo, AuthenticationManager authenticationManager){
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public ResponseDto Login(LoginDto loginDto) {
        User user = userRepo.findByUsername(loginDto.getUsername());
        if (user == null){
            return ResponseBuilder.getFailureMessage(HttpStatus.UNAUTHORIZED, "Invalid Username or Password");
        }
        Authentication authentications = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if (authentications.isAuthenticated()){
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(jwtTokenProvider.generateToken(authentications));
            loginResponseDto.setUsername(user.getUsername());
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Log in successfully", loginResponseDto);
        }

        return ResponseBuilder.getFailureMessage(HttpStatus.BAD_REQUEST, "Invalid Username or password");
    }
}
