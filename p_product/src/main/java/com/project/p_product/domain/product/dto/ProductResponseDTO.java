package com.project.p_product.domain.product.dto;

import com.project.p_product.domain.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;
import java.util.UUID;

@Getter
public class ProductResponseDTO {

    private UUID productId;

    private UUID companyId;

    private UUID hubId;

    private String name;

    private Integer stock;

    private Integer price;

    public ProductResponseDTO(Product product) {
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.name = product.getName();
        this.hubId = product.getHubId();
        this.companyId = product.getCompanyId();
        this.productId = product.getProductId();
    }
}
