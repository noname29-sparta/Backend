package com.project.p_hub.infrastructure.repository;

import com.project.p_hub.domain.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HubJpaRepository extends JpaRepository<Hub, UUID> {
}
