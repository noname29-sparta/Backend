package com.project.p_delivery.presentation.controller;

import com.project.p_delivery.application.service.delivery.DeliveryService;
import com.project.p_delivery.domain.model.DeliveryStatus;
import com.project.p_delivery.presentation.request.delivery.DeliveryRequest;
import com.project.p_delivery.presentation.request.delivery.SearchDeliveryRequest;
import com.project.p_delivery.presentation.request.delivery.UpdateDeliveryRequest;
import com.project.p_delivery.presentation.response.delivery.DeliveryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송 생성
    @PostMapping
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryRequest deliveryRequest) {

        return ResponseEntity.ok().body(deliveryService.createDelivery(deliveryRequest.toDTO()));
    }

    // 베송 단건 조회
    @GetMapping("/{deliveryId}")
    public ResponseEntity<?> getDelivery(@PathVariable(name = "deliveryId") UUID deliveryId) {

        return ResponseEntity.ok().body(deliveryService.getDelivery(deliveryId));
    }

    // 베송 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllHubs(){

        return ResponseEntity.ok().body(deliveryService.getAllDeliveries());
    }


    // 베송 수정
    @PatchMapping("/{deliveryId}")
    public ResponseEntity<?> updateDelivery(@PathVariable(name = "deliveryId") UUID deliveryId,
                                       @RequestBody UpdateDeliveryRequest updateDeliveryRequest) {

        return ResponseEntity.ok().body(deliveryService.updateDelivery(deliveryId, updateDeliveryRequest.toDTO()));
    }

    // 베송 삭제
    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<?> deleteDelivery(@PathVariable(name = "deliveryId") UUID deliveryId) {

        return ResponseEntity.ok().body(deliveryService.deleteDelivery(deliveryId));
    }

    // 베송 검색
    @GetMapping("/search")
    public ResponseEntity<Page<DeliveryResponse>> searchHubs(@RequestParam(required = false) UUID order_id,
                                                             @RequestParam(required = false) UUID startHubId,
                                                             @RequestParam(required = false) UUID endHubId,
                                                             @RequestParam(required = false) String address,
                                                             @RequestParam(required = false) String recipientName,
                                                             @RequestParam(required = false) DeliveryStatus deliveryStatus,
                                                             @RequestParam(required = false) String phone,
                                                             @RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "20") int size) {

        // HubSearchDto에 파라미터 설정
        SearchDeliveryRequest searchDto = SearchDeliveryRequest.builder()
                .orderId(order_id)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .address(address)
                .recipientName(recipientName)
                .deliveryStatus(deliveryStatus)
                .phone(phone)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(deliveryService.searchDeliveries(searchDto.toDTO(), pageRequest));
    }


}
