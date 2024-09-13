package com.project.p_hub.presentation.request.hubpath;

import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.application.dtos.HubPathDto;
import lombok.*;

import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HubPathRequest {

    private UUID startHubId;

    private UUID endHubId;

    private Duration travelTime;

    private String displayName; // 이동 경로 전시명 (Optional)

    public HubPathDto toDTO() {
        return HubPathDto.create(
                this.startHubId,
                this.endHubId,
                this.travelTime,
                this.displayName);
    }

}
