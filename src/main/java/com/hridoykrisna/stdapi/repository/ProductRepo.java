package com.hridoykrisna.stdapi.repository;

import com.hridoykrisna.stdapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByIdAndIsActiveTrue(Long id);
}
