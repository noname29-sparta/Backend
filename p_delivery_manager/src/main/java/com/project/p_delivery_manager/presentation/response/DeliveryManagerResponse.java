package com.project.p_delivery_manager.presentation.response;


import com.project.p_delivery_manager.domain.model.DeliveryManager;
import com.project.p_delivery_manager.domain.model.DeliveryManagerType;
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
public class DeliveryManagerResponse {

    private UUID id;

    private UUID hubId;  // 소속 허브

    private UUID userId; // 유저 아이디

    @Enumerated(EnumType.STRING)
    private DeliveryManagerType type;  // 배송 담당자 유형 (허브 이동 담당자, 업체 배송 담당자)


    public static DeliveryManagerResponse of(DeliveryManager deliveryManager) {
        return DeliveryManagerResponse.builder()
                .id(deliveryManager.getId())
                .hubId(deliveryManager.getHubId())
                .userId(deliveryManager.getUserId())
                .type(deliveryManager.getType())
                .build();
    }

    public static List<DeliveryManagerResponse> of(List<DeliveryManager> deliveryManagers) {
        return deliveryManagers.stream()
                .map(DeliveryManagerResponse::of)
                .collect(Collectors.toList());
    }
}
