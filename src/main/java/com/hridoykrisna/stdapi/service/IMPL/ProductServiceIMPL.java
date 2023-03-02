package com.hridoykrisna.stdapi.service.IMPL;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;
import com.hridoykrisna.stdapi.model.Product;
import com.hridoykrisna.stdapi.repository.ProductRepo;
import com.hridoykrisna.stdapi.service.ProductService;
import com.hridoykrisna.stdapi.utli.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ProductService")
public class ProductServiceIMPL implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private int numberOfElement = 0;

    public ProductServiceIMPL(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product = productRepo.save(product);

        if (product != null) {
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "Product Saved", product);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public ResponseDto update(Long Id, ProductDto productDto) {
        Product product = productRepo.findByIdAndIsActiveTrue(Id);
        if (product != null) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(productDto, product);
            product = productRepo.save(product);
            if (product != null) {
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Product Update", product);
            }
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found!!");
    }

    @Override
    public ResponseDto delete(Long Id) {
        Product product = productRepo.findByIdAndIsActiveTrue(Id);
        if (product != null) {
            product.setIsActive(false);
            product = productRepo.save(product);
            if (product != null) {
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Product Deleted", product);
            }
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found!!");
    }

    @Override
    public ResponseDto getDetails(Long Id) {
        Product product = productRepo.findByIdAndIsActiveTrue(Id);
        if (product != null) {
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", product);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found!!");
    }

    @Override
    public ResponseDto getAll() {
        List<Product> productList = productRepo.findAllByIsActiveTrue();
        List<ProductDto> productDtoList = this.getProductDtos(productList);
        numberOfElement = productDtoList.get(0).getClass().getDeclaredFields().length;
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", productDtoList, numberOfElement, productDtoList.size());
    }

    private List<ProductDto> getProductDtos(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        products.forEach(product -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            productDtoList.add(productDto);

        });
        return productDtoList;
    }
}
