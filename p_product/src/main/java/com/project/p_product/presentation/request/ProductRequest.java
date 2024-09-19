package com.project.p_product.presentation.request;

import com.project.p_product.application.dtos.ProductDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String name;

    private Integer stock;

    private Integer price;

    private UUID hubId;  // 업체 ID

    private UUID companyId;  // 상품 관리 허브 ID

    public ProductDto toDTO() {
        return ProductDto.create(
                this.name,
                this.stock,
                this.price,
                this.hubId,
                this.companyId
        );
    }
}
