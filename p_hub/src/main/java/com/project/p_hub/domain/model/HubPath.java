package com.project.p_hub.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_hub_paths")
public class HubPath {

    @Id
    private UUID id;

    private UUID startHubId;

    private UUID endHubId;

    private Duration travelTime;

}
