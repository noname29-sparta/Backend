package com.project.p_delivery.application.dtos.deliveryRoute;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.domain.model.DeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class DeliveryRouteDto {

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


    public static DeliveryRouteDto create(UUID deliveryId, Integer sequence, UUID startHubId, UUID endHubId, Double estimatedDistance, Duration estimatedTime, Double actualDistance, Duration actualTime, DeliveryStatus deliveryStatus) {
        return DeliveryRouteDto.builder()
                .deliveryId(deliveryId)
                .sequence(sequence)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .estimatedDistance(estimatedDistance)
                .estimatedTime(estimatedTime)
                .actualDistance(actualDistance)
                .actualTime(actualTime)
                .deliveryStatus(deliveryStatus)
                .build();
    }
}
