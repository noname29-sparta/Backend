package com.project.p_hub.application.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class HubDto {

    private String name;

    private String city;

    private Double latitude;

    private Double longitude;

    private String address;

    public static HubDto create(String name, String city, Double latitude, Double longitude, String address ) {
        return HubDto.builder()
                .name(name)
                .city(city)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .build();
    }
}
