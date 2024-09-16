package com.project.p_product.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Builder
@Getter
@Entity
public class Product extends BaseTimeEntity{

    @Id
    @UuidGenerator
    private UUID productId;

    @Setter
    private UUID companyId;

    @Setter
    private UUID hubId;

    @Setter
    private String name;

    @Setter
    private Integer stock;

    @Setter
    private Integer price;

    public Product() {
    }

    public static Product create(
            UUID companyId,
            UUID hubId,
            String name,
            Integer stock,
            Integer price) {

        return Product.builder()
                .companyId(companyId)
                .hubId(hubId)
                .name(name)
                .stock(stock)
                .price(price)
                .build();
    }

    public void update(String name, Integer stock, Integer price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}
