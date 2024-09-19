package com.project.p_delivery_manager.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_delivery_managers")
public class DeliveryManager extends BaseEntity{

    @Id
    private UUID id;

    private UUID hubId;  // 소속 허브

    private UUID userId; // 유저 아이디

    @Enumerated(EnumType.STRING)
    private DeliveryManagerType type;  // 배송 담당자 유형 (허브 이동 담당자, 업체 배송 담당자)


    public static DeliveryManager create(UUID hubId, UUID userId, DeliveryManagerType type) {
        return DeliveryManager.builder()
                .id(UUID.randomUUID())
                .hubId(hubId)
                .userId(userId)
                .type(type)
                .build();
    }


    public void update(UUID hubId, UUID userId, DeliveryManagerType type) {
        this.hubId = hubId;
        this.userId = userId;
        this.type = type;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }
}
