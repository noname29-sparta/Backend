package com.project.p_product.application.dtos;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID id;

    private String name;

    private Integer stock;

    private Integer price;

    private UUID hubId;  // 업체 ID

    private UUID companyId;  // 상품 관리 허브 ID

    public static ProductDto create(String name, Integer stock, Integer price, UUID hubId, UUID companyId) {
        return ProductDto.builder()
                .name(name)
                .stock(stock)
                .price(price)
                .hubId(hubId)
                .companyId(companyId)
                .build();
    }
}
