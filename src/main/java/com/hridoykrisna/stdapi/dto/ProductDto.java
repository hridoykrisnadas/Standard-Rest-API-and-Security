package com.hridoykrisna.stdapi.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDto {
    private long id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Description is Mandatory")
    private String Description;
    @Min(value = 1, message = "Price Must Mandatory")
    private float price;
}
