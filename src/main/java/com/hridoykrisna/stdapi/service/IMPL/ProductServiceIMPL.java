package com.hridoykrisna.stdapi.service.IMPL;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.model.Product;
import com.hridoykrisna.stdapi.repository.ProductRepo;
import com.hridoykrisna.stdapi.service.ProductService;
import com.hridoykrisna.stdapi.utli.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService")
public class ProductServiceIMPL implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    public ProductServiceIMPL(ProductRepo productRepo, ModelMapper modelMapper){
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product = productRepo.save(product);
        if (product != null){
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "Product Saved", product);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public ResponseDto update(Long Id, ProductDto productDto) {
        return null;
    }

    @Override
    public ResponseDto delete(Long Id) {
        return null;
    }

    @Override
    public ResponseDto getDetails(Long Id) {
        return null;
    }

    @Override
    public List<ResponseDto> getAll() {
        return null;
    }
}
