package com.project.p_delivery.infrastructure.repository.delivery;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.QDelivery;
import com.project.p_delivery.domain.repository.delivery.DeliveryRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Delivery save(Delivery delivery) {

        return deliveryJpaRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> findById(UUID id) {
        QDelivery delivery = QDelivery.delivery;

        Delivery foundelivery = jpaQueryFactory
                        .selectFrom(delivery)
                        .where(delivery.id.eq(id)
                        .and(delivery.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                        .fetchOne();

        return Optional.ofNullable(foundelivery);
    }

    @Override
    public List<Delivery> findAll() {
        QDelivery delivery = QDelivery.delivery;

        return jpaQueryFactory
                .selectFrom(delivery)
                .where(delivery.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }

    @Override
    public Page<Delivery> searchDeliveries(DeliveryDto request, Pageable pageable) {
        QDelivery delivery = QDelivery.delivery;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete가 false인 항목만 조회
        whereBuilder.and(delivery.is_delete.eq(false));

        // order_id 필터링
        if (request.getOrderId() != null) {
            whereBuilder.and(delivery.orderId.eq(request.getOrderId()));
        }

        // startHubId 필터링
        if (request.getStartHubId() != null) {
            whereBuilder.and(delivery.startHubId.eq(request.getStartHubId()));
        }

        // endHubId 필터링
        if (request.getEndHubId() != null) {
            whereBuilder.and(delivery.endHubId.eq(request.getEndHubId()));
        }

        // address 필터링
        if(StringUtils.hasText(request.getAddress()) && !request.getAddress().isEmpty()) {
            whereBuilder.and(delivery.address.containsIgnoreCase(request.getAddress()));
        }

        // recipientName 필터링
        if (StringUtils.hasText(request.getRecipientName()) && !request.getRecipientName().isEmpty()) {
            whereBuilder.and(delivery.recipientName.containsIgnoreCase(request.getRecipientName()));
        }

        // deliveryStatus 필터링
        if (request.getDeliveryStatus() != null) {
            whereBuilder.and(delivery.deliveryStatus.eq(request.getDeliveryStatus()));
        }

        // phone 필터링
        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            whereBuilder.and(delivery.phone.eq(request.getPhone()));
        }

        List<Delivery> results = jpaQueryFactory
                .selectFrom(delivery)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(delivery)
                .where(whereBuilder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
