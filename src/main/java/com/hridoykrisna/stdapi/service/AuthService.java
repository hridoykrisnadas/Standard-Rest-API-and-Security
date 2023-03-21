package com.hridoykrisna.stdapi.service;

import com.hridoykrisna.stdapi.dto.LoginDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;

public interface AuthService {
    ResponseDto Login(LoginDto loginDto);
}
