package com.hridoykrisna.stdapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role extends BaseModel {
    @ManyToMany(mappedBy = "roleList")
    @JsonIgnore
    private final List<User> userList = new ArrayList<>();
    @NaturalId
    private String name;
}
