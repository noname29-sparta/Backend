package com.project.p_product.presentation.controller;

import com.project.p_product.application.service.ProductService;
import com.project.p_product.presentation.request.ProductRequest;
import com.project.p_product.presentation.request.SearchProductRequest;
import com.project.p_product.presentation.request.UpdateProductRequest;
import com.project.p_product.presentation.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // 상품 생성
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {

        return ResponseEntity.ok().body(productService.createProduct(productRequest.toDTO()));
    }

    // 상품 단건 조회
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "productId") UUID productId) {

        return ResponseEntity.ok().body(productService.getProduct(productId));
    }

    // 상품 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllProducts(){

        return ResponseEntity.ok().body(productService.getAllProducts());
    }


    // 상품 수정
    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "productId") UUID productId,
                                       @RequestBody UpdateProductRequest updateProductRequest) {

        return ResponseEntity.ok().body(productService.updateProduct(productId, updateProductRequest.toDTO()));
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") UUID productId) {

        return ResponseEntity.ok().body(productService.deleteProduct(productId));
    }

    // 상품 검색
    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> searchProducts(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) UUID hubId,
                                                        @RequestParam(required = false) UUID companyId,
                                                        @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "20") int size) {

        // HubSearchDto에 파라미터 설정
        SearchProductRequest searchDto = SearchProductRequest.builder()
                .name(name)
                .hubId(hubId)
                .companyId(companyId)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(productService.searchProducts(searchDto.toDTO(), pageRequest));
    }
}
