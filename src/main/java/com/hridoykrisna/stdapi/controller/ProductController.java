package com.hridoykrisna.stdapi.controller;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.model.Product;
import com.hridoykrisna.stdapi.service.ProductService;
import com.hridoykrisna.stdapi.utli.ResponseBuilder;
import com.hridoykrisna.stdapi.utli.URLConstraint;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstraint.ProductManagement.ROOT)
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(URLConstraint.ProductManagement.CREATE)
    public ResponseDto createProduct(@Valid @RequestBody ProductDto productDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        return productService.save(productDto);
    }
}
