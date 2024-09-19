package com.project.p_order.presentation.response;

import com.project.p_order.domain.model.Order;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private UUID id;

    private UUID requesterCompanyId; // 요청 업체 ID (공급업체)

    private UUID receiverCompanyId; // 수령 업체 ID

    private UUID productId; // 상품 ID

    private UUID deliveryId; // 배송 ID

    private Integer quantity; // 수량

    private Integer price;

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .requesterCompanyId(order.getReceiverCompanyId())
                .receiverCompanyId(order.getRequesterCompanyId())
                .productId(order.getProductId())
                .deliveryId(order.getDeliveryId())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();
    }

    public static List<OrderResponse> of(List<Order> companies) {
        return companies.stream()
                .map(OrderResponse::of)
                .collect(Collectors.toList());
    }
}
