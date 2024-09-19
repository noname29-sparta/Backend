package com.project.p_delivery_manager.domain.repository;

import com.project.p_delivery_manager.application.dtos.DeliveryManagerDto;
import com.project.p_delivery_manager.domain.model.DeliveryManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryManagerRepository {

    DeliveryManager save(DeliveryManager delivery);
    Optional<DeliveryManager> findById(UUID id);
    List<DeliveryManager> findAll();
    Page<DeliveryManager> searchDeliveryManagers(DeliveryManagerDto request, Pageable pageable);
}
