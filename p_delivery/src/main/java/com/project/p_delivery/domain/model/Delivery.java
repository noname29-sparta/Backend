package com.project.p_delivery.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="p_deliveries")
public class Delivery extends BaseEntity{

    @Id
    private UUID id;

    private UUID orderId;

    private UUID startHubId;

    private UUID endHubId;

    private String address;

    private String recipientName;

    private String phone;

    @Enumerated(EnumType.STRING)  // Enum을 문자열로 DB에 저장
    private DeliveryStatus deliveryStatus;

    public static Delivery create(UUID orderId, UUID startHubId, UUID endHubId, String address, String recipientName, DeliveryStatus deliveryStatus, String phone) {
            return Delivery.builder()
                    .id(UUID.randomUUID())
                    .orderId(orderId)
                    .startHubId(startHubId)
                    .endHubId(endHubId)
                    .address(address)
                    .recipientName(recipientName)
                    .deliveryStatus(deliveryStatus)
                    .phone(phone)
                    .build();
    }


    public void update(UUID orderId, UUID startHubId, UUID endHubId, String address, String recipientName, DeliveryStatus deliveryStatus, String phone) {
        this.orderId = orderId;
        this.startHubId = startHubId;
        this.endHubId = endHubId;
        this.address = address;
        this.recipientName = recipientName;
        this.deliveryStatus = deliveryStatus;
        this.phone = phone;
    }

    public void delete(Boolean is_delete) {
        this.setIs_delete(is_delete);
    }

}
