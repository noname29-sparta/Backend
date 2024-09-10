package com.project.p_hub.presentation.request;

import com.project.p_hub.application.dtos.HubDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchHubRequest {

    private String name;

    private String city;

    private Double latitude;

    private Double longitude;

    private String address;

    public HubDto toDTO() {
        return HubDto.create(
                this.name,
                this.city,
                this.latitude,
                this.longitude,
                this.address);
    }

}
