package com.project.p_delivery.presentation.controller;

import com.project.p_delivery.application.service.delivery.DeliveryService;
import com.project.p_delivery.application.service.deliveryRoute.DeliveryRouteService;
import com.project.p_delivery.domain.model.DeliveryRoute;
import com.project.p_delivery.domain.model.DeliveryStatus;
import com.project.p_delivery.presentation.request.delivery.DeliveryRequest;
import com.project.p_delivery.presentation.request.delivery.SearchDeliveryRequest;
import com.project.p_delivery.presentation.request.delivery.UpdateDeliveryRequest;
import com.project.p_delivery.presentation.request.deliveryroute.DeliveryRouteRequest;
import com.project.p_delivery.presentation.request.deliveryroute.SearchDeliveryRouteRequest;
import com.project.p_delivery.presentation.request.deliveryroute.UpdateDeliveryRouteRequest;
import com.project.p_delivery.presentation.response.delivery.DeliveryResponse;
import com.project.p_delivery.presentation.response.deliveryroute.DeliveryRouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveryRoutes")
public class DeliveryRouteController {

    private final DeliveryRouteService deliveryRouteService;

    // 배송경로 생성
    @PostMapping
    public ResponseEntity<?> createDeliveryRoute(@RequestBody DeliveryRouteRequest deliveryRouteRequest) {

        return ResponseEntity.ok().body(deliveryRouteService.createDeliveryRoute(deliveryRouteRequest.toDTO()));
    }

    // 배송경로 단건 조회
    @GetMapping("/{deliveryRouteId}")
    public ResponseEntity<?> getDeliveryRoute(@PathVariable(name = "deliveryRouteId") UUID deliveryRouteId) {

        return ResponseEntity.ok().body(deliveryRouteService.getDeliveryRoute(deliveryRouteId));
    }

    // 배송경로 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllDeliveryRoutes(){

        return ResponseEntity.ok().body(deliveryRouteService.getAllDeliveryRoutes());
    }


    // 배송경로 수정
    @PatchMapping("/{deliveryRouteId}")
    public ResponseEntity<?> updateDeliveryRoute(@PathVariable(name = "deliveryRouteId") UUID deliveryRouteId,
                                            @RequestBody UpdateDeliveryRouteRequest updateDeliveryRouteRequest) {

        return ResponseEntity.ok().body(deliveryRouteService.updateDeliveryRoute(deliveryRouteId, updateDeliveryRouteRequest.toDTO()));
    }

    // 배송경로 삭제
    @DeleteMapping("/{deliveryRouteId}")
    public ResponseEntity<?> deleteDelivery(@PathVariable(name = "deliveryRouteId") UUID deliveryRouteId) {

        return ResponseEntity.ok().body(deliveryRouteService.deleteDeliveryRoute(deliveryRouteId));
    }

    // 배송경로 검색
    @GetMapping("/search")
    public ResponseEntity<Page<DeliveryRouteResponse>> searchHubs(@RequestParam(required = false) UUID deliveryId,
                                                                  @RequestParam(required = false) Integer sequence,
                                                                  @RequestParam(required = false) UUID startHubId,
                                                                  @RequestParam(required = false) UUID endHubId,
                                                                  @RequestParam(required = false) Double estimatedDistance,
                                                                  @RequestParam(required = false) Duration estimatedTime,
                                                                  @RequestParam(required = false) Double actualDistance,
                                                                  @RequestParam(required = false) Duration actualTime,
                                                                  @RequestParam(required = false) DeliveryStatus deliveryStatus,
                                                                  @RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "20") int size) {

        // HubSearchDto에 파라미터 설정
        SearchDeliveryRouteRequest searchDto = SearchDeliveryRouteRequest.builder()
                .deliveryId(deliveryId)
                .sequence(sequence)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .estimatedDistance(estimatedDistance)
                .estimatedTime(estimatedTime)
                .actualDistance(actualDistance)
                .actualTime(actualTime)
                .deliveryStatus(deliveryStatus)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(deliveryRouteService.searchDeliveryRoute(searchDto.toDTO(), pageRequest));
    }
}
