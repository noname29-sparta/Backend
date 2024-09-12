package com.project.p_delivery.presentation.request.delivery;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.domain.model.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {

    private UUID orderId;

    private UUID startHubId;

    private UUID endHubId;

    private String address;

    private String recipientName;

    private String phone;

    @Enumerated(EnumType.STRING)  // Enum을 문자열로 DB에 저장
    private DeliveryStatus deliveryStatus;

    public DeliveryDto toDTO() {
        return DeliveryDto.create(
                this.orderId,
                this.startHubId,
                this.endHubId,
                this.address,
                this.recipientName,
                this.deliveryStatus,
                this.phone
        );
    }

}
