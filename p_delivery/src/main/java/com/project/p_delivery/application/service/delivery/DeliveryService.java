package com.project.p_delivery.application.service.delivery;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.repository.delivery.DeliveryRepository;
import com.project.p_delivery.presentation.response.delivery.DeliveryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;


    // 배송 생성
    @Transactional
    public DeliveryResponse createDelivery(DeliveryDto request) {

        Delivery delivery = Delivery.create(
                request.getOrderId(),
                request.getStartHubId(),
                request.getEndHubId(),
                request.getAddress(),
                request.getRecipientName(),
                request.getDeliveryStatus(),
                request.getPhone()
        );

        deliveryRepository.save(delivery);

        return DeliveryResponse.of(delivery);
    }

    // 배송 단건 조회
    @Transactional
    public DeliveryResponse getDelivery(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("존재하는 배송이 없습니다."));

        return DeliveryResponse.of(delivery);
    }

    // 배송 전체 조회
    @Transactional
    public List<DeliveryResponse> getAllDeliveries() {

        List<Delivery> deliveries = deliveryRepository.findAll();

        return DeliveryResponse.of(deliveries);
    }

    // 배송 수정
    @Transactional
    public DeliveryResponse updateDelivery(UUID deliveryId, DeliveryDto request) {
        return deliveryRepository.findById(deliveryId).map(delivery -> {
            delivery.update(request.getOrderId(), request.getStartHubId(), request.getEndHubId(), request.getAddress(), request.getRecipientName(), request.getDeliveryStatus(), request.getPhone());
            return DeliveryResponse.of(delivery);
        }).orElseThrow(() -> new RuntimeException("존재하는 배송이 없습니다."));
    }

    // 배송 삭제
    @Transactional
    public DeliveryResponse deleteDelivery(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId).map(delivery -> {
            delivery.delete(true);
            return DeliveryResponse.of(delivery);
        }).orElseThrow(() -> new RuntimeException("존재하는 배송이 없습니다."));

    }

    // 배송 검색
    @Transactional
    public Page<DeliveryResponse> searchDeliveries(DeliveryDto request, Pageable pageable) {
        Page<Delivery> deliveries = deliveryRepository.searchDeliveries(request, pageable);
        return deliveries.map(DeliveryResponse::of);
    }



}
