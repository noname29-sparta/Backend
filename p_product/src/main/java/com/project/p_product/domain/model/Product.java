package com.project.p_product.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_products")
public class Product extends BaseEntity{

    @Id
    private UUID id;

    private String name;

    private Integer stock;

    private Integer price;

    private UUID hubId;  // 업체 ID

    private UUID companyId;  // 상품 관리 허브 ID

    public static Product create(String name,
                                 Integer stock,
                                 Integer price,
                                 UUID hubId,
                                 UUID companyId
                              ) {
        return Product.builder()
                .id(UUID.randomUUID())
                .name(name)
                .stock(stock)
                .price(price)
                .hubId(hubId)
                .companyId(companyId)
                .build();
    }

    public void update(String name,
                       Integer stock,
                       Integer price,
                       UUID hubId,
                       UUID companyId) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.hubId = hubId;
        this.companyId = companyId;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }

}
