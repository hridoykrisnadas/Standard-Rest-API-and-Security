package com.hridoykrisna.stdapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.util.List;
@Data
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    @NaturalId
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private List<Role> roleList;
}
