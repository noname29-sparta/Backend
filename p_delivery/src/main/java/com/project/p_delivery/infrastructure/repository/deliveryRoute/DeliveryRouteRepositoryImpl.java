package com.project.p_delivery.infrastructure.repository.deliveryRoute;

import com.project.p_delivery.application.dtos.deliveryRoute.DeliveryRouteDto;
import com.project.p_delivery.domain.model.Delivery;
import com.project.p_delivery.domain.model.DeliveryRoute;
import com.project.p_delivery.domain.model.QDeliveryRoute;
import com.project.p_delivery.domain.repository.delivery.DeliveryRepository;
import com.project.p_delivery.domain.repository.deliveryRoute.DeliveryRouteRepository;
import com.project.p_delivery.infrastructure.repository.delivery.DeliveryJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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



        return Optional.ofNullable(null);
    }

    @Override
    public List<DeliveryRoute> findAll() {
        return List.of();
    }

    @Override
    public Page<DeliveryRoute> searchDeliveryRoutes(DeliveryRouteDto request, Pageable pageable) {
        return null;
    }
}
