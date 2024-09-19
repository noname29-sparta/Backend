package com.project.p_delivery.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_delivery_routes")
public class DeliveryRoute extends BaseEntity{

    @Id
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

    public static DeliveryRoute create(UUID deliveryId, Integer sequence, UUID startHubId, UUID endHubId, Double estimatedDistance, Duration estimatedTime, Double actualDistance, Duration actualTime, DeliveryStatus deliveryStatus) {
        return DeliveryRoute.builder()
                .id(UUID.randomUUID())
                .sequence(sequence)
                .deliveryId(deliveryId)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .estimatedDistance(estimatedDistance)
                .estimatedTime(estimatedTime)
                .actualDistance(actualDistance)
                .actualTime(actualTime)
                .deliveryStatus(deliveryStatus)
                .build();
    }


    public void update(UUID deliveryId, Integer sequence, UUID startHubId, UUID endHubId, Double estimatedDistance, Duration estimatedTime, Double actualDistance, Duration actualTime, DeliveryStatus deliveryStatus) {
        this.deliveryId = deliveryId;
        this.sequence = sequence;
        this.startHubId = startHubId;
        this.endHubId = endHubId;
        this.estimatedDistance = estimatedDistance;
        this.estimatedTime = estimatedTime;
        this.actualDistance = actualDistance;
        this.actualTime = actualTime;
        this.deliveryStatus = deliveryStatus;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }
}
