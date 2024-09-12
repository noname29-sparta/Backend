package com.project.p_delivery.domain.model;

public enum DeliveryStatus {
    HUB_WAITING,  // 허브에서 대기 중
    IN_TRANSIT,   // 허브 사이를 이동 중
    ARRIVED_AT_DESTINATION_HUB,  // 목적지 허브에 도착
    OUT_FOR_DELIVERY,  // 최종 배송 중
    DELIVERED  // 수령인에게 배송 완료
}
