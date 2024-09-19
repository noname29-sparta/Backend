package com.project.p_product.infrastructure.repository;

import com.project.p_product.application.dtos.ProductDto;
import com.project.p_product.domain.model.Product;
import com.project.p_product.domain.model.QProduct;
import com.project.p_product.domain.repository.ProductRepository;
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
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Product save(Product product) {

        return productJpaRepository.save(product);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        QProduct product = QProduct.product;

        Product foundProduct = jpaQueryFactory.selectFrom(product)
                        .where(product.id.eq(id)
                        .and(product.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                        .fetchOne();

        return Optional.ofNullable(foundProduct);
    }

    @Override
    public List<Product> findAll() {

        QProduct product = QProduct.product;

        return jpaQueryFactory.selectFrom(product)
                .where(product.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }


    @Override
    public Page<Product> searchProducts(ProductDto request, Pageable pageable) {
        QProduct product = QProduct.product;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete가 false인 항목만 조회
        whereBuilder.and(product.is_delete.eq(false));


        if (StringUtils.hasText(request.getName())) {
            whereBuilder.and(product.name.containsIgnoreCase(request.getName()));
        }

        // CompanyId 필터링
        if (request.getCompanyId() != null) {
            whereBuilder.and(product.companyId.eq(request.getCompanyId()));
        }

        // HubId 필터링
        if (request.getHubId() != null) {
            whereBuilder.and(product.hubId.eq(request.getHubId()));
        }



        List<Product> results = jpaQueryFactory
                .selectFrom(product)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(product)
                .where(whereBuilder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
