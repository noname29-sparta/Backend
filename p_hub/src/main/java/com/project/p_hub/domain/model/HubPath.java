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
public class HubPath extends BaseEntity{

    @Id
    private UUID id;

    private UUID startHubId;

    private UUID endHubId;

    private Duration travelTime;

    private String displayName; // 이동 경로 전시명 (Optional)

    public static HubPath create(UUID startHubId, UUID endHubId, Duration travelTime, String displayName) {
        return HubPath.builder()
                .id(UUID.randomUUID())
                .startHubId(startHubId)
                .endHubId(endHubId)
                .travelTime(travelTime)
                .displayName(displayName)
                .build();
    }

    public void update(UUID startHubId, UUID endHubId, Duration travelTime, String displayName) {
        this.startHubId = startHubId;
        this.endHubId = endHubId;
        this.travelTime = travelTime;
        this.displayName = displayName;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }
}
