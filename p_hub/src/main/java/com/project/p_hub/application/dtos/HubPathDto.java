package com.project.p_hub.application.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class HubPathDto {

    private UUID startHubId;

    private UUID endHubId;

    private Duration travelTime;

    private String displayName; // 이동 경로 전시명 (Optional)

    public static HubPathDto create(UUID startHubId, UUID endHubId, Duration travelTime, String displayName) {
        return HubPathDto.builder()
                .startHubId(startHubId)
                .endHubId(endHubId)
                .travelTime(travelTime)
                .displayName(displayName)
                .build();
    }
}
