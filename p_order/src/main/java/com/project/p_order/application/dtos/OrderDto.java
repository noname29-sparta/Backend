package com.project.p_order.application.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID id;

    private UUID requesterCompanyId; // 요청 업체 ID (공급업체)

    private UUID receiverCompanyId; // 수령 업체 ID

    private UUID productId; // 상품 ID

    private UUID deliveryId; // 배송 ID

    private Integer quantity; // 수량

    private Integer price;

    public static OrderDto create(UUID requesterCompanyId,
                                  UUID receiverCompanyId,
                                  UUID productId,
                                  UUID deliveryId,
                                  Integer quantity,
                                  Integer price) {
        return OrderDto.builder()
                .requesterCompanyId(requesterCompanyId)
                .receiverCompanyId(receiverCompanyId)
                .productId(productId)
                .deliveryId(deliveryId)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
