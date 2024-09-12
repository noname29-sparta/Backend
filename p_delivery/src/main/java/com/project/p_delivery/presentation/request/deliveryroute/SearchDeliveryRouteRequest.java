package com.project.p_delivery.presentation.request.deliveryroute;

import com.project.p_delivery.application.dtos.deliveryRoute.DeliveryRouteDto;
import com.project.p_delivery.domain.model.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDeliveryRouteRequest {

    private UUID deliveryId;

    private Integer sequence;

    private UUID startHubId;

    private UUID endHubId;

    private Double estimatedDistance;

    private Duration estimatedTime;

    private Double actualDistance;

    private Duration actualTime;

    @Enumerated(EnumType.STRING)  // Enum을 문자열로 DB에 저장
    private DeliveryStatus deliveryStatus;

    public DeliveryRouteDto toDTO() {
        return DeliveryRouteDto.create(
                this.deliveryId,
                this.sequence,
                this.startHubId,
                this.endHubId,
                this.estimatedDistance,
                this.estimatedTime,
                this.actualDistance,
                this.actualTime,
                this.deliveryStatus
        );
    }
}
