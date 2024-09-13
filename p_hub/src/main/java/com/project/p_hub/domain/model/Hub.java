package com.project.p_hub.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_hubs")
public class Hub extends BaseEntity{

    @Id
    private UUID id;

    private String name;

    private String city;

    private Double latitude;

    private Double longitude;

    private String address;

    public static Hub create(String name, String city, Double latitude, Double longitude, String address) {
        return Hub.builder()
                .id(UUID.randomUUID())
                .name(name)
                .city(city)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .build();
    }

    public void update(String name, String city, Double latitude, Double longitude, String address) {
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }


}
