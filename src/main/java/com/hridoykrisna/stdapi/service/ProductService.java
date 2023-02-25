package com.hridoykrisna.stdapi.service;

import com.hridoykrisna.stdapi.dto.ProductDto;
import com.hridoykrisna.stdapi.dto.ResponseDto;

import java.util.List;

public interface ProductService {
    ResponseDto save(ProductDto productDto);
    ResponseDto update(Long Id, ProductDto productDto);
    ResponseDto delete(Long Id);
    ResponseDto getDetails(Long Id);
    List<ResponseDto> getAll();
}
