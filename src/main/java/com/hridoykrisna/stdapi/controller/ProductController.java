package com.hridoykrisna.stdapi.controller;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.service.ProductService;
import com.hridoykrisna.stdapi.utli.ResponseBuilder;
import com.hridoykrisna.stdapi.utli.URLConstraint;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping(URLConstraint.ProductManagement.UPDATE)
    public ResponseDto updateProduct(@PathVariable("id") long id, @Valid @RequestBody ProductDto productDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        return productService.update(id, productDto);
    }

    @DeleteMapping(URLConstraint.ProductManagement.DELETE)
    public ResponseDto deleteProduct(@PathVariable("id") long id){
        return productService.delete(id);
    }

    @GetMapping(URLConstraint.ProductManagement.GET_DETAILS)
    public ResponseDto getProduct(@PathVariable("id") long id){
        return productService.getDetails(id);
    }
    @GetMapping(URLConstraint.ProductManagement.GET_ALL)
    public ResponseDto getAllProduct(){
        return productService.getAll();
    }
}
