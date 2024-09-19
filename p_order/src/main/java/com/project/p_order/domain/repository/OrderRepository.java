package com.project.p_order.domain.repository;

import com.project.p_order.application.dtos.OrderDto;
import com.project.p_order.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(UUID id);
    List<Order> findAll();
    Page<Order> searchOrders(OrderDto request, Pageable pageable);
}
