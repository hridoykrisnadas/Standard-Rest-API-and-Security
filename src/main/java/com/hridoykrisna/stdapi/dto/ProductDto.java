package com.hridoykrisna.stdapi.dto;

import com.hridoykrisna.stdapi.model.BaseModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDto extends BaseModel{
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Description is Mandatory")
    private String Description;
    @Min(value = 1, message = "Price Must Mandatory")
    private float price;

}
