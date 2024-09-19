package com.project.p_product.application.service;

import com.project.p_product.application.dtos.ProductDto;
import com.project.p_product.domain.model.Product;
import com.project.p_product.domain.repository.ProductRepository;
import com.project.p_product.presentation.response.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 생성
    @Transactional
    public ProductResponse createProduct(ProductDto request) {

        Product product = Product.create(
                request.getName(),
                request.getStock(),
                request.getPrice(),
                request.getHubId(),
                request.getCompanyId()
        );

        productRepository.save(product);

        return ProductResponse.of(product);
    }

    // 상품 단건 조회
    @Transactional
    public ProductResponse getProduct(UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("존재하는 상품이 없습니다."));

        return ProductResponse.of(product);
    }

    // 상품 전체 조회
    @Transactional
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return ProductResponse.of(products);
    }

    // 상품 수정
    @Transactional
    public ProductResponse updateProduct(UUID productId, ProductDto request) {
        return productRepository.findById(productId).map(order -> {
            order.update(request.getName(), request.getStock(), request.getPrice(), request.getHubId(), request.getCompanyId());
            return ProductResponse.of(order);
        }).orElseThrow();
    }

    // 상품 삭제
    @Transactional
    public ProductResponse deleteProduct(UUID productId) {
        return productRepository.findById(productId).map(product -> {
            product.delete(true);
            return ProductResponse.of(product);
        }).orElseThrow();

    }

    // 상품 검색
    @Transactional
    public Page<ProductResponse> searchProducts(ProductDto request, Pageable pageable) {
        Page<Product> hubs = productRepository.searchProducts(request, pageable);
        return hubs.map(ProductResponse::of);
    }
}
