package com.project.p_delivery.application.service.deliveryRoute;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.application.dtos.deliveryRoute.DeliveryRouteDto;
import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryRoute;
import com.project.p_delivery.domain.repository.delivery.DeliveryRepository;
import com.project.p_delivery.domain.repository.deliveryRoute.DeliveryRouteRepository;
import com.project.p_delivery.presentation.response.delivery.DeliveryResponse;
import com.project.p_delivery.presentation.response.deliveryroute.DeliveryRouteResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryRouteService {

    private final DeliveryRouteRepository deliveryRouteRepository;


    // 배송경로 생성
    @Transactional
    public DeliveryRouteResponse createDeliveryRoute(DeliveryRouteDto request) {

        DeliveryRoute deliveryRoute = DeliveryRoute.create(
                request.getDeliveryId(),
                request.getSequence(),
                request.getStartHubId(),
                request.getEndHubId(),
                request.getEstimatedDistance(),
                request.getEstimatedTime(),
                request.getActualDistance(),
                request.getActualTime(),
                request.getDeliveryStatus()
        );

        deliveryRouteRepository.save(deliveryRoute);

        return DeliveryRouteResponse.of(deliveryRoute);
    }

    // 배송경로 단건 조회
    @Transactional
    public DeliveryRouteResponse getDeliveryRoute(UUID deliveryRouteId) {

        DeliveryRoute deliveryRoute = deliveryRouteRepository.findById(deliveryRouteId)
                .orElseThrow(() -> new RuntimeException("존재하는 배송경로가 없습니다."));

        return DeliveryRouteResponse.of(deliveryRoute);
    }

    // 배송경로 전체 조회
    @Transactional
    public List<DeliveryRouteResponse> getAllDeliveryRoutes() {

        List<DeliveryRoute> deliveryRoutes = deliveryRouteRepository.findAll();

        return DeliveryRouteResponse.of(deliveryRoutes);
    }

    // 배송경로 수정
    @Transactional
    public DeliveryRouteResponse updateDeliveryRoute(UUID deliveryRouteId, DeliveryRouteDto request) {
        return deliveryRouteRepository.findById(deliveryRouteId).map(deliveryRoute -> {
            deliveryRoute.update(request.getDeliveryId(), request.getSequence(), request.getStartHubId(), request.getEndHubId(), request.getEstimatedDistance(), request.getEstimatedTime(), request.getActualDistance(), request.getActualTime(), request.getDeliveryStatus());
            return DeliveryRouteResponse.of(deliveryRoute);
        }).orElseThrow(() -> new RuntimeException("존재하는 배송경로가 없습니다."));
    }

    // 배송경로 삭제
    @Transactional
    public DeliveryRouteResponse deleteDeliveryRoute(UUID deliveryRouteId) {
        return deliveryRouteRepository.findById(deliveryRouteId).map(deliveryRoute -> {
            deliveryRoute.delete(true);
            return DeliveryRouteResponse.of(deliveryRoute);
        }).orElseThrow(() -> new RuntimeException("존재하는 배송경로가 없습니다."));

    }

    // 배송경로 검색
    @Transactional
    public Page<DeliveryRouteResponse> searchDeliveryRoute(DeliveryRouteDto request, Pageable pageable) {
        Page<DeliveryRoute> deliveryRoutes = deliveryRouteRepository.searchDeliveryRoutes(request, pageable);
        return deliveryRoutes.map(DeliveryRouteResponse::of);
    }


}
