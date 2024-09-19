package com.project.p_company.presentation.controller;

import com.project.p_company.application.service.CompanyService;
import com.project.p_company.domain.model.CompanyType;
import com.project.p_company.presentation.request.CompanyRequest;
import com.project.p_company.presentation.request.SearchCompanyRequest;
import com.project.p_company.presentation.request.UpdateCompanyRequest;
import com.project.p_company.presentation.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    // 업체 생성
    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CompanyRequest companyRequest) {

        return ResponseEntity.ok().body(companyService.createCompany(companyRequest.toDTO()));
    }

    // 업체 단건 조회
    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable(name = "companyId") UUID companyId) {

        return ResponseEntity.ok().body(companyService.getCompany(companyId));
    }

    // 업체 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllCompanies(){

        return ResponseEntity.ok().body(companyService.getAllCompanies());
    }


    // 업체 수정
    @PatchMapping("/{companyId}")
    public ResponseEntity<?> updateCompany(@PathVariable(name = "companyId") UUID companyId,
                                       @RequestBody UpdateCompanyRequest updateCompanyRequest) {

        return ResponseEntity.ok().body(companyService.updateCompany(companyId, updateCompanyRequest.toDTO()));
    }

    // 업체 삭제
    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "companyId") UUID companyId) {

        return ResponseEntity.ok().body(companyService.deleteCompany(companyId));
    }

    // 업체 검색
    @GetMapping("/search")
    public ResponseEntity<Page<CompanyResponse>> searchCompanies(@RequestParam(required = false) String name,
                                                                 @RequestParam(required = false) CompanyType type,
                                                                 @RequestParam(required = false) UUID managedHub,
                                                                 @RequestParam(required = false) String address,
                                                                 @RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "20") int size) {

        // HubSearchDto에 파라미터 설정
        SearchCompanyRequest searchDto = SearchCompanyRequest.builder()
                .name(name)
                .type(type)
                .managedHub(managedHub)
                .address(address)
                .build();

        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(companyService.searchCompanies(searchDto.toDTO(), pageRequest));
    }
}
