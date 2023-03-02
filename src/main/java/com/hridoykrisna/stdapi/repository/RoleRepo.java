package com.hridoykrisna.stdapi.repository;

import com.hridoykrisna.stdapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    int countByName(String name);

    Role findByName(String name);
}
