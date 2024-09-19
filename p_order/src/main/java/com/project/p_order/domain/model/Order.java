package com.project.p_order.domain.model;

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
@Table(name = "p_orders")
public class Order extends BaseEntity{

    @Id
    private UUID id;

    private UUID requesterCompanyId; // 요청 업체 ID (공급업체)

    private UUID receiverCompanyId; // 수령 업체 ID

    private UUID productId; // 상품 ID

    private UUID deliveryId; // 배송 ID

    private Integer quantity; // 수량

    private Integer price;


    public static Order create(UUID requesterCompanyId,
                               UUID receiverCompanyId,
                               UUID productId,
                               UUID deliveryId,
                               Integer quantity,
                               Integer price) {
        return Order.builder()
                .id(UUID.randomUUID())
                .requesterCompanyId(requesterCompanyId)
                .receiverCompanyId(receiverCompanyId)
                .productId(productId)
                .deliveryId(deliveryId)
                .quantity(quantity)
                .price(price)
                .build();
    }

    public void update(UUID requesterCompanyId,
                       UUID receiverCompanyId,
                       UUID productId,
                       UUID deliveryId,
                       Integer quantity,
                       Integer price) {
        this.requesterCompanyId = requesterCompanyId;
        this.receiverCompanyId = receiverCompanyId;
        this.productId = productId;
        this.deliveryId = deliveryId;
        this.quantity = quantity;
        this.price = price;

    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }
}
