package com.project.p_product.presentation.response;

import com.project.p_product.domain.model.Product;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private UUID id;

    private String name;

    private Integer stock;

    private Integer price;

    private UUID hubId;  // 업체 ID

    private UUID companyId;  // 상품 관리 허브 ID

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .price(product.getPrice())
                .hubId(product.getHubId())
                .companyId(product.getCompanyId())
                .build();
    }

    public static List<ProductResponse> of(List<Product> products) {
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
