package com.project.p_company.domain.repository;

import com.project.p_company.application.dtos.CompanyDto;
import com.project.p_company.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {
    Company save(Company company);
    Optional<Company> findById(UUID id);
    List<Company> findAll();
    Page<Company> searchCompanies(CompanyDto request, Pageable pageable);
}
