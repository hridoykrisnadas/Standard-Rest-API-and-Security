package com.hridoykrisna.stdapi.repository;

import com.hridoykrisna.stdapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByIdAndIsActiveTrue(Long id);
}
