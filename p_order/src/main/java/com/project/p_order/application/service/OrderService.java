package com.project.p_order.application.service;

import com.project.p_order.application.dtos.OrderDto;
import com.project.p_order.domain.model.Order;
import com.project.p_order.domain.repository.OrderRepository;
import com.project.p_order.presentation.response.OrderResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문 생성
    @Transactional
    public OrderResponse createOrder(OrderDto request) {

        Order order = Order.create(
                request.getRequesterCompanyId(),
                request.getReceiverCompanyId(),
                request.getProductId(),
                request.getDeliveryId(),
                request.getQuantity(),
                request.getPrice()
        );

        orderRepository.save(order);

        return OrderResponse.of(order);
    }

    // 주문 단건 조회
    @Transactional
    public OrderResponse getOrder(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("존재하는 주문이 없습니다."));

        return OrderResponse.of(order);
    }

    // 주문 전체 조회
    @Transactional
    public List<OrderResponse> getAllOrders() {

        List<Order> companies = orderRepository.findAll();

        return OrderResponse.of(companies);
    }

    // 주문 수정
    @Transactional
    public OrderResponse updateOrder(UUID orderId, OrderDto request) {
        return orderRepository.findById(orderId).map(order -> {
            order.update(request.getRequesterCompanyId(), request.getReceiverCompanyId(), request.getProductId(), request.getDeliveryId(), request.getQuantity(), request.getPrice());
            return OrderResponse.of(order);
        }).orElseThrow();
    }

    // 주문 삭제
    @Transactional
    public OrderResponse deleteOrder(UUID orderId) {
        return orderRepository.findById(orderId).map(order -> {
            order.delete(true);
            return OrderResponse.of(order);
        }).orElseThrow();

    }

    // 주문 검색
    @Transactional
    public Page<OrderResponse> searchOrders(OrderDto request, Pageable pageable) {
        Page<Order> hubs = orderRepository.searchOrders(request, pageable);
        return hubs.map(OrderResponse::of);
    }
}
