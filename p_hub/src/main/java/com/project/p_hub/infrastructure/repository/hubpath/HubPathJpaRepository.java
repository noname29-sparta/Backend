package com.project.p_hub.infrastructure.repository.hubpath;

import com.project.p_hub.domain.model.HubPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HubPathJpaRepository extends JpaRepository<HubPath, UUID> {
}
