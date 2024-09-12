package com.project.p_delivery.presentation.response.delivery;

import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {

    private UUID id;

    private UUID orderId;

    private UUID startHubId;

    private UUID endHubId;

    private String address;

    private String recipientName;

    @Enumerated(EnumType.STRING)  // Enum을 문자열로 DB에 저장
    private DeliveryStatus deliveryStatus;

    private String phone;

    public static DeliveryResponse of(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .orderId(delivery.getOrderId())
                .startHubId(delivery.getStartHubId())
                .endHubId(delivery.getEndHubId())
                .address(delivery.getAddress())
                .recipientName(delivery.getRecipientName())
                .deliveryStatus(delivery.getDeliveryStatus())
                .phone(delivery.getPhone())
                .build();
    }

    public static List<DeliveryResponse> of(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(DeliveryResponse::of)
                .collect(Collectors.toList());
    }
}
