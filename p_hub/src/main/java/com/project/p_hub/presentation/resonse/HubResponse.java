package com.project.p_hub.presentation.resonse;

import com.project.p_hub.domain.model.Hub;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HubResponse {

    private UUID id;

    private String name;

    private String city;

    private Double latitude;

    private Double longitude;

    private String address;

    public static HubResponse of(Hub hub) {
        return HubResponse.builder()
                .id(hub.getId())
                .name(hub.getName())
                .city(hub.getCity())
                .latitude(hub.getLatitude())
                .longitude(hub.getLongitude())
                .address(hub.getAddress())
                .build();
    }

    public static List<HubResponse> of(List<Hub> hubs) {
        return hubs.stream()
                .map(HubResponse::of)
                .collect(Collectors.toList());
    }
}
