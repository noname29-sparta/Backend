package com.project.p_delivery.presentation.response.deliveryroute;

import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryRoute;
import com.project.p_delivery.domain.model.DeliveryStatus;
import com.project.p_delivery.presentation.response.delivery.DeliveryResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRouteResponse {

    private UUID id;

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

    public static DeliveryRouteResponse of(DeliveryRoute deliveryRoute) {
        return DeliveryRouteResponse.builder()
                .id(deliveryRoute.getId())
                .deliveryId(deliveryRoute.getDeliveryId())
                .sequence(deliveryRoute.getSequence())
                .startHubId(deliveryRoute.getStartHubId())
                .endHubId(deliveryRoute.getEndHubId())
                .estimatedDistance(deliveryRoute.getEstimatedDistance())
                .estimatedTime(deliveryRoute.getEstimatedTime())
                .actualDistance(deliveryRoute.getActualDistance())
                .actualTime(deliveryRoute.getActualTime())
                .deliveryStatus(deliveryRoute.getDeliveryStatus())
                .build();
    }

    public static List<DeliveryRouteResponse> of(List<DeliveryRoute> deliveryRoutes) {
        return deliveryRoutes.stream()
                .map(DeliveryRouteResponse::of)
                .collect(Collectors.toList());
    }
}
