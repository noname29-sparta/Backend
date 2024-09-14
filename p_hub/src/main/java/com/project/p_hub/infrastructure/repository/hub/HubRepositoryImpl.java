package com.project.p_hub.infrastructure.repository.hub;


import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.model.QHub;
import com.project.p_hub.domain.model.QHubPath;
import com.project.p_hub.domain.repository.hub.HubRepository;
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
public class HubRepositoryImpl implements HubRepository {

    private final HubJpaRepository hubJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Hub save(Hub hub) {

        return hubJpaRepository.save(hub);
    }

    @Override
    public Optional<Hub> findById(UUID id) {
        QHub hub = QHub.hub;

        Hub foundHub = jpaQueryFactory.selectFrom(hub)
                        .where(hub.id.eq(id)
                        .and(hub.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                        .fetchOne();

        return Optional.ofNullable(foundHub);
    }

    @Override
    public List<Hub> findAll() {
        QHub hub = QHub.hub;

        return jpaQueryFactory.selectFrom(hub)
                .where(hub.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }

    @Override
    public Page<Hub> searchHubs(HubDto request, Pageable pageable) {
        QHub hub = QHub.hub;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete 가 false 인것만 조회
        whereBuilder.and(hub.is_delete.eq(false));

        if (StringUtils.hasText(request.getName())) {
            whereBuilder.and(hub.name.containsIgnoreCase(request.getName()));
        }

        if (StringUtils.hasText(request.getCity())) {
            whereBuilder.and(hub.city.containsIgnoreCase(request.getCity()));
        }

        if (request.getLatitude() != null) {
            whereBuilder.and(hub.latitude.eq(request.getLatitude()));
        }

        if (request.getLongitude() != null) {
            whereBuilder.and(hub.longitude.eq(request.getLongitude()));
        }

        if (StringUtils.hasText(request.getAddress())) {
            whereBuilder.and(hub.city.containsIgnoreCase(request.getAddress()));
        }



        // 결과 리스트 조회
        List<Hub> results = jpaQueryFactory
                .selectFrom(hub)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 카운트 조회
        long total = jpaQueryFactory
                .selectFrom(hub)
                .where(whereBuilder)
                .fetch().size();


        return new PageImpl<>(results, pageable, total);
    }



}
