package com.project.p_company.application.service;

import com.project.p_company.application.dtos.CompanyDto;
import com.project.p_company.domain.model.Company;
import com.project.p_company.domain.repository.CompanyRepository;
import com.project.p_company.presentation.response.CompanyResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    // 업체 생성
    @Transactional
    public CompanyResponse createCompany(CompanyDto request) {

        Company company = Company.create(
                request.getName(),
                request.getType(),
                request.getManagedHub(),
                request.getAddress()
        );

        companyRepository.save(company);

        return CompanyResponse.of(company);
    }

    // 업체 단건 조회
    @Transactional
    public CompanyResponse getCompany(UUID hubId) {

        Company company = companyRepository.findById(hubId)
                .orElseThrow(() -> new RuntimeException("존재하는 업체가 없습니다."));

        return CompanyResponse.of(company);
    }

    // 업체 전체 조회
    @Transactional
    public List<CompanyResponse> getAllCompanies() {

        List<Company> companies = companyRepository.findAll();

        return CompanyResponse.of(companies);
    }

    // 업체 수정
    @Transactional
    public CompanyResponse updateCompany(UUID companyId, CompanyDto request) {
        return companyRepository.findById(companyId).map(hub -> {
            hub.update(request.getName(), request.getType(), request.getManagedHub(), request.getAddress());
            return CompanyResponse.of(hub);
        }).orElseThrow();
    }

    // 업체 삭제
    @Transactional
    public CompanyResponse deleteCompany(UUID hubId) {
        return companyRepository.findById(hubId).map(hub -> {
            hub.delete(true);
            return CompanyResponse.of(hub);
        }).orElseThrow();

    }

    // 업체 검색
    @Transactional
    public Page<CompanyResponse> searchCompanies(CompanyDto request, Pageable pageable) {
        Page<Company> hubs = companyRepository.searchCompanies(request, pageable);
        return hubs.map(CompanyResponse::of);
    }
}
