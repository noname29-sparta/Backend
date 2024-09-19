package com.project.p_product.domain.repository;

import com.project.p_product.application.dtos.ProductDto;
import com.project.p_product.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    Page<Product> searchProducts(ProductDto request, Pageable pageable);
}
