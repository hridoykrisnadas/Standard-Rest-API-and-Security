package com.hridoykrisna.stdapi.service;

import com.hridoykrisna.stdapi.model.Role;
import com.hridoykrisna.stdapi.model.User;
import com.hridoykrisna.stdapi.repository.RoleRepo;
import com.hridoykrisna.stdapi.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


//@Configuration
public class DBInit {
    /*@Value("${login.usename}")
    private String username;
    @Value("${login.password")
    private String password;

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public DBInit(RoleRepo roleRepo, UserRepo userRepo){
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void init(){
        String rolename = "ADMIN";
        int roleExistCount = roleRepo.countByName(rolename);
        Role role = null;
        if (roleExistCount == 0){
            role = roleRepo.findByName(rolename);
        } else {
            role = new Role();
            role.setName(rolename);
            roleRepo.save(role);
        }

        User user = userRepo.findByUsernameAndIsActiveTrue(username);
        if (user==null){
            user = new User();
            user.setName(username);
            user.setPassword(password);
            user.setEmail("admin@hridoykrisna.com");
        }
        user.setRoleList(Arrays.asList(role));
        userRepo.save(user);
    }*/
}
