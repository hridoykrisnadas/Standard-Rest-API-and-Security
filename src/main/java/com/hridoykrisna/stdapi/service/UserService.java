package com.hridoykrisna.stdapi.service;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User update(Long Id, User user);

    String delete(Long Id);

    User getDetails(Long Id);

    User getByUsername(String username);

    List<User> getAll();

}
