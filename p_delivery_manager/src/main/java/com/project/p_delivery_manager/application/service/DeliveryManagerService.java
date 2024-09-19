package com.project.p_delivery_manager.application.service;

import com.project.p_delivery_manager.application.dtos.DeliveryManagerDto;
import com.project.p_delivery_manager.domain.model.DeliveryManager;
import com.project.p_delivery_manager.domain.repository.DeliveryManagerRepository;
import com.project.p_delivery_manager.presentation.response.DeliveryManagerResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryManagerService {

    private final DeliveryManagerRepository deliveryManagerRepository;

    // 배송담당자 생성
    @Transactional
    public DeliveryManagerResponse createDeliveryManager(DeliveryManagerDto request) {

        DeliveryManager delivery = DeliveryManager.create(
                request.getHubId(),
                request.getUserId(),
                request.getType()
        );

        deliveryManagerRepository.save(delivery);

        return DeliveryManagerResponse.of(delivery);
    }

    // 배송담당자 단건 조회
    @Transactional
    public DeliveryManagerResponse getDeliveryManger(UUID deliveryManagerId) {

        DeliveryManager delivery = deliveryManagerRepository.findById(deliveryManagerId)
                .orElseThrow(() -> new RuntimeException("존재하는 배송 담당자기 없습니다."));

        return DeliveryManagerResponse.of(delivery);
    }

    // 배송담당자 전체 조회
    @Transactional
    public List<DeliveryManagerResponse> getAllDeliveryMangers() {

        List<DeliveryManager> deliveries = deliveryManagerRepository.findAll();

        return DeliveryManagerResponse.of(deliveries);
    }

    // 배송담당자 수정
    @Transactional
    public DeliveryManagerResponse updateDeliveryManger(UUID deliveryManagerId, DeliveryManagerDto request) {
        return deliveryManagerRepository.findById(deliveryManagerId).map(delivery -> {
            delivery.update(request.getHubId(), request.getUserId(), request.getType());
            return DeliveryManagerResponse.of(delivery);
        }).orElseThrow(() -> new RuntimeException("존재하는 배송담당자가 없습니다."));
    }

    // 배송담당자 삭제
    @Transactional
    public DeliveryManagerResponse deleteDeliveryManger(UUID deliveryId) {
        return deliveryManagerRepository.findById(deliveryId).map(delivery -> {
            delivery.delete(true);
            return DeliveryManagerResponse.of(delivery);
        }).orElseThrow(() -> new RuntimeException("존재하는 배송담당자가 없습니다."));

    }

    // 배송담당자 검색
    @Transactional
    public Page<DeliveryManagerResponse> searchDeliveryMangers(DeliveryManagerDto request, Pageable pageable) {
        Page<DeliveryManager> deliveries = deliveryManagerRepository.searchDeliveryManagers(request, pageable);
        return deliveries.map(DeliveryManagerResponse::of);
    }
}
