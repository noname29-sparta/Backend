package com.project.p_delivery_manager.presentation.request;

import com.project.p_delivery_manager.application.dtos.DeliveryManagerDto;
import com.project.p_delivery_manager.domain.model.DeliveryManagerType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryManagerRequest {

    private UUID hubId;  // 소속 허브

    private UUID userId; // 유저 아이디

    @Enumerated(EnumType.STRING)
    private DeliveryManagerType type;  // 배송 담당자 유형 (허브 이동 담당자, 업체 배송 담당자)

    public DeliveryManagerDto toDTO() {
        return DeliveryManagerDto.create(
                this.hubId,
                this.userId,
                this.type
        );
    }

}
