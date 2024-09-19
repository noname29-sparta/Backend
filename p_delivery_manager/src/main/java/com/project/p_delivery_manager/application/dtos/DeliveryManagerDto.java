package com.project.p_delivery_manager.application.dtos;


import com.project.p_delivery_manager.domain.model.DeliveryManagerType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class DeliveryManagerDto {

    private UUID hubId;  // 소속 허브

    private UUID userId; // 유저 아이디

    @Enumerated(EnumType.STRING)
    private DeliveryManagerType type;  // 배송 담당자 유형 (허브 이동 담당자, 업체 배송 담당자)

    public static DeliveryManagerDto create(UUID hubId, UUID userId, DeliveryManagerType type) {
        return DeliveryManagerDto.builder()
                .hubId(hubId)
                .userId(userId)
                .type(type)
                .build();
    }
}
