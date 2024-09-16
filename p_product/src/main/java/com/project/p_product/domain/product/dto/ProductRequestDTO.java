package com.project.p_product.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class ProductRequestDTO {

    private UUID companyId;

    private UUID hubId;

    private String name;

    private Integer stock;

    private Integer price;
}
