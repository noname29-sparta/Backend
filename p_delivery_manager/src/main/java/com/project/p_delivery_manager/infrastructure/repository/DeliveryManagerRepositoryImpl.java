package com.project.p_delivery_manager.infrastructure.repository;

import com.project.p_delivery_manager.application.dtos.DeliveryManagerDto;
import com.project.p_delivery_manager.domain.model.DeliveryManager;
import com.project.p_delivery_manager.domain.model.QDeliveryManager;
import com.project.p_delivery_manager.domain.repository.DeliveryManagerRepository;
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
public class DeliveryManagerRepositoryImpl implements DeliveryManagerRepository {

    private final DeliveryManagerJpaRepository deliveryManagerJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public DeliveryManager save(DeliveryManager delivery) {

        return deliveryManagerJpaRepository.save(delivery);
    }

    @Override
    public Optional<DeliveryManager> findById(UUID id) {
        QDeliveryManager delivery = QDeliveryManager.deliveryManager;

        DeliveryManager foundelivery = jpaQueryFactory
                .selectFrom(delivery)
                .where(delivery.id.eq(id)
                        .and(delivery.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                .fetchOne();

        return Optional.ofNullable(foundelivery);
    }

    @Override
    public List<DeliveryManager> findAll() {
        QDeliveryManager delivery = QDeliveryManager.deliveryManager;

        return jpaQueryFactory
                .selectFrom(delivery)
                .where(delivery.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }

    @Override
    public Page<DeliveryManager> searchDeliveryManagers(DeliveryManagerDto request, Pageable pageable) {
        QDeliveryManager deliveryManager = QDeliveryManager.deliveryManager;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete가 false인 항목만 조회
        whereBuilder.and(deliveryManager.is_delete.eq(false));

        // hubId 필터링
        if (request.getHubId() != null) {
            whereBuilder.and(deliveryManager.hubId.eq(request.getHubId()));
        }

        // userId 필터링
        if (request.getUserId() != null) {
            whereBuilder.and(deliveryManager.userId.eq(request.getUserId()));
        }

        // type 필터링
        if (request.getType() != null) {
            whereBuilder.and(deliveryManager.type.eq(request.getType()));
        }


        List<DeliveryManager> results = jpaQueryFactory
                .selectFrom(deliveryManager)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(deliveryManager)
                .where(whereBuilder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
