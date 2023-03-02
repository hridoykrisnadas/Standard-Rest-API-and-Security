package com.hridoykrisna.stdapi.repository;

import com.hridoykrisna.stdapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsernameAndIsActiveTrue(String username);
}
