package com.project.p_delivery.domain.repository.deliveryRoute;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.application.dtos.deliveryRoute.DeliveryRouteDto;
import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryRouteRepository {

    DeliveryRoute save(DeliveryRoute deliveryRoute);
    Optional<DeliveryRoute> findById(UUID id);
    List<DeliveryRoute> findAll();
    Page<DeliveryRoute> searchDeliveryRoutes(DeliveryRouteDto request, Pageable pageable);
}
