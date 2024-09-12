package com.project.p_delivery.infrastructure.repository.deliveryRoute;

import com.project.p_delivery.domain.model.DeliveryRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRouteJpaRepository extends JpaRepository<DeliveryRoute, UUID> {
}
