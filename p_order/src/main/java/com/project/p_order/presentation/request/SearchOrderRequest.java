package com.project.p_order.presentation.request;

import com.project.p_order.application.dtos.OrderDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderRequest {

    private UUID requesterCompanyId; // 요청 업체 ID (공급업체)

    private UUID receiverCompanyId; // 수령 업체 ID

    private UUID productId; // 상품 ID

    private UUID deliveryId; // 배송 ID

    private Integer quantity; // 수량

    private Integer price;

    public OrderDto toDTO() {
        return OrderDto.create(
                this.requesterCompanyId,
                this.receiverCompanyId,
                this.productId,
                this.deliveryId,
                this.quantity,
                this.price
        );
    }
}
