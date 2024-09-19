package com.project.p_delivery_manager.presentation.controller;

import com.project.p_delivery_manager.application.service.DeliveryManagerService;
import com.project.p_delivery_manager.domain.model.DeliveryManagerType;
import com.project.p_delivery_manager.presentation.request.DeliveryManagerRequest;
import com.project.p_delivery_manager.presentation.request.SearchDeliveryManagerRequest;
import com.project.p_delivery_manager.presentation.request.UpdateDeliveryManagerRequest;
import com.project.p_delivery_manager.presentation.response.DeliveryManagerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveryManagers")
public class DeliveryManagerController {

    private final DeliveryManagerService deliveryManagerService;

    // 배송담당자 생성
    @PostMapping
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryManagerRequest deliveryManagerRequest) {

        return ResponseEntity.ok().body(deliveryManagerService.createDeliveryManager(deliveryManagerRequest.toDTO()));
    }

    // 배송담당자 단건 조회
    @GetMapping("/{deliveryManagerId}")
    public ResponseEntity<?> getDelivery(@PathVariable(name = "deliveryManagerId") UUID deliveryManagerId) {

        return ResponseEntity.ok().body(deliveryManagerService.getDeliveryManger(deliveryManagerId));
    }

    // 배송담당자 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllHubs(){

        return ResponseEntity.ok().body(deliveryManagerService.getAllDeliveryMangers());
    }


    // 배송담당자 수정
    @PatchMapping("/{deliveryManagerId}")
    public ResponseEntity<?> updateDelivery(@PathVariable(name = "deliveryManagerId") UUID deliveryManagerId,
                                            @RequestBody UpdateDeliveryManagerRequest updateDeliveryManagerRequest) {

        return ResponseEntity.ok().body(deliveryManagerService.updateDeliveryManger(deliveryManagerId, updateDeliveryManagerRequest.toDTO()));
    }

    // 배송담당자 삭제
    @DeleteMapping("/{deliveryManagerId}")
    public ResponseEntity<?> deleteDelivery(@PathVariable(name = "deliveryManagerId") UUID deliveryManagerId) {

        return ResponseEntity.ok().body(deliveryManagerService.deleteDeliveryManger(deliveryManagerId));
    }

    // 배송담당자 검색
    @GetMapping("/search")
    public ResponseEntity<Page<DeliveryManagerResponse>> searchHubs(@RequestParam(required = false) UUID hubId,
                                                                    @RequestParam(required = false) UUID userId,
                                                                    @RequestParam(required = false) DeliveryManagerType type,
                                                                    @RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "20") int size) {

        // SearchDto에 파라미터 설정
        SearchDeliveryManagerRequest searchDto = SearchDeliveryManagerRequest.builder()
                .hubId(hubId)
                .userId(userId)
                .type(type)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(deliveryManagerService.searchDeliveryMangers(searchDto.toDTO(), pageRequest));
    }

}
