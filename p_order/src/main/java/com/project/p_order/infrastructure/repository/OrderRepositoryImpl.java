package com.project.p_order.infrastructure.repository;

import com.project.p_order.application.dtos.OrderDto;
import com.project.p_order.domain.model.Order;
import com.project.p_order.domain.model.QOrder;
import com.project.p_order.domain.repository.OrderRepository;
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
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Order save(Order order) {

        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        QOrder order = QOrder.order;

        Order foundCompany = jpaQueryFactory.selectFrom(order)
                .where(order.id.eq(id)
                        .and(order.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                .fetchOne();

        return Optional.ofNullable(foundCompany);
    }

    @Override
    public List<Order> findAll() {

        QOrder order = QOrder.order;

        return jpaQueryFactory.selectFrom(order)
                .where(order.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }


    @Override
    public Page<Order> searchOrders(OrderDto request, Pageable pageable) {
        QOrder order = QOrder.order;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete가 false인 항목만 조회
        whereBuilder.and(order.is_delete.eq(false));


        // RequesterCompany 필터링
        if (request.getRequesterCompanyId() != null) {
            whereBuilder.and(order.requesterCompanyId.eq(request.getRequesterCompanyId()));
        }

        // ReceiverCompany 필터링
        if (request.getReceiverCompanyId() != null) {
            whereBuilder.and(order.receiverCompanyId.eq(request.getReceiverCompanyId()));
        }

        // productId 필터링
        if (request.getProductId() != null) {
            whereBuilder.and(order.productId.eq(request.getProductId()));
        }

        // DeliveryId 필터링
        if (request.getDeliveryId() != null) {
            whereBuilder.and(order.deliveryId.eq(request.getDeliveryId()));
        }


        List<Order> results = jpaQueryFactory
                .selectFrom(order)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(order)
                .where(whereBuilder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
