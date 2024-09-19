package com.project.p_company.infrastructure.repository;

import com.project.p_company.application.dtos.CompanyDto;
import com.project.p_company.domain.model.Company;
import com.project.p_company.domain.model.QCompany;
import com.project.p_company.domain.repository.CompanyRepository;
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
public class CompanyRepositoryImpl implements CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Company save(Company company) {

        return companyJpaRepository.save(company);
    }

    @Override
    public Optional<Company> findById(UUID id) {
        QCompany company = QCompany.company;

        Company foundCompany = jpaQueryFactory.selectFrom(company)
                .where(company.id.eq(id)
                        .and(company.is_delete.eq(false)))  // isDelete가 false인 경우만 필터링
                .fetchOne();

        return Optional.ofNullable(foundCompany);
    }

    @Override
    public List<Company> findAll() {

        QCompany company = QCompany.company;

        return jpaQueryFactory.selectFrom(company)
                .where(company.is_delete.eq(false))  // isDelete가 false인 경우만 필터링
                .fetch();
    }


    @Override
    public Page<Company> searchCompanies(CompanyDto request, Pageable pageable) {
        QCompany company = QCompany.company;

        BooleanBuilder whereBuilder = new BooleanBuilder();

        // is_delete가 false인 항목만 조회
        whereBuilder.and(company.is_delete.eq(false));


        // name 필터링
        if (StringUtils.hasText(request.getName()) && !request.getName().isEmpty()) {
            whereBuilder.and(company.name.containsIgnoreCase(request.getName()));
        }

        // type 필터링
        if (request.getType() != null) {
            whereBuilder.and(company.type.eq(request.getType()));
        }

        // managedHub 필터링
        if (request.getManagedHub() != null) {
            whereBuilder.and(company.managedHub.eq(request.getManagedHub()));
        }

        // address 필터링
        if(StringUtils.hasText(request.getAddress()) && !request.getAddress().isEmpty()) {
            whereBuilder.and(company.address.containsIgnoreCase(request.getAddress()));
        }


        List<Company> results = jpaQueryFactory
                .selectFrom(company)
                .where(whereBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(company)
                .where(whereBuilder)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
