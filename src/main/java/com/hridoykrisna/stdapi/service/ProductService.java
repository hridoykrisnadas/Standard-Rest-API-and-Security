package com.hridoykrisna.stdapi.service;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;


public interface ProductService {
    ResponseDto save(ProductDto productDto);

    ResponseDto update(Long Id, ProductDto productDto);

    ResponseDto delete(Long Id);

    ResponseDto getDetails(Long Id);

    ResponseDto getAll();
}
