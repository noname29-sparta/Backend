package com.project.p_hub.presentation.response;

import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.model.HubPath;
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
public class HubPathResponse {

    private UUID id;

    private UUID startHubId;

    private UUID endHubId;

    private Duration travelTime;

    private String displayName; // 이동 경로 전시명 (Optional)

    public static HubPathResponse of(HubPath hubPath) {
        return HubPathResponse.builder()
                .id(hubPath.getId())
                .startHubId(hubPath.getStartHubId())
                .endHubId(hubPath.getEndHubId())
                .travelTime(hubPath.getTravelTime())
                .displayName(hubPath.getDisplayName())
                .build();
    }

    public static List<HubPathResponse> of(List<HubPath> hubPaths) {
        return hubPaths.stream()
                .map(HubPathResponse::of)
                .collect(Collectors.toList());
    }
}
