package com.hridoykrisna.stdapi.service;


import com.hridoykrisna.stdapi.dto.UserPrincipal;
import com.hridoykrisna.stdapi.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        UserPrincipal userPrincipal = UserPrincipal.createUser(user);
        if (username == null){
            throw new UsernameNotFoundException("Username Not Found");
        }
        return userPrincipal;
    }
}
