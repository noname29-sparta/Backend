package com.project.p_delivery.infrastructure.repository.deliveryRoute;

import com.project.p_delivery.application.dtos.delivery.DeliveryDto;
import com.project.p_delivery.application.dtos.deliveryRoute.DeliveryRouteDto;
import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryRoute;
import com.project.p_delivery.domain.model.QDelivery;
import com.project.p_delivery.domain.model.QDeliveryRoute;
import com.project.p_delivery.domain.repository.delivery.DeliveryRepository;
import com.project.p_delivery.domain.repository.deliveryRoute.DeliveryRouteRepository;
import com.project.p_delivery.infrastructure.repository.delivery.DeliveryJpaRepository;
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
public class DeliveryRouteRepositoryImpl implements DeliveryRouteRepository {

    private final DeliveryRouteJpaRepository deliveryRouteJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public DeliveryRoute save(DeliveryRoute deliveryRoute) {

        return deliveryRouteJpaRepository.save(deliveryRoute);
    }

    @Override
    public Optional<DeliveryRoute> findById(UUID id) {
        QDeliveryRoute deliveryRoute = QDeliveryRoute.deliveryRoute;

        DeliveryRoute foundDeliveryRoute = jpaQueryFactory
                .selectFrom(deliveryRoute)
                .where(deliveryRoute.id.eq(id)
                        .and(deliveryRoute.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                .fetchOne();

        return Optional.ofNullable(foundDeliveryRoute);
    }

    @Override
    public List<DeliveryRoute> findAll() {
        QDeliveryRoute deliveryRoute = QDeliveryRoute.deliveryRoute;

        return jpaQueryFactory
                .selectFrom(deliveryRoute)
                .where(deliveryRoute.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }

    @Override
    public Page<DeliveryRoute> searchDeliveryRoutes(DeliveryRouteDto request, Pageable pageable) {

        QDeliveryRoute deliveryRoute = QDeliveryRoute.deliveryRoute;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete가 false인 항목만 조회
        whereBuilder.and(deliveryRoute.is_delete.eq(false));

        // deliveryId 필터링
        if (request.getDeliveryId() != null) {
            whereBuilder.and(deliveryRoute.deliveryId.eq(request.getDeliveryId()));
        }

        // startHubId 필터링
        if (request.getStartHubId() != null) {
            whereBuilder.and(deliveryRoute.startHubId.eq(request.getStartHubId()));
        }

        // endHubId 필터링
        if (request.getEndHubId() != null) {
            whereBuilder.and(deliveryRoute.endHubId.eq(request.getEndHubId()));
        }

        // DeliveryStatus 필터링
        if (request.getDeliveryStatus() != null) {
            whereBuilder.and(deliveryRoute.deliveryStatus.eq(request.getDeliveryStatus()));
        }


        List<DeliveryRoute> results = jpaQueryFactory
                .selectFrom(deliveryRoute)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(deliveryRoute)
                .where(whereBuilder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }

}
