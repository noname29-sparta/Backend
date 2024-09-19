package com.project.p_order.presentation.controller;

import com.project.p_order.application.service.OrderService;
import com.project.p_order.presentation.request.OrderRequest;
import com.project.p_order.presentation.request.SearchOrderRequest;
import com.project.p_order.presentation.request.UpdateOrderRequest;
import com.project.p_order.presentation.response.OrderResponse;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {

        return ResponseEntity.ok().body(orderService.createOrder(orderRequest.toDTO()));
    }

    // 주문 단건 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable(name = "orderId") UUID orderId) {

        return ResponseEntity.ok().body(orderService.getOrder(orderId));
    }

    // 주문 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllOrders(){

        return ResponseEntity.ok().body(orderService.getAllOrders());
    }


    // 주문 수정
    @PatchMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable(name = "orderId") UUID orderId,
                                           @RequestBody UpdateOrderRequest updateOrderRequest) {

        return ResponseEntity.ok().body(orderService.updateOrder(orderId, updateOrderRequest.toDTO()));
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "orderId") UUID orderId) {

        return ResponseEntity.ok().body(orderService.deleteOrder(orderId));
    }

    // 주문 검색
    @GetMapping("/search")
    public ResponseEntity<Page<OrderResponse>> searchOrders(@RequestParam(required = false) UUID requesterCompanyId,
                                                            @RequestParam(required = false) UUID receiverCompanyId,
                                                            @RequestParam(required = false) UUID productId,
                                                            @RequestParam(required = false) UUID deliveryId,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "20") int size) {


        // SearchDto에 파라미터 설정
        SearchOrderRequest searchDto = SearchOrderRequest.builder()
                .requesterCompanyId(requesterCompanyId)
                .receiverCompanyId(receiverCompanyId)
                .productId(productId)
                .deliveryId(deliveryId)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(orderService.searchOrders(searchDto.toDTO(), pageRequest));
    }

}
