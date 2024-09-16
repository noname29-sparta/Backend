package com.project.p_product.domain.product.service;

import com.project.p_product.domain.product.dto.ProductRequestDTO;
import com.project.p_product.domain.product.dto.ProductResponseDTO;
import com.project.p_product.domain.product.entity.Product;
import com.project.p_product.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        Product product = Product.create(
                productRequestDTO.getCompanyId(),
                productRequestDTO.getHubId(),
                productRequestDTO.getName(),
                productRequestDTO.getStock(),
                productRequestDTO.getPrice()
        );

        productRepository.save(product);

        return new ProductResponseDTO(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getProduct(UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("상품이 없습니다."));

        return new ProductResponseDTO(product);
    }

    @Transactional
    public ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO productRequestDTO) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));

        product.update(
                productRequestDTO.getName(),
                productRequestDTO.getStock(),
                productRequestDTO.getPrice()
        );

        productRepository.save(product);

        return new ProductResponseDTO(product);
    }

    @Transactional
    public void deleteProduct(UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));

        productRepository.delete(product);
    }


}
