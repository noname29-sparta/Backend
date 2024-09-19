package com.project.p_delivery_manager.infrastructure.repository;

import com.project.p_delivery_manager.domain.model.DeliveryManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryManagerJpaRepository extends JpaRepository<DeliveryManager, UUID> {
}
