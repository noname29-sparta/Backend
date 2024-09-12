package com.project.p_delivery.application.dtos.delivery;

import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class DeliveryDto {

    private UUID orderId;

    private UUID startHubId;

    private UUID endHubId;

    private String address;

    private String recipientName;

    private DeliveryStatus deliveryStatus;

    private String phone;

    public static DeliveryDto create(UUID order_id, UUID startHubId, UUID endHubId, String address, String recipientName, DeliveryStatus deliveryStatus, String phone) {
        return DeliveryDto.builder()
                .orderId(order_id)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .address(address)
                .recipientName(recipientName)
                .deliveryStatus(deliveryStatus)
                .phone(phone)
                .build();
    }

}
