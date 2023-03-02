package com.hridoykrisna.stdapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hridoykrisna.stdapi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> grandAuthorities;

    public UserPrincipal(long id, String username, String password, Collection<? extends GrantedAuthority> grandAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grandAuthorities = grandAuthorities;
    }

    public static UserPrincipal createUser(User user){
        try {
            List<GrantedAuthority> authorityList = user.getRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
            return new UserPrincipal(user.getId(), user.getName(), user.getPassword(), authorityList);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
