package com.project.p_hub.infrastructure.repository.hubpath;


import com.project.p_hub.application.dtos.HubDto;
import com.project.p_hub.application.dtos.HubPathDto;
import com.project.p_hub.domain.model.Hub;
import com.project.p_hub.domain.model.HubPath;
import com.project.p_hub.domain.model.QHub;
import com.project.p_hub.domain.model.QHubPath;
import com.project.p_hub.domain.repository.hub.HubRepository;
import com.project.p_hub.domain.repository.hubpath.HubPathRepository;
import com.project.p_hub.infrastructure.repository.hub.HubJpaRepository;
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
public class HubPathRepositoryImpl implements HubPathRepository {

    private final HubPathJpaRepository hubPathJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public HubPath save(HubPath hubPath) {

        return hubPathJpaRepository.save(hubPath);
    }

    @Override
    public Optional<HubPath> findById(UUID id) {
        QHubPath hubPath = QHubPath.hubPath;

        HubPath foundHubPath = jpaQueryFactory.selectFrom(hubPath)
                .where(hubPath.id.eq(id)
                        .and(hubPath.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                .fetchOne();

        return Optional.ofNullable(foundHubPath);
    }

    @Override
    public List<HubPath> findAll() {
        QHubPath hubPath = QHubPath.hubPath;

        return jpaQueryFactory.selectFrom(hubPath)
                .where(hubPath.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }

    @Override
    public Page<HubPath> searchHubs(HubDto request, Pageable pageable) {
        return null;
    }

//    @Override
//    public Page<HubPath> searchHubPaths(HubPathDto request, Pageable pageable) {
//        QHubPath hubPath = QHubPath.hubPath;
//
//        BooleanBuilder whereBuilder = new BooleanBuilder();
//
//        // is_delete 가 false 인것만 조회
//        whereBuilder.and(hubPath.is_delete.eq(false));
//
//        if (StringUtils.hasText(request.getName())) {
//            whereBuilder.and(hub.name.containsIgnoreCase(request.getName()));
//        }
//
//        if (StringUtils.hasText(request.getCity())) {
//            whereBuilder.and(hub.city.containsIgnoreCase(request.getCity()));
//        }
//
//        if (request.getLatitude() != null) {
//            whereBuilder.and(hub.latitude.eq(request.getLatitude()));
//        }
//
//        if (request.getLongitude() != null) {
//            whereBuilder.and(hub.longitude.eq(request.getLongitude()));
//        }
//
//        if (StringUtils.hasText(request.getAddress())) {
//            whereBuilder.and(hub.city.containsIgnoreCase(request.getAddress()));
//        }
//
//
//
//        // 결과 리스트 조회
//        List<Hub> results = jpaQueryFactory
//                .selectFrom(hub)
//                .where(whereBuilder)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        // 전체 카운트 조회
//        long total = jpaQueryFactory
//                .selectFrom(hub)
//                .where(whereBuilder)
//                .fetch().size();
//
//
//        return new PageImpl<>(results, pageable, total);
//    }

}
