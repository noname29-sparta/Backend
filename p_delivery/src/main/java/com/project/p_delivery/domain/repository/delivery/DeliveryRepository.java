package com.project.p_delivery.domain.repository.delivery;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.domain.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {
    Delivery save(Delivery delivery);
    Optional<Delivery> findById(UUID id);
    List<Delivery> findAll();
    Page<Delivery> searchDeliveries(DeliveryDto request, Pageable pageable);
}
