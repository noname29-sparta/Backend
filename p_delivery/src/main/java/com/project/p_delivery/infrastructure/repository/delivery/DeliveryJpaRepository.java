package com.project.p_delivery.infrastructure.repository.delivery;

import com.project.p_delivery.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryJpaRepository extends JpaRepository<Delivery, UUID> {
}
