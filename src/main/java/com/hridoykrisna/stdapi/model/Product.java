package com.hridoykrisna.stdapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel{
    private String name;
    private String Description;
    private float price;
}
