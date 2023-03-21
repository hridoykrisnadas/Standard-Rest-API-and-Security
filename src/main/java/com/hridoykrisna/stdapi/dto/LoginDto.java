package com.hridoykrisna.stdapi.dto;

import com.hridoykrisna.stdapi.model.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginDto extends BaseModel {
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank (message = "Password is mandatory")
    private String password;

}
